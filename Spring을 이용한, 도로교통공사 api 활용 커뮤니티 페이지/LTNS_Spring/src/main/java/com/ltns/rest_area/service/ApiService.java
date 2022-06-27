package com.ltns.rest_area.service;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.restarea.FoodMenuDAO;
import com.ltns.rest_area.domain.restarea.FoodMenuDTO;
import com.ltns.rest_area.domain.restarea.GasStationDAO;
import com.ltns.rest_area.domain.restarea.GasStationDTO;
import com.ltns.rest_area.domain.restarea.RestAreaDAO;
import com.ltns.rest_area.domain.restarea.RestAreaDTO;

import oracle.sql.TIMESTAMP;

@Service
public class ApiService {

	// 고속도로 휴게소 기준정보 현황(위치)
	final static String endpoint0 = "http://data.ex.co.kr/openapi/locationinfo/locationinfoRest";
	// 휴게소 이름 정리해놓은 해쉬맵을 만들어두기... 주유소 정보에 넣어야 한다... ('휴게소'뺀 이름:휴게소코드)
	
	//휴게소 방향정보를 위함
	final static String endpoint0_1 = "http://data.ex.co.kr/openapi/business/serviceAreaRoute";
	
	// 주유소 가격, 업체 현황
	final static String endpoint1 = "http://data.ex.co.kr/openapi/business/curStateStation";
	// => unitName에서 주유소를 잘라, 휴게소 코드를 가져와야 한다...
	// 휴게소 정리가 끝나고, 휴게소 이름 정리해놓은 해쉬맵을 활용하기! ('휴게소'뺀 이름:휴게소코드)

	// 휴게소 푸드메뉴현황 조회 서비스
	final static String endpoint2 = "http://data.ex.co.kr/openapi/restinfo/restBestfoodList";
	final static String serviceKey = "2082754925";
	final static String type = "json";

	List<DTO> rAdtos = new ArrayList<DTO>();// 확인 필요...
	List<DTO> gSdtos = new ArrayList<DTO>();
	List<DTO> fMdtos = new ArrayList<DTO>();

	@Autowired
	private SqlSession sqlSession;

	DAO dao;

	/* 전체 작업 */
	public int refreshApiData() throws Exception {
		// sqlSession 확인
		System.out.println("sqlSession : " + sqlSession);

		int result = 0;

		// api 가져오기
		try {
		getDTOFromJson();
		}catch (Exception e){
			System.out.println("  도로교통공사 api 에러");
			throw e;
		}
		
		try {
		// 기존 정보 날리기
		result = deleteAllBeforeApiDataInDB();

		// 새 정보 넣기
		result = insertRestAreaByDTOs(rAdtos);

		result = insertGasStationByDTOs(gSdtos);
//		
		result = insertFoodMenuByDTOs(fMdtos);
		}catch (Exception e) {
			System.out.println("   DB연결 에러");			
			throw e;
		}
		
		System.out.println("작업 완료!");

		return result;
	}

	/* api 가져오기 */
	public int getDTOFromJson() throws Exception {
		int result = 0;

		/*
		 * 휴게소 정보 (휴게소 코드 : ra_code, 휴게소 이름 : ra_name, 노선코드 : ra_routeNo, 노선명 :
		 * ra_routeName, 방향 : ra_destination, x좌표 : ra_xValue, y좌표 : ra_yValue)
		 */
		// 해쉬맵을 작성할것...
		HashMap<String, String> ranameMap = new HashMap<String, String>(); // ('휴게소'뺀 이름:휴게소코드)
		HashMap<String, String> ra_codeNstdRestCdMap = new HashMap<String, String>(); // (serviceAreaCode : stdRestCd)
		HashMap<String, String> ra_codeNra_nameMap=new HashMap<String, String>();	//(ra_code:ra_name)
		HashMap<String,Integer> ra_codeNi = new HashMap<String, Integer>();	//(ra_code:i)
		String url = endpoint0 + "?" + "key=" + serviceKey + "&type=" + type + "&numOfRows=99";
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(readURL(url));
		// 여러 페이지를 다 가져와야 한다!
		// 페이지 확인
		int fullcnt = Integer.parseInt((jsonObj.get("count").toString())) / 99;

		System.out.println("휴게소 위치 api를 가져오는 중..");
		// 전부 꺼내기
		// 디비에 집어넣기
		int rAdtosIndex=0;
		for (int i = 1; i <= fullcnt + 1; i++) {
			jsonParser = new JSONParser();
			jsonObj = (JSONObject) jsonParser.parse(readURL(url + "&pageNo=" + i));
			JSONArray array = (JSONArray) jsonObj.get("list");

			// 최대갯수로 페이지마다 죠지기
			for (int j = 0; j < array.size(); j++) {
				JSONObject row = (JSONObject) array.get(j);
				String ra_code = (String) row.get("serviceAreaCode");
				String stdRestCd = (String) row.get("stdRestCd");
				ra_codeNstdRestCdMap.put(stdRestCd, ra_code);

				String ra_name = (String) row.get("unitName");
				
				String ra_destination="X";
				if(!ra_name.contains("(")) {
					ra_destination="양방향";
				}else {
					ra_destination = ra_name.replaceAll("[^(]*[(]", "");
					ra_destination = ra_destination.replaceAll("[)].*", "");
				}
				ranameMap.put(ra_name.replaceAll("휴게소", ""), ra_code);
				ra_codeNra_nameMap.put(ra_code, ra_name);

				String ra_routeNo = (String) row.get("routeNo");
				String ra_routeName = (String) row.get("routeName");
				String ra_xValue = (String) row.get("xValue");
				String ra_yValue = (String) row.get("yValue");

				rAdtos.add(new RestAreaDTO().builder().ra_code(ra_code).ra_name(ra_name).ra_routeNo(ra_routeNo)
						.ra_routeName(ra_routeName).ra_destination(ra_destination).ra_xValue(ra_xValue)
						.ra_yValue(ra_yValue).build());
				ra_codeNi.put(ra_code, rAdtosIndex);
				rAdtosIndex++;
				System.out.print("|");
			}
		}
		//방향 api
		System.out.println("휴게소 방향 api를 가져오는 중..1");
		url = endpoint0_1 + "?" + "key=" + serviceKey + "&type=" + type + "&numOfRows=99";
		jsonParser = new JSONParser();
		jsonObj = (JSONObject) jsonParser.parse(readURL(url));
		// 여러 페이지를 다 가져와야 한다!
		// 페이지 확인
		fullcnt = Integer.parseInt((jsonObj.get("count").toString())) / 99;

		System.out.println("휴게소 방향 api를 가져오는 중..");
		// 전부 꺼내기
		// 디비에 집어넣기
		for (int i = 1; i <= fullcnt + 1; i++) {
			jsonParser = new JSONParser();
			jsonObj = (JSONObject) jsonParser.parse(readURL(url + "&pageNo=" + i));
			JSONArray array = (JSONArray) jsonObj.get("list");

			// 최대갯수로 페이지마다 죠지기
			for (int j = 0; j < array.size(); j++) {
				JSONObject row = (JSONObject) array.get(j);
				String ra_code=(String)row.get("serviceAreaCode");
				String ra_destination=(String)row.get("direction");
				if(ra_codeNi.get(ra_code)==null) {
					continue;
				}
				
				int tmpIndex=ra_codeNi.get(ra_code);
				((RestAreaDTO)rAdtos.get(tmpIndex)).setRa_destination(ra_destination);
				
//				System.out.println(rAdtos.get(tmpIndex));//test
				System.out.print("|");
			}
		}
		System.out.println("\ndtos 휴게소 생성 완료!");

		/*
		 * 주유소 정보 (주유소 코드 : gs_code, 주유소 이름 : gs_name, 휴게소 코드 : ra_code, 정유소 :
		 * gs_company, 경유 : gs_diesel, 휘발유 : gs_gasoline, LPG : gs_lpg)
		 */
		url = endpoint1 + "?" + "key=" + serviceKey + "&type=" + type + "&numOfRows=99";
		jsonParser = new JSONParser();
		jsonObj = (JSONObject) jsonParser.parse(readURL(url));
		// 여러 페이지를 다 가져와야 한다!
		// 페이지 확인
		fullcnt = Integer.parseInt((jsonObj.get("count").toString())) / 99;

		System.out.println("주유소 api를 가져오는 중..");
		System.out.println();
		// 전부 꺼내기
		// 디비에 집어넣기
		for (int i = 1; i <= fullcnt + 1; i++) {
			jsonParser = new JSONParser();
			jsonObj = (JSONObject) jsonParser.parse(readURL(url + "&pageNo=" + i));
			JSONArray array = (JSONArray) jsonObj.get("list");
			// 최대갯수로 페이지마다 죠지기
			for (int j = 0; j < array.size(); j++) {
				JSONObject row = (JSONObject) array.get(j);
				String gs_code = (String) row.get("serviceAreaCode");
				String gs_name = (String) row.get("serviceAreaName");
				if(gs_name==null) continue;
				String ra_code = ranameMap.get(gs_name.replaceAll("주유소", ""));
				if (ra_code == null) {
//					ra_code = "X";
					//휴게소 위치정보가 없는 곳들은 열외합니다...
					continue;
				}

				String ra_name=ra_codeNra_nameMap.get(ra_code);

				String gs_company = (String) row.get("oilCompany");
				String gs_diesel = (String) row.get("diselPrice");
				String gs_gasoline = (String) row.get("gasolinePrice");
				String gs_lpg = (String) row.get("lpgPrice");

				gSdtos.add(new GasStationDTO().builder().gs_code(gs_code).gs_name(gs_name).ra_code(ra_code).ra_name(ra_name)
						.gs_company(gs_company).gs_diesel(gs_diesel).gs_gasoline(gs_gasoline).gs_lpg(gs_lpg).build());
				System.out.print("|");
			}

		}
		System.out.println("\ndtos 주유소 생성 완료!");

		/*
		 * 음식메뉴 (음식 고유번호 : fm_id, 음식 코드 : fm_code, 휴게소 코드 : ra_code, 음식 이름 : fm_name, 음식
		 * 가격 : fm_price, 음식 재료 : fm_material, 음식 상세 내역 : fm_etc)
		 */
		url = endpoint2 + "?" + "key=" + serviceKey + "&type=" + type + "&numOfRows=99";
		jsonParser = new JSONParser();
		jsonObj = (JSONObject) jsonParser.parse(readURL(url));
		// 여러 페이지를 다 가져와야 한다!
		// 페이지 확인
		fullcnt = Integer.parseInt((jsonObj.get("count").toString())) / 99;

		System.out.println("음식메뉴 api를 가져오는 중..");
		// 전부 꺼내기
		// 디비에 집어넣기
		int fm_id = 1;
		for (int i = 1; i <= fullcnt + 1; i++) {
			jsonParser = new JSONParser();
			jsonObj = (JSONObject) jsonParser.parse(readURL(url + "&pageNo=" + i));
			JSONArray array = (JSONArray) jsonObj.get("list");

			// 최대갯수로 페이지마다 죠지기
			for (int j = 0; j < array.size(); j++) {
				JSONObject row = (JSONObject) array.get(j);

				String fm_code = (String) row.get("seq");

				String stdRestCd = (String) row.get("stdRestCd");
				String ra_code = ra_codeNstdRestCdMap.get(stdRestCd);
				if (ra_code == null) {
//					ra_code = "";
					//휴게소 위치정보가 없는 곳들은 열외합니다...
					continue;
				}
				String ra_name=ra_codeNra_nameMap.get(ra_code);

				String fm_name = (String) row.get("foodNm");
				int fm_price = Integer.parseInt((String)row.get("foodCost"));

				String fm_material = (String) row.get("foodMaterial");
				if (fm_material == null) {
					fm_material = "X";
				}
				String fm_etc = (String) row.get("etc");
				if (fm_etc == null) {
					fm_etc = "X";
				}

				fMdtos.add(new FoodMenuDTO().builder().fm_id("" + fm_id).fm_code(fm_code).ra_code(ra_code).ra_name(ra_name)
						.fm_name(fm_name).fm_price(fm_price).fm_material(fm_material).fm_etc(fm_etc).build());
//				System.out.println(fMdtos.get(fm_id-1));
				fm_id++;
				System.out.print("|");
			}

		}
		System.out.println("\ndtos 음식 생성 완료!");
		return result;
	}

	// url GET request
	public String readURL(String _url) throws IOException {
		BufferedInputStream reader = null;

		try {
			URL url = new URL(_url);

			reader = new BufferedInputStream(url.openStream());

			StringBuffer buffer = new StringBuffer();
			int i = 0;
			byte[] b = new byte[8192];
			while ((i = reader.read(b)) != -1) {
				buffer.append(new String(b, 0, i));
			}
			String str = buffer.toString();
			return str;
		} finally {
			if (reader != null)
				reader.close();
		}
	}

//	public void printOutFile(String str) {
//		
//		Date d = new Date();
//		SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddHHmmss");
//		String fName = sm.format(d) + ".json"; 
//		
//		File file=new File(pathname+"/"+fName);
//		try {
//			System.out.println(file.getAbsolutePath()+"에 저장합니다...");
//			file.createNewFile();
//			BufferedWriter writer=new BufferedWriter(new FileWriter(file));
//			writer.write(str);
//			writer.close();
//		} catch (IOException e) {
//			System.out.println("fio 실패! : \n"+str+"\n");
//			e.printStackTrace();
//		}
//	}

	// api 테이블들 비우기
	public int deleteAllBeforeApiDataInDB() throws Exception {
		int result = 0;
		System.out.println("delete sqlSession : " + sqlSession);
		System.out.println("기존 정보 삭제중..");
		dao = sqlSession.getMapper(FoodMenuDAO.class);
		dao.deleteAll();

		dao = sqlSession.getMapper(GasStationDAO.class);
		dao.deleteAll();

		dao = sqlSession.getMapper(RestAreaDAO.class);
		dao.deleteAll();
		System.out.println("삭제 완료!");


		return result;
	}

	public int insertRestAreaByDTOs(List<DTO> DTOs) throws Exception {
		int result = 0;
		System.out.println("RestArea DB 처리중..");
		dao = sqlSession.getMapper(RestAreaDAO.class);
		System.out.println(dao);
		result = dao.insertAllByDTOs(DTOs);
		return result;
	};

	public int insertGasStationByDTOs(List<DTO> DTOs) throws Exception {
		int result = 0;
		System.out.println("GasStation DB 처리중..");
		dao = sqlSession.getMapper(GasStationDAO.class);
		System.out.println(dao);
		result = dao.insertAllByDTOs(DTOs);
		return result;
	};

	public int insertFoodMenuByDTOs(List<DTO> dtos) throws Exception {
		System.out.println("FoodMenu DB 처리중..");
		int result = 0;
		dao = sqlSession.getMapper(FoodMenuDAO.class);
		dao.insertAllByDTOs(dtos);
		return result;
	};

}
