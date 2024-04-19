package blog.blogapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.blogapp.entities.Category;
import blog.blogapp.entities.Post;
import blog.blogapp.entities.User;

public interface PostRepository extends JpaRepository<Post,Integer> {
	public Post findByUserEmailAndPostId(String email, int postId);
 public List<Post> findByUser(User user);
 public List<Post> findByCategory(Category category);
 public List<Post> findByTitleContaining(String title);

}
