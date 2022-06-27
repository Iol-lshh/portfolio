package com.ltns.rest_area.validator.user;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ltns.rest_area.domain.user.UserAuthDTO;

public class AuthValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserAuthDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserAuthDTO userAuth = (UserAuthDTO) target;

		long uid = userAuth.getUid();

		String message = userAuth.getMessage();

		if (message == null || message.equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "empty", "동작 메시지를 추가해주세요");
			return;
		}
		switch (message) {
		case "uid":
			if (uid == 0)
				errors.rejectValue("uid", "validation", new Long[] { uid }, "uid 값이 없습니다.");
			break;
		case "username":
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty", "아이디 값이 공백입니다.");
			break;
		case "delete":
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "authority", "empty", "authority 값이 공백입니다.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty", "아이디 값이 공백입니다.");
			break;
		case "insert":
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addAuthority", "empty", "addAuthority 값이 공백입니다.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty", "아이디 값이 공백입니다.");
			break;
		case "update":
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty", "아이디 값이 공백입니다.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prevAuthority", "empty", "prevAuthority 값이 공백입니다.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nextAuthority", "empty", "nextAuthority 값이 공백입니다.");
			break;

		default:
			break;
		}
	}

}
