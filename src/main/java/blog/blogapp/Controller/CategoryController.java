package blog.blogapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blog.blogapp.Dto.CategoryDto;
import blog.blogapp.Payload.ApiResponse;
import blog.blogapp.Services.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory( @Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createcategory=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createcategory,HttpStatus.CREATED);
	}
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory( @Valid @RequestBody CategoryDto categoryDto,@PathVariable int catId){
		CategoryDto udatedcategory=this.categoryService.updateCategory(categoryDto,catId);
		return new ResponseEntity<CategoryDto>(udatedcategory,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory( @Valid @PathVariable int catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted",false),HttpStatus.OK);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@Valid @PathVariable int catId){
		
		CategoryDto categoryDto=this.categoryService.getCategory(catId);
	return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		
		List<CategoryDto> categoryDtos=this.categoryService.getCategories();
	return ResponseEntity.ok(categoryDtos);
	}
	
	
}

