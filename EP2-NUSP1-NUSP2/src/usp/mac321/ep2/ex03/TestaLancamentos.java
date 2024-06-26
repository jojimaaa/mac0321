package usp.mac321.ep2.ex03;

import usp.mac321.ep2.ex01.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestaLancamentos {
    public final boolean R = false;
    public final boolean D = true;

    private LeitorTunadoImplementation leitor = new LeitorTunadoImplementation();
    private LancamentoTunadoManager manager;

    @BeforeEach
    public void setUp() {
        leitor.leUsuarios("csv\\usuarios.csv");
        leitor.leTiposDespesas("csv\\tiposDespesas.csv");
        leitor.leTiposReceitas("csv\\tiposReceitas.csv");
        leitor.leLancamentosTunados("csv\\lancamentos.csv");
        manager = new LancamentoTunadoManager();
        manager = manager.fromLancamentoTunadoList(leitor.getLancamentos());
    }

    @AfterEach
    public void tearDown() {
        leitor = null;
    }

    @Test
    public void testaExecutado() {
        LancamentoTunado novo = new LancamentoTunado(9, "01/01/21", "Pai", true, "Cinema", 100.0, "Divertidamente 2"); // Executado
        manager.add(novo);
        assertEquals(novo.getStatus(), "Executado");
    }

    @Test
    public void testaPlanejado() {
        LancamentoTunado novo = new LancamentoTunado(9, "01/01/25", "Pai", true, "Cinema", 100.0, "Divertidamente 2"); // Planejado
        manager.add(novo);
        assertEquals(novo.getStatus(), "Planejado");
    }

    @Test
    public void testaInvalidoValor() {
        LancamentoTunado novo = new LancamentoTunado(9, "01/01/21", "Pai", true, "Cinema", -100.0, "Divertidamente 2"); // Inválido
        manager.add(novo);
        assertEquals(novo.getStatus(), "Inválido");
    }

    @Test
    public void testaInvalidoApelido(){
        LancamentoTunado novo = new LancamentoTunado(9, "01/01/21", "Mãe", true, "Cinema", 100.0, "Divertidamente 2"); // Inválido
        manager.add(novo);
        statusFix(novo);
        assertEquals(novo.getStatus(), "Inválido");
    }

    @Test
    public void testaInvalidoID(){

        manager = manager.fromLancamentoTunadoList(leitor.getLancamentos());
        LancamentoTunado novo = new LancamentoTunado(1, "01/01/21", "Pai", true, "Cinema", 100.0, "Divertidamente 2"); // Inválido
        manager.add(novo);
        assertEquals(novo.getStatus(), "Inválido");
    }

    @Test
    public void testaInvalidoTipo(){
        LancamentoTunado novo = new LancamentoTunado(9, "01/01/21", "Pai", false, "Cinema", 100.0, "Divertidamente 2"); // Inválido
        manager.add(novo);
        statusFix(novo);
        assertEquals(novo.getStatus(), "Inválido");
    }

    @Test void ExecutadoToPlanejado(){
        LancamentoTunado novo = new LancamentoTunado(9, "01/01/23", "Pai", true, "Cinema", 100.0, "Divertidamente 2"); // Inválido
        manager.add(novo);
        statusFix(novo);
        assertEquals(novo.getStatus(), "Executado");
        novo.setData("01/01/25");
        statusFix(novo);
        assertEquals(novo.getStatus(), "Planejado");
    }

    @Test void PlanejadoToExecutado(){
        LancamentoTunado novo = new LancamentoTunado(9, "01/01/25", "Pai", true, "Cinema", 100.0, "Divertidamente 2"); // Inválido
        manager.add(novo);
        statusFix(novo);
        assertEquals(novo.getStatus(), "Planejado");
        novo.setData("01/01/23");
        statusFix(novo);
        assertEquals(novo.getStatus(), "Executado");
    }

    @Test void PlanejadoToInvalido(){
        LancamentoTunado novo = new LancamentoTunado(9, "01/01/25", "Pai", true, "Cinema", 100.0, "Divertidamente 2"); // Inválido
        manager.add(novo);
        statusFix(novo);
        assertEquals(novo.getStatus(), "Planejado");
        novo.setValor(-100.0);
        statusFix(novo);
        assertEquals(novo.getStatus(), "Inválido");
    }

    @Test void ExecutadoToInvalido(){
        LancamentoTunado novo = new LancamentoTunado(9, "01/01/23", "Pai", true, "Cinema", 100.0, "Divertidamente 2"); // Inválido
        manager.add(novo);
        statusFix(novo);
        assertEquals(novo.getStatus(), "Executado");
        novo.setValor(-100);
        statusFix(novo);
        assertEquals(novo.getStatus(), "Inválido");
    }

    @Test void InvalidoToExecutado(){
        LancamentoTunado novo = new LancamentoTunado(9, "01/01/23", "Pai", true, "Cinema", -100.0, "Divertidamente 2"); // Inválido
        manager.add(novo);
        statusFix(novo);
        assertEquals(novo.getStatus(), "Inválido");
        novo.setValor(100);
        statusFix(novo);
        assertEquals(novo.getStatus(), "Executado");
    }

    @Test void InvalidoToPlanejado(){
        LancamentoTunado novo = new LancamentoTunado(9, "01/01/25", "Pai", true, "Cinema", -100.0, "Divertidamente 2"); // Inválido
        manager.add(novo);
        statusFix(novo);
        assertEquals(novo.getStatus(), "Inválido");
        novo.setValor(100);
        statusFix(novo);
        assertEquals(novo.getStatus(), "Planejado");
    }


/*
 * Abaixo temos uma parte do código simulando o gerenciador, que cuidará do intermediário entre os lançamentos e os demais componentes como usuários e tipos de despesas e receitas.
 */
    public boolean isLancamentoValido(LancamentoTunado lancamento) {
        String userResponsable = lancamento.getUserResponsable();
        boolean tipo = lancamento.getTipo();
        String subcategoria = lancamento.getSubcategoria();
        double valor = lancamento.getValor();
        if ((tipo == D && !isSubcategoriaDespesa(subcategoria))
                || (tipo == R && !isSubcategoriaReceita(subcategoria)
                        || (!usuarioExiste(userResponsable))
                        || (valor < 0)))
            return false;
        return true;
    }

    public boolean isSubcategoriaDespesa(String subcategoria) {
        for (TipoDespesa td : leitor.getTiposDespesas()) {
            if (td.getSubcategoria().equals(subcategoria)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSubcategoriaReceita(String subcategoria) {
        for (TipoReceita tr : leitor.getTiposReceitas()) {
            if (tr.getSubcategoria().equals(subcategoria)) {
                return true;
            }
        }
        return false;
    }

    public boolean usuarioExiste(String apelido) {
        for (Usuario usuario : leitor.getUsuarios()) {
            if (usuario.getApelido().equals(apelido)) {
                return true;
            }
        }
        return false;
    }

    /*
     * returns:
     * > 0 = date is in the past
     * 0 = date is today
     * < 0 = date is in the future
     * see
     * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html#compareTo(
     * java.util.Calendar)
     */
    public int DateComparison(String data) {
        Date dataFormatada = null;
        try {
            System.out.println(data);
            dataFormatada = new SimpleDateFormat("dd/MM/yy").parse(data);
            System.out.println(dataFormatada);
        } catch (ParseException e) {
            System.err.println("Data inválida");
        }
        Calendar hoje = new GregorianCalendar();
        Calendar dataLancamento = new GregorianCalendar();
        dataLancamento.setTime(dataFormatada);
        return hoje.compareTo(dataLancamento);
    }

    public boolean statusFix(LancamentoTunado lancamento) {
        if (lancamento == null)
            return false;
        if (!isLancamentoValido(lancamento)) {
            lancamento.setStatus("Inválido");
            return true;
        }
        if (DateComparison(lancamento.getData()) >= 0) {
            lancamento.setStatus("Executado");
            return true;
        }
        lancamento.setStatus("Planejado");
        return true;
    }

}