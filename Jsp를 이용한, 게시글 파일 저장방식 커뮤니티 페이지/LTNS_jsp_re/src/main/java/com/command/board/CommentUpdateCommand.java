package main.java.com.command.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import main.java.com.command.Command;
import main.java.com.model.board.CommentListAjaxJSON;
import main.java.com.model.board.CommentDAO;
import main.java.com.model.board.CommentDTO;
import main.java.com.model.board.CommentJSONModel;
import main.java.com.util.LogUtil;

public class CommentUpdateCommand implements Command, Board_Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		CommentJSONModel model = null;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			StringBuffer json = new StringBuffer();
			String str;
			while ((str = reader.readLine()) != null) {
				json.append(str + "\n");
			}
			System.out.println(json.toString());
			model = new ObjectMapper().readValue(json.toString(), CommentJSONModel.class);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		}


		String content = model.getComment();
		int userId = -1;
		int postId = -1;
		int page = -1;
		int commentId = -1;
		try {
			userId = Integer.parseInt(model.getUserId());
			postId = Integer.parseInt(model.getPostId());
			page = Integer.parseInt(model.getPage());
			commentId = Integer.parseInt(model.getCommentId());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		}

		if (content == null || content.equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "내용을 모두 채워주세요.");
			return;
		}

		if (userId == -1 || postId == -1 || page == -1) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "요청 오류");
			return;
		}

		if (commentId == -1) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "코멘트가 존재하지 않습니다.");
			return;
		}

		int create = new CommentDAO().updateCommentByCommentId(content, commentId);
		if (create == -1) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "데이터 베이스 오류");
			return;
		}

		long count = new CommentDAO().getAllCommentByPostId(postId).size();
		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
		for (int i = 1; i <= page; i++) {
			list.addAll(new CommentDAO().getPageCommentListByPostId(postId, i));
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		responseJSON(request, response);

	} // end excute()

	@Override
	public void responseJSON(HttpServletRequest request, HttpServletResponse response) {
		long count = (long) request.getAttribute("count");
		ArrayList<CommentDTO> list = (ArrayList<CommentDTO>) request.getAttribute("list");

		CommentListAjaxJSON result = new CommentListAjaxJSON();

		if (list.size() == 0) {
			result.setStatus("FAIL");
			result.setCount(0);
		} else {
			result.setStatus("OK");
			result.setCount(count);
			result.setList(list);
		} // end if

		try {
			JsonMapper mapper = new JsonMapper();
			String json = mapper.writeValueAsString(result);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("[BoarAjaxCommand] [responseJSON] " + e.getMessage());
		} // end try

	} // responseJSON()

	@Override
	public void responseXML(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
