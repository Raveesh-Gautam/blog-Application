package blog.blogapp.Controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import blog.blogapp.Config.AppConstants;
import blog.blogapp.Dto.PostDto;
import blog.blogapp.Payload.ApiResponse;
import blog.blogapp.Payload.PostResponse;
import blog.blogapp.Services.FileService;
import blog.blogapp.Services.PostService;
import blog.blogapp.entities.Post;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")

public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public  ResponseEntity<PostDto>  createPost(@RequestBody PostDto postDto,@PathVariable int userId,@PathVariable int categoryId){
		
	PostDto createPost=	this.postService.createPost(postDto, userId, categoryId);
		
	return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
//	get by user
	
	
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getpostsByUser(@PathVariable int userId){
		List<PostDto> posts=this.postService.getPostByUser(userId);
		return new  ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	
	
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getpostsByCategory(@PathVariable int categoryId){
		List<PostDto> posts=this.postService.getPostsByCategory(categoryId);
		return new  ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false) int pageNumber,@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="SortBy",defaultValue=AppConstants.SORT_BY,required=false)String sortBy)
{
		PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int postId){
		PostDto singlePost=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(singlePost,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable int postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("post is successFully deleted !",true);
	}
	
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto>  updatePost(@RequestBody PostDto postDto,@PathVariable int postId) {
		PostDto updatePost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
//	search 
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords ){
		List<PostDto> result=this.postService.serachPosts(keywords);
		return  new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
		
	}
	@PostMapping("/posts/image/upload/{postId}")
	public  ResponseEntity<PostDto> uploadPostIage(@RequestParam("image") MultipartFile image,@PathVariable int postId) throws IOException{
		
		PostDto postDto=this.postService.getPostById(postId);
String fileName=this.fileService.uploadImage(path, image);
	postDto.setImage(fileName);
	PostDto updatePost=this.postService.updatePost(postDto, postId);
	return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	@GetMapping(value="post/image/{image}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("image") String image,HttpServletResponse response)throws IOException {
		InputStream resource=this.fileService.getResource(path, image);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	StreamUtils.copy(resource, response.getOutputStream());
	
	}
	
	
	
}
