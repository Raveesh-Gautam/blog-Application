package blog.blogapp.Services;

import java.util.List;

import blog.blogapp.Dto.PostDto;
import blog.blogapp.Payload.PostResponse;
import blog.blogapp.entities.Post;

public interface PostService {

	
	PostDto createPost(PostDto postDto,int userId,int categoryId);
	public PostDto updatePost(PostDto postDto,int postId);
	
	void deletePost(int postId);
	PostResponse getAllPost(int pageNumber,int pageSize,String sortBy);
	
	List<PostDto> getAllPost();
	PostDto getPostById(int postId);
	
	List<PostDto> getPostsByCategory(int categoryId);
	
	List<PostDto> getPostByUser(int userId);
	List<PostDto> serachPosts(String keyword);
}
