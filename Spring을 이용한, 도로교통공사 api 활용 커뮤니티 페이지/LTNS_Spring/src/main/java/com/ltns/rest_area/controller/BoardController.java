package com.ltns.rest_area.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ltns.rest_area.domain.AjaxList;
import com.ltns.rest_area.domain.AjaxResult;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.VO;
import com.ltns.rest_area.domain.post.CommentVO;
import com.ltns.rest_area.domain.post.LikeVO;
import com.ltns.rest_area.domain.post.PostVO;
import com.ltns.rest_area.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	//휴게소 뷰 불러오기
	@GetMapping("/{idCode}")
	public AjaxResult view(@PathVariable String idCode) {
		AjaxResult result=new AjaxResult();
		
		result.setObj(boardService.view(idCode));
		result.setStatus("OK");
		
		return result;
	}
	
	//포스트 불러오기
	@PostMapping("/post/view/{post_id}")
	public AjaxResult post_view(@PathVariable int post_id) {
		AjaxResult result = new AjaxResult();
		DTO dto = boardService.postView(post_id);
		result.setObj(dto);
		return result;
	}
	
	//기본 포스트 리스트(안씀 ViewDTO가 다 가져다주고있음..)
	@PostMapping("/post/list/{ra_code}/{orderBy}")
	public AjaxList post_list(@PathVariable String ra_code,@PathVariable String orderBy) {
		AjaxList result=new AjaxList();
		
		int numOfRows=10;
		int lastRow=0;
		int pageNo=1;
		result=post_list(ra_code, orderBy, numOfRows, lastRow, pageNo);
		
		return result;
	}
	
	//추가 포스트 리스트
	@PostMapping("/post/list/{ra_code}/{orderBy}/{numOfRows}/{lastRow}/{pageNo}")
	public AjaxList post_list(@PathVariable String ra_code,@PathVariable String orderBy, @PathVariable int numOfRows, @PathVariable int lastRow, @PathVariable int pageNo) {
		
		int fromRow=lastRow+1;
		
		VO vo =new PostVO().builder().ra_code(ra_code).orderBy(orderBy).numOfRows(numOfRows).fromRow(fromRow).pageNo(pageNo).build();
		AjaxList result= boardService.selectPostList(vo);
		
		return result;
	}
	
	//기본 댓글 리스트
	@PostMapping("/comment/list/{post_id}/{orderBy}")
	public AjaxList comment_list(@PathVariable int post_id,@PathVariable String orderBy) {
		AjaxList result=new AjaxList();
		
		int numOfRows=10;
		int lastRow=0;
		
		result=comment_list(post_id, orderBy, numOfRows, lastRow);
		
		
		return result;
	}
	
	//추가 댓글 리스트
	@PostMapping("/comment/list/{post_id}/{orderBy}/{numOfRows}/{lastRow}")
	public AjaxList comment_list(@PathVariable int post_id,@PathVariable String orderBy, @PathVariable int numOfRows, @PathVariable int lastRow) {
		
		int fromRow=lastRow+1;
		
		VO vo = new CommentVO().builder().post_id(post_id).orderBy(orderBy).numOfRows(numOfRows).fromRow(fromRow).build();
		AjaxList result= boardService.selectCommentList(vo);
		
		return result;
	}
	
	//새글 집어넣기
	@PutMapping("/post")
	public AjaxResult post_insert(@RequestBody PostVO vo) {	//PostVO에 내용을 넣을 것
		System.out.println("insert Post : "+vo);//확인
		AjaxResult result = boardService.insertPost(vo);
		return result;
	}
	
	//글 업데이트
	@PatchMapping("/post")
	public AjaxResult post_update(@RequestBody PostVO vo) {
		AjaxResult result=boardService.updatePost(vo);
		
		return result;
	}
	
	//글 삭제
	@DeleteMapping("/post")
	public AjaxResult post_delete(@RequestBody PostVO vo) {
		AjaxResult result=boardService.deletePost(vo);

		return result;
	}
	
	@PutMapping("/comment")
	public AjaxResult comment_insert(@RequestBody CommentVO vo) {	//CommentVO에 내용을 넣을 것
		AjaxResult result=boardService.insertComment(vo);
		
		return result;
	}
	
	@PatchMapping("/comment")
	public AjaxResult comment_update(@RequestBody CommentVO vo) {
		AjaxResult result=boardService.updateComment(vo);
		
		return result;
	}
	
	@DeleteMapping("/comment")
	public AjaxResult comment_delete(@RequestBody CommentVO vo) {
		AjaxResult result=boardService.deleteComment(vo);

		return result;
	}
	
	//like 버튼 클릭 처리
	@PatchMapping("/like")
	public AjaxResult like_process(@RequestBody LikeVO vo) {
		AjaxResult result=boardService.likeProcess(vo);
		
		return result;
	}
	
	//like 확인
	@PostMapping("/like")
	public AjaxResult like_chk(@RequestBody LikeVO vo) {
		AjaxResult result=boardService.likeCheck(vo);
		
		return result;
	}
}
