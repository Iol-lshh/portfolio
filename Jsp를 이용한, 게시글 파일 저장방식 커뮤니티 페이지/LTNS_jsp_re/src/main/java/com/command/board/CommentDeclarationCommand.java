package main.java.com.command.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.command.Command;
import main.java.com.model.board.CommentDAO;
import main.java.com.model.board.CommentDTO;
import main.java.com.model.board.CommentDeclarationJSON;
import main.java.com.util.Gmail;
import main.java.com.util.LogUtil;
import main.java.com.view.MemberDAO;
import main.java.com.view.MemberDTO;

public class CommentDeclarationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		CommentDeclarationJSON model = null;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			StringBuffer json = new StringBuffer();
			String str;
			while ((str = br.readLine()) != null) {
				json.append(str + "\n");
			}
			model = new ObjectMapper().readValue(json.toString(), CommentDeclarationJSON.class);
		} catch (IOException e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		}

		StringBuffer mailContent = new StringBuffer();
		mailContent.append("<h1>신고 내용</h1>");
		mailContent.append("<table style=\"border: 1px solid black; border-collapse: collapse\">");
		mailContent.append("<tbody>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 110px\">신고날짜</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(model.getDeclarationDate());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 110px\">신고자 댓글 ID</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(model.getDeclarationCommentId() + "");
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 110px\">신고자 ID</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(model.getDeclarationNickname());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 110px\">신고 제목</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(model.getDeclarationTitle());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 110px\">신고 제목</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(model.getDeclarationContent());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("</tbody>");
		mailContent.append("</table>");
		mailContent.append("<br />");

		// 피신고자 및 피 신고자의 코멘트
		ArrayList<CommentDTO> Respondent = new CommentDAO().getCommendById(model.getDeclarationCommentId());
		// [commentId=1, commentContents=asdasdasd, writerId=1, writer=a,
		// nickName=admin, email=admin@LTNS.com, postId=21, regdate=2020-11-12
		// (오후)11:56]
		
		mailContent.append("<h1>신고 댓글 정보</h1>");
		mailContent.append("<table style=\"border: 1px solid black; border-collapse: collapse\">");
		mailContent.append("<tbody>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">작성자 ID</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(Respondent.get(0).getWriter());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">작성자 닉네임</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(Respondent.get(0).getNickName());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">작성자 이메일</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(Respondent.get(0).getEmail());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">작성 날짜</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(Respondent.get(0).getRegdate());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">작성 포스트 ID</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(Respondent.get(0).getPostId());
		mailContent.append("</td>");
		mailContent.append("</tr>");	
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">작성 내용</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(Respondent.get(0).getCommentContents());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("</tbody>");
		mailContent.append("</table>");
		mailContent.append("<br />");

		// 신고자
		MemberDTO[] reporter = null;
		try {
			reporter = new MemberDAO().member_MM_NICK_Select(model.getDeclarationNickname());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// [mm_id=2, id=hapj8, password=zclb604o2, nickname=z148hll,
		// email=tzjq51@naver.com, grade=MEMBER]

		mailContent.append("<h1>신고자 정보</h1>");
		mailContent.append("<table style=\"border: 1px solid black; border-collapse: collapse\">");
		mailContent.append("<tbody>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">신고자 ID</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(reporter[0].getId());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">신고자 닉네임</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(reporter[0].getNickname());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">신고자 이메일</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(reporter[0].getEmail());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">신고자 회원 등급</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(reporter[0].getGrade());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("</tbody>");
		mailContent.append("</table>");
		mailContent.append("<br />");

		mailContent.append("<h1>피신고자 정보</h1>");
		mailContent.append("<table style=\"border: 1px solid black; border-collapse: collapse\">");
		mailContent.append("<tbody>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">피신고자 ID</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(Respondent.get(0).getWriter());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">피신고자 닉네임</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(Respondent.get(0).getNickName());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">피신고자 이메일</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(Respondent.get(0).getEmail());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<th style=\"border: 1px solid black; width: 150px\">신고자 회원 등급</th>");
		mailContent.append("<td style=\"border: 1px solid black\">");
		mailContent.append(Respondent.get(0).getGrade());
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("</tbody>");
		mailContent.append("</table>");
		mailContent.append("<br />");

		response.setContentType("text/html; charset=UTF-8");
		try {
			new Gmail(request).emailSend("commentAdmin", "ltnsreport@gmail.com", "[comment]신고", mailContent.toString());
			response.getWriter().write("true");
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
			try {
				response.getWriter().write("false");
			} catch (IOException e1) {
				e1.printStackTrace();
				LogUtil.error(e.getMessage());
			}
		}

	}

}
