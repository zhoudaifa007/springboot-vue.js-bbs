package com.talsist.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.talsist.domain.BoardRepository;
import com.talsist.domain.Comment;
import com.talsist.domain.CommentRepository;
import com.talsist.domain.User;
import com.talsist.exception.NotLoggedInException;
import com.talsist.service.CommentService;
import com.talsist.util.HttpSessionUtils;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentSvc;
	
	@PostMapping("/board/{boardId}/comment")
	public String reply(@PathVariable Long boardId, int page, Comment comment, HttpSession session) {
		try {
			User user = HttpSessionUtils.getSessionUser(session);
			commentSvc.save(comment, user, boardId);
			return "redirect:/board/" + boardId + "?page=" + page;
			
		} catch (NotLoggedInException e) {
			session.setAttribute("prevPage", "/board/" + boardId + "?page=" + page);
			return "redirect:/login";
		}
	}
	
	@PutMapping("/board/{boardId}/comment")
	public String modify(@PathVariable Long boardId, int page, Long id, Comment reqComment, HttpSession session) {
		try {
			commentSvc.update(reqComment, id, HttpSessionUtils.getSessionUser(session).getId());
			return "redirect:/board/" + boardId + "?page=" + page;
			
		} catch (NotLoggedInException e) {
			session.setAttribute("prevPage", "/board/" + boardId + "?page=" + page);
			return "redirect:/login";
		}
	}
	
	@DeleteMapping("/board/{boardId}/comment")
	public String delete(@PathVariable Long boardId, int page, Long id, HttpSession session) {
		try {
			commentSvc.delete(boardId, id, HttpSessionUtils.getSessionUser(session).getId());
		} catch (Exception e) {
			System.out.println("댓글 삭제 중 오류 발생");
			e.printStackTrace();
		}
		return "redirect:/board/" + boardId + "?page=" + page;
	}

}
