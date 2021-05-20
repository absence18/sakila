package com.gd.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CommentService;
import com.gd.sakila.vo.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class CommentController {
	@Autowired CommentService commentService;
	
	//댓글입력폼
	@PostMapping("/addComment")
	public String addComment(Comment comment) {
		//컨트롤러 매개변수 디버깅
		log.debug("controller◆◆◆◆◆◆ addComment parm comment=" + comment);
		//서비스에 추가 요청
		int addRow = commentService.addComment(comment);
		//서비스 실행 결과 디버깅
		log.debug("controller◆◆◆◆◆◆addComment addRow=" + addRow);
		//리다이렉트
		return "redirect:/admin/getBoardOne?boardId=" + comment.getBoardId();
	}
	
	@GetMapping("/removeComment")
	public String removeComment(@RequestParam(value = "commentId", required = true)int commentId,
								@RequestParam(value = "boardId", required = true)int boardId) {
		//컨트롤러 매개변수 디버깅
		log.debug("controller◆◆◆◆◆◆removeComment parm commentId=" + commentId);
		log.debug("controller◆◆◆◆◆◆removeComment parm boardId=" + boardId);
		//서비스에 삭제 요청
		int removeRow = commentService.removeComment(commentId);
		//서비스 실행 결과 디버깅
		log.debug("controller◆◆◆◆◆◆removeComment removeRow=" + removeRow);
		//리다이렉트
		return "redirect:/admin/getBoardOne?boardId=" + boardId;
	}
}