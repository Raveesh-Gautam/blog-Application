package blog.blogapp.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.blogapp.Dto.CategoryDto;
import blog.blogapp.Exception.ResourceNotFoundException;
import blog.blogapp.Repository.CategoryRepository;
import blog.blogapp.Services.CategoryService;
import blog.blogapp.entities.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository  categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
	Category cat=	this.modelMapper.map(categoryDto, Category.class);
	Category addedCat=	this.categoryRepository.save(cat);
	return this.modelMapper.map(addedCat, CategoryDto.class);
		
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		// TODO Auto-generated method stub
        Category cat = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        cat.setCategoryTitle(categoryDto.getCategoryTitle());

        // Save the updated category
        Category updatedCategory = this.categoryRepository.save(cat);


		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
	this.categoryRepository.delete(cat);
	
	
	}

	@Override
	public CategoryDto getCategory(int categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        
        // Map each Category to CategoryDto using ModelMapper
        List<CategoryDto> catDtos = categories.stream()
                .map(cat -> modelMapper.map(cat, CategoryDto.class))
                .collect(Collectors.toList());

        return catDtos;
    }

	
}
