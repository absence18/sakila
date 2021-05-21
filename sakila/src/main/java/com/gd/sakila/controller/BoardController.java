package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.BoardService;
import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.BoardForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	// 리턴타입 뷰이름 문자열 C -> V
	@GetMapping("/removeBoard")
	public String removeBoard(Model model, @RequestParam(value = "boardId", required = true) int boardId) {
		log.debug("▶▶▶▶▶▶ param: "+boardId);
		
		Map<String, Object> map = boardService.getBoardOne(boardId);
	    model.addAttribute("boardMap", map.get("boardMap"));
	    log.debug("▶▶▶▶▶▶ boardMap :" + map.get("boardMap"));
	    
		return "removeBoard";
	}
	// C -> M -> redirect(C)
	@PostMapping("/removeBoard")
	public String removeBoard(Board board) {
		int row = boardService.removeBoard(board);
		log.debug("removeBoard(): "+row);
		if(row == 0) {
			return "redirect:/admin/removeBoard?boardId="+board.getBoardId();
		}
		return "redirect:/admin/getBoardList";
	}

	
	@GetMapping("/addBoard")
	public String addBoard() {
		return "addBoard";
	}
	
	@PostMapping("/addBoard")
	public String addBoard(BoardForm boardForm) { // 커맨드객체 // Board board에서 BoardForm으로 바뀜.. web에 맞추려고..!
		log.debug("★★★★ boardForm :" + boardForm);
		// boardService.addBoard(board);
		return "redirect:/admin/getBoardList";
	}
	
	
	@GetMapping("/getBoardOne")
	   public String getBoardOne(Model model, @RequestParam(value="boardId", required = true) int boardId) {
	      // 디버깅 코드
	      log.debug(" boardId : "+boardId); 
	      
	      Map<String, Object> map = boardService.getBoardOne(boardId);
	      
	      //디버깅 코드
	      log.debug(" map : "+map);
	      
	      model.addAttribute("boardMap", map.get("boardMap"));
	      model.addAttribute("boardfileList", map.get("boardfileList"));
	      model.addAttribute("commentList", map.get("commentList"));
	      return "getBoardOne";
	   }

	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model,
							@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
							@RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage,
							@RequestParam(value="searchWord", required = false ) String searchWord) {
			System.out.println(currentPage+"<--currentPage");
			System.out.println(rowPerPage+"<--rowPerPage");
			System.out.println(searchWord+"<--searchWord");
		
		Map<String, Object> map = boardService.getBoardList(currentPage, rowPerPage, searchWord);	
		// model.addAttribute("map", map);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("boardList", map.get("boardList"));
		
		return "getBoardList";
	}
	
	
	// 수정 폼
   @GetMapping("/modifyBoard")
   public String modifyBoard(Model model, @RequestParam(value = "boardId", required = true) int boardId) {
      log.debug("modifyBoard() param : " +boardId); //디버깅
      
      // select
      Map<String, Object> map = boardService.getBoardOne(boardId);
      model.addAttribute("boardMap", map.get("boardMap"));
      return "modifyBoard";
   }
   
   // 수정 액션
   @PostMapping("/modifyBoard")
   public String modifyBoard(Board board) {
      log.debug("modifyBoard() param : " +board.toString()); //디버깅
    
      // update
      int row = boardService.modifyBoard(board);
      log.debug("update row : " +row);
      
      if(row == 1) {
    	  return "redirect:/admin/getBoardOne?boardId=" +board.getBoardId();
      }
   
      return "redirect:/admin/modifyBoard?boardId=" +board.getBoardId();
   }
}