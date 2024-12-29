package br.com.biblioteca.services;


import java.util.EnumSet;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.dto.ProjetoDTO;
import br.com.biblioteca.enums.Status;
import br.com.biblioteca.exceptions.ProjetoNaoPodeSerExcluidoException;
import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.model.Projeto;
import br.com.biblioteca.repository.PessoaRepository;
import br.com.biblioteca.repository.ProjetoRepository;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	 public String calcularRisco(Projeto projeto) {
	        // Critérios de exemplo
	        if (projeto.getOrcamento() <= 10000 && projeto.getDataFim().isAfter(projeto.getDataPrevisaoFim())) {
	            return "Baixo";
	        } else if (projeto.getOrcamento() <= 50000 && projeto.getDataFim().isBefore(projeto.getDataPrevisaoFim())) {
	            return "Médio";
	        } else {
	            return "Alto";
	        }
	    }
	
	public Projeto buscarProjetoId(Long id) {
		Projeto projeto = projetoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado"));
		
		return projeto;
	}
	
	public List<Projeto> buscarTodosProjeto() {
		return projetoRepository.findAll();
	}
	
	public Projeto criarProjeto(ProjetoDTO projetoDTO) {

	    Pessoa gerente = pessoaRepository.findById(projetoDTO.getIdGerente())
	                                     .orElseThrow(() -> new IllegalArgumentException("Gerente não encontrado."));

	    Projeto novoProjeto = new Projeto();
	    novoProjeto.setNome(projetoDTO.getNome());
	    novoProjeto.setDataInicio(projetoDTO.getDataInicio());
	    novoProjeto.setDataPrevisaoFim(projetoDTO.getDataPrevisaoFim());
	    novoProjeto.setDataFim(projetoDTO.getDataFim());
	    novoProjeto.setDescricao(projetoDTO.getDescricao());
	    novoProjeto.setStatus(projetoDTO.getStatus());
	    novoProjeto.setOrcamento(projetoDTO.getOrcamento());
	    novoProjeto.setGerente(gerente);
	    
	    novoProjeto.setRisco(calcularRisco(novoProjeto));

	    if (projetoDTO.getIdMembros() != null && !projetoDTO.getIdMembros().isEmpty()) {
	        List<Pessoa> membros = pessoaRepository.findAllById(projetoDTO.getIdMembros());
	        novoProjeto.setMembros(membros);
	    }

	    projetoRepository.save(novoProjeto);

	    return novoProjeto;
	}
	
	public Projeto editarProjeto(Projeto projeto) {
		Projeto existeProjeto = projetoRepository.getById(projeto.getId());
		
		if(existeProjeto != null) {
			Projeto editProjeto = new Projeto();
			editProjeto.setNome(projeto.getNome());
			editProjeto.setDataFim(projeto.getDataFim());
			editProjeto.setDataInicio(projeto.getDataInicio());
			editProjeto.setDataPrevisaoFim(projeto.getDataPrevisaoFim());
			editProjeto.setDescricao(projeto.getDescricao());
			editProjeto.setGerente(projeto.getGerente());
			editProjeto.setMembros(projeto.getMembros());
			editProjeto.setOrcamento(projeto.getOrcamento());
			editProjeto.setStatus(projeto.getStatus());
			projetoRepository.save(editProjeto);
			
			return editProjeto;
		}else {
			return null;
		}
		
		
		
	}

	public void excluirProjeto(Long id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        if (projeto.getStatus() == Status.INICIADO || 
        	projeto.getStatus() == Status.EM_ANDAMENTO || 
        	projeto.getStatus() == Status.ENCERRADO) {
        	
        	throw new ProjetoNaoPodeSerExcluidoException("O projeto não pode ser excluído porque está " + projeto.getStatus());
        }
		projetoRepository.delete(projeto);
	}
	
	 public String classificarRisco(Projeto projeto) {
	        Double orcamento = projeto.getOrcamento();
	        Status status = projeto.getStatus();

	        if (orcamento <= 10000 && status.equals(Status.PLANEJADO)) {
	            return "Baixo Risco";
	        } else if (orcamento <= 50000 && (status.equals(Status.EM_ANDAMENTO) || status.equals(Status.ANALISE_APROVADA))) {
	            return "Médio Risco";
	        } else if (orcamento > 50000 && (status.equals(Status.CANCELADO) || status.equals(Status.ENCERRADO))) {
	            return "Alto Risco";
	        } else {
	            return "Médio Risco"; 
	        }
	    }
	

}
