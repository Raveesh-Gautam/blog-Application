package blog.blogapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.blogapp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
//	public List<Comment> findByUserIdAndPostId(int userId,int postId);

}
