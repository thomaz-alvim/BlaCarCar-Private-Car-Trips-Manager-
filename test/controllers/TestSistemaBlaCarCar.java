package controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes. Contém 2 testes da compatibilidade entre passageiro e viagem.
 */
class TestSistemaBlaCarCar {
	private SistemaBlaCarCar sistema;

	@BeforeEach
	void setUp() throws Exception {
		this.sistema = new SistemaBlaCarCar();
		
	}

	@Test
	void testCompatibilidadePassageiroViagem() {
		sistema.cadastrarPassageiro("Tomen", "00000000001", new String[] {"City-over-Yonder", "Lightwise", "Waveways"});
		int indiceViagem = sistema.cadastrarViagem("Fullor", "I learned to drive on Mario Kart!", 3, "001", new String[] {"City-over-Yonder", "Lightwise", "Figlindith"});
		int compatibilidade = sistema.calcularCompatibilidade("00000000001", indiceViagem);
		assertEquals(compatibilidade, 70);
	}
	
	@Test
	void testCompatibilidadePassageiroViagem2() {
		sistema.cadastrarPassageiro("Tomen", "00000000001", new String[] {"City-over-Yonder", "Lightwise", "Waveways"});
		int indiceViagem = sistema.cadastrarViagem("Fullor", "I learned to drive on Mario Kart!", 3, "001", new String[] {"", "", ""});
		int compatibilidade = sistema.calcularCompatibilidade("00000000001", indiceViagem);
		assertEquals(compatibilidade, 0);
	}

}
