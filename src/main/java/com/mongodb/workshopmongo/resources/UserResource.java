package com.mongodb.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mongodb.workshopmongo.domain.Post;
import com.mongodb.workshopmongo.domain.User;
import com.mongodb.workshopmongo.dto.UserDTO;
import com.mongodb.workshopmongo.services.UserServices;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserServices service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<UserDTO> list = service.findAll().stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
			UserDTO userdto = new UserDTO(service.findById(id));
			return ResponseEntity.ok().body(userdto);
	}
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO userdto){
		User obj = service.fromDTO(userdto);
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	@PutMapping(value="/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody User user){
		UserDTO userdto = new UserDTO(service.updateData(id, user));
		return ResponseEntity.ok().body(userdto);
	}
	@GetMapping(value="/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String  id) {
		return ResponseEntity.ok().body(service.findById(id).getPosts());
	}
	
}
