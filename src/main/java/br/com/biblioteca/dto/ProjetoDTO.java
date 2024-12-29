package br.com.biblioteca.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import br.com.biblioteca.enums.Status;

public class ProjetoDTO {
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataPrevisaoFim;
    private LocalDate dataFim;
    private String descricao;
    private Status status;
    private Double orcamento;
    private Long idGerente; 
    private List<Long> idMembros;
    
    public ProjetoDTO() {
	
	} 
    
	public ProjetoDTO(String nome, LocalDate dataInicio, LocalDate dataPrevisaoFim, LocalDate dataFim, String descricao,
			Status status, Double orcamento, Long idGerente, List<Long> idMembros) {
		super();
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataPrevisaoFim = dataPrevisaoFim;
		this.dataFim = dataFim;
		this.descricao = descricao;
		this.status = status;
		this.orcamento = orcamento;
		this.idGerente = idGerente;
		this.idMembros = idMembros;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataPrevisaoFim() {
		return dataPrevisaoFim;
	}

	public void setDataPrevisaoFim(LocalDate dataPrevisaoFim) {
		this.dataPrevisaoFim = dataPrevisaoFim;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Double orcamento) {
		this.orcamento = orcamento;
	}

	public Long getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(Long idGerente) {
		this.idGerente = idGerente;
	}

	public List<Long> getIdMembros() {
		return idMembros;
	}

	public void setIdMembros(List<Long> idMembros) {
		this.idMembros = idMembros;
	}

	@Override
	public String toString() {
		return "ProjetoDTO [nome=" + nome + ", dataInicio=" + dataInicio + ", dataPrevisaoFim=" + dataPrevisaoFim
				+ ", dataFim=" + dataFim + ", descricao=" + descricao + ", status=" + status + ", orcamento="
				+ orcamento + ", idGerente=" + idGerente + ", idMembros=" + idMembros + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataFim, dataInicio, dataPrevisaoFim, descricao, idGerente, idMembros, nome, orcamento,
				status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjetoDTO other = (ProjetoDTO) obj;
		return Objects.equals(dataFim, other.dataFim) && Objects.equals(dataInicio, other.dataInicio)
				&& Objects.equals(dataPrevisaoFim, other.dataPrevisaoFim) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(idGerente, other.idGerente) && Objects.equals(idMembros, other.idMembros)
				&& Objects.equals(nome, other.nome) && Objects.equals(orcamento, other.orcamento)
				&& status == other.status;
	} 
    
	
    
}
