let initBody = {
  userId: 2,
  postId: null,
  page: 1,
  commentId: null,
  comment: null,
};

let userProfile = null;
let grade = null;
let myNick = null;

let body = {
  method: 'POST', // *GET, POST, PUT, DELETE, etc.
  mode: 'cors', // no-cors, cors, *same-origin
  cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
  credentials: 'same-origin', // include, *same-origin, omit
  headers: {
    'Content-Type': 'application/json',
  },
  redirect: 'follow', // manual, *follow, error
  referrer: 'no-referrer', // no-referrer, *client
  body: null, // body data type must match "Content-Type" header
};

let getInfiniteComment = null;

const loadDataEvent = () => {
  $('.inserBtn').each(function () {
    $(this).on('click', handleChangeContent);
  });

  $('.commentModify').on('keyup', hadleInputChange);
  $('.commentModify').on('keydown', hadleInputChange);

  $('.trashBtn').each(function () {
    $(this).on('click', handleRemoveAjax);
  });

  $('.cancelBtn').each(function () {
    $(this).on('click', handleCancel);
  });

  $('.declarationBtn').each(function() {
	$(this).on('click', handleDeclaration);
  })

  $('.modifyDeleteform').each(function () {
    $(this).on('submit', handleUpdateAjax);
  });
 
};

const createDataNullMessage = () => {
  const none = $(
    '<div id="zeroData">등록된 댓글이 없습니다...<i class="far fa-comment-dots"></i></div>'
  );
  $('.comment__list').html(none);
};

const createComment = (datas) => {
  let comment = '';
  const modyfyIcon = '<i class="fas fa-pencil-alt ml-5 inserBtn"></i>';
  const deleteIncon = '<i class="fas fa-trash ml-3 trashBtn"></i>';
  const declarationIncon = '<i class="fas fa-bullhorn ml-5 text-danger declarationBtn"></i>';
    
  let form = '';

  datas.forEach((data) => {
    comment += '<div class="commentContainer">';
    if (initBody.userId === parseInt(data.writerId)) {
      comment += '<div id="alert" class="alert alert-warning" role="alert">';
    } else {
      comment += '<div id="alert" class="alert alert-light" role="alert">';
    }
    comment += '<div class="alertBox">';
    comment += `<span class="comment_content">${data.commentContents}</span>`;
    comment += '<div>';
    comment += `<span class="coment__commendId hide">${data.commentId}</span>`;
    comment += `<span class="coment__writer">${data.nickName}</span>`;
    comment += '&nbsp;&nbsp;';
    comment += `<span class="coment_regdate">${data.regdate}</span>`; 
    if (initBody.userId === parseInt(data.writerId)) {
	  comment += modyfyIcon;
	  comment += deleteIncon;
	} else if(grade === 'admin') comment += deleteIncon;
	
	if(initBody.userId !== parseInt(data.writerId) && userProfile !== 0 && grade !== 'admin' && data.grade !== 'admin') comment += declarationIncon;

    comment += '</div>';
    comment += '</div>';

    if (initBody.userId === parseInt(data.writerId) || grade === 'admin') {
      form = '<form class="modifyDeleteform hide">';
      form += `<input type="hidden" class="comment_id" name="comment_id" data-id="${data.commentId}" />`;
      form += '<div class="input-group">';
      form +=
        '<input type="text" class="form-control commentModify" placeholder="수정할 댓글을 입력해주세요." />';
      form += '<div class="input-group-append">';
      form += '<button class="btn btn-primary" type="submit">수정</button>';
      form += '</div>';
      form += '<div class="input-group-append">';
      form +=
        '<button class="btn btn-secondary cancelBtn" type="button">취소</button>';
      form += '</div>';
      form += '</div>';
      form += '</form>';
      comment += form;
    }
    comment += '</div>';
    comment += '</div>';
  });
  return comment;
};

const jsShowModal = (messageType, messageContent) => {
  $('#modalTitle').text(messageType);
  $('#modalContent').text(messageContent);
  if (messageType === '오류 메시지') $('#messageModal').addClass('bg-warning');
  else $('#messageModal').addClass('bg-success');
  $('#modal').modal('show');
};


const hadleInputChange = (e) => {
  const value = $(e.target).val();
  if (value.length >= 300) {
    $(e.target).val(value.substring(0, 300));
    return;
  }
};

const handleChangeContent = (event) => {
  if (getInfiniteComment != null) clearInterval(getInfiniteComment);
  const parent = $(event.target).parent().parent().parent();
  parent.find('.alertBox').hide();
  parent.find('.commentModify').val(parent.find('.comment_content').text());
  parent.find('.modifyDeleteform').removeClass('hide');
  parent.find('.commentModify').focus();
};

const handleDeclarationSubmit = (e) => {
  e.preventDefault();
  if (
    $('#declarationTitle').val() === null ||
    $('#declarationTitle').val() === '' ||
    $('#declarationContent').val() === null ||
    $('#declarationContent').val() === ''
  ) {
    $('#declarationModal').modal('hide');
    jsShowModal('오류 메시지', '신고 제목과 내용을 모두 입력해 주세요.');
    return;
  }
  $('#declarationModal').modal('hide');
  $('#declarationTitle').val('');
  $('#declarationContent').val('');
  $('#staticBackdrop').modal('show');
  const form = new FormData(document.getElementById('declarationForm'));
  const formData = Object.fromEntries(form);
  fetch('commentDeclaration.ajax', {
    method: 'POST', // *GET, POST, PUT, DELETE, etc.
    mode: 'cors', // no-cors, cors, *same-origin
    cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'same-origin', // include, *same-origin, omit
    headers: {
      'Content-Type': 'application/json',
    },
    redirect: 'follow', // manual, *follow, error
    referrer: 'no-referrer', // no-referrer, *client
    body: JSON.stringify(formData), // body data type must match "Content-Type" header
  }) //
    .then((response) => response.text()) //
    .then((text) => {
      $('#staticBackdrop').modal('hide');
      if (text === 'true') jsShowModal('성공 메시지', '신고가 접수되었습니다.');
      else
        jsShowModal(
          '오류 메시지',
          '일시적인 서버 오류로 인해 신고처리가 되지 않았습니다.'
        );
    });
};


const handleDeclarationClick = () => {
  $('#declarationForm').submit();
}

const handleDeclaration = (e) => {
  const date = (new Date().toLocaleDateString()) + " " + (new Date().toLocaleTimeString());
  $('#declarationCommentId').val($(e.target).parent().find('.coment__commendId').text());
  $('#declarationDate').val(date);
  $('#copyDate').val(date);
  $('#declarationNickname').val(myNick);
  $('#copyNickname').val(myNick);
  $('#declarationModal').modal('show');
}

const handleCancel = (event) => {
  const parent = $(event.target).parent().parent().parent().parent();
  parent.find('.modifyDeleteform').addClass('hide');
  parent.find('.alertBox').show();
  createInfinite();
};

const hadleTextArea = (e) => {
  const value = $(e.target).val();
  if (value.length >= 300) {
    $(e.target).val(value.substring(0, 300));
    $('#textLength').text(300);
    return;
  }
  $('#textLength').text(value.length);
};

const createViewMoreBtn = () => {
  let btn =
    '<div id="more" class="btn btn-outline-primary form-control">댓글 더보기</div>';
  $('.comment__list').append(btn);
  $('#more').on('click', function () {
    handleNextAjax();
  });
};

const handleUpdateAjax = (e) => {
  e.preventDefault();

  const commentId = $(e.target).find('.comment_id').attr('data-id');
  const commentValue = $(e.target).find('.commentModify').val();

  initBody.commentId = commentId;
  initBody.comment = commentValue;

  body.body = JSON.stringify(initBody);

  fetch('commentUpdate.ajax', body) //
    .then((response) => response.json()) //
    .then((json) => {
      $('.comment__list').html('');
      const comment = createComment(json.data);
      $('.comment__list').html(comment);
      if (json.count != $('.commentContainer').length) createViewMoreBtn();
      loadDataEvent();
      createInfinite();
    })
    .catch((e) => {
      console.error(e);
      location.reload();
    });
};

const handleRemoveAjax = (event) => {
  const parent = $(event.target).parent().parent().parent();
  const chk = confirm('정말로 삭제하시겠습니까?');
  if (!chk) return;

  initBody.commentId = parent.find('.comment_id').attr('data-id');

  body.body = JSON.stringify(initBody);
  fetch('commentRemove.ajax', body) //
    .then((response) => response.json()) //
    .then((json) => {
      $('.comment__list').html('');
      if (json.data === null) {
        createDataNullMessage();
        return;
      }
      const comment = createComment(json.data);
      $('.comment__list').html(comment);
      if (json.count != $('.commentContainer').length) createViewMoreBtn();
      loadDataEvent();
    })
    .catch((e) => {
      console.error(e);
      location.reload();
    });
};

const handleNextAjax = () => {
  initBody.page++;
  body.body = JSON.stringify(initBody);
  fetch('commentNext.ajax', body) //
    .then((response) => response.json()) //
    .then((json) => {
      const prev = $('.comment__list').html();
      $('.comment__list').html('');
      const comment = createComment(json.data);
      $('.comment__list').html(prev + comment);
      $('.comment__list').find('#more').remove();
      if (json.count != $('.commentContainer').length) createViewMoreBtn();
      loadDataEvent();
    })
    .catch((e) => {
      console.error(e);
      location.reload();
    });
};

const createInfinite = () => {
  getInfiniteComment = setInterval(() => {
    body.body = JSON.stringify(initBody);

    fetch('commentLoad.ajax', body) //
      .then((response) => response.json()) //
      .then((json) => {
        $('.comment__list').html('');
        if (json.data === null) {
          createDataNullMessage();
          return;
        }
        const comment = createComment(json.data);
        $('.comment__list').html(comment);
        if (json.count != $('.commentContainer').length) createViewMoreBtn();
        loadDataEvent();
      })
      .catch((e) => {
        console.error(e);
        location.reload();
      });
  }, 1000);
};

const handleLoadAjax = () => {
  $('#downArrow').toggleClass('active');

  if ($('#commentBtnText').text() === '닫기') {
    $('#commentBtnText').text('펼치기');
    $('.comment__list').slideToggle();
    if (getInfiniteComment != null) clearInterval(getInfiniteComment);
  } else {
    $('#commentBtnText').text('닫기');

    body.body = JSON.stringify(initBody);

    fetch('commentLoad.ajax', body) //
      .then((response) => response.json()) //
      .then((json) => {
        $('.comment__list').html('');
        if (json.data === null) {
          createDataNullMessage();
          $('.comment__list').slideToggle();
          return;
        }
        const comment = createComment(json.data);
        $('.comment__list').html(comment);
        if (json.count != $('.commentContainer').length) createViewMoreBtn();
        loadDataEvent();
        $('.comment__list').slideToggle();
        createInfinite();
      })
      .catch((e) => {
        console.error(e);
        location.reload();
      });
  }
};

const handleWriteAjax = (e) => {
  e.preventDefault();

  initBody.comment = $('#commentArea').val();

  body.body = JSON.stringify(initBody);
  fetch('commentWrite.ajax', body) //
    .then((response) => response.json()) //
    .then((json) => {
      $('.comment__list').html('');
      const comment = createComment(json.data);
      $('.comment__list').html(comment);
      if (json.count != $('.commentContainer').length) createViewMoreBtn();

      $('#textLength').text('0');
      $('#commentArea').val('');
      loadDataEvent();
    })
    .catch((e) => {
      console.error(e);
      location.reload();
    });
};

const checkUser = () => {
  if (userProfile != null && userProfile === 0) {
    $('#commentArea').attr('disabled', 'disabled');
    $('.card-footer').html(
      '댓글을 작성하려면 <a href="/LTNS_jsp_re/membermanage/loginmain.do">로그인</a> 해주세요.'
    );
  }
};

const commenInit = (uid, pid, profile, sessionGrade, nickname) => {
  initBody.userId = parseInt(uid);
  initBody.postId = parseInt(pid);
  userProfile = parseInt(profile);
  grade = sessionGrade;
  myNick = nickname;
  checkUser();
  $('.comment__list').hide();
  $('#commentInsert').on('submit', handleWriteAjax);
  $('#commentArea').on('keyup', hadleTextArea);
  $('#commentArea').on('keydown', hadleTextArea);
  $('#commentBtn').on('click', handleLoadAjax);
  $('#declarationSubmit').on('click', handleDeclarationClick);
  $('#declarationForm').on('submit', handleDeclarationSubmit);
};
