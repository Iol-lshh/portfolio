package main.java.com.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAO {

	void close() throws SQLException;

	/*---------------------select---------------------*/
	/*디폴트 방식*/
	// 1-1. ResultSet => DTO
	DTO mkDTO(ResultSet rs) throws SQLException;
	
	// 1-2. ResultSet => DTOs
	DTO[] mkDTOs(ResultSet rs) throws SQLException;
	
	// 0. sql => ResultSet : sql 문장을 매개변수로 받아, rs를 디비로 부터 받아내고, mkDTO로 rs를 넘겨서 DTO를 반환받으세요!
	DTO[] selectBySQL(String sql) throws SQLException;
	
	// 0-1-1. sql => ResultSet : Integer 매개변수를 받는 sql문으로 가져오기
	public DTO[] selectBySQL(String sql, int integerParamForPstmt) throws SQLException;

	// 0-1-2. sql => ResultSet : Integer 매개변수들을 받는 sql문으로 가져오기
	public DTO[] selectBySQL(String sql, int ...integerParamForPstmt) throws SQLException;
	
	// 0-2-1. sql => ResultSet : String 매개변수를 받는 sql문으로 가져오기
	public DTO[] selectBySQL(String sql, String stringParamForPstmt) throws SQLException;
	
	// 0-2-2. sql => ResultSet : String 매개변수들을 받는 sql문으로 가져오기
	public DTO[] selectBySQL(String sql, String ...stringParamForPstmt) throws SQLException;
	
	
	
	/*시그널 방식*/
	// 1-1-1 ResultSet => DTO : signal을 추가하여, 내부 switch문을 통한 다양한 DTO 생성
	DTO mkDTO(ResultSet rs, int signal) throws SQLException;
	
	// 1-2-1 ResultSet => DTOs : signal을 추가하여, 내부 switch문을 통한 다양한 DTO 생성
	DTO[] mkDTOs(ResultSet rs, int signal) throws SQLException;

	
	//시그널 추가 함수
	public DTO[] withSignal(DTO[] _arr, int signal) throws SQLException;
	
	//시그널 추가 매개변수 함수
	public DTO[] withSignal(DTO[] _arr, int signal, int integerParamForPstmt) throws SQLException;


	// 0-0-0-1. sql => ResultSet : signal을 추가하여, 내부 switch문을 통한 다양한 DTO 생성
	public DTO[] selectBySQL_withSignal(String sql, int signal) throws SQLException;

	// 0-1-1-1.
	public DTO[] selectBySQL_withSignal(String sql, int signal, int integerParamForPstmt) throws SQLException;

	//0-1-2-1.
	public DTO[] selectBySQL_withSignal(String sql, int signal, int... integerParamForPstmt) throws SQLException;

	// 0-2-1-1. sql => ResultSet : String 매개변수를 받는 sql문으로 가져오기
	public DTO[] selectBySQL_withSignal(String sql, int signal, String stringParamForPstmt) throws SQLException;
	
	// 0-2-2-1. sql => ResultSet : String 매개변수들을 받는 sql문으로 가져오기
	public DTO[] selectBySQL_withSignal(String sql, int signal, String ...stringParamForPstmt) throws SQLException;


	/*---------------------insert---------------------*/
	// insert : DTO => DB 
	public int insertBySQL_withDTO(String sql, DTO dto) throws SQLException;

	// insert : DTOs => DB 
	public int insertBySQL_withDTO(String sql, DTO ...dtos) throws SQLException;
	
	/*---------------------delete---------------------*/
	
	
	/*---------------------update---------------------*/
	

}
