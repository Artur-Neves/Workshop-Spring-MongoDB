package com.mongodb.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mongodb.workshopmongo.domain.Post;
import com.mongodb.workshopmongo.domain.User;
import com.mongodb.workshopmongo.dto.AuthorDTO;
import com.mongodb.workshopmongo.dto.ComentsDTO;
import com.mongodb.workshopmongo.repository.PostRepository;
import com.mongodb.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		userRepository.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
	     userRepository.saveAll(Arrays.asList(maria, alex, bob));
	     Post pot1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
	     Post pot2 = new Post(null, sdf.parse("21/03/2018"), "Bom dia ", "Acordei feliz hoje!", new AuthorDTO(maria));
	     ComentsDTO c1 = new ComentsDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
	     ComentsDTO c2 = new ComentsDTO("Aproveite!", sdf.parse("21/03/2018"), new AuthorDTO(bob));
	     ComentsDTO c3 = new ComentsDTO("Tenha um ótimo dia!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
	     pot1.getComents().addAll(Arrays.asList(c1, c2));
	     pot2.getComents().add(c3);
	     postRepository.deleteAll();
	     postRepository.saveAll(Arrays.asList(pot1, pot2));
	     maria.getPosts().addAll(Arrays.asList(pot1, pot2));
	     userRepository.save(maria);
	}

}
