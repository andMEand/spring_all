package com.spring.mb2reply.comment;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
	
	@Autowired
	CommentService mCommentService;
	
	@RequestMapping(value="/comment_list.bo", produces="application/json;charset=utf-8")
	private List<CommentVO> mCommentServiceList(@RequestParam int bno)throws Exception{//��� ����Ʈ
		List<CommentVO> comment_list = mCommentService.commentListService(bno);
		
		return comment_list;
		//ajax����� �ƴϴ� ��ũ����� ���
		//@RestController ���� �⺻�� �� �𵨾غ䰡 �ƴ�, ��ȯ���� �䰡 �ƴ� ������ �����Ѵ�. : ��� �����͸� �����ϰ��� �Ҷ� ����� �� �ִ� ���
	}
	
	@RequestMapping(value="/comment_insert.bo", produces="application/json;charset=utf-8")
	private int  mCommentServiceInsert(CommentVO comment, HttpSession session)throws Exception{
		comment.setWriter((String)session.getAttribute("id"));

		
		return mCommentService.commentInsertService(comment);
	}
	
	
	//ver1
	@RequestMapping(value="/comment_update2.bo", produces="application/json;charset=utf-8")
	private int  mCommentServiceUpdate2(@RequestParam int cno, @RequestParam String content )throws Exception{  //@RequestParam int cno, @RequestParam String content  ���COmmentVO�� �޾Ƶ� �ȴ�
		CommentVO comment = new CommentVO();
		comment.setCno(cno);
		comment.setContent(content);
		return mCommentService.commentUpdateService(comment);
}
	//ver2
	@RequestMapping(value="/comment_update.bo", produces="application/json;charset=utf-8")
	private int  mCommentServiceUpdate(CommentVO comment )throws Exception{  //@RequestParam int cno, @RequestParam String content  ���COmmentVO�� �޾Ƶ� �ȴ�
				
		return mCommentService.commentUpdateService(comment);
	}
	
	
	@RequestMapping(value="/comment_delete.bo", produces="application/json;charset=utf-8")
	private int  mCommentServiceDelete(@RequestParam(value="cno") int cno )throws Exception{
	
		return mCommentService.commentDeleteService(cno);
	}
	

}
