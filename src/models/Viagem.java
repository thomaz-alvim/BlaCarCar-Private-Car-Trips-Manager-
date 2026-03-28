package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Cria objetos do tipo Viagem. Armazena inscritos em um mapa, e confirmados em um Array.
 */
public class Viagem {
	private String motorista;
	private String descricao;
	private int vagas;
	private String contato;
	private String[] pontos;
	
	private Map<String, Passageiro> inscritos;
	private Passageiro[] confirmados;
	private int proximoConfirmado;
	
	public Viagem(String motorista, String descricao, int vagas, String contato, String[] pontos) {
		this.motorista = motorista;
		this.descricao = descricao;
		this.vagas = vagas;
		this.contato = contato;
		this.pontos = pontos;
		
		this.inscritos = new HashMap<>();
		this.confirmados = new Passageiro[vagas];
		this.proximoConfirmado = 0;
	}
	
	public void adicionarInscrito(String cpf, Passageiro passageiro) {
		inscritos.put(cpf, passageiro);
	}
	
	public String[] listarConfirmados() {
		String[] listaConfirmados = new String[proximoConfirmado];
		
		for (int i = 0 ; i < proximoConfirmado ; i++) {
			String compatibilidade = String.valueOf(calcularCompatibilidade(confirmados[i].getPontos()));
			listaConfirmados[i] = confirmados[i].toString() + "; Compatibilidade: " + compatibilidade + "%";
		}
		
		return listaConfirmados;
	}
	
	public String[] listarInscritos() {
		String[] listaInscritos = new String[inscritos.size()];
		int indiceAdicao = 0;
		
		for (Passageiro passageiro : inscritos.values()) {
			String compatibilidade = String.valueOf(calcularCompatibilidade(passageiro.getPontos()));
			listaInscritos[indiceAdicao] = passageiro.toString() + "; Compatibilidade: " + compatibilidade + "%";
			indiceAdicao++;
		}
		
		return listaInscritos;
	}
	
	public String[] listarInscritosCompativeis(int compatibilidadeMinima) {
		ArrayList<String> listaInscritosCompativeis = new ArrayList<>();
		
		for (Passageiro passageiro : inscritos.values()) {
			int compatibilidadePassageiro = calcularCompatibilidade(passageiro.getPontos());
			if (compatibilidadePassageiro >= compatibilidadeMinima) {
				String compatibilidade = String.valueOf(compatibilidadePassageiro);
				listaInscritosCompativeis.add(passageiro.toString() + "; Compatibilidade: " + compatibilidade + "%");
			}
		}
		
		String[] arrayInscritosCompativeis = listaInscritosCompativeis.toArray(String[]:: new);
		return arrayInscritosCompativeis;
	}
	
	public int calcularCompatibilidade(String[] pontosPassageiro) {
		int pontosEmComum = 0;
		int compatibilidade = 0;
		
		for (String ponto : pontos) {
			for (String pontoPassageiro : pontosPassageiro) {
				if (ponto.equals(pontoPassageiro)) {
					pontosEmComum++;
				}
			}
		}
		
		if (pontosEmComum == 1) {
			compatibilidade = 30;
		}
		
		if (pontosEmComum == 2) {
			compatibilidade = 70;
		}
		
		if (pontosEmComum == 3) {
			compatibilidade = 100;
		}
		
		return compatibilidade;
	}
	
	public String[] confirmarPassageiros(int compatibilidadeMinima) {
		int indiceAdicao = 0;
		proximoConfirmado = 0;
				
			for (Passageiro passageiro : inscritos.values()) {
				int compatibilidadePassageiro = calcularCompatibilidade(passageiro.getPontos());
				if (compatibilidadePassageiro >= compatibilidadeMinima && indiceAdicao < vagas) {
					confirmados[indiceAdicao] = passageiro;
					proximoConfirmado++;
					indiceAdicao++;
				}
			}
			
			return listarConfirmados();
	}

}
