package com.ltns.rest_area.security.handler;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ltns.rest_area.domain.user.UserDTO;
import com.ltns.rest_area.service.user.UserService;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	UserService service;
	JdbcTemplate jdbc;

	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}

	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String name = authentication.getName();

		List<UserDTO> list = jdbc.query("select * from usermember where um_username=?", new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, name);
			}
		}, new BeanPropertyRowMapper<UserDTO>(UserDTO.class));

		request.getSession().setAttribute("user", name);
		request.getSession().setAttribute("userObj", list.get(0));
		List<String> role = new ArrayList<String>();
		authentication.getAuthorities().forEach(auth -> role.add(auth.getAuthority()));
		request.getSession().setAttribute("auth", role);
		response.sendRedirect(request.getContextPath() + request.getContextPath() + "/front");
	}

}
