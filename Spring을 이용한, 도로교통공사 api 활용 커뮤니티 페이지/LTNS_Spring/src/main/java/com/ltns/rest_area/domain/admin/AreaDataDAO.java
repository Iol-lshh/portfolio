package com.ltns.rest_area.domain.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;

public interface AreaDataDAO extends DAO {

	int SelectTotalGas() ;
	int SelectTotalMenu();
	
	
	
	List<DTO> SeachXY(String names);
	List<DTO> SerachFoodMenu(String FoodName);
	List<DTO> ReastAreaName(String RName);
	List<DTO> GasstionName(String GName);
	
	
	public List<DTO> selectFromGasRow(
			@Param("from") int from, @Param("pagenationPage") int pagenationPage
	);
	
	public List<DTO> selectFromMenuRow(
			@Param("from") int from, @Param("pagenationPage") int pagenationPage
	);
	
}
