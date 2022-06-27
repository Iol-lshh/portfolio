$(document).ready(function () {
  $('#emainBtn').click(function () {
    $('#kind').val('findIdByUsername');
    $('#message').val('findByUsername');
    $.ajax({
      type: 'post',
      url: '/rest_area/auth/user/email',
      data: $('#lookupid').serialize(),
      success: function (data, status) {
        if (status == 'success') {
          if (data.status == 'OK') {
            alert(
              '이메일이 성공적으로 전송되었습니다.\n가입시 등록한 이메일을 확인해 주세요.',
            );
			location.href='/rest_area/auth/user/login';
          } else {
            alert(data.message);
          }
        }
      },
    });
  });
  $('#nickBtn').click(function () {
    $('#kind').val('findIdByNickname');
    $('#message').val('findByNickname');
    $.ajax({
      type: 'post',
      url: '/rest_area/auth/user/email',
      data: $('#lookupid').serialize(),
      success: function (data, status) {
        if (status == 'success') {
          if (data.status == 'OK') {
            alert(
              '이메일이 성공적으로 전송되었습니다.\n가입시 등록한 이메일을 확인해 주세요.',
            );
			location.href='/rest_area/auth/user/login';
          } else {
            alert(data.message);
          }
        }
      },
    });
  });
});
