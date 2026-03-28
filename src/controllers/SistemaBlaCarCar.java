package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.*;

/**
 * Classe de controlador do sistema.
 */
public class SistemaBlaCarCar {
	private Map<String, Passageiro> passageiros;
	private List<Viagem> viagens;
	
	public SistemaBlaCarCar() {
		this.passageiros = new HashMap<>();
		this.viagens = new ArrayList<>();
	}
	
	public int cadastrarViagem(String motorista, String descricao, int vagas, String contato, String[] pontos) {
		viagens.add(new Viagem(motorista, descricao, vagas, contato, pontos));
		return viagens.size() - 1;
	}
	
	public boolean cadastrarPassageiro(String nome, String cpf, String link, String[] pontos) {
		if (cpf == null || cpf.isEmpty() || cpf.length() != 11 || passageiros.containsKey(cpf) == true) return false;
		passageiros.put(cpf, new Passageiro(nome, cpf, link, pontos));
		return true;
	}
	
	public boolean cadastrarPassageiro(String nome, String cpf, String[] pontos) {
		if (cpf == null || cpf.isEmpty() || cpf.length() != 11 || passageiros.containsKey(cpf) == true) return false;
		passageiros.put(cpf, new Passageiro(nome, cpf, pontos));
		return true;
	}
	
	public void atualizarLinkPerfil(String cpf, String novoLink) {
		if (cpf == null || cpf.isEmpty() || cpf.length() != 11) {
			throw new IllegalArgumentException("O CPF digitado não é válido.");
		}
		
		if (passageiros.containsKey(cpf) == false) {
			throw new IllegalArgumentException("Não há nenhum passageiro cadastrado com esse CPF.");
		}
		
		Passageiro passageiro = passageiros.get(cpf);
		passageiro.setLink(novoLink);
	}
	
	public String[] listarPassageiros() {
		String[] listaPassageiros = new String[passageiros.size()];
		int indiceAdicao = 0;
		
		for (Passageiro passageiro : passageiros.values()) {
			listaPassageiros[indiceAdicao] = passageiro.toString();
			indiceAdicao++;
		}
		
		return listaPassageiros;
	}
	
	public void solicitarVaga(String cpf, int indexViagem) {
		if (cpf == null || cpf.isEmpty() || cpf.length() != 11) {
			throw new IllegalArgumentException("O CPF digitado não é válido.");
		}
		
		if (!passageiros.containsKey(cpf)) {
			throw new IllegalArgumentException("Não há nenhum passageiro cadastrado com esse CPF.");
		}
		
		Viagem viagem = viagens.get(indexViagem);
		Passageiro passageiro = passageiros.get(cpf);
		
		viagem.adicionarInscrito(cpf, passageiro);
	}
	
	public String[] listarPassageirosInscritosViagem(int indexViagem) {
		Viagem viagem = viagens.get(indexViagem);
		return viagem.listarInscritos();
	}
	
	public String[] listarPassageirosInscritosCompatíveis(int indexViagem, int compatibilidadeMinima) {
		Viagem viagem = viagens.get(indexViagem);
		return viagem.listarInscritosCompativeis(compatibilidadeMinima);
	}
	
	public int calcularCompatibilidade(String cpf, int indexViagem) {
		if (cpf == null || cpf.isEmpty() || cpf.length() != 11) {
			throw new IllegalArgumentException("O CPF digitado não é válido.");
		}
		
		if (passageiros.containsKey(cpf) == false) {
			throw new IllegalArgumentException("Não há nenhum passageiro cadastrado com esse CPF.");
		}
		
		Passageiro passageiro = passageiros.get(cpf);
		Viagem viagem = viagens.get(indexViagem);
		
		return viagem.calcularCompatibilidade(passageiro.getPontos());
	}
	
	public String[] listarPassageirosConfirmadosViagem(int indexViagem) {
		Viagem viagem = viagens.get(indexViagem);
		return viagem.listarConfirmados();
	}
	
	public String[] confirmarPassageiros(int indexViagem, int compatibilidade) {
		return viagens.get(indexViagem).confirmarPassageiros(compatibilidade);
	}
 
}
