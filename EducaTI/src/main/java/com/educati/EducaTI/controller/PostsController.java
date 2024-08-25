package com.educati.EducaTI.controller; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educati.EducaTI.model.Posts;
import com.educati.EducaTI.repository.PostsRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RequestMapping("/post")
public class PostsController {

	private PostsRepository repository;

	@Autowired
    public PostsController(PostsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
	@ApiOperation(value="Retorna lista de Posts")
	public ResponseEntity<List<Posts>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Retorna lista de Posts por id")
	public ResponseEntity<Posts>getById(@PathVariable Long id){
		return repository.findById(id).map(post -> ResponseEntity.ok(post))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/texto/{texto}")
	@ApiOperation(value="Retorna lista de Posts por trecho de texto específico")
	public ResponseEntity<List<Posts>>getByTexto(@PathVariable String texto){
		return ResponseEntity.ok(repository.findAllByTextoPostsContainingIgnoreCase(texto));
	}
	
	@GetMapping("/titulo/{titulo}")
	@ApiOperation(value="Retorna lista de Posts por Titulo")
	public ResponseEntity<List<Posts>>getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloPostsContainingIgnoreCase(titulo));
	}

	@PostMapping
	@ApiOperation(value="Cria Posts")
	public ResponseEntity<Posts> post (@RequestBody Posts posts){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(posts));
	}
	
	@PutMapping
	@ApiOperation(value="Atualiza dados de Posts")
	public ResponseEntity<Posts> put (@RequestBody Posts posts){
		return  ResponseEntity.ok(repository.save(posts));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Deleta Posts por id")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
