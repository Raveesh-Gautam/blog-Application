package blog.blogapp.Services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.blogapp.Dto.UserDto;
import blog.blogapp.Repository.UserRepository;
import blog.blogapp.Services.UserService;
import blog.blogapp.entities.User;
import blog.blogapp.Exception.ResourceNotFoundException;
@Service
public class UserServicesImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
    	Optional<User> optionalUser = userRepository.findById(userId);
    	User user;
    	if (optionalUser.isPresent()) {
    	    user = optionalUser.get();
    	} else {
    	    throw new ResourceNotFoundException("User", "id", userId);
    	}

        // Update user attributes with userDto
        
        // Save the updated user
    	user.setName(userDto.getName());
    	user.setPassword(userDto.getPassword());
    	user.setEmail(userDto.getEmail());
    	user.setAbout(user.getAbout());
        User updatedUser = userRepository.save(user);
        return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        // Retrieve all users from the repository
        List<User> users = userRepository.findAll();
        // Convert each user to UserDto
        return users.stream()
                    .map(this::userToDto)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(int userId) {
        // Delete user by userId
    	User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        userRepository.deleteById(userId);
    }

    public User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
//        user.setId(userDto.getId());
//        user.setEmail(userDto.getEmail());
//        user.setName(userDto.getName());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user) {
       
        UserDto userDto = this.modelMapper.map(user, UserDto.class);

        return userDto;
    }
}
