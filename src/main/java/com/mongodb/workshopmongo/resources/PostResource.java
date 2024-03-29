package com.mongodb.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.workshopmongo.domain.Post;
import com.mongodb.workshopmongo.services.PostServices;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostServices service;
	
	@GetMapping
	public ResponseEntity<List<Post>> findAll(){
		List<Post> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
			Post post = service.findById(id);
			return ResponseEntity.ok().body(post);
	}
	
}
