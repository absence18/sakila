package com.gd.sakila.service;

import java.io.File;
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

@Slf4j 
@Service 
@Transactional 
public class BoardService {
	@Autowired BoardMapper boardMapper;
	@Autowired CommentMapper commentMapper;
	@Autowired BoardfileMapper boardfileMapper;
	
	// 추가 액션
	public void addBoard(BoardForm boardForm) {
		// boardForm --> board, boardfile
		
		// 1)
		Board board = boardForm.getBoard(); // 여기서는 boardId값이 null
		log.debug("board :" + board);
		boardMapper.insertBoard(board); // 입력시 만들어진 key값을 리턴받아야 한다... -> 지금은 리턴못받음. -> 매개변수 board의 boardId값을 변경해줘야함. (jdbc에 이런 기능이 있다..!)
		
		// 2)
		List<MultipartFile> list = boardForm.getBoardfile();
		if(list != null) {
			for(MultipartFile f : list) {
				Boardfile boardfile = new Boardfile();
				boardfile.setBoardId(board.getBoardId()); // auto increment로 입력된 값
				// test.txt -> newname.txt
				String originalFilename = f.getOriginalFilename();
				int p = originalFilename.lastIndexOf("."); // 4
				String ext = originalFilename.substring(p).toLowerCase(); // .txt
				String prename = UUID.randomUUID().toString().replace("-", "");
				String filename = prename + ext;
				boardfile.setBoardfileName(filename); // 이슈 >>> 중복으로 인해 덮어쓰기 가능
				boardfile.setBoardfileSize(f.getSize());
				boardfile.setBoardfileType(f.getContentType());
				boardfileMapper.insertBoardfile(boardfile);
				log.debug("boardfile : " + boardfile);
				// 2-1)
				// boardfileMapper.insertBoardfile(boardfile);
				
				
				
				// 2-2)
				// 파일을 저장..
				try {
					File temp = new File(""); // 프로젝트 위치(프로젝트 폴더)에 빈 파일이 만들어짐.
					String path = temp.getAbsolutePath(); // 프로젝트 폴다
					
					f.transferTo(new File(path + "\\src\\main\\webapp\\resource\\" + filename));
				} catch (Exception e) {
					throw new RuntimeException();
					
				}
				
			}
			
		}
		
	}
	
	// 삭제 액션
	public int removeBoard(Board board) {
		log.debug("▶▶▶▶▶▶ removeBoard() param: "+ board.toString());
		
		// 1) 게시글삭제 FK를 지정하지 않은 경우
		int boardRow = boardMapper.deleteBoard(board);
		if(boardRow == 0) {
			return 0;
		}
		
		// 2) 댓글삭제
		int commentRow = commentMapper.deleteCommentByBoardId(board.getBoardId());
		
		
		// 3) 물리적 파일 삭제 (/resource/안의 파일)
		List<Boardfile> boardfileList = boardfileMapper.selectBoardfileByBoardId(board.getBoardId());
		if(boardfileList != null) {
			for(Boardfile f : boardfileList) {
				File temp = new File(""); // 프로젝트 위치(프로젝트 폴더)에 빈 파일이 만들어짐.
				String path = temp.getAbsolutePath(); // 프로젝트 폴다
				File file = new File(path + "\\src\\main\\webapp\\resource\\" + f.getBoardfileName());
				file.delete();
			}
		}
		
		// 4) 파일 테이블 행 삭제
		int boardfileRow = boardfileMapper.deleteBoardfileByBoardId(board.getBoardId());
		
		return boardRow;
	}
	
	
	// 수정 액션
	public int modifyBoard(Board board) {
		log.debug("▶▶▶▶▶▶ modifyBoard() param: "+ board.toString());
		return boardMapper.updateBoard(board);
	}
	
	
	// 추가 액션
	public int addBoard(Board board) {
		return boardMapper.insertBoard(board);
	}
	
	// 1)상세보기 + 2)댓글목록, 수정 폼
	public Map<String, Object> getBoardOne(int boardId) {
		log.debug("▶▶▶▶▶▶ modifyBoard() param: "+ boardId);
		// 1) 상세보기
		Map<String, Object> boardMap = boardMapper.selectBoardOne(boardId);
		log.debug("▶▶▶▶▶▶ boardMap :"+boardMap);
		
		// 2) boardfile 목록
		List<Boardfile> boardfileList = boardfileMapper.selectBoardfileByBoardId(boardId);
		
		// 3) 댓글 목록
		List<Comment> commentList = commentMapper.selectCommentListByBoard(boardId);
		log.debug("▶▶▶▶▶▶ commentList size() :"+commentList.size());
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardMap", boardMap);
		map.put("boardfileList", boardfileList);
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
