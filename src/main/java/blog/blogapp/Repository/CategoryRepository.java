package blog.blogapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.blogapp.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
