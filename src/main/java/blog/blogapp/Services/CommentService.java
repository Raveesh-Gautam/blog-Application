package blog.blogapp.Services;

import org.springframework.stereotype.Service;

import blog.blogapp.Dto.CommentDto;


public interface CommentService {

	CommentDto createComment(CommentDto commentDto,int postId);
	
	void deleteComment(int commentId);
}
