package com.mongodb.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.workshopmongo.domain.Post;
import com.mongodb.workshopmongo.repository.PostRepository;
import com.mongodb.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostServices {
	@Autowired
	private PostRepository PostRepository;
	
	public List<Post> findAll(){
	return	PostRepository.findAll();
	}
	
	public Post findById(String id) {
		// o optional foi feito para trabalhar com atributos que podem retornar nulo
		Optional<Post> Post = PostRepository.findById(id);
		return Post.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
}
