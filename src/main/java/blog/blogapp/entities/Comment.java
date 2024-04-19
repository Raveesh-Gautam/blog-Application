package blog.blogapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int commentId;

	private String content;
	@ManyToOne
	private User user;
	@ManyToOne(fetch = FetchType.EAGER)

	private Post post;

}
