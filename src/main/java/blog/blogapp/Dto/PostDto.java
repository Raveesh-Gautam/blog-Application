package blog.blogapp.Dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import blog.blogapp.entities.Category;
import blog.blogapp.entities.Comment;
import blog.blogapp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	private int postId;
	private String  title;
	
	private String content;
	private String image;
	private Date date;

	private UserDto user;
//	private List<Comment> comments=new ArrayList<>();


	private CategoryDto category;
	private  Set<CommentDto> comments=new HashSet<>();
}
