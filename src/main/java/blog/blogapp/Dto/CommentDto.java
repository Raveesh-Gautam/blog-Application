package blog.blogapp.Dto;

import blog.blogapp.entities.Post;
import blog.blogapp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
	private int commentId;
	
	private String content;
	
//	private User user;
//	
//	private Post post;

}
