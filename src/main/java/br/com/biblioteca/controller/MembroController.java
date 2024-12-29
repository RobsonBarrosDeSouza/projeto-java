package br.com.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.dto.MembroDTO;
import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.services.MembroService;

@RestController
@RequestMapping(value = "/webservice/v1/membro")
public class MembroController {

	@Autowired
	private MembroService membroService;
	

    @PostMapping("/cadastrar")
    public ResponseEntity<Pessoa> cadastrarMembro(@RequestBody MembroDTO membroDTO) {
        Pessoa membro = membroService.cadastrarMembro(membroDTO);
        return ResponseEntity.ok(membro);
    }

	@PostMapping(value = "/associar")
	public ResponseEntity<Void> associarMembro(@RequestParam Long idProjeto, @RequestParam Long idPessoa,
			@RequestParam Long idCargo) {
		membroService.associarMembroComCargo(idProjeto, idPessoa, idCargo);
		return ResponseEntity.ok().build();
	}
}
