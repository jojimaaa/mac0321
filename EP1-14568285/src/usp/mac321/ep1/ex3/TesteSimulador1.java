package usp.mac321.ep1.ex3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;

public class TesteSimulador1 {

	private Simulador1 s;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@BeforeEach
	public void setup() {
		s = new Simulador1();
		System.setOut(new PrintStream(outContent, true));
	}
	
	@AfterEach
	public void cleanup() {
		s = null;
		System.setOut(null);
	}
	
	@Test
	public void TesteSim1() {
		long tm = System.currentTimeMillis();
		s.restart(tm);
		s.run();
		assertEquals("Simulação Resetada\n"
				+ "0 min\n"
				+ "Médico criado\n"
				+ "Paciente criado\n"
				+ "Medico consulta temperatura: 36.5\n"
				+ "Medico consulta concentração: 110000.0\n"
				+ "5 min\n"
				+ "Paciente inicia surto infecioso\n"
				+ "10 min\n"
				+ "Medico consulta temperatura: 41.5\n"
				+ "Medico consulta concentração: 110000.0\n"
				+ "Paciente inicia surto infecioso\n"
				+ "15 min\n"
				+ "Paciente inicia surto infecioso\n"
				+ "20 min\n"
				+ "Medico consulta temperatura: 41.5\n"
				+ "Medico consulta concentração: 110000.0\n"
				+ "Paciente inicia surto infecioso\n"
				+ "25 min\n"
				+ "Paciente inicia surto infecioso\n"
				+ "30 min\n"
				+ "Medico consulta temperatura: 41.5\n"
				+ "Medico consulta concentração: 110000.0\n"
				+ "Paciente inicia surto infecioso\n"
				+ "35 min\n"
				+ "Paciente inicia surto infecioso\n"
				+ "40 min\n"
				+ "Medico consulta temperatura: 41.5\n"
				+ "Medico consulta concentração: 110000.0\n"
				+ "Paciente inicia surto infecioso\n"
				+ "45 min\n"
				+ "Paciente inicia surto infecioso\n"
				+ "50 min\n"
				+ "Medico consulta temperatura: 41.5\n"
				+ "Medico consulta concentração: 110000.0\n"
				+ "Paciente inicia surto infecioso\n"
				+ "55 min\n"
				+ "Paciente inicia surto infecioso\n"
				+ "60 min\n"
				+ "Medico consulta temperatura: 41.5\n"
				+ "Medico consulta concentração: 110000.0\n"
				+ "Paciente inicia surto infecioso\n"
				+ "65 min\n"
				+ "Paciente inicia surto infecioso\n"
				+ "70 min\n"
				+ "Médico verifica morte do paciente\n"
				+ "Simulação terminada\n", outContent.toString());
	}
	
}
