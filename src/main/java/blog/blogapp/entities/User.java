package blog.blogapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*
;@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String name;
private String email;

private String password;
private String about;
 @OneToMany(mappedBy = "user")
private List<Post> posts=new ArrayList<>();

 @OneToMany(mappedBy = "user")
private List<Comment> comments=new ArrayList<>();


}
