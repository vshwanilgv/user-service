package com.example.user_service.service;
import com.example.user_service.model.User;
import org.springframework.stereotype.Service;
import com.example.user_service.dto.UserDTO;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.user_service.exception.UserNotFoundException;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public List<UserDTO> getAllUsers() {
        try {
            logger.info("Fetching all users");
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(user -> modelMapper.map(user, UserDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Failed to fetch users", e);
            throw new RuntimeException("Failed to fetch users");
        }
    }
    @Override
    public UserDTO getUserById(Long id) {
        try {
            logger.info("Fetching user with ID: {}", id);
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(id));
            return modelMapper.map(user, UserDTO.class);
        } catch (Exception e) {
            logger.error("Failed to fetch user with ID: " + id, e);
            throw new RuntimeException("Failed to fetch user with id: ");
        }
    }
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        try {
            logger.info("Adding a new user");
            userDTO.setUserId(null);
            User user = modelMapper.map(userDTO, User.class);
            User addedUser = userRepository.save(user);
            return modelMapper.map(addedUser, UserDTO.class);
        } catch (Exception e) {
            logger.error("Failed to add user. Reason:", e);
            throw new RuntimeException("Failed to add user");
        }
    }
    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        try {
            logger.info("Updating user with ID: {}", id);
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(id));
            modelMapper.map(userDTO, existingUser);

            User updatedUser = userRepository.save(existingUser);
            return modelMapper.map(updatedUser, UserDTO.class);
        } catch (Exception e) {
            logger.error("Failed to update user with ID: " + id, e);
            throw new RuntimeException("Failed to update user with id: ");
        }
    }

    @Override
    public UserDTO updateVerificationStatus(String email, boolean isVerified) {
        try {
            logger.info("Updating verification status for user with email: {}", email);
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));

            user.setIsVerified(isVerified);
            User updatedUser = userRepository.save(user);
            return modelMapper.map(updatedUser, UserDTO.class);
        } catch (Exception e) {
            logger.error("Failed to update verification status for email: {}", email, e);
            throw new RuntimeException("Failed to update verification status for email: " + email);
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            logger.info("Deleting user with ID: {}", id);
            if (!userRepository.existsById(id)) {
                throw new UserNotFoundException(id);
            }
            userRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete user with ID: " + id, e);
            throw new RuntimeException("Failed to delete user with id: " + id, e);
        }
    }
}



