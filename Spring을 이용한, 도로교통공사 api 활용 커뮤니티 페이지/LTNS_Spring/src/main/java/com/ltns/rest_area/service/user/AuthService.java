package com.ltns.rest_area.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.user.AuthDAO;
import com.ltns.rest_area.domain.user.AuthDAOImpl;
import com.ltns.rest_area.domain.user.AuthDTO;
import com.ltns.rest_area.domain.user.UserAuthDTO;
import com.ltns.rest_area.domain.user.UserDTO;

public class AuthService {

	AuthDAOImpl authDAO;

	UserService service;

	@Autowired
	public void setAuthDAO(AuthDAOImpl authDAO) {
		this.authDAO = authDAO;
	}

	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}

	public int insertAuth(long uid) {
		return authDAO.insertByObject(uid);
	}

	public int insertAuthByUsername(UserAuthDTO userAuth) {
		UserDTO user = new UserDTO();
		user.setUm_username(userAuth.getUsername());
		userAuth.setUid(service.findByUsername(user).get(0).getUm_uid());
		return authDAO.insertByDTO(userAuth);
	}

	public int deleteAuth(long uid) {
		return authDAO.deleteByObject(uid);
	}

	public int deleteByUsername(UserAuthDTO userAuth) {
		UserDTO user = new UserDTO();
		user.setUm_username(userAuth.getUsername());
		List<UserDTO> users = service.findByUsername(user);
		userAuth.setUid(users.get(0).getUm_uid());
		return authDAO.deleteByUserAuth(userAuth);
	}

	public List<AuthDTO> findByUid(long uid) {
		List<DTO> list = null;
		list = authDAO.selectByObject(uid);
		List<AuthDTO> result = new ArrayList<AuthDTO>();
		if (list != null)
			list.forEach(item -> result.add((AuthDTO) item));
		return result;
	}

	public List<UserAuthDTO> finAll() {
		List<DTO> list = null;
		list = authDAO.selectAll();
		List<UserAuthDTO> result = new ArrayList<UserAuthDTO>();
		if (list != null)
			list.forEach(item -> result.add((UserAuthDTO) item));
		return result;
	}

	public List<UserAuthDTO> findByUsername(String username) {
		List<DTO> list = null;
		list = authDAO.selectByString(username);
		List<UserAuthDTO> result = new ArrayList<UserAuthDTO>();
		if (list != null)
			list.forEach(item -> result.add((UserAuthDTO) item));
		return result;
	}

	public int updateByUid(UserAuthDTO userAuth) {
		UserDTO user = new UserDTO();
		user.setUm_username(userAuth.getUsername());
		List<UserDTO> users = service.findByUsername(user);
		userAuth.setUid(users.get(0).getUm_uid());
		return authDAO.updateByObject(userAuth);
	}

}
