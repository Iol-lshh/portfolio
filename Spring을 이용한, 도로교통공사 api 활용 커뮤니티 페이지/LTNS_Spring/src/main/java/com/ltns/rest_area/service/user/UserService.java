package com.ltns.rest_area.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.post.CommentDTO;
import com.ltns.rest_area.domain.post.PostDTO;
import com.ltns.rest_area.domain.restarea.FoodMenuDTO;
import com.ltns.rest_area.domain.restarea.GasStationDTO;
import com.ltns.rest_area.domain.restarea.RestAreaDTO;
import com.ltns.rest_area.domain.user.LikeDTO;
import com.ltns.rest_area.domain.user.UserDAO;
import com.ltns.rest_area.domain.user.UserDAOImpl;
import com.ltns.rest_area.domain.user.UserDTO;

public class UserService {

	UserDAOImpl userDAO;

	AuthService authService;

	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	@Autowired
	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public List<UserDTO> findAll() {
		List<UserDTO> users = new ArrayList<UserDTO>();

		List<DTO> list = userDAO.selectAll();

		if (list != null)
			list.forEach(item -> users.add((UserDTO) item));

		return users;
	}

	public List<UserDTO> findByNickname(UserDTO dto) {
		List<UserDTO> user = userDAO.findByNickname(dto);
		return user;
	}

	public List<UserDTO> findByUid(UserDTO dto) {
		List<UserDTO> user = new ArrayList<UserDTO>();
		List<DTO> temp = userDAO.selectByObject(dto);
		if (temp != null && temp.size() != 0)
			user.add((UserDTO) temp.get(0));
		return user;
	}

	public List<UserDTO> findByUsername(UserDTO dto) {
		List<UserDTO> user = new ArrayList<UserDTO>();
		List<DTO> temp = userDAO.selectByString(dto.getUm_username());
		if (temp != null && temp.size() != 0)
			user.add((UserDTO) temp.get(0));
		return user;
	}

	public int insertUser(UserDTO user) {
		user.setUm_password(bCryptPasswordEncoder.encode(user.getUm_password()));
		int result = userDAO.insertByObject(user);
		List<UserDTO> users = findByUsername(user);

		if (result == 1) {
			long seq = users.get(0).getUm_uid();
			result = authService.insertAuth(seq);
		}
		return result;
	}

	public int updatePasswordByUesrname(UserDTO user) {
		user.setUm_password(bCryptPasswordEncoder.encode(user.getUm_password()));
		return userDAO.updateByObject(user);
	}

	public int updateNicknameByUesrname(UserDTO user) {
		return userDAO.updateByDTO(user);
	}

	public int updateAllByUsername(UserDTO user) {
		user.setUm_password(bCryptPasswordEncoder.encode(user.getUm_password()));
		return userDAO.updateAllByUsername(user);
	}

	public int deleteByUesrname(UserDTO user) {
		List<UserDTO> findUsers = findByUsername(user);
		UserDTO findUser = findUsers.get(0);
		userDAO.deleteByPost_like(findUser);
		userDAO.deleteByPOST(findUser);
		userDAO.deleteByFM_like(findUser);
		userDAO.deleteByGS_like(findUser);
		userDAO.deleteByRA_like(findUser);
		userDAO.deleteByComments(findUser);
		int result = authService.deleteAuth(findUser.getUm_uid());

		if (result == 1)
			result = userDAO.deleteByObject(user);

		return result;
	}

	public List<LikeDTO> findByRA_like_All(UserDTO user) {
		// TODO Auto-generated method stub
		return userDAO.findByRA_like_All(user);
	}

	public List<LikeDTO> findByGS_like_All(UserDTO user) {
		// TODO Auto-generated method stub
		return userDAO.findByGS_like_All(user);
	}

	public List<LikeDTO> findByFM_like_All(UserDTO user) {
		// TODO Auto-generated method stub
		return userDAO.findByFM_like_All(user);
	}

	public List<LikeDTO> findByPost_like_All(UserDTO user) {
		// TODO Auto-generated method stub
		return userDAO.findByPost_like_All(user);
	}

	public List<PostDTO> findByPost_All(UserDTO user) {
		// TODO Auto-generated method stub
		return userDAO.findByPost_All(user);
	}

	public List<CommentDTO> findByComment_All(UserDTO user) {
		// TODO Auto-generated method stub
		return userDAO.findByComment_All(user);
	}

	public List<LikeDTO> findByRA_like(UserDTO user, int from, int to) {
		// TODO Auto-generated method stub
		return userDAO.findByRA_like(user, from, to);
	}

	public List<LikeDTO> findByGS_like(UserDTO user, int from, int to) {
		// TODO Auto-generated method stub
		return userDAO.findByGS_like(user, from, to);
	}

	public List<LikeDTO> findByFM_like(UserDTO user, int from, int to) {
		// TODO Auto-generated method stub
		return userDAO.findByFM_like(user, from, to);
	}

	public List<LikeDTO> findByPost_like(UserDTO user, int from, int to) {
		// TODO Auto-generated method stub
		return userDAO.findByPost_like(user, from, to);
	}

	public List<PostDTO> findByPost(UserDTO user, int from, int to) {
		// TODO Auto-generated method stub
		return userDAO.findByPost(user, from, to);
	}

	public List<CommentDTO> findByComments(UserDTO user, int from, int to) {
		// TODO Auto-generated method stub
		return userDAO.findByComments(user, from, to);
	}

	public List<FoodMenuDTO> findByFoodMenu(LikeDTO like) {
		// TODO Auto-generated method stub
		return userDAO.findByFoodMenu(like);
	}

	public List<GasStationDTO> findByGasStation(LikeDTO like) {
		// TODO Auto-generated method stub
		return userDAO.findByGasStation(like);
	}

	public List<RestAreaDTO> findByRestArea(LikeDTO like) {
		// TODO Auto-generated method stub
		return userDAO.findByRestArea(like);
	}

	public List<PostDTO> findByLikePost(LikeDTO like) {
		// TODO Auto-generated method stub
		return userDAO.findByLikePost(like);
	}

	public int deleteLike(long uid, String table, String column, String[] strCode) {
		return userDAO.deleteStrLike(uid, table, column, strCode);
	}

	public int deleteLike(long uid, String table, String column, int[] numCode) {
		return userDAO.deleteNumLike(uid, table, column, numCode);
	}

}
