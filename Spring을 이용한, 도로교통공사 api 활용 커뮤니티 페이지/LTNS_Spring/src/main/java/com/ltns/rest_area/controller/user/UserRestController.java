package com.ltns.rest_area.controller.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ltns.rest_area.domain.AjaxResult;
import com.ltns.rest_area.domain.post.CommentDTO;
import com.ltns.rest_area.domain.post.PostDTO;
import com.ltns.rest_area.domain.restarea.FoodMenuDTO;
import com.ltns.rest_area.domain.restarea.GasStationDTO;
import com.ltns.rest_area.domain.restarea.RestAreaDTO;
import com.ltns.rest_area.domain.user.AjaxList;
import com.ltns.rest_area.domain.user.Gmail;
import com.ltns.rest_area.domain.user.LikeDTO;
import com.ltns.rest_area.domain.user.UserDTO;
import com.ltns.rest_area.service.user.UserService;
import com.ltns.rest_area.validator.user.UserValidation;

@RestController
public class UserRestController {

	UserService service;

	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}

	@PostMapping("/auth/user/joinLookup")
	public AjaxResult joinLookup(@Valid UserDTO user, BindingResult bindingResult) {

		String message = user.getMessage();

		AjaxResult result = new AjaxResult();
		result.setStatus("FAIL");
		List<String> msgs = new ArrayList<String>();

		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(msg -> {
				msgs.add(msg.getDefaultMessage());
			});
			result.setMessage(msgs.get(0));
		} else {
			try {
				List<UserDTO> users = null;
				switch (message) {
				case "checkId":
					users = service.findByUsername(user);
					if (users.size() == 1) {
						result.setCount(users.size());
						result.setMessage(users.get(0).getUm_username() + " 아이디 중복");
					} else {
						result.setStatus("OK");
						result.setMessage("중복되는 아이디가 존재 하지 않습니다.");
					}
					break;
				case "checkNick":
					users = service.findByNickname(user);
					if (users.size() == 1) {
						result.setCount(users.size());
						result.setMessage(users.get(0).getUm_nickname() + " 닉네임 중복");
					} else {
						result.setStatus("OK");
						result.setMessage("중복되는 닉네임이 존재 하지 않습니다.");
					}
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setMessage("트랜잭션 에러");
			}
		}

		return result;
	}

	@PostMapping("/auth/user/join")
	public AjaxResult join(@Valid UserDTO user, BindingResult bindingResult) {

		AjaxResult result = new AjaxResult();
		result.setStatus("FAIL");
		List<String> msgs = new ArrayList<String>();

		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(msg -> {
				msgs.add(msg.getDefaultMessage());
			});
			result.setMessage(msgs.get(0));
		} else {
			try {
				int count = service.insertUser(user);
				result.setCount(count);
				result.setStatus("OK");
				result.setMessage("회원가입에 성공했습니다.");
			} catch (Exception e) {
				e.printStackTrace();
				result.setMessage("트랜잭션 오류");
			}
		}

		return result;
	}

	@PostMapping("/auth/user/email")
	public AjaxResult email(HttpServletRequest request, @Valid UserDTO user, BindingResult bindingResult, String select,
			String email, String subjects, String artcle) {

		String kind = user.getKind();

		AjaxResult result = new AjaxResult();
		result.setStatus("FAIL");
		List<String> msgs = new ArrayList<String>();

		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(msg -> {
				msgs.add(msg.getDefaultMessage());
			});
			result.setMessage(msgs.get(0));
		} else {
			UserDTO findUser = null;
			String subject = null;
			String contentText = null;
			int random = ((int) (Math.random() * 9 + 1)) * 100000000;
			long number = System.currentTimeMillis() / random;
			if (kind.equals("authentication")) {
				subject = "[회원가입] 인증번호";
				contentText = subject + " : " + number;
				new Gmail(request).emailSend("joinAdmin", user.getUm_username(), subject, contentText);
				result.setCount((int) number);
				result.setStatus("OK");
				result.setMessage("이메일 전송 성공");
			} else if (kind.equals("findIdByUsername")) {
				List<UserDTO> users = service.findByUsername(user);
				if (users.size() == 1) {
					findUser = users.get(0);
					subject = "[아이디 찾기] 결과";
					contentText = "조회하신 유저 아이디는 " + findUser.getUm_username() + " 입니다.";
					new Gmail(request).emailSend("findAdmin", findUser.getUm_username(), subject, contentText);
					result.setCount(users.size());
					result.setStatus("OK");
					result.setMessage("이메일 전송 성공");
				} else {
					result.setMessage("정보에 해당하는 아이디가 존재 하지 않습니다.");
				}
			} else if (kind.equals("findIdByNickname")) {
				List<UserDTO> users = service.findByNickname(user);
				if (users.size() == 1) {
					findUser = users.get(0);
					subject = "[아이디 찾기] 결과";
					contentText = "조회하신 유저 아이디는 " + findUser.getUm_username() + " 입니다.";
					new Gmail(request).emailSend("findAdmin", findUser.getUm_username(), subject, contentText);
					result.setCount(users.size());
					result.setStatus("OK");
					result.setMessage("이메일 전송 성공");
				} else {
					result.setMessage("정보에 해당하는 아이디가 존재 하지 않습니다.");
				}
			} else if (kind.equals("question")) {
				List<UserDTO> users = service.findByUsername(user);
				if (users.size() == 1) {
					findUser = users.get(0);
					subject = "[" + select + " 문의사항] " + findUser.getUm_username();
					contentText = "<table style=\"border: 1px solid black; border-collapse: collapse;\">";
					contentText += "<thead style=\"border: 1px solid black;\">";
					contentText += "<tr>";
					contentText += "<th colspan=\"2\" style=\"border: 1px solid black;\">";
					contentText += subject;
					contentText += "</th>";
					contentText += "</tr>";
					contentText += "</thead>";
					contentText += "<tbody style=\"border: 1px solid black;\">";
					contentText += "<tr>";
					contentText += "<th style=\"border: 1px solid black;\">문의 사항</th>";
					contentText += "<td style=\"border: 1px solid black;\"><select><option selected=\"seleted\">"
							+ select + "</option></select>";
					contentText += "</tr>";
					contentText += "<tr>";
					contentText += "<th style=\"border: 1px solid black;\">문의자 Email</th>";
					contentText += "<td style=\"border: 1px solid black;\">" + email + "</td>";
					contentText += "</tr>";
					contentText += "<tr>";
					contentText += "<th style=\"border: 1px solid black;\">문의자 아이디</th>";
					contentText += "<td style=\"border: 1px solid black;\">" + findUser.getUm_username() + "</td>";
					contentText += "</tr>";
					contentText += "<tr>";
					contentText += "<th style=\"border: 1px solid black;\">제목</th>";
					contentText += "<td style=\"border: 1px solid black;\">" + subjects + "</td>";
					contentText += "</tr>";
					contentText += "<tr>";
					contentText += "<th style=\"border: 1px solid black;\">내용</th>";
					contentText += "<td style=\"border: 1px solid black;\">" + artcle + "</td>";
					contentText += "</tr>";
					contentText += "</tbody>";
					contentText += "</table>";
					new Gmail(request).emailSend("questionAdmin", "LTNSReport@gmail.com", subject, contentText);
					result.setCount(users.size());
					result.setStatus("OK");
					result.setMessage("이메일 전송 성공");
				} else {
					result.setMessage("정보에 해당하는 아이디가 존재 하지 않습니다.");
				}
			}
		}
		return result;
	}

	@PostMapping("/member/user/lookUp")
	public AjaxList lookup(@Valid UserDTO user, BindingResult bindingResult) {

		String message = user.getMessage();

		AjaxList result = new AjaxList();
		result.setStatus("FAIL");
		List<String> msgs = new ArrayList<String>();

		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(msg -> {
				msgs.add(msg.getDefaultMessage());
			});
			result.setMessage(msgs.get(0));
		} else {
			try {
				List<UserDTO> users = null;
				switch (message) {
				case "findByUid":
					users = service.findByUid(user);
					result.setCount(users.size());
					result.setListAll(users);
					result.setStatus("OK");
					result.setMessage("findByUid 트랜잭션 성공");
					break;
				case "findByUsername":
					users = service.findByUsername(user);
					result.setCount(users.size());
					result.setListAll(users);
					result.setStatus("OK");
					result.setMessage("findByUsername 트랜잭션 성공");
					break;
				case "findByNickname":
					users = service.findByNickname(user);
					result.setCount(users.size());
					result.setListAll(users);
					result.setStatus("OK");
					result.setMessage("findByNickname 트랜잭션 성공");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setMessage("트랜잭션 오류");
			}
		}

		return result;

	}

	@PutMapping("/auth/user/update")
	public AjaxResult update(@Valid UserDTO user, BindingResult bindingResult) {

		AjaxResult result = new AjaxResult();
		result.setStatus("FAIL");
		List<String> msgs = new ArrayList<String>();

		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(msg -> {
				msgs.add(msg.getDefaultMessage());
			});
			result.setMessage(msgs.get(0));
		} else {
			try {
				int update = service.updatePasswordByUesrname(user);
				if (update == 1) {
					result.setCount(update);
					result.setStatus("OK");
					result.setMessage("트랜잭션 성공(패스워드 변경)");
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setMessage("트랜잭션 오류");
			}
		}

		return result;
	}

	@PutMapping("/member/user/modify")
	public AjaxList modify(HttpServletRequest request, @Valid UserDTO user, BindingResult bindingResult) {

		String message = user.getMessage();

		AjaxList result = new AjaxList();
		result.setStatus("FAIL");
		List<String> msgs = new ArrayList<String>();

		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(msg -> {
				msgs.add(msg.getDefaultMessage());
			});
			result.setMessage(msgs.get(0));
		} else {
			try {
				int update = 0;
				List<UserDTO> users = null;
				switch (message) {
				case "updatePw":
					update = service.updatePasswordByUesrname(user);
					if (update == 1) {
						users = service.findByUsername(user);
						result.setCount(users.size());
						result.setListAll(users);
						result.setStatus("OK");
						result.setMessage("updatePw 트랜잭션 성공");
					}
					break;
				case "updateNick":
					update = service.updateNicknameByUesrname(user);
					if (update == 1) {
						users = service.findByUsername(user);
						result.setCount(users.size());
						result.setListAll(users);
						result.setStatus("OK");
						result.setMessage("updateNick 트랜잭션 성공");
					}
					break;
				case "updatePwAndNick":
					update = service.updateAllByUsername(user);
					if (update == 1) {
						users = service.findByUsername(user);
						result.setCount(users.size());
						result.setListAll(users);
						result.setStatus("OK");
						result.setMessage("updatePwAndNick 트랜잭션 성공");
					}
					break;
				}
				request.getSession().removeAttribute("userObj");
				request.getSession().setAttribute("userObj", users.get(0));
			} catch (Exception e) {
				e.printStackTrace();
				result.setMessage("트랜잭션 오류");
			}
		}

		return result;
	}

	@DeleteMapping("/member/user/delete")
	public AjaxResult delete(@Valid UserDTO user, BindingResult bindingResult) {

		AjaxResult result = new AjaxResult();
		result.setStatus("FAIL");
		List<String> msgs = new ArrayList<String>();

		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(msg -> {
				msgs.add(msg.getDefaultMessage());
			});
			result.setMessage(msgs.get(0));
		} else {
			try {
				int delete = service.deleteByUesrname(user);
				if (delete == 1) {
					result.setCount(delete);
					result.setStatus("OK");
					result.setMessage("트랜잭션 성공(유저 삭제)");
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setMessage("트랜잭션 오류");
			}
		}

		return result;
	}

	@PostMapping("/member/user/mypage")
	public AjaxList mypage(@Valid UserDTO user, BindingResult bindingResult) {
		AjaxList result = new AjaxList();
		result.setStatus("FAIL");
		List<String> msgs = new ArrayList<String>();

		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(msg -> {
				msgs.add(msg.getDefaultMessage());
			});
			result.setMessage(msgs.get(0));
		} else {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				List<LikeDTO> foodLikes = service.findByFM_like_All(user);
				List<FoodMenuDTO> foods = new ArrayList<FoodMenuDTO>();
				if (foodLikes.size() != 0) {
					foodLikes.forEach(like -> foods.add(service.findByFoodMenu(like).get(0)));
				}
				if (foods.size() != 0) {
					map.put("foods", foods);
				}
				List<LikeDTO> gasStationLikes = service.findByGS_like_All(user);
				List<GasStationDTO> gasStations = new ArrayList<GasStationDTO>();
				if (gasStationLikes.size() != 0) {
					gasStationLikes.forEach(like -> gasStations.add(service.findByGasStation(like).get(0)));
				}
				if (gasStations.size() != 0) {
					map.put("gasStations", gasStations);
				}
				List<LikeDTO> restAreaLikes = service.findByRA_like_All(user);
				List<RestAreaDTO> restAreas = new ArrayList<RestAreaDTO>();
				if (restAreaLikes.size() != 0) {
					restAreaLikes.forEach(like -> restAreas.add(service.findByRestArea(like).get(0)));
				}
				if (restAreas.size() != 0) {
					map.put("restAreas", restAreas);
				}
				List<LikeDTO> postLikes = service.findByPost_like_All(user);
				List<PostDTO> likePosts = new ArrayList<PostDTO>();
				if (postLikes.size() != 0) {
					postLikes.forEach(like -> likePosts.add(service.findByLikePost(like).get(0)));
				}
				if (likePosts.size() != 0) {
					map.put("likePosts", likePosts);
				}
				List<PostDTO> posts = service.findByPost_All(user);
				if (posts.size() != 0) {
					map.put("posts", posts);
				}
				List<CommentDTO> comments = service.findByComment_All(user);
				if (comments.size() != 0) {
					map.put("comments", comments);
				}
				result.setMap(map);
				result.setStatus("OK");
				result.setMessage("정보전송 성공");
			} catch (Exception e) {
				e.printStackTrace();
				result.setMessage("트랜잭션 오류");
			}
		}

		return result;

	}

	@GetMapping("/member/user/list/{type}/{uid}/{page}/{pageRows}")
	public AjaxList list(@PathVariable String type, @PathVariable long uid, @PathVariable int page,
			@PathVariable int pageRows) {
		AjaxList result = new AjaxList();
		result.setStatus("FAIL");
		UserDTO user = new UserDTO();
		user.setUm_uid(uid);
		List<LikeDTO> likes = null;
		int from = (page - 1) * 10 + 1;
		int to = from + (pageRows - 1);
		if (type.equals("foods")) {
			result.setTotalCnt(service.findByFM_like_All(user).size());
			result.setPageNo(page);
			result.setNumOfRows(pageRows);
			result.setWritePages(pageRows);
			likes = service.findByFM_like(user, from, to);
			List<FoodMenuDTO> foods = new ArrayList<FoodMenuDTO>();
			likes.forEach(like -> foods.add(service.findByFoodMenu(like).get(0)));
			result.setStatus("OK");
			result.setListAll(foods);
		} else if (type.equals("gasStations")) {
			result.setTotalCnt(service.findByGS_like_All(user).size());
			result.setPageNo(page);
			result.setNumOfRows(pageRows);
			result.setWritePages(pageRows);
			likes = service.findByGS_like(user, from, to);
			List<GasStationDTO> gasStations = new ArrayList<GasStationDTO>();
			likes.forEach(like -> gasStations.add(service.findByGasStation(like).get(0)));
			result.setStatus("OK");
			result.setListAll(gasStations);
		} else if (type.equals("restAreas")) {
			result.setTotalCnt(service.findByRA_like_All(user).size());
			result.setPageNo(page);
			result.setNumOfRows(pageRows);
			result.setWritePages(pageRows);
			likes = service.findByRA_like(user, from, to);
			List<RestAreaDTO> restAreas = new ArrayList<RestAreaDTO>();
			likes.forEach(like -> restAreas.add(service.findByRestArea(like).get(0)));
			result.setStatus("OK");
			result.setListAll(restAreas);
		} else if (type.equals("likePosts")) {
			result.setTotalCnt(service.findByPost_like_All(user).size());
			result.setPageNo(page);
			result.setNumOfRows(pageRows);
			result.setWritePages(pageRows);
			likes = service.findByPost_like(user, from, to);
			List<PostDTO> likePosts = new ArrayList<PostDTO>();
			likes.forEach(like -> likePosts.add(service.findByLikePost(like).get(0)));
			result.setStatus("OK");
			result.setListAll(likePosts);
		} else if (type.equals("posts")) {
			result.setTotalCnt(service.findByPost_All(user).size());
			result.setPageNo(page);
			result.setNumOfRows(pageRows);
			result.setWritePages(pageRows);
			List<PostDTO> posts = service.findByPost(user, from, to);
			result.setStatus("OK");
			result.setListAll(posts);
		} else if (type.equals("comments")) {
			result.setTotalCnt(service.findByComment_All(user).size());
			result.setPageNo(page);
			result.setNumOfRows(pageRows);
			result.setWritePages(pageRows);
			List<CommentDTO> comments = service.findByComments(user, from, to);
			result.setStatus("OK");
			result.setListAll(comments);
		}
		return result;
	}

	@DeleteMapping("/member/user/list")
	public AjaxResult delete(@RequestParam("um_uid") long uid, String table, String column, String[] strCode,
			int[] numCode) {
		AjaxResult result = new AjaxResult();
		result.setStatus("FAIL");
		try {
			int num = 0;
			if (strCode != null) {
				num = service.deleteLike(uid, table, column, strCode);
			} else {
				num = service.deleteLike(uid, table, column, numCode);
			}
			result.setCount(num);
			result.setStatus("OK");
			result.setMessage("삭제 성공");
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("트랜잭션 에러");
		}

		return result;
	}

	@InitBinder
	public void intBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new UserValidation());
	}
}
