package blog.blogapp.Services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import blog.blogapp.Dto.PostDto;
import blog.blogapp.Exception.ResourceNotFoundException;
import blog.blogapp.Payload.PostResponse;
import blog.blogapp.Repository.CategoryRepository;
import blog.blogapp.Repository.PostRepository;
import blog.blogapp.Repository.UserRepository;
import blog.blogapp.Services.PostService;
import blog.blogapp.entities.Category;
import blog.blogapp.entities.Post;
import blog.blogapp.entities.User;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostDto createPost(PostDto postDto,int userId,int categoryId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepository.findById(userId)
			    .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Category category = this.categoryRepository.findById(categoryId)
			    .orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
        Post post = this.modelMapper.map(postDto, Post.class);
post.setImage("default.png");
post.setDate(new Date());
post.setCategory(category);
post.setUser(user);
Post newPost=this.postRepository.save(post);

		
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		// TODO Auto-generated method stub
		Post post=	this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
post.setTitle(postDto.getTitle());
post.setContent(postDto.getContent());
post.setImage(postDto.getImage());
Post updetedPost=this.postRepository.save(post);
return this.modelMapper.map(updetedPost, PostDto.class);

	
	}

	@Override
	public void deletePost(int postId) {
		// TODO Auto-generated method stub
		Post post=	this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
this.postRepository.delete(post);
		
	}

	@Override
	public List<PostDto> getAllPost() {
		// TODO Auto-generated method stub
		List<Post> allPosts=this.postRepository.findAll();
		List<PostDto> postDtos=allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto getPostById(int postId) {
		// TODO Auto-generated method stub
	Post post=	this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(int categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepository.findById(categoryId)
			    .orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
		
			List<Post> posts=this.postRepository.findByCategory(cat)	;
			List<PostDto> postDtos=posts.stream().map((post->this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(int userId) {
		// TODO Auto-generated method stub
		User user=this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts=this.postRepository.findByUser(user);
		List<PostDto> postDtos=posts.stream().map((post->this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> serachPosts(String keyword) {
		// TODO Auto-generated method stub
		List<Post> posts=this.postRepository.findByTitleContaining(keyword);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostResponse getAllPost(int pageNumber, int pageSize,String sortBy ) {
		// TODO Auto-generated method stub
		
		Pageable p=PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		Page<Post> pagePost=this.postRepository.findAll(p);
		List<Post> allPosts=pagePost.getContent();
		List<PostDto> postDtos=allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPage(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
		
	}

	
}
