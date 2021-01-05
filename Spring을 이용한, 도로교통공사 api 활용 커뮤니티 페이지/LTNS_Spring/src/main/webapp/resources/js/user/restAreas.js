var page = 1;
var pageRows = 10;
var viewItem = undefined;
var uid = $('#uid').val();
var type = location.href.substring(location.href.lastIndexOf('/'));
$(document).ready(function () {
  loadPage(1);
  $('#removeBtn').click(function () {
	console.log($('#listForm').serialize());
    var uids = [];
    $('#listForm input[name=strCode]').each(function () {
      if ($(this).is(':checked')) {
        uids.push($(this).val());
      }
    });

    if (uids.length == 0) {
      alert('즐겨찾기 해제할 항목을 체크해주세요');
	  return;
    } else {
      if (!confirm(uids.length + '개의 글을 삭제하시겠습니까?')) return;
    }
    $.ajax({
      url: '/rest_area/member/user/list',
      type: 'delete',
      data: $('#listForm').serialize(),
      success: function (data) {
        if (data.status == 'OK') {
          alert('DELETE 성공 :' + data.count + '개');
          loadPage(page);
        } else {
          alert('DELETE 실패 ' + data.message);
          return;
        }
      },
    });
  });
});
function loadPage(page) {
  $.ajax({
    url:
      '/rest_area/member/user/list' +
      type +
      '/' +
      uid +
      '/' +
      page +
      '/' +
      pageRows,
    type: 'GET',
    cache: false,
    success: function (data, status) {
      if (status == 'success') {
        $('#jsonList').html('');
        var result = '';
        for (i = 0; i < data.data.length; i++) {
          result += '<tr>';
          result +=
            '<td class="text-center"><input type="checkbox" name="strCode" value="' +
            data.data[i].ra_code +
            '" /></td>';
          result += '<td>' + data.data[i].ra_name + '</td>';
          result += '<td>' + data.data[i].RA_ROUTENAME + '</td>';
          result += '<td>' + data.data[i].RA_DESTINATION + '</td>';
          result += '</tr>';
        }
        $('#jsonList').html(result);
        var pagination = buildPagination(
          data.writePages,
          Math.ceil(data.totalCnt / data.numOfRows),
          data.pageNo,
        );
        $('#pagination').html(pagination);
      }
    },
  });
}

function buildPagination(writePages, totalPage, curPage) {
  var str = '';
  var start_page = parseInt((curPage - 1) / writePages) * writePages + 1;
  var end_page = start_page + writePages - 1;
  if (end_page >= totalPage) {
    end_page = totalPage;
  }

  //■ << 표시 여부
  if (curPage > 1) {
    str +=
      "<li><a onclick='loadPage(" +
      1 +
      ")' class='tooltip-top' title='처음'><i class='fas fa-angle-double-left'></i></a></li>\n";
  }

  //■  < 표시 여부
  if (start_page > 1)
    str +=
      "<li><a onclick='loadPage(" +
      (start_page - 1) +
      ")' class='tooltip-top' title='이전'><i class='fas fa-angle-left'></i></a></li>\n";

  //■  페이징 안의 '숫자' 표시
  if (totalPage > 1) {
    for (var k = start_page; k <= end_page; k++) {
      if (curPage != k)
        str += "<li><a onclick='loadPage(" + k + ")'>" + k + '</a></li>\n';
      else
        str +=
          "<li><a class='active tooltip-top' title='현재페이지'>" +
          k +
          '</a></li>\n';
    }
  }

  //■ > 표시
  if (totalPage > end_page) {
    str +=
      "<li><a onclick='loadPage(" +
      (end_page + 1) +
      ")' class='tooltip-top' title='다음'><i class='fas fa-angle-right'></i></a></li>\n";
  }

  //■ >> 표시
  if (curPage < totalPage) {
    str +=
      "<li><a onclick='loadPage(" +
      totalPage +
      ")' class='tooltip-top' title='맨끝'><i class='fas fa-angle-double-right'></i></a></li>\n";
  }

  return str;
}
