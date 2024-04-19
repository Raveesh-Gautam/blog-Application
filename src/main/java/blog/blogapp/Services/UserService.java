package blog.blogapp.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import blog.blogapp.Dto.UserDto;

@Service
public interface UserService {

	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto user,int userId);
	
	UserDto getUserById(int userId);
	List<UserDto> getAllUsers();
	void deleteUser(int userId);
	
	
}
