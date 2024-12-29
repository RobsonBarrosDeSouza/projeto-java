package br.com.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.dto.ProjetoDTO;
import br.com.biblioteca.exceptions.ProjetoNaoPodeSerExcluidoException;
import br.com.biblioteca.model.Projeto;
import br.com.biblioteca.services.ProjetoService;

@RestController
@RequestMapping(value = "/webservice/v1/projeto")
public class ProjetoController {

	@Autowired
	private ProjetoService projetoService;

	@GetMapping(value = "/findAll")
	public ResponseEntity<List<Projeto>> buscarTodosProjeto() {
		List<Projeto> list = projetoService.buscarTodosProjeto();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Projeto> buscarPorId(@PathVariable Long id) {
		Projeto obj = projetoService.buscarProjetoId(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping(value = "/createProjeto")
	public ResponseEntity<Projeto> criarProjeto(@RequestBody ProjetoDTO projeto) {
		Projeto novoProjeto = projetoService.criarProjeto(projeto);
		return ResponseEntity.ok().body(novoProjeto);

	}

	@PutMapping(value = "/editeProjeto")
	public ResponseEntity<Projeto> editarProjeto(@RequestBody Projeto projeto) {
		Projeto novoProjeto = projetoService.editarProjeto(projeto);
		return ResponseEntity.ok().body(novoProjeto);

	}

	@DeleteMapping(value = "/deleteProjeto/{id}")
	public ResponseEntity<Void> excluirProjeto(@PathVariable Long id) {
		try {
			projetoService.excluirProjeto(id);
			return ResponseEntity.noContent().build();
		} catch (ProjetoNaoPodeSerExcluidoException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); 
		}
	}
}
