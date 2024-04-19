package blog.blogapp.Controller;

import java.util.List;
import java.util.Map;

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

import blog.blogapp.Dto.UserDto;
import blog.blogapp.Exception.ResourceNotFoundException;
import blog.blogapp.Payload.ApiResponse;
import blog.blogapp.Services.UserService;
import blog.blogapp.Services.impl.UserServicesImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto){
		UserDto createUserDto=this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		
	
	}
	 @PutMapping("/{userId}")
	    public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto, @PathVariable("userId") int uid) {
//	        try {
//	            UserDto user = userService.getUserById(uid);
//	            user.setAbout(userDto.getAbout());
//	            user.setEmail(userDto.getEmail());
//	            user.setName(userDto.getName());
//	            user.setPassword(userDto.getPassword());
//	            UserDto updatedUser = userService.updateUser(user, uid);
//	            return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
//	        } catch (ResourceNotFoundException e) {
//	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	        }
		 
		 UserDto updatedUser=this.userService.updateUser(userDto, uid);
		 return ResponseEntity.ok(updatedUser);
	    }
	 
	 @DeleteMapping("/{userId}")
	    public ResponseEntity<ApiResponse> deleteUser( @PathVariable("userId") int uid) {
	    	this.userService.deleteUser(uid);
	    	return  new ResponseEntity<ApiResponse>(new ApiResponse(),HttpStatus.OK);
	    }

	 
	 @GetMapping("/")
	 public ResponseEntity<List<UserDto>> getAllUsers(){
		 return ResponseEntity.ok(this.userService.getAllUsers());
	 }
	 
	 
	 @GetMapping("/{userId}")
	 public ResponseEntity<UserDto> getSingleUsers(@PathVariable("userId") int uid){
		 return ResponseEntity.ok(this.userService.getUserById(uid));
	 }
	 
}
