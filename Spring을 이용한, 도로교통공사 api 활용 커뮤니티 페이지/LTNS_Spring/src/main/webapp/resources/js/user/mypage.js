/**
 *
 */

$(document).ready(function () {
  $.ajax({
    type: 'post',
    url: '/rest_area/member/user/mypage',
    data: $('#mypageForm').serialize(),
    success: function (data) {
      var result = '';
      var content = '';
      if (data.dataList.foods) {
        for (i = 0; i < 5; i++) {
          if (data.dataList.foods[i]) {
            if (data.dataList.foods[i].fm_name.length > 15) {
              content = data.dataList.foods[i].fm_name.substring(12) + '...';
            } else {
              content = data.dataList.foods[i].fm_name;
            }
            result += '<li class="list-group-item">' + content + '</li>';
          } else {
            content = '등록된 즐겨찾기가 없습니다.';
            result += '<li class="list-group-item text-muted">' + content + '</li>';
          }
        }
        if (data.dataList.foods.length > 5) {
          content = '<a href="/rest_area/member/user/foods">더보기</a>';
          result +=
            '<li class="list-group-item text-center">' + content + '</li>';
        } else {
          content = '<a class="text-muted">더보기</a>';
          result +=
            '<li class="list-group-item text-center">' + content + '</li>';
        }
        $('#likeFood').html(result);
      } else {
        content = '등록된 즐겨찾기가 없습니다.';
        for (i = 0; i < 5; i++) {
          result += '<li class="list-group-item text-muted">' + content + '</li>';
        }
        content = '<a class="text-muted">더보기</a>';
        result +=
          '<li class="list-group-item text-center">' + content + '</li>';
        $('#likeFood').html(result);
      }
      result = '';
      content = '';
      if (data.dataList.gasStations) {
        for (i = 0; i < 5; i++) {
          if (data.dataList.gasStations[i]) {
            if (data.dataList.gasStations[i].gs_name.length > 15) {
              content =
                data.dataList.gasStations[i].gs_name.substring(12) + '...';
            } else {
              content = data.dataList.gasStations[i].gs_name;
            }
            result += '<li class="list-group-item">' + content + '</li>';
          } else {
            content = '등록된 즐겨찾기가 없습니다.';
            result += '<li class="list-group-item text-muted">' + content + '</li>';
          }
        }
        if (data.dataList.gasStations.length > 5) {
          content = '<a href="/rest_area/member/user/gasStations">더보기</a>';
          result +=
            '<li class="list-group-item text-center">' + content + '</li>';
        } else {
          content = '<a class="text-muted">더보기</a>';
          result +=
            '<li class="list-group-item text-center">' + content + '</li>';
        }
        $('#likeGas').html(result);
      } else {
        content = '등록된 즐겨찾기가 없습니다.';
        for (i = 0; i < 5; i++) {
          result += '<li class="list-group-item text-muted">' + content + '</li>';
        }
        content = '<a class="text-muted">더보기</a>';
        result +=
          '<li class="list-group-item text-center">' + content + '</li>';
        $('#likeGas').html(result);
      }
      result = '';
      content = '';
      if (data.dataList.restAreas) {
        for (i = 0; i < 5; i++) {
          if (data.dataList.restAreas[i]) {
            if (data.dataList.restAreas[i].ra_name.length > 15) {
              content =
                data.dataList.restAreas[i].ra_name.substring(12) + '...';
            } else {
              content = data.dataList.restAreas[i].ra_name;
            }
            result += '<li class="list-group-item">' + content + '</li>';
          } else {
            content = '등록된 즐겨찾기가 없습니다.';
            result += '<li class="list-group-item text-muted">' + content + '</li>';
          }
        }
        if (data.dataList.restAreas.length > 5) {
          content = '<a href="/rest_area/member/user/restAreas">더보기</a>';
          result +=
            '<li class="list-group-item text-center">' + content + '</li>';
        } else {
          content = '<a class="text-muted">더보기</a>';
          result +=
            '<li class="list-group-item text-center">' + content + '</li>';
        }
        $('#likeRest').html(result);
      } else {
        content = '등록된 즐겨찾기가 없습니다.';
        for (i = 0; i < 5; i++) {
          result += '<li class="list-group-item text-muted">' + content + '</li>';
        }
        content = '<a class="text-muted">더보기</a>';
        result +=
          '<li class="list-group-item text-center">' + content + '</li>';
        $('#likeRest').html(result);
      }
      result = '';
      content = '';
      if (data.dataList.likePosts) {
        for (i = 0; i < 5; i++) {
          if (data.dataList.likePosts[i]) {
            if (data.dataList.likePosts[i].post_title.length > 15) {
              content =
                data.dataList.likePosts[i].post_title.substring(12) + '...';
            } else {
              content = data.dataList.likePosts[i].post_title;
            }
            result += '<li class="list-group-item">' + content + '</li>';
          } else {
            content = '등록된 즐겨찾기가 없습니다.';
            result += '<li class="list-group-item text-muted">' + content + '</li>';
          }
        }
        if (data.dataList.likePosts.length > 5) {
          content = '<a href="/rest_area/member/user/likePosts">더보기</a>';
          result +=
            '<li class="list-group-item text-center">' + content + '</li>';
        } else {
          content = '<a class="text-muted">더보기</a>';
          result +=
            '<li class="list-group-item text-center">' + content + '</li>';
        }
        $('#likePost').html(result);
      } else {
        content = '등록된 즐겨찾기가 없습니다.';
        for (i = 0; i < 5; i++) {
          result += '<li class="list-group-item text-muted">' + content + '</li>';
        }
        content = '<a class="text-muted">더보기</a>';
        result +=
          '<li class="list-group-item text-center">' + content + '</li>';
        $('#likePost').html(result);
      }
      result = '';
      content = '';
      if (data.dataList.posts) {
        for (i = 0; i < 5; i++) {
          if (data.dataList.posts[i]) {
            if (data.dataList.posts[i].post_title.length > 15) {
              content = data.dataList.posts[i].post_title.substring(12) + '...';
            } else {
              content = data.dataList.posts[i].post_title;
            }
            result += '<li class="list-group-item">' + content + '</li>';
          } else {
            content = '작성된 게시물이 없습니다.';
            result += '<li class="list-group-item text-muted">' + content + '</li>';
          }
        }
        if (data.dataList.posts.length > 5) {
          content = '<a href="/rest_area/member/user/posts">더보기</a>';
          result +=
            '<li class="list-group-item text-center">' + content + '</li>';
        } else {
          content = '<a class="text-muted">더보기</a>';
          result +=
            '<li class="list-group-item text-center">' + content + '</li>';
        }
        $('#posts').html(result);
      } else {
        content = '작성된 게시물이 없습니다.';
        for (i = 0; i < 5; i++) {
          result += '<li class="list-group-item text-muted">' + content + '</li>';
        }
        content = '<a class="text-muted">더보기</a>';
        result +=
          '<li class="list-group-item text-center">' + content + '</li>';
        $('#posts').html(result);
      }
      result = '';
      content = '';
      if (data.dataList.comments) {
        for (i = 0; i < 5; i++) {
          if (data.dataList.comments[i]) {
            if (data.dataList.comments[i].comment_contents.length > 15) {
              content =
                data.dataList.comments[i].comment_contents.substring(12) +
                '...';
            } else {
              content = data.dataList.comments[i].comment_contents;
            }
            result += '<li class="list-group-item">' + content + '</li>';
          } else {
            content = '등록된 댓글이 없습니다.';
            result += '<li class="list-group-item text-muted">' + content + '</li>';
          }
        }
        if (data.dataList.comments.length > 5) {
          content = '<a href="/rest_area/member/user/comments">더보기</a>';
          result +=
            '<li class="list-group-item text-center">' + content + '</li>';
        } else {
          content = '<a class="text-muted">더보기</a>';
          result +=
            '<li class="list-group-item text-center">' + content + '</li>';
        }
        $('#comments').html(result);
      } else {
        content = '등록된 댓글이 없습니다.';
        for (i = 0; i < 5; i++) {
          result += '<li class="list-group-item text-muted">' + content + '</li>';
        }
        content = '<a class="text-muted">더보기</a>';
        result +=
          '<li class="list-group-item text-center">' + content + '</li>';
        $('#comments').html(result);
      }
    },
  });
});


