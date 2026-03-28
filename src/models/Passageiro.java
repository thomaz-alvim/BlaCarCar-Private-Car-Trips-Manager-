package models;

import java.util.Arrays;
import java.util.Objects;

/**
 * Cria objetos do tipo Passageiro. Produz representação textual com todos os atributos.
 */
public class Passageiro {
	private String nome;
	private String cpf;
	private String link;
	private String[] pontos;
	
	public Passageiro(String nome, String cpf, String link, String[] pontos) {
		this.nome = nome;
		this.cpf = cpf;
		this.link = link;
		this.pontos = pontos;
	}
	
	public Passageiro(String nome, String cpf, String[] pontos) {
		this.nome = nome;
		this.cpf = cpf;
		this.pontos = pontos;
	}
	
	public String[] getPontos() {
		return pontos;
	}
	
	public void setLink(String novoLink) {
		this.link = novoLink;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passageiro other = (Passageiro) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public String toString() {
		return "Passageiro [Nome: " + nome + ", CPF: " + cpf + ", Rede Social: " + link + ", Pontos de Conexão: " + Arrays.toString(pontos)
				+ "]";
	}

}
