<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="comment mb-5">
  <div class="comment__insertForm">
    <form id="commentInsert">
      <div class="card text-center">
        <div class="card-body">
          <textarea
            id="commentArea"
            name="content"
            class="form-control comment_area"
            placeholder="저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시물은 이용약관 및 관련 법률에 의해 제재를 받을 수 있습니다. 건전한 토론문화와 양질의 댓글 문화를 위해, 타인에게 불쾌감을 주는 욕설 또는 특정 계층/민족, 종교 등을 비하하는 단어들은 표시가 제한됩니다."
          ></textarea>
          <span id="textLength" class="text-muted">0</span>
          <span class="text-muted"> / 300</span>
        </div>
        <div class="card-footer text-left bg-white">
          <span class="text-primary"
            >※ 이댓글에 대한 법적 책임은 작성자에게 귀속됩니다.</span
          >
          <button class="btn btn-outline-primary float-right" type="submit">
            등록
          </button>
          <div class="clearfix"></div>
        </div>
      </div>
    </form>
  </div>

  <div class="comment__actionBtn">
    <div id="commentBtn" class="btn float-right">
      <span id="commentBtnText">펼치기</span>
      <i id="downArrow" class="fas fa-angle-down"></i>
    </div>
    <div class="clearfix"></div>
  </div>
  <div class="comment__list">
  </div>
</div>
<div id="declarationModal" class="modal" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">신고하기 양식</h5>
        <button type="button" class="close" data-dismiss="modal">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="declarationForm">
          <input
            type="hidden"
            name="declarationCommentId"
            id="declarationCommentId"
          />
          <table class="table table-bordered">
            <tr>
              <th class="align-middle text-center">신고 날짜</th>
              <td>
                <input
                  type="hidden"
                  id="declarationDate"
                  name="declarationDate"
                />
                <input
                  type="text"
                  id="copyDate"
                  class="form-control"
                  disabled
                />
              </td>
            </tr>
            <tr>
              <th class="align-middle text-center">신고자 ID</th>
              <td>
                <input
                  type="hidden"
                  id="declarationNickname"
                  name="declarationNickname"
                />
                <input
                  type="text"
                  id="copyNickname"
                  class="form-control"
                  disabled
                />
              </td>
            </tr>
            <tr>
              <th class="align-middle text-center">신고 제목</th>
              <td>
                <input
                  type="text"
                  id="declarationTitle"
                  name="declarationTitle"
                  class="form-control"
                />
              </td>
            </tr>
            <tr>
              <th class="align-middle text-center">신고 내용</th>
              <td>
                <textarea
                  id="declarationContent"
                  name="declarationContent"
                  class="form-control"
                  style="height: 15rem; resize: none"
                ></textarea>
              </td>
            </tr>
          </table>
        </form>
      <span class="text-danger">
      * 신고까지의 약간의 소요시간이 필요합니다. <br />
      &nbsp;&nbsp;신고하기 버튼을 누르고 팝업메시지가 나올때까지 <br />
      &nbsp;&nbsp;잠시만 기다려주세요.
      </span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        <button id="declarationSubmit" type="button" class="btn btn-danger">신고하기</button>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../../../loading/component/emailLoading.jsp"></jsp:include>
<!-- 
<div
  class="modal fade"
  id="staticBackdrop"
  data-backdrop="static"
  data-keyboard="false"
  tabindex="-1"
  aria-labelledby="staticBackdropLabel"
  aria-hidden="true"
>
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-body">
        <div class="loader">
          <div class="dot"></div>
          <div class="dot"></div>
          <div class="dot"></div>
          <div class="dot"></div>
          <div class="dot"></div>
        </div>
      </div>
    </div>
  </div>
</div>
 -->