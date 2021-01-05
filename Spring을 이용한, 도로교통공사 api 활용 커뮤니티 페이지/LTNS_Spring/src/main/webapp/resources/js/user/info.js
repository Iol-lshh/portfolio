import { logout } from '../../js/user/logout.js';
var check = false;
var nick = $('#um_nickname').val();
$(document).ready(function() {
	$('#logout').html(
		logout(
			$('#modiyfyForm').attr('action'),
			$('#um_username').val(),
			$('#token').attr('name'),
			$('#token').val(),
		),
	);
	$('#um_password').focusout(function() {
		if ($('#um_password').val() == '') return;
		var passwordReg = new RegExp(
			/(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+?])[A-Za-z0-9!@#$%^&*()\\-_=+?]{8,20}/,
		);
		if (!passwordReg.test($('#um_password').val())) {
			check = false;
			$('#password').text(
				'* 비밀번호는 특수문자, 대문자, 소문자, 숫자를 포함한 8 ~ 20 자리 입니다.',
			);
			$('#password').removeClass('hide');
			$('#passwordCheck').attr('disabled', true);
			$('#um_password').focus();
		} else {
			var chk = false;
			$('#passwordCheck').attr('disabled', false);
			var classList = $($('#password').attr('class').split(' '));
			for (var i = 0; i < classList.length; i++) {
				if (classList[i] != 'hide') {
					chk = true;
					break;
				}
			}
			if (chk) $('#password').addClass('hide');
		}
	});
	$('#passwordCheck').focusout(function() {
		if ($('#passwordCheck').val() == '') return;
		if (
			$('#um_password').val() == $('#passwordCheck').val() &&
			$('#um_password').val() != ''
		) {
			check = true;
			$('#password').removeClass('hide');
			$('#password').removeClass('error');
			$('#password').addClass('success');
			$('#password').text('* 사용하실 수 있는 비밀번호 입니다.');
		} else if (
			$('#um_password').val() != $('#passwordCheck').val() &&
			$('#um_password').val() != ''
		) {
			check = false;
			$('#password').removeClass('hide');
			$('#password').text('* 비밀번호가 서로 다릅니다.');
			$('#passwordCheck').focus();
		}
	});
	$('#um_nickname').focusout(function() {
		if ($('#um_nickname').val() == nick) return;
		var nickReg = RegExp(/^[a-zA-Z0-9가-힣]{2,5}$/);
		if (!nickReg.test($('#um_nickname').val())) {
			check = false;
			$('#nickname').removeClass('hide');
			$('#nickname').text(
				'* 형식에 맞지 않습니다. 영문, 숫자, 한글 2 ~ 5글자 입력해 주시기 바랍니다.',
			);
		} else {
			$('#message').val('findByNickname');
			$.ajax({
				type: 'post',
				url: '/rest_area/member/user/lookUp',
				data: $('#modiyfyForm').serialize(),
				success: function(data, status) {
					if (status == 'success') {
						if (data.status == 'OK') {
							if (data.count == 0) {
								check = true;
								$('#nickname').removeClass('hide');
								$('#nickname').removeClass('error');
								$('#nickname').addClass('success');
								$('#nickname').text('* 사용할수 있는 닉네임 입니다.');
							} else if (data.count == 1) {
								check = false;
								$('#nickname').removeClass('hide');
								$('#nickname').addClass('error');
								$('#nickname').removeClass('success');
								$('#nickname').text('* 사용중인 닉네임 입니다.');
							} else {
								check = false;
								$('#nickname').removeClass('hide');
								$('#nickname').addClass('error');
								$('#nickname').removeClass('success');
								$('#nickname').text('* ' + data.message);
							}
						} else {
							alert('에러');
						}
					}
				},
				error: function() {
					alert('실패');
				},
			});
		}
	});
	$('#nickBtn').click(function() {
		if ($('#nickBtn').text() == '변경') {
			$('#um_nickname').attr('disabled', false);
			$('#nickBtn').text('취소');
			$('#nickBtn').removeClass('btn-primary');
			$('#nickBtn').addClass('btn-danger');
		} else {
			$('#nickname').addClass('hide');
			$('#um_nickname').attr('disabled', true);
			$('#nickBtn').text('변경');
			$('#nickBtn').addClass('btn-primary');
			$('#nickBtn').removeClass('btn-danger');
			$('#um_nickname').val(nick);
		}
	});
	$('#changeBtn').click(function() {
		var message = null;
		if (
			$('#um_nickname').val() == nick &&
			!$('#um_nickname').attr('disabled')
		) {
			alert('변경전 닉네임과 동일합니다.');
			return;
		}
		if (!check) {
			alert('변경항목을 다시 확인해 주세요');
			return;
		}
		if ($('#um_nickname').val() != '' && $('#um_password').val() == '') {
			message = 'updateNick';
			$('#message').val(message);
		} else if (
			$('#um_password').val() != '' &&
			$('#passwordCheck').val() != '' &&
			$('#um_nickname').attr('disabled') == 'disabled'
		) {
			message = 'updatePw';
			$('#message').val(message);
		} else if ($('#um_nickname').val() == '' && $('#um_password').val() == '') {
			return;
		} else {
			message = 'updatePwAndNick';
			$('#message').val(message);
		}
		$.ajax({
			url: '/rest_area/member/user/modify',
			type: 'put',
			data: $('#modiyfyForm').serialize(),
			success: function(data, status) {
				if (status == 'success') {
					if (data.status == 'OK') {
						if (message == 'updatePw' || message == 'updatePwAndNick') {
							alert('정보가 변경되었습니다.\n다시 로그인 해주세요.');
							$('#logoutForm').submit();
							return;
						}
						$('#password').addClass('hide');
						$('#nickname').addClass('hide');
						$('#um_nickname').attr('disabled', true);
						$('#nickBtn').text('변경');
						$('#nickBtn').addClass('btn-primary');
						$('#nickBtn').removeClass('btn-danger');
						$('#passwordCheck').val('');
						$('#um_password').val('');
						nick = data.data[0].um_nickname;
						$('#um_nickname').val(nick);
						$('.welcomes').text(nick);
						alert('정보가 변경되었습니다.');
					}
				}
			},
		});
	});
	$('#deleteBtn').click(function() {
		if (!confirm('회원탈퇴를 진행하시겠습니까?')) {
			return;
		}

		$('#message').val('findByUsername');

		$.ajax({
			url: '/rest_area/member/user/delete',
			type: 'delete',
			data: $('#modiyfyForm').serialize(),
			success: function(data, status) {
				if (status == 'success') {
					if (data.status == 'OK') {
						if (data.count == 1) {
							alert('탈퇴 되었습니다.\n그동안 이용해주셔서 감사합니다.');
							$('#logoutForm').submit();
						}
					}
				}
			},
		});
	});
});
