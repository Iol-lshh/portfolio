var check = false;
$(document).ready(function () {
  $('#pwBtn').click(function () {
    $('#message').val('checkId');
    $.ajax({
      type: 'post',
      url: '/rest_area/auth/user/joinLookup',
      data: $('#lookuppw').serialize(),
      success: function (data, status) {
        if (status == 'success') {
          if (data.count == 1) {
            $('#changePassword').removeClass('hide');
            $('#um_username').attr('disabled', true);
            $('#pwBtn').attr('disabled', true);
          } else {
            alert('존재하지 않는 아이디 입니다.');
          }
        }
      },
    });
  });
  $('#um_password').focusout(function () {
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
      check = true;
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
  $('#passwordCheck').focusout(function () {
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
  $('#pwChangeBtn').click(function () {
    if (!check) {
      alert('변경항목을 다시 확인해 주세요');
      return;
    }
    $('#um_username').attr('disabled', false);
    $('#message').val('updatePw');
    $.ajax({
      type: 'put',
      url: '/rest_area/auth/user/update',
      data: $('#lookuppw').serialize(),
      success: function (data, status) {
        if (status == 'success') {
          if (data.status == 'OK') {
            alert('비밀번호가 성공적으로 변경되었습니다.');
            location.href = '/rest_area/auth/user/login';
          } else {
            alert(data.message);
          }
        }
      },
    });
  });
});
