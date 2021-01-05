/**
 *
 */

$(document).ready(function () {
  $('#emailSend').click(function () {
    if (!chk_Submit()) return;
    $('#cotents').hide();
    $('#staticBackdrop').modal('show');
    $('#message').val('findByUsername');
    $('#kind').val('question');
    $.ajax({
      type: 'post',
      url: '/rest_area/auth/user/email',
      data: $('#reportForm').serialize(),
      success: function (data, status) {
        if (status == 'success') {
          if (data.count == 1) {
            alert('문의가 성공적으로 접수 되었습니다.');
            history.go(-2);
          } else {
            alert(
              '일치하는 회원을 찾을수 없습니다.\n비회원은 문의 할 수 없습니다.',
            );
          }
        }
      },
    });
  });
});

function chk_Submit() {
  var frm = document.forms['reportForm'];
  var subject = frm['subjects'].value.trim();
  var artcle = frm['artcle'].value.trim();

  if (subject == '') {
    alert('제목 입력');
    frm['subjects'].focus();
    return false;
  }

  if (artcle == '') {
    alert('내용 입력');
    frm['artcle'].focus();
    return false;
  }

  var v = grecaptcha.getResponse();
  if (v.length == 0) {
    alert('Captcha 를 체크해주세요');
    return false;
  }

  return true;
}
