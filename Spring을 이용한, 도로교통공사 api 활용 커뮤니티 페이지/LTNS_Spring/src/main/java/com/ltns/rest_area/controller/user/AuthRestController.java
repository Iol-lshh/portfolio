package com.ltns.rest_area.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltns.rest_area.domain.AjaxResult;
import com.ltns.rest_area.domain.user.AjaxList;
import com.ltns.rest_area.domain.user.AuthDTO;
import com.ltns.rest_area.domain.user.UserAuthDTO;
import com.ltns.rest_area.service.user.AuthService;
import com.ltns.rest_area.validator.user.AuthValidation;

@RestController
@RequestMapping("/admin/auth")
public class AuthRestController {

	AuthService authSerivece;

	@Autowired
	public void setAuthSerivece(AuthService authSerivece) {
		this.authSerivece = authSerivece;
	}

	@PostMapping("/find/all")
	public AjaxList findUserAuth() {

		AjaxList result = new AjaxList();
		result.setStatus("FAIL");

		try {
			List<UserAuthDTO> users = null;

			users = authSerivece.finAll();
			result.setCount(users.size());
			result.setListAll(users);
			result.setStatus("OK");
			result.setMessage("트랜잭션 성공");

		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("트랜잭션 오류");
		}

		return result;

	}

	@PostMapping("/find/username")
	public AjaxList findUserAuthByUsername(@Valid UserAuthDTO userAuth, BindingResult bindingResult) {

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

				List<UserAuthDTO> users = authSerivece.findByUsername(userAuth.getUsername());
				result.setCount(users.size());
				result.setListAll(users);
				result.setStatus("OK");
				result.setMessage("트랜잭션 성공");

			} catch (Exception e) {
				e.printStackTrace();
				result.setMessage("트랜잭션 오류");
			}
		}

		return result;

	}

	@PostMapping("/find/uid")
	public AjaxList findUserAuthByUid(@Valid UserAuthDTO userAuth, BindingResult bindingResult) {

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

			List<AuthDTO> users = authSerivece.findByUid(userAuth.getUid());
			result.setCount(users.size());
			result.setListAll(users);
			result.setStatus("OK");
			result.setMessage("트랜잭션 성공");

		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("트랜잭션 오류");
		}
		}
		return result;

	}
	
    
	@PostMapping("/insert")
	public AjaxResult addAuthority(@Valid UserAuthDTO userAuth, BindingResult bindingResult) {
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
			int insert = authSerivece.insertAuthByUsername(userAuth);
			result.setCount(insert);
			result.setStatus("OK");
			result.setMessage("트랜잭션 성공");

		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("트랜잭션 오류");
		}
		}
		return result;
	}
	
	@PutMapping("/update")
	public AjaxList updateAuthority(@Valid UserAuthDTO userAuth, BindingResult bindingResult) {
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

			int update = authSerivece.updateByUid(userAuth);
			
			if(update ==1) {
				List<UserAuthDTO> users = authSerivece.findByUsername(userAuth.getUsername());
				result.setCount(users.size());
				result.setListAll(users);
				result.setStatus("OK");
				result.setMessage("트랜잭션 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("트랜잭션 오류");
		}
		}
		return result;
	}

	@DeleteMapping("/delete")
	public AjaxResult deleteByUsername(@Valid UserAuthDTO userAuth, BindingResult bindingResult) {
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
			int delete = authSerivece.deleteByUsername(userAuth);
			result.setCount(delete);
			result.setStatus("OK");
			result.setMessage("트랜잭션 성공");

		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("트랜잭션 오류");
		}
		}
		return result;
	}

	@InitBinder
	public void intBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new AuthValidation());
	}

}
