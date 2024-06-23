package usp.mac321.ep2.ex01;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestaLeitorFinancasPessoais {

	List<Usuario> usuarios;
	List<TipoDespesa> tiposDespesas;
	List<TipoReceita> tiposReceitas;
	List<Lancamento> lancamentos;
	LeitorFinancasPessoaisDAO leitor;

	@BeforeEach
	void setUp() throws Exception {
		leitor = new LeitorImplementation();
	}

	@AfterEach
	void tearDown() throws Exception {
		leitor = null;
	}

	@Test
	public void testTiposDespesas() {
		tiposDespesas = leitor.leTiposDespesas("csv\\tiposDespesas.csv");
		assertEquals(6, tiposDespesas.size());
	}

	@Test
	public void testTiposReceitas() {
		tiposReceitas = leitor.leTiposReceitas("csv\\tiposReceitas.csv");
		assertEquals(4, tiposReceitas.size());
	}

	@Test
	public void testUsuarios() {
		usuarios = leitor.leUsuarios("csv\\usuarios.csv");
		assertEquals(2, usuarios.size());
	}

	@Test
	public void testLancamentosOK() {
		leitor.leTiposDespesas("csv\\tiposDespesas.csv");
		leitor.leTiposReceitas("csv\\tiposReceitas.csv");
		leitor.leUsuarios("csv\\usuarios.csv");
		lancamentos = leitor.leLancamentos("csv\\lancamentos.csv");
		assertEquals(8, lancamentos.size());
	}

	@Test
	public void testLancamentoUsuarioDesconhecido() {
		leitor.leTiposDespesas("csv\\tiposDespesas.csv");
		leitor.leTiposReceitas("csv\\tiposReceitas.csv");
		leitor.leUsuarios("csv\\usuarios.csv");
		InvalidLancamentoInput e = assertThrows(InvalidLancamentoInput.class, () -> 
			leitor.leLancamentos("csv\\lancamentosSemResponsa.csv"), "Expected leLancalancamentos to throw InvalidLancalmentoInput exception, but didn't");
		
		assertEquals("Usuário de lançamento não existe", e.getMessage());
	}

	@Test
	public void testLancamentoDespesaDesconhecida() {

		leitor.leTiposDespesas("csv\\tiposDespesas.csv");
		leitor.leTiposReceitas("csv\\tiposReceitas.csv");
		leitor.leUsuarios("csv\\usuarios.csv");
		InvalidLancamentoInput e = assertThrows(InvalidLancamentoInput.class, () -> 
			leitor.leLancamentos("csv\\lancamentosDespesaErrada.csv"), "Expected leLancalancamentos to throw InvalidLancalmentoInput exception, but didn't");
		
		assertEquals("Subcategoria incompatível com a categoria DESPESA", e.getMessage());

		
	}

	@Test
	public void testLancamentoReceiraDesconhecida() {
		leitor.leTiposDespesas("csv\\tiposDespesas.csv");
		leitor.leTiposReceitas("csv\\tiposReceitas.csv");
		leitor.leUsuarios("csv\\usuarios.csv");
		InvalidLancamentoInput e = assertThrows(InvalidLancamentoInput.class, () -> 
			leitor.leLancamentos("csv\\lancamentosReceitaErrada.csv"), "Expected leLancalancamentos to throw InvalidLancalmentoInput exception, but didn't");
		assertEquals("Subcategoria incompatível com a categoria RECEITA", e.getMessage());
	}

}
