package br.com.biblioteca.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.services.PessoaService;



@RestController
@RequestMapping(value = "/webservice/v1/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;

	
	
	@PostMapping(value = "/createPessoa")
	public ResponseEntity<Pessoa> criarProjeto(@RequestBody Pessoa pessoa){
		Pessoa pessoaNova = pessoaService.criarPessoa(pessoa);
		return ResponseEntity.ok().body(pessoaNova);
		
	}
	
	
}
