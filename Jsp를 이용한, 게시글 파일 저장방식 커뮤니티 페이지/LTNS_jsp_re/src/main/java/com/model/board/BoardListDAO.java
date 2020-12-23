package main.java.com.model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.java.com.util.DataUtil;
import main.java.com.util.LogUtil;

public class BoardListDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	Statement stmt = null;
	ResultSet rs = null;

	public ArrayList<BoardListDTO> getCategoryAllList(String category) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_CATEGORY_ALL);
			psmt.setString(1, category);
			psmt.setString(2, category);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getCategoryAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getCategoryAllList()

	public ArrayList<BoardListDTO> getCategoryList(String category, String type, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_CATEGORY_PAGE);
			psmt.setString(1, category);
			psmt.setString(2, category);
			psmt.setInt(3, startNo);
			psmt.setInt(4, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getCategoryList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getCategoryList()

	public ArrayList<BoardListDTO> getCategorySearchDateAndNicknameAllList(String category, String start, String end,
			String nickname) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_CATEGORY_SEARCH_DATE_WRITER_ALL);
			psmt.setString(1, category);
			psmt.setString(2, start);
			psmt.setString(3, end);
			psmt.setString(4, nickname);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getCategorySearchDateAndNicknameAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getCategorySearchDateAndNicknameAllList()

	public ArrayList<BoardListDTO> getCategorySearchDateAndNicknameList(String category, String start, String end,
			String nickname, String type, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_CATEGORY_SEARCH_DATE_WRITER_PAGE);
			psmt.setString(1, category);
			psmt.setString(2, start);
			psmt.setString(3, end);
			psmt.setString(4, nickname);
			psmt.setInt(5, startNo);
			psmt.setInt(6, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getCategorySearchDateAndNicknameList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getCategorySearchDateAndNicknameList()

	public ArrayList<BoardListDTO> getCategorySearchDateAndTitleAllList(String category, String start, String end,
			String title) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_CATEGORY_SEARCH_DATE_TITLE_ALL);
			psmt.setString(1, category);
			psmt.setString(2, start);
			psmt.setString(3, end);
			psmt.setString(4, title);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getCategorySearchDateAndTitleAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getCategorySearchDateAndTitleAllList()

	public ArrayList<BoardListDTO> getCategorySearchDateAndTitleList(String category, String start, String end,
			String title, String type, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_CATEGORY_SEARCH_DATE_TITLE_PAGE);
			psmt.setString(1, category);
			psmt.setString(2, start);
			psmt.setString(3, end);
			psmt.setString(4, title);
			psmt.setInt(5, startNo);
			psmt.setInt(6, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getCategorySearchDateAndTitleList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getCategorySearchDateAndTitleList()

	public ArrayList<BoardListDTO> getCategorySearchDateAllList(String category, String start, String end) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_CATEGORY_SEARCH_DATE_ALL);
			psmt.setString(1, category);
			psmt.setString(2, start);
			psmt.setString(3, end);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getCategorySearchDateAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getCategorySearchDateAllList()

	public ArrayList<BoardListDTO> getCategorySearchDateList(String category, String start, String end, String type,
			int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_CATEGORY_SEARCH_DATE_PAGE);
			psmt.setString(1, category);
			psmt.setString(2, start);
			psmt.setString(3, end);
			psmt.setInt(4, startNo);
			psmt.setInt(5, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getCategorySearchDateList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getCategorySearchDateList()

	public ArrayList<BoardListDTO> getCategorySearchNickNameAllList(String category, String nickname) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_CATEGORY_SEARCH_WRITER_ALL);
			psmt.setString(1, category);
			psmt.setString(2, nickname);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getCategorySearchNickNameAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getCategorySearchNickNameAllList()

	public ArrayList<BoardListDTO> getCategorySearchNickNameList(String category, String type, String nickname,
			int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_CATEGORY_SEARCH_WRITER_PAGE);
			psmt.setString(1, category);
			psmt.setString(2, nickname);
			psmt.setInt(3, startNo);
			psmt.setInt(4, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getCategorySearchNickNameList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getCategorySearchNickNameList()

	public ArrayList<BoardListDTO> getCategorySearchTitleAllList(String category, String title) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_CATEGORY_SEARCH_TITLE_ALL);
			psmt.setString(1, category);
			psmt.setString(2, title);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getCategorySearchTitleAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getCategorySearchTitleAllList()

	public ArrayList<BoardListDTO> getCategorySearchTitleList(String category, String type, String title, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_CATEGORY_SEARCH_TITLE_PAGE);
			psmt.setString(1, category);
			psmt.setString(2, title);
			psmt.setInt(3, startNo);
			psmt.setInt(4, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getCategorySearchTitleList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getCategorySearchTitleList()

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public ArrayList<BoardListDTO> getViewcntAllList() {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_VIEWCNT_ALL);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getViewcntAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getViewcntAllList()

	public ArrayList<BoardListDTO> getViewcntList(String type, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_VIEWCNT_PAGE);
			psmt.setInt(1, startNo);
			psmt.setInt(2, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getViewcntList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getViewcntList()

	public ArrayList<BoardListDTO> getViewcntSearchDateAndNicknameAllList(String start, String end, String nickname) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_VIEWCNT_SEARCH_DATE_WRITER_ALL);
			psmt.setString(1, start);
			psmt.setString(2, end);
			psmt.setString(3, nickname);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getViewcntSearchDateAndNicknameAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getViewcntSearchDateAndNicknameAllList()

	public ArrayList<BoardListDTO> getViewcntSearchDateAndNicknameList(String start, String end, String nickname,
			String type, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_VIEWCNT_SEARCH_DATE_WRITER_PAGE);
			psmt.setString(1, start);
			psmt.setString(2, end);
			psmt.setString(3, nickname);
			psmt.setInt(4, startNo);
			psmt.setInt(5, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getViewcntSearchDateAndNicknameList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getViewcntSearchDateAndNicknameList()

	public ArrayList<BoardListDTO> getViewcntSearchDateAndTitleAllList(String start, String end, String title) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_VIEWCNT_SEARCH_DATE_TITLE_ALL);
			psmt.setString(1, start);
			psmt.setString(2, end);
			psmt.setString(3, title);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getViewcntSearchDateAndTitleAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getViewcntSearchDateAndTitleAllList()

	public ArrayList<BoardListDTO> getViewcntSearchDateAndTitleList(String start, String end, String title, String type,
			int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_VIEWCNT_SEARCH_DATE_TITLE_PAGE);
			psmt.setString(1, start);
			psmt.setString(2, end);
			psmt.setString(3, title);
			psmt.setInt(4, startNo);
			psmt.setInt(5, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getViewcntSearchDateAndTitleList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getViewcntSearchDateAndTitleList()

	public ArrayList<BoardListDTO> getViewcntSearchDateAllList(String start, String end) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_VIEWCNT_SEARCH_DATE_ALL);
			psmt.setString(1, start);
			psmt.setString(2, end);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getViewcntSearchDateAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getViewcntSearchDateAllList()

	public ArrayList<BoardListDTO> getViewcntSearchDateList(String start, String end, String type, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_VIEWCNT_SEARCH_DATE_PAGE);
			psmt.setString(1, start);
			psmt.setString(2, end);
			psmt.setInt(3, startNo);
			psmt.setInt(4, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getViewcntSearchDateList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getViewcntSearchDateList()

	public ArrayList<BoardListDTO> getViewcntSearchNickNameAllList(String nickname) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_VIEWCNT_SEARCH_WRITER_ALL);
			psmt.setString(1, nickname);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getViewcntSearchNickNameAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getViewcntSearchNickNameAllList()

	public ArrayList<BoardListDTO> getViewcntSearchNickNameList(String type, String nickname, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_VIEWCNT_SEARCH_WRITER_PAGE);
			psmt.setString(1, nickname);
			psmt.setInt(2, startNo);
			psmt.setInt(3, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getViewcntSearchNickNameList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getViewcntSearchNickNameList()

	public ArrayList<BoardListDTO> getViewCntSearchTitleAllList(String title) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_VIEWCNT_SEARCH_TITLE_ALL);
			psmt.setString(1, title);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getViewCntSearchTitleAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getViewCntSearchTitleAllList()

	public ArrayList<BoardListDTO> getViewcntSearchTitleList(String type, String title, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_VIEWCNT_SEARCH_TITLE_PAGE);
			psmt.setString(1, title);
			psmt.setInt(2, startNo);
			psmt.setInt(3, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getViewcntSearchTitleList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getViewcntSearchTitleList()

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public ArrayList<BoardListDTO> getEmpathizeAllList() {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_EMPATHIZE_ALL);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getEmpathizeAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getEmpathizeAllList()

	public ArrayList<BoardListDTO> getEmpathizeList(String type, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_EMPATHIZE_PAGE);
			psmt.setInt(1, startNo);
			psmt.setInt(2, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getEmapthizeList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getEmapthizeList()

	public ArrayList<BoardListDTO> getEmpathizeSearchDateAndNicknameAllList(String start, String end, String nickname) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_EMPATHIZET_SEARCH_DATE_WRITER_ALL);
			psmt.setString(1, start);
			psmt.setString(2, end);
			psmt.setString(3, nickname);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getEmpathizeSearchDateAndNicknameAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getEmpathizeSearchDateAndNicknameAllList()

	public ArrayList<BoardListDTO> getEmpathizeSearchDateAndNicknameList(String start, String end, String nickname,
			String type, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_EMPATHIZE_SEARCH_DATE_WRITER_PAGE);
			psmt.setString(1, start);
			psmt.setString(2, end);
			psmt.setString(3, nickname);
			psmt.setInt(4, startNo);
			psmt.setInt(5, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getEmpathizeSearchDateAndNicknameList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getEmpathizeSearchDateAndNicknameList()

	public ArrayList<BoardListDTO> getEmpathizeSearchDateAndTitleAllList(String start, String end, String title) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_EMPATHIZET_SEARCH_DATE_TITLE_ALL);
			psmt.setString(1, start);
			psmt.setString(2, end);
			psmt.setString(3, title);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getEmpathizeSearchDateAndTitleAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getEmpathizeSearchDateAndTitleAllList()

	public ArrayList<BoardListDTO> getEmpathizeSearchDateAndTitleList(String start, String end, String title,
			String type, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_EMPATHIZE_SEARCH_DATE_TITLE_PAGE);
			psmt.setString(1, start);
			psmt.setString(2, end);
			psmt.setString(3, title);
			psmt.setInt(4, startNo);
			psmt.setInt(5, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getEmpathizeSearchDateAndTitleList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getEmpathizeSearchDateAndTitleList()

	public ArrayList<BoardListDTO> getEmpathizeSearchDateAllList(String start, String end) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_EMPATHIZE_SEARCH_DATE_ALL);
			psmt.setString(1, start);
			psmt.setString(2, end);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getEmpathizeSearchDateAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getEmpathizeSearchDateAllList()

	public ArrayList<BoardListDTO> getEmpathizeSearchDateList(String start, String end, String type, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_EMPATHIZE_SEARCH_DATE_PAGE);
			psmt.setString(1, start);
			psmt.setString(2, end);
			psmt.setInt(3, startNo);
			psmt.setInt(4, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getEmpathizeSearchDateList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getEmpathizeSearchDateList()

	public ArrayList<BoardListDTO> getEmpathizeSearchNickNameAllList(String nickname) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_EMPATHIZE_SEARCH_WRITER_ALL);
			psmt.setString(1, nickname);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getEmpathizeSearchNickNameAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getEmpathizeSearchNickNameAllList()

	public ArrayList<BoardListDTO> getEmpathizeSearchNickNameList(String type, String nickname, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_EMPATHIZE_SEARCH_WRITER_PAGE);
			psmt.setString(1, nickname);
			psmt.setInt(2, startNo);
			psmt.setInt(3, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getEmpathizeSearchNickNameList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getEmpathizeSearchNickNameList()

	public ArrayList<BoardListDTO> getEmpathizeSearchTitleAllList(String title) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_EMPATHIZE_SEARCH_TITLE_ALL);
			psmt.setString(1, title);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getEmpathizeSearchTitleAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getEmpathizeSearchTitleAllList()

	public ArrayList<BoardListDTO> getEmpathizeSearchTitleList(String type, String title, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_EMPATHIZE_SEARCH_TITLE_PAGE);
			psmt.setString(1, title);
			psmt.setInt(2, startNo);
			psmt.setInt(3, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getEmpathizeSearchTitleList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getEmpathizeSearchTitleList()

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public ArrayList<BoardListDTO> getMypageAllList(int writer) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_MYPAGE_ALL);
			psmt.setInt(1, writer);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getMypageAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getMypageAllList()

	public ArrayList<BoardListDTO> getMypageList(int writer, String type, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_MYPAGE_PAGE);
			psmt.setInt(1, writer);
			psmt.setInt(2, startNo);
			psmt.setInt(3, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getMypageList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getMypageList()

	public ArrayList<BoardListDTO> getMypageSearchDateAndTitleAllList(int writer, String start, String end,
			String title) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_MYPAGE_SEARCH_DATE_TITLE_ALL);
			psmt.setInt(1, writer);
			psmt.setString(2, start);
			psmt.setString(3, end);
			psmt.setString(4, title);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getMypageSearchDateAndTitleAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getMypageSearchDateAndTitleAllList()

	public ArrayList<BoardListDTO> getMypageSearchDateAndTitleList(int writer, String start, String end, String title,
			String type, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_MYPAGE_SEARCH_DATE_TITLE_PAGE);
			psmt.setInt(1, writer);
			psmt.setString(2, start);
			psmt.setString(3, end);
			psmt.setString(4, title);
			psmt.setInt(5, startNo);
			psmt.setInt(6, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getMypageSearchDateAndTitleList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getMypageSearchDateAndTitleList()

	public ArrayList<BoardListDTO> getMypageSearchDateAllList(int writer, String start, String end) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_MYPAGE_SEARCH_DATE_ALL);
			psmt.setInt(1, writer);
			psmt.setString(2, start);
			psmt.setString(3, end);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getMypageSearchDateAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getMypageSearchDateAllList()

	public ArrayList<BoardListDTO> getMypageSearchDateList(int writer, String start, String end, String type,
			int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_MYPAGE_SEARCH_DATE_PAGE);
			psmt.setInt(1, writer);
			psmt.setString(2, start);
			psmt.setString(3, end);
			psmt.setInt(4, startNo);
			psmt.setInt(5, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getMypageSearchDateList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getMypageSearchDateList()

	public ArrayList<BoardListDTO> getMypageSearchTitleAllList(int writer, String title) {

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_MYPAGE_SEARCH_TITLE_ALL);
			psmt.setInt(1, writer);
			psmt.setString(2, title);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getMypageSearchTitleAllList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getMypageSearchTitleAllList()

	public ArrayList<BoardListDTO> getMypageSearchTitleList(int writer, String type, String title, int page) {
		int startNo = 1;
		int endNo = 1;
		int cnt = 10;

		if (!type.equals("list"))
			cnt = 8;

		startNo = (page - 1) * cnt + 1;
		endNo = page * cnt;

		ArrayList<BoardListDTO> list = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(BoardList.SELECT_POST_BY_MYPAGE_SEARCH_TITLE_PAGE);
			psmt.setInt(1, writer);
			psmt.setString(2, title);
			psmt.setInt(3, startNo);
			psmt.setInt(4, endNo);
			rs = psmt.executeQuery();
			list = createList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [getMypageSearchTitleList] " + e.getMessage());
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		} // end try

		return list;

	} // end getMypageSearchTitleList()

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public ArrayList<BoardListDTO> createList(ResultSet rs) {
		ArrayList<BoardListDTO> list = new ArrayList<BoardListDTO>();

		try {
			while (rs.next()) {
				BoardListDTO dto = new BoardListDTO();

				dto.setPostId(rs.getLong("POST_ID"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setWriter(rs.getString("ID"));
				dto.setWriterNum(rs.getLong("WRITER"));
				dto.setNickName(rs.getString("NICKNAME"));
				dto.setCategory(rs.getString("CATEGORY"));

				Date date = rs.getDate("REGDATE");
				Time time = rs.getTime("REGDATE");

				String strDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
				String strTime = new SimpleDateFormat("HH:mm").format(time);

				String meridiem = "오전";
				int integerTime = Integer.parseInt(strTime.substring(0, 2));
				if (integerTime > 12) {
					meridiem = "오후";
					integerTime -= 12;
				} // end if
				String regDate = strDate + " (" + meridiem + ")" + integerTime + strTime.substring(2);
				dto.setRegdate(regDate);

				dto.setRealFilePath(rs.getString("REAL_FILENAME"));
				dto.setEmpathizeCnt(rs.getLong("EMPATHIZE_CNT"));
				dto.setViewcnt(rs.getLong("VIEWCNT"));

				list.add(dto);
			} // end while
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.error("[BoardListDAO] [createList] " + e.getMessage());
		} // end try

		return list;
	} // end createList

}
