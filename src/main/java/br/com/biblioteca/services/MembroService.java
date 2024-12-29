package br.com.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.dto.MembroDTO;
import br.com.biblioteca.model.Cargo;
import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.model.Projeto;
import br.com.biblioteca.repository.CargoRepository;
import br.com.biblioteca.repository.PessoaRepository;
import br.com.biblioteca.repository.ProjetoRepository;

@Service
public class MembroService {

	@Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CargoRepository cargoRepository;

    public void associarMembroComCargo(Long idProjeto, Long idPessoa, Long idCargo) {
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        Cargo cargo = cargoRepository.findById(idCargo)
                .orElseThrow(() -> new RuntimeException("Cargo não encontrado"));

        pessoa.setCargo(cargo);

        projeto.getMembros().add(pessoa);

        projetoRepository.save(projeto);
    }
    
 

    public Pessoa cadastrarMembro(MembroDTO membroDTO) {
        
        Pessoa novoMembro = new Pessoa();
        novoMembro.setNome(membroDTO.getNome());
        novoMembro.setFuncionario(true); 
        novoMembro.setGerente(false); 

        return pessoaRepository.save(novoMembro);
    }
}
