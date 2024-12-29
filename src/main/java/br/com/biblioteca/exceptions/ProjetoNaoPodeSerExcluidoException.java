package br.com.biblioteca.exceptions;

public class ProjetoNaoPodeSerExcluidoException  extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public ProjetoNaoPodeSerExcluidoException(String mensagem) {
        super(mensagem);
    }
}
