package com.ltns.rest_area.validator.user;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ltns.rest_area.domain.user.UserDTO;

public class UserValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserDTO user = (UserDTO) target;

		String message = user.getMessage();

		String regex = null;

		long uid = user.getUm_uid();

		String username = user.getUm_username();

		String nickname = user.getUm_nickname();

		String password = user.getUm_password();

		String passwordCheck = user.getPasswordCheck();

		if (message == null || message.equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "empty", "동작 메시지를 추가해주세요");
			return;
		}

		switch (message) {
		case "checkId":
			if (username != null && !username.equals("")) {
				regex = "[a-zA-Z0-9]{1,}@[a-zA-Z]{1,}.[a-zA-Z]{1,}";
				if (!inspect(regex, username)) {
					errors.rejectValue("um_username", "validation", new String[] { username }, "아이디(이메일) 형식이 아닙니다.");
				}
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_username", "empty", "아이디 값이 공백입니다.");
			}
			break;
		case "checkPw":
			if (password != null && !password.equals("")) {
				regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+?])[A-Za-z0-9!@#$%^&*()\\-_=+?]{8,20}";
				if (!inspect(regex, password)) {
					errors.rejectValue("um_password", "validation", new String[] { password },
							"비밀번호는 특수문자, 대문자, 소문자, 숫자를 포함한 8 ~ 20 자리입니다.");
				} else {
					if (passwordCheck != null && !passwordCheck.equals("")) {
						if (!password.equals(passwordCheck))
							errors.rejectValue("passwordCheck", "validation", new String[] { passwordCheck },
									"비밀번호가 서로 다릅니다.");
					} else {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordCheck", "empty",
								"비밀번호 체크 값이 공백입니다.");
					}
				}
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_password", "empty", "비밀번호 값이 공백입니다.");
			}
			break;
		case "checkNick":
			if (nickname != null && !nickname.equals("")) {
				regex = "[a-zA-Z0-9ㄱ-힣]{2,8}";
				if (!inspect(regex, nickname))
					errors.rejectValue("um_nickname", "validation", new String[] { nickname }, "허용하는 범위에 맞지 않습니다.");
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_nickname", "empty", "닉네임이 공백입니다.");
			}
			break;
		case "checkPwAndNick":
			if (username != null && !username.equals("")) {
				regex = "[a-zA-Z0-9]{1,}@[a-zA-Z]{1,}.[a-zA-Z]{1,}";
				if (!inspect(regex, username)) {
					errors.rejectValue("um_username", "validation", new String[] { username }, "아이디(이메일) 형식이 아닙니다.");
				}
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_username", "empty", "아이디 값이 공백입니다.");
			}
			if (password != null && !password.equals("")) {
				regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+?])[A-Za-z0-9!@#$%^&*()\\-_=+?]{8,20}";
				if (!inspect(regex, password)) {
					errors.rejectValue("um_password", "validation", new String[] { password },
							"비밀번호는 특수문자, 대문자, 소문자, 숫자를 포함한 8 ~ 20 자리입니다.");
				} else {
					if (passwordCheck != null && !passwordCheck.equals("")) {
						if (!password.equals(passwordCheck))
							errors.rejectValue("passwordCheck", "validation", new String[] { passwordCheck },
									"비밀번호가 서로 다릅니다.");
					} else {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordCheck", "empty",
								"비밀번호 체크 값이 공백입니다.");
					}
				}
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_password", "empty", "비밀번호 값이 공백입니다.");
			}
			break;
		case "insertUser":
			if (username != null && !username.equals("")) {
				regex = "[a-zA-Z0-9]{1,}@[a-zA-Z]{1,}.[a-zA-Z]{1,}";
				if (!inspect(regex, username)) {
					errors.rejectValue("um_username", "validation", new String[] { username }, "아이디(이메일) 형식이 아닙니다.");
				}
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_username", "empty", "아이디 값이 공백입니다.");
			}
			if (nickname != null && !nickname.equals("")) {
				regex = "[a-zA-Z0-9ㄱ-힣]{2,8}";
				if (!inspect(regex, nickname))
					errors.rejectValue("um_nickname", "validation", new String[] { nickname }, "허용하는 범위에 맞지 않습니다.");
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_nickname", "empty", "닉네임이 공백입니다.");
			}
			if (password != null && !password.equals("")) {
				regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+?])[A-Za-z0-9!@#$%^&*()\\-_=+?]{8,20}";
				if (!inspect(regex, password)) {
					errors.rejectValue("um_password", "validation", new String[] { password },
							"비밀번호는 특수문자, 대문자, 소문자, 숫자를 포함한 8 ~ 20 자리입니다.");
				} else {
					if (passwordCheck != null && !passwordCheck.equals("")) {
						if (!password.equals(passwordCheck))
							errors.rejectValue("passwordCheck", "validation", new String[] { passwordCheck },
									"비밀번호가 서로 다릅니다.");
					} else {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordCheck", "empty",
								"비밀번호 체크 값이 공백입니다.");
					}
				}
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_password", "empty", "비밀번호 값이 공백입니다.");
			}
			break;
		case "updatePw": 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_username", "empty", "아이디 값이 공백입니다.");
			if (password != null && !password.equals("")) {
				regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+?])[A-Za-z0-9!@#$%^&*()\\-_=+?]{8,20}";
				if (!inspect(regex, password)) {
					errors.rejectValue("um_password", "validation", new String[] { password },
							"비밀번호는 특수문자, 대문자, 소문자, 숫자를 포함한 8 ~ 20 자리입니다.");
				} else {
					if (passwordCheck != null && !passwordCheck.equals("")) {
						if (!password.equals(passwordCheck))
							errors.rejectValue("passwordCheck", "validation", new String[] { passwordCheck },
									"비밀번호가 서로 다릅니다.");
					} else {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordCheck", "empty",
								"비밀번호 체크 값이 공백입니다.");
					}
				}
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_password", "empty", "비밀번호 값이 공백입니다.");
			}
			break;

		case "updateNick": 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_username", "empty", "아이디 값이 공백입니다.");
			if (nickname != null && !nickname.equals("")) {
				regex = "[a-zA-Z0-9ㄱ-힣]{2,8}";
				if (!inspect(regex, nickname))
					errors.rejectValue("um_nickname", "validation", new String[] { nickname }, "허용하는 범위에 맞지 않습니다.");
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_nickname", "empty", "닉네임이 공백입니다.");
			}
			break;

		case "updatePwAndNick": 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_username", "empty", "아이디 값이 공백입니다.");
			if (password != null && !password.equals("")) {
				regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+?])[A-Za-z0-9!@#$%^&*()\\-_=+?]{8,20}";
				if (!inspect(regex, password)) {
					errors.rejectValue("um_password", "validation", new String[] { password },
							"비밀번호는 특수문자, 대문자, 소문자, 숫자를 포함한 8 ~ 20 자리입니다.");
				} else {
					if (passwordCheck != null && !passwordCheck.equals("")) {
						if (!password.equals(passwordCheck))
							errors.rejectValue("passwordCheck", "validation", new String[] { passwordCheck },
									"비밀번호가 서로 다릅니다.");
					} else {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordCheck", "empty",
								"비밀번호 체크 값이 공백입니다.");
					}
				}
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_password", "empty", "비밀번호 값이 공백입니다.");
			}
			if (nickname != null && !nickname.equals("")) {
				regex = "[a-zA-Z0-9ㄱ-힣]{2,8}";
				if (!inspect(regex, nickname))
					errors.rejectValue("um_nickname", "validation", new String[] { nickname }, "허용하는 범위에 맞지 않습니다.");
			} else {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_nickname", "empty", "닉네임이 공백입니다.");
			}
			break;

		case "deleteUser": 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_uid", "empty", "uid 값이 공백입니다.");
			break;
		case "findByUid":
			if (uid == 0)
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_uid", "empty", "uid 값이 공백입니다.");
			break;
		case "findByUsername":
			if (username == null || username.equals(""))
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_username", "empty", "아이디 값이 공백입니다.");
			break;
		case "findByNickname":
			if (nickname == null || nickname.equals(""))
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_nickname", "empty", "닉네임 값이 공백입니다.");
			break;
		default:
			break;
		}

	}

	boolean inspect(String regex, String input) {
		if (Pattern.matches(regex, input))
			return true;
		return false;
	}
}
