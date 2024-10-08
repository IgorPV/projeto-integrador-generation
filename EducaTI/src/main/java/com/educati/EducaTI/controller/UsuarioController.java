package com.educati.EducaTI.controller;

import java.util.List;
import java.util.Optional;

import com.educati.EducaTI.utils.ErrorMessages;
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
import com.educati.EducaTI.model.Temas;
import com.educati.EducaTI.model.Usuario;
import com.educati.EducaTI.model.UsuarioLogin;
import com.educati.EducaTI.repository.PostsRepository;
import com.educati.EducaTI.repository.TemasRepository;
import com.educati.EducaTI.repository.UsuarioRepository;
import com.educati.EducaTI.services.UsuarioServices;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usuario")

public class UsuarioController {

	private UsuarioRepository repository;

	private TemasRepository temasRepository;

	private PostsRepository postRepository; 

	private UsuarioServices service;

	@Autowired
    public UsuarioController(UsuarioRepository repository, TemasRepository temasRepository, PostsRepository postRepository, UsuarioServices service) {
        this.repository = repository;
        this.temasRepository = temasRepository;
        this.postRepository = postRepository;
        this.service = service;
    }


    @GetMapping
	@ApiOperation(value="Retorna uma lista de Usuário")
	public ResponseEntity<List<Usuario>>getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Usuário selecionado por id")
	public ResponseEntity<Usuario>getById(@PathVariable Long id){
		return repository.findById(id).map(usuario -> ResponseEntity.ok(usuario))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	@ApiOperation(value="Retorna lista de Usuário por nome")
	public ResponseEntity<List<Usuario>>getByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping("/cadastrar")
	@ApiOperation(value="Cadastra um Usuário")
	public ResponseEntity<Object> cadastro(@RequestBody Usuario usuario){
		Optional<Usuario> novoUsuario = service.cadastroUsuario(usuario);
			if (novoUsuario.isEmpty()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessages.USER_ALREADY_EXISTS);
			}
		return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
	}
	
	@PostMapping("/login")
	@ApiOperation(value="Realiza o login do Usuário")
	public ResponseEntity<Object> logar(@RequestBody Optional<UsuarioLogin> usuarioConnect){
		Optional<UsuarioLogin> novoLogin = service.loginUsuario(usuarioConnect);
			if (novoLogin.isEmpty()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessages.LOGIN_ERROR);
			}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(novoLogin);
	}
	
		
	@PostMapping("/cadastrar/{id}/post")
	@ApiOperation(value="Post criado por um Usuário")
	public ResponseEntity<Object> criarPost(@RequestBody Posts posts, @PathVariable Long id){
		Optional<Usuario> postNovo = service.criarPost(posts, id);
			if (postNovo.isEmpty()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessages.ACCOUNT_NOT_FOUND);
			}
		return ResponseEntity.status(HttpStatus.CREATED).body(postNovo);
	}
	
	@PostMapping("/inscricao/{idTema}/{idUsuario}")
	@ApiOperation(value="Usuário inscreve um Tema")
	public ResponseEntity<Object> inscreverTema(@PathVariable Long idTema, @PathVariable Long idUsuario){
		Optional<Usuario> usuarioValido = repository.findById(idUsuario);
		Optional<Temas> temaValido = temasRepository.findById(idTema);
			if(usuarioValido.isEmpty() || temaValido.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessages.USER_OR_THEME_NOT_FOUND);
			}
		return ResponseEntity.ok(service.inscreverTema(idTema, idUsuario));
	}
	
	@PutMapping
	@ApiOperation(value="Atualiza dados do Usuário")
	public ResponseEntity<Object> put(@RequestBody Usuario usuario){
		return  ResponseEntity.ok(service.atualizaUsuario(usuario));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Usuário deletado por id")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@DeleteMapping("/post/{idUsuario}/{idPost}")
	@ApiOperation(value="Deleta Post de Usuário")
	public void delete(@PathVariable Long idUsuario, @PathVariable Long idPost){
		if (postRepository.findById(idPost).isPresent()) {
		service.deletarPost(idPost, idUsuario);
		}
	}
	
	
	
}
