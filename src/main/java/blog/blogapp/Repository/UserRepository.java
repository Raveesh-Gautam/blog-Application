package blog.blogapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.blogapp.entities.User;

public interface UserRepository  extends JpaRepository<User,Integer>{
	Optional<User> findById(int userId);


}
