package blog.blogapp.Services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.blogapp.Dto.CommentDto;
import blog.blogapp.Dto.PostDto;
import blog.blogapp.Exception.ResourceNotFoundException;
import blog.blogapp.Repository.CommentRepository;
import blog.blogapp.Repository.PostRepository;
import blog.blogapp.Services.CommentService;
import blog.blogapp.entities.Comment;
import blog.blogapp.entities.Post;

@Service
public class CommentServiceImpl implements CommentService  {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private ModelMapper modelMapper;
//	@Override
//	public CommentDto createComment(CommentDto commentDto, int postId) {
//		// TODO Auto-generated method stub
//		Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
//		Comment comment=this.modelMapper.map(commentDto, Comment.class);
////		comment.setPost(post);
//		
//		Comment commentSaved=this.commentRepository.save(comment);
//        post.getComments().add(commentSaved);
//        this.postRepository.save(post);
//
//		return this.modelMapper.map(commentSaved, CommentDto.class);
//		
//	}
    @Override
    public CommentDto createComment(CommentDto commentDto, int postId) {
        // Retrieve the post from the database
        Post post = this.postRepository.findById(postId)
                                        .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        
        // Map the CommentDto to Comment entity
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        
        // Associate the comment with the post
        comment.setPost(post);
        
        // Save the comment
        Comment commentSaved = this.commentRepository.save(comment);
        
        // Update the post with the latest comment
        post.getComments().add(commentSaved);
        
        // Update the post in the database
        this.postRepository.save(post);
        
        // Map the saved comment to CommentDto
        CommentDto savedCommentDto = this.modelMapper.map(commentSaved, CommentDto.class);
        
        // Map the updated post to PostDto (optional)
        PostDto updatedPostDto = this.modelMapper.map(post, PostDto.class);
        
        // Set the total number of comments in the CommentDto
        savedCommentDto.setCommentId(updatedPostDto.getPostId());
        
        // Return the CommentDto
        return savedCommentDto;
    }

	@Override
	public void deleteComment(int commentId) {
		// TODO Auto-generated method stub
	Comment com=this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Commen","Comment Id",commentId));
this.commentRepository.delete(com);
		
	}

}
