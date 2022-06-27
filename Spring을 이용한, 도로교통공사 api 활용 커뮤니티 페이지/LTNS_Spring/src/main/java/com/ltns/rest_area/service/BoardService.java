package com.ltns.rest_area.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltns.rest_area.domain.AjaxList;
import com.ltns.rest_area.domain.AjaxResult;
import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.VO;
import com.ltns.rest_area.domain.post.CommentDAO;
import com.ltns.rest_area.domain.post.CommentVO;
import com.ltns.rest_area.domain.post.LikeDAO;
import com.ltns.rest_area.domain.post.LikeVO;
import com.ltns.rest_area.domain.post.PostDAO;
import com.ltns.rest_area.domain.post.PostVO;
import com.ltns.rest_area.domain.post.ViewDTO;
import com.ltns.rest_area.domain.restarea.FoodMenuDAO;
import com.ltns.rest_area.domain.restarea.GasStationDAO;
import com.ltns.rest_area.domain.restarea.RestAreaDAO;

@Service
public class BoardService {

	@Autowired
	SqlSession sqlSession;
	
	DAO dao;
	
	//뷰
	public DTO view(String ra_code){
		ViewDTO viewDTO = new ViewDTO();
		
		System.out.println(ra_code);
		dao=sqlSession.getMapper(RestAreaDAO.class);		
		viewDTO.setRaDTO(dao.selectByString(ra_code).get(0));
		
		dao=sqlSession.getMapper(GasStationDAO.class);
		viewDTO.setGsDTOs(dao.selectByString(ra_code));
		
		dao=sqlSession.getMapper(FoodMenuDAO.class);
		viewDTO.setFmDTOs(dao.selectByString(ra_code));
		
		dao=sqlSession.getMapper(PostDAO.class);
		viewDTO.setPostDTOs(dao.selectByString(ra_code));
		
		return viewDTO;
	}

	//포스트 리스트 list에 넣어 반환.. 페이지네이션으로 넘겨줄 생각.. 좋아요 순>최신순 => 토글버튼
	//{ra_code}/{orderBy}/{numOfRows}/{lastRow}/{pageNo} => vo : ra_code, orderBy ,numOfRows, fromRow, pageNo
	public AjaxList selectPostList(VO vo) {
		dao=sqlSession.getMapper(PostDAO.class);
		PostVO _vo=(PostVO) vo;
		int totalCnt=dao.selectCntByString(_vo.getRa_code()); //todo
		List<DTO> list=dao.selectByVO(vo); //todo
		
		int totalPage=(int)(totalCnt/_vo.getNumOfRows())+1;
		int writePages=10;
		if(totalPage<10) {
			writePages=totalPage;
		}
		int pagenationPage = list.size();
		
		AjaxList result=new AjaxList().builder().list(list).totalCnt(totalCnt).pageNo(_vo.getPageNo()).numOfRows(_vo.getNumOfRows()).totalPage(totalPage).pagenationPage(pagenationPage).writePages(writePages).build();
		return result;
	}

	//댓글 리스트를 가져온다.. 무한스크롤 버튼 방식으로 가져오게 할 생각
	//{post_id}/{orderBy}/{numOfRows}/{lastRow}
	public AjaxList selectCommentList(VO vo) {
		dao=sqlSession.getMapper(CommentDAO.class);
		List<DTO> dtos= dao.selectByVO(vo);
		 
		AjaxList result= new AjaxList().builder().list(dtos).build();
		return result;
	}

	//포스트 list 낱개에 대해 포스트 글을 불러옴(REST)
	public DTO postView(int post_id) {
		dao=sqlSession.getMapper(PostDAO.class);
		DTO dto = dao.selectByInt(post_id).get(0);
		return dto;
	}

	//insert update delete 작업에 대한 후처리
	private AjaxResult returnResult(int _result) {
		AjaxResult result=new AjaxResult();
		if(_result==1) {
			result.setCount(1);
			result.setStatus("OK");
		}else {
			result.setCount(0);
			result.setStatus("SomeError");
		}
		
		return result;
	}
	
	public AjaxResult insertPost(PostVO vo) {
		dao=sqlSession.getMapper(PostDAO.class);
		int _result=dao.insertByVO(vo);
		return returnResult(_result);
	}

	public AjaxResult updatePost(PostVO vo) {
		dao=sqlSession.getMapper(PostDAO.class);
		int _result=dao.updateByVO(vo);
		return returnResult(_result);
	}

	public AjaxResult deletePost(PostVO vo) {
		dao=sqlSession.getMapper(PostDAO.class);
		int _result=dao.deleteByVO(vo);
		return returnResult(_result);
	}

	public AjaxResult insertComment(CommentVO vo) {
		dao=sqlSession.getMapper(CommentDAO.class);
		int _result=dao.insertByVO(vo);
		return returnResult(_result);
	}

	public AjaxResult updateComment(CommentVO vo) {
		dao=sqlSession.getMapper(CommentDAO.class);
		int _result=dao.updateByVO(vo);
		return returnResult(_result);
	}

	public AjaxResult deleteComment(CommentVO vo) {
		dao=sqlSession.getMapper(CommentDAO.class);
		int _result=dao.deleteByVO(vo);
		return returnResult(_result);
	}

	//like 작업
	public AjaxResult likeProcess(LikeVO vo) {
		LikeDAO dao=sqlSession.getMapper(LikeDAO.class);
		
		if(dao.selectByVO(vo).isEmpty()) {
			dao.insertByVO(vo);
		}else {
			dao.deleteByVO(vo);
		}
		AjaxResult result=new AjaxResult();
		
		try {
			result.setObj(dao.selectCntByVO(vo));//이거 0일때 문제생긴다...
		}catch(Exception e) {
			System.out.println("like cnt 추출에서 0 문제 [LikeVO : "+vo+"]");
			result.setObj(0);
		}
		return result;
	}

	public AjaxResult likeCheck(LikeVO vo) {
		LikeDAO dao=sqlSession.getMapper(LikeDAO.class);
		AjaxResult result=new AjaxResult();
		try {
			result.setObj(dao.selectCntByObject(vo));//이거 0일때 문제생긴다...
		}catch(Exception e) {
			System.out.println("like cnt 추출에서 0 문제 [LikeVO : "+vo+"]");
			result.setObj(0);
		}
		return result;
	}


}
