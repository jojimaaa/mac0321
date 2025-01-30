package usp.mac321.ep1.ex2;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class Testes {
	private Paciente paciente;
	private Droga droga;
	private Medico medico;
	
	@BeforeEach
	public void setup() {
		paciente = new Paciente(50, 30);
		droga = new Droga(10, 20);
		medico = new Medico(100, droga, 60); 
	}
	
	//Testes Paciente ========================================================
	@Test
	public void testFreqPaciente() {
		assertEquals(50, paciente.freq());
	}
	
	@Test
	public void testTempCheck() {
		assertEquals(36.5, paciente.tempCheck(System.currentTimeMillis()));
	}
	
	@Test
	public void testIniciaSurto() {
		paciente.iniciaSurto();
		assertEquals(41.5, paciente.tempCheck(System.currentTimeMillis()));
	}
	
	@Test
	public void testPacCheck() {
		assertEquals(110000, paciente.pacCheck(System.currentTimeMillis()));
		paciente.recebeDroga(droga);
		assertEquals(109980, paciente.pacCheck(System.currentTimeMillis()));
	}
	
	@Test
	public void testDead(){
		assertFalse(paciente.dead());
	}
	
	@Test
	public void testDies() {
		paciente.dies();
		assertTrue(paciente.dead());
	}
	
	@Test
	public void testSick() {
		assertFalse(paciente.sick());
		paciente.iniciaSurto();
		assertTrue(paciente.sick());
	}
	
	@Test
	public void testRecebeDroga(){
		paciente.iniciaSurto();
		assertTrue(paciente.sick());
		paciente.recebeDroga(droga);
		assertFalse(paciente.sick());
		assertEquals(109980, paciente.pacCheck(System.currentTimeMillis()));
	}
	
	//Testes Medico ========================================================
	@Test
	public void testFreqMedico() {
		assertEquals(100, medico.freq());
	}
	
	@Test
	public void testAdministrarDrogas() {
		assertFalse(medico.administrarDrogas(paciente.tempCheck(System.currentTimeMillis())));
		paciente.iniciaSurto();
		assertTrue(medico.administrarDrogas(paciente.tempCheck(System.currentTimeMillis())));
	}
	
	//Testes Droga ===========================================================
	@Test
	public void testVdt() {
		assertEquals(10, droga.Vdt());
	}
	
	@Test
	public void testVdc() {
		assertEquals(20, droga.Vdc());
	}
}
