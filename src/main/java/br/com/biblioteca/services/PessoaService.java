package br.com.biblioteca.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.biblioteca.model.Pessoa;

import br.com.biblioteca.repository.PessoaRepository;


@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	
	
	public Pessoa buscarPessoaId(Long id) {
		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("não encontrado essa pessoa "));
		
		return pessoa;
	}
	
	public List<Pessoa> buscarTodasPessoas() {
		return pessoaRepository.findAll();
	}
	
	public Pessoa criarPessoa(Pessoa pessoa) {
		Pessoa novaPessoa = new Pessoa();
		novaPessoa.setCpf(pessoa.getCpf());
		novaPessoa.setDataNascimento(pessoa.getDataNascimento());
		novaPessoa.setFuncionario(pessoa.getFuncionario());
		novaPessoa.setGerente(pessoa.getGerente());
		novaPessoa.setNome(pessoa.getNome());
		pessoaRepository.save(novaPessoa);
		
		return novaPessoa;
		
	}
	
	
	

}
