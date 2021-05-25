package com.gd.sakila.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gd.sakila.mapper.BoardMapper;
import com.gd.sakila.mapper.BoardfileMapper;
import com.gd.sakila.mapper.CommentMapper;
import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.BoardForm;
import com.gd.sakila.vo.Boardfile;
import com.gd.sakila.vo.Comment;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j //log객체 생성후 주입
@Service //스프링에서 서비스 스캔 뉴객체생성
@Transactional
public class BoardService {
	@Autowired BoardMapper boardMapper;
	@Autowired BoardfileMapper boardfileMapper;
	@Autowired CommentMapper commentMapper;
	
	// boardUpadte( 수정 액션 )
	public int modifyBoard(Board board) {
		log.debug("modifyBoard 에서 board : "+ board.toString());
		return boardMapper.updateBoard(board);
	}
	// 삭제 액션
		public int removeBoard(Board board) {
			log.debug("▶▶▶▶▶▶ removeBoard() param: "+ board);
			
			// 1) 게시글삭제 FK를 지정하지 않은 경우
			int boardRow = boardMapper.deleteBoard(board);
			if(boardRow == 0) {
				return 0;
			}
			
			// 2) 댓글삭제
			int commentRow = commentMapper.deleteCommentByBoardId(board.getBoardId());
			
			
			// 3) 물리적 파일 삭제(/resource/안에 파일)
			List<Boardfile> boardfileList = boardfileMapper.selectBoardfileByBoardId(board.getBoardId());
			if(boardfileList != null) {
				for(Boardfile f : boardfileList) {
					File temp = new File(""); // 프로젝트 폴더에 빈파일이 만들어진다
					String path = temp.getAbsolutePath(); // 프로젝트 폴더
					File file = new File(path+"\\src\\main\\webapp\\resource\\"+f.getBoardfileName());
					file.delete();
				}
			}
			
			// 4) 파일 테이블 행삭제
			int boardfileRow = boardfileMapper.deleteBoardfileByBoardId(board.getBoardId());
			
			return boardRow;
		}	
	//추가액션
	public void addBoard(BoardForm boardForm) {
		log.debug("BoardService▶▶▶▶▶▶ parm boardForm: "+boardForm);
		//boardForm --> board, boardfile
		//1.
		Board board = boardForm.getBoard();
		log.debug("BoardService▶▶▶▶▶▶ board: "+board);
		boardMapper.insertBoard(board); //입력시 만들어진 키값을 리턴받아야한다. 근데 리턴 받을순 없으니까 전달해준 매개변수(참조값)에 데이터 추가
		log.debug("BoardService▶▶▶▶▶▶ board: "+board); //boardId 값 추가됨.
		
		//2.
		List<MultipartFile> list = boardForm.getBoardfile();
		if(list != null) {
			for(MultipartFile f : list) {
				Boardfile boardfile = new Boardfile();
				boardfile.setBoardId(board.getBoardId());
				//test.txt -> (newname).txt 확장자는 유지돼야함.
				String originalFileName = f.getOriginalFilename();
				log.debug("BoardService▶▶▶▶▶▶ originalFileName:" + originalFileName);
				int p = originalFileName.lastIndexOf(".");
				String ext = originalFileName.substring(p).toLowerCase();
				String prename = UUID.randomUUID().toString().replace("-", "");
				String fileName = prename+ext;
				boardfile.setBoardfileName(fileName);		
				boardfile.setBoardfileSize(f.getSize());
				boardfile.setBoardfileType(f.getContentType());
				
				log.debug("BoardService▶▶▶▶▶▶ boardFile:" + boardfile);
				boardfileMapper.insertBoardfile(boardfile);
				
				//파일을 저장
				try {
					File temp = new File(""); //프로젝트 폴더에 빈파일 만들어짐
					
					String path = temp.getAbsolutePath();
					
					f.transferTo(new File(path+"\\src\\main\\\\webapp\\resource\\" + fileName));
				} catch (Exception e) {
					throw new RuntimeException();
				}
			}
		}
	}
	
	public Map<String, Object> getBoardOne(int boardId) {
      //1)상세보기
      Map<String, Object> boardMap = boardMapper.selectBoardOne(boardId);
      //2) boardfile목록
      List<Boardfile> boardfileList = boardfileMapper.selectBoardfileByBoardId(boardId);
      
      //3)댓글 목록
      List<Comment> commentList = commentMapper.selectCommentListByBoard(boardId);
      log.debug("commentList size() : "+ commentList.size());
      
      Map<String, Object> map = new HashMap<>();
      map.put("boardfileList", boardfileList);
      map.put("boardMap", boardMap);
      map.put("commentList", commentList);

      return map;
	}

	public Map<String, Object> getBoardList(int currentPage, int rowPerPage, String searchWord) {
		//1
		int boardTotal = boardMapper.selectBoardTotal(searchWord); // searchWord
		int lastPage = (int)(Math.ceil((double)boardTotal / rowPerPage));
		
		//2
		Page page = new Page();
		page.setBeginRow((currentPage-1)*rowPerPage);
		page.setRowPerPage(rowPerPage);
		page.setSearchWord(searchWord);
		List<Board> boardList = boardMapper.selectBoardList(page); // Page
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lastPage", lastPage);
		map.put("boardList", boardList);
		return map;
	}
}