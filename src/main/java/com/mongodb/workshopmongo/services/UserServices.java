package com.mongodb.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.workshopmongo.domain.User;
import com.mongodb.workshopmongo.dto.UserDTO;
import com.mongodb.workshopmongo.repository.UserRepository;
import com.mongodb.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserServices {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
	return	userRepository.findAll();
	}
	
	public User findById(String id) {
		// o optional foi feito para trabalhar com atributos que podem retornar nulo
		Optional<User> user = userRepository.findById(id);
		
		return user.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public User insert(User user) {
	return	userRepository.save(user);
	}
	
	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail());
	}
	public void deleteById(String id) {
        findById(id);  
		userRepository.deleteById(id);
	}
	
	public User updateData(String id, User user) {
		User entity = findById(id);
		update(entity, user);
		return userRepository.save(entity);
	}
	
	public void update (User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
	}
}
