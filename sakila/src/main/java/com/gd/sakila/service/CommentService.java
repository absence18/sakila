package com.gd.sakila.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gd.sakila.mapper.CommentMapper;
import com.gd.sakila.vo.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentService {
	@Autowired CommentMapper commentMapper;
	
	public int addComment(Comment comment) {
		//서비스에서 받은 매개변수디버깅
		log.debug("service◆◆◆◆◆◆ addComment parm comment="+ comment);
		//쿼리 실행
		int addRow = commentMapper.insertComment(comment);
		//쿼리 실행결과 디버깅
		log.debug("service◆◆◆◆◆◆ addComment addRow="+ addRow);
		//리턴
		return addRow;
	}
	public int removeComment(int commentId) {
		//서비스에서 받은 매개변수디버깅
		log.debug("service◆◆◆◆◆◆ deleteComment parm commentId="+ commentId);
		//쿼리 실행
		int removeRow = commentMapper.deleteCommentByCommentId(commentId);
		//쿼리 실행결과 디버깅
		log.debug("service◆◆◆◆◆◆ removeComment removeRow="+ removeRow);
		//리턴
		return removeRow;
	}
}