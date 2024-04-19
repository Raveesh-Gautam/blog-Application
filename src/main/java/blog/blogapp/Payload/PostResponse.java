package blog.blogapp.Payload;

import java.util.List;

import blog.blogapp.Dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostResponse {

	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private int totalPage;
	private long totalElements;
private boolean lastPage;
	
}
