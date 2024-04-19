package blog.blogapp.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
@Table(name="categories")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int categoryId;
	@Column(name="title",nullable=false)
private String categoryTitle;
	
	@Column(name="description")
private String categoryDescription;
 @OneToMany(mappedBy="category",cascade=CascadeType.ALL)
	private Set<Post> posts=new HashSet<>();
}
