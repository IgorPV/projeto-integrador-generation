package com.educati.EducaTI.services;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.educati.EducaTI.model.Posts;
import com.educati.EducaTI.model.Temas;
import com.educati.EducaTI.model.Usuario;
import com.educati.EducaTI.model.UsuarioLogin;
import com.educati.EducaTI.repository.PostsRepository;
import com.educati.EducaTI.repository.TemasRepository;
import com.educati.EducaTI.repository.UsuarioRepository;

@Service
public class UsuarioServices {
	
	private UsuarioRepository usuarioRepository;
	
	private PostsRepository postsRepository;
	
	private TemasRepository temasRepository;

	@Autowired
    public UsuarioServices(UsuarioRepository usuarioRepository, PostsRepository postsRepository, TemasRepository temasRepository) {
        this.usuarioRepository = usuarioRepository;
        this.postsRepository = postsRepository;
        this.temasRepository = temasRepository;
    }


    public Optional<Usuario> cadastroUsuario(Usuario novoUsuario) {
	
		if (usuarioRepository.findByEmail(novoUsuario.getEmail()).isEmpty()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaEncoder = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(senhaEncoder);
			return Optional.ofNullable(usuarioRepository.save(novoUsuario));
		}
		else {
			return Optional.empty();
		}
	}
	
	public Optional<Usuario> atualizaUsuario(Usuario usuario){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return Optional.ofNullable(usuarioRepository.save(usuario));
	}
	
	public Optional<UsuarioLogin> loginUsuario(Optional<UsuarioLogin> usuario){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.get().getEmail());
		
		if(usuarioExistente.isEmpty()) {
			return Optional.empty();
		}
		else {
			if (encoder.matches(usuario.get().getSenha(), usuarioExistente.get().getSenha())) {

				String auth = usuario.get().getEmail() + ":" + usuario.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				usuario.get().setToken(authHeader);
				usuario.get().setId(usuarioExistente.get().getId());
				usuario.get().setNome(usuarioExistente.get().getNome());
				usuario.get().setEmail(usuarioExistente.get().getEmail());
				usuario.get().setSenha(usuarioExistente.get().getSenha());
				usuario.get().setFoto(usuarioExistente.get().getFoto());
				
				return usuario;
			}
			else {
				
				return Optional.empty();
			}
			
		}
		
	}
	
	public Optional<Usuario> criarPost(Posts postNovo, Long idUsuario ){
		if (usuarioRepository.findById(idUsuario).isPresent()) {
			postNovo.setUsuarioCriador(usuarioRepository.findById(idUsuario).get());
			postsRepository.save(postNovo);
			
			return usuarioRepository.findById(idUsuario);
		} 
		else {
			return Optional.empty();
		}
	}
	
	public Optional<Usuario> inscreverTema(Long idTema, Long idUsuario){
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);
		Optional<Temas> temaExistente = temasRepository.findById(idTema); 
		if (usuarioExistente.get().getTemasInscritos().contains(temaExistente.get())) {

			return Optional.empty();
		}
		else {
			usuarioExistente.get().getTemasInscritos().add(temaExistente.get());
			temaExistente.get().getUsuariosInscritos().add(usuarioExistente.get());
			
			return usuarioExistente;
		}
	}
	public void deletarPost(Long idPost, Long idUsuario) {
		Usuario criadorValido = postsRepository.findById(idPost).get().getUsuarioCriador();
		Usuario usuarioExistente = usuarioRepository.findById(idUsuario).get();
		
		if (criadorValido.equals(usuarioExistente)){
			postsRepository.deleteById(idPost); 
		}
		
	}
	
	

}
