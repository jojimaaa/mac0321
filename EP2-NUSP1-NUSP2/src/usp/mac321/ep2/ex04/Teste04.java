package usp.mac321.ep2.ex04;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Testa as outras funcionalidades do Gerenciador
public class Teste04 {
    
    public final boolean R = false;
    public final boolean D = true;

    private String user = "src\\usp\\mac321\\ep2\\ex04\\constantInputs\\usuarios.csv";
    private String lancamentos = "src\\usp\\mac321\\ep2\\ex04\\constantInputs\\lancamentos.csv";
    private String despesas = "src\\usp\\mac321\\ep2\\ex04\\teste04\\inputs\\tiposDespesas04.csv";
    private String receitas = "src\\usp\\mac321\\ep2\\ex04\\teste04\\inputs\\tiposReceitas04.csv";
    private Gerenciador gerenciador;

    @BeforeEach
    public void setUp() {
        gerenciador = new Gerenciador(user, despesas, receitas, lancamentos);

    }

    @AfterEach
    public void tearDown() {
        gerenciador = null;
    }
    
    @Test
    public void testaDateComparison(){
        assertTrue(gerenciador.DateComparison("15/06/24","16/06/24") < 0);
        assertTrue(gerenciador.DateComparison("16/06/24","15/06/24") > 0);
        assertTrue(gerenciador.DateComparison("15/06/24","15/06/24") == 0);
    }
    
    @Test
    public void testaGetStatus(){
        assertEquals("Executado", gerenciador.getStatus(10));
        assertEquals("Inválido", gerenciador.getStatus(9));
        assertEquals("Lançamento não encontrado", gerenciador.getStatus(16));
    }
    
    @Test
    public void testaCriaUsuario() {
        assertTrue(gerenciador.criaUsuario("Joao", "Joao Legal Insano"));
        assertFalse(gerenciador.criaUsuario("Jojima", "AAAAAAAAAAAAAAAAAAAA"));
    }
    
    @Test
    public void testaCriaLancamento() {
        gerenciador.criaUsuario("Joao", "Joao Legal Insano");
        assertTrue(gerenciador.criaLancamento(16, "26/06/1960", "Joao", D,"Essencial", 165, "Teste"));
        assertEquals(gerenciador.getStatus(16), "Executado");
    }

    @Test
    public void testaCriaLancamentoInvalido() {
        gerenciador.criaUsuario("Joao", "Joao Legal Insano");
        assertTrue(gerenciador.criaLancamento(16, "26/06/1960", "Joao", D,"Essencial", -165, "Teste"));
        assertEquals(gerenciador.getStatus(16), "Inválido");
    }

    @Test
    public void testaCriaTipoDespesa(){
        assertTrue(gerenciador.criaTipoDespesa("Alimentação", "McDonalds"));
        assertFalse(gerenciador.criaTipoDespesa("Alimentação", "Essencial"));
    }

    @Test
    public void testaCriaTipoReceita(){
        assertTrue(gerenciador.criaTipoReceita("Salário", "Trabalho"));
        assertFalse(gerenciador.criaTipoReceita("Salário", "Principal"));
    }

    @Test
    public void testaRemoveUsuario(){
        assertTrue(gerenciador.removeUsuario("Jojima"));
        gerenciador.statusFixAll();
        assertEquals(gerenciador.getStatus(1), "Inválido");
        assertEquals(gerenciador.getStatus(3), "Inválido");
        assertEquals(gerenciador.getStatus(4), "Inválido");
        assertEquals(gerenciador.getStatus(7), "Inválido");
    }

    @Test
    public void testaRemoveTipoReceita(){
        assertTrue(gerenciador.removeTipoReceita("Salário", "Principal"));
        gerenciador.statusFixAll();
        assertEquals(gerenciador.getStatus(1), "Inválido");
        assertEquals(gerenciador.getStatus(2), "Inválido");
        assertEquals(gerenciador.getStatus(3), "Executado"); 
    }

    @Test
    public void testaRemoveTipoDespesa(){
        assertTrue(gerenciador.removeTipoDespesa("Alimentação", "Essencial"));
        gerenciador.statusFixAll();
        assertEquals(gerenciador.getStatus(8), "Inválido");
        assertEquals(gerenciador.getStatus(7), "Inválido");
        assertEquals(gerenciador.getStatus(3), "Executado"); 
    }

    @Test
    public void testaRemoveLancamento(){
        assertTrue(gerenciador.removeLancamento(1));
        assertEquals(gerenciador.getStatus(1), "Lançamento não encontrado"); 
    }

    @Test
    public void testaMudaValor(){
        assertTrue(gerenciador.mudaValor(1, -100));
        gerenciador.statusFixAll();
        assertEquals(gerenciador.getStatus(1), "Inválido");
    }

    @Test
    public void testaMudaData(){
        assertTrue(gerenciador.mudaData(1, "26/06/2052"));
        gerenciador.statusFixAll();
        assertEquals(gerenciador.getStatus(1), "Planejado");
    }

    @Test
    public void testaMudaSubcategoria(){
        assertTrue(gerenciador.mudaSubcategoria(1, "Tigrinho"));
        gerenciador.statusFixAll();
        assertEquals(gerenciador.getStatus(1), "Executado");
        assertTrue(gerenciador.mudaSubcategoria(1, "lasdfkasdgfa"));
        gerenciador.statusFixAll();
        assertEquals(gerenciador.getStatus(1), "Inválido");
    }

    @Test
    public void testaMudaTipo(){
        assertTrue(gerenciador.mudaTipo(1, D));
        gerenciador.statusFixAll();
        assertEquals(gerenciador.getStatus(1), "Inválido");

        gerenciador.mudaSubcategoria(1, "Essencial");
        gerenciador.statusFixAll();
        assertEquals(gerenciador.getStatus(1), "Executado");

        assertTrue(gerenciador.mudaTipo(1, R));
        gerenciador.statusFixAll();
        assertEquals(gerenciador.getStatus(1), "Inválido");
    }

    @Test
    public void testaMudaUsuario(){
        assertTrue(gerenciador.mudaUsuario(1, "Jorge"));
        gerenciador.statusFixAll();
        assertEquals(gerenciador.getStatus(1), "Inválido");

        assertTrue(gerenciador.mudaUsuario(1, "Tutu"));
        gerenciador.statusFixAll();
        assertEquals(gerenciador.getStatus(1), "Executado");
    }

    @Test
    public void testaTotalValidValue(){
        assertEquals(69483.15, gerenciador.totalValidValue("01/05/24", "05/05/24"));
        gerenciador.mudaUsuario(1, "Jorge");
        gerenciador.statusFixAll();
        assertEquals(69463.15, gerenciador.totalValidValue("01/05/24", "05/05/24"));
    }

    @Test
    public void testaTotalCategoryValue(){
        assertEquals(79020, gerenciador.totalCategoryValue("01/05/24", "06/05/24", R));
        gerenciador.mudaUsuario(1, "Jorge");
        gerenciador.statusFixAll();
        assertEquals(79000, gerenciador.totalCategoryValue("01/05/24", "06/05/24", R));
    }

    @Test
    public void testaTotalSubcategoryValue(){
        assertEquals(28020, gerenciador.totalSubcategoryValue("01/05/24", "06/05/24", R, "Principal"));
        gerenciador.mudaUsuario(2, "Jorge");
        gerenciador.statusFixAll();
        assertEquals(20, gerenciador.totalSubcategoryValue("01/05/24", "06/05/24", R, "Principal"));
    }

    @Test
    public void testaCurrentState() throws IOException{
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy@HH_mm");
        String formattedNow = now.format(formatter);
        gerenciador.currentState("src\\usp\\mac321\\ep2\\ex04\\teste04\\outputs");

        File outputLancamentos = new File("src\\usp\\mac321\\ep2\\ex04\\teste04\\outputs\\lancamentos"+formattedNow+".csv");
        File outputUsuarios = new File("src\\usp\\mac321\\ep2\\ex04\\teste04\\outputs\\usuarios"+formattedNow+".csv");
        File outputDespesas = new File("src\\usp\\mac321\\ep2\\ex04\\teste04\\outputs\\tiposDespesas"+formattedNow+".csv");
        File outputReceitas = new File("src\\usp\\mac321\\ep2\\ex04\\teste04\\outputs\\tiposReceitas"+formattedNow+".csv");
        
        File expectedLancamentos = new File("src\\usp\\mac321\\ep2\\ex04\\teste04\\expected\\lancamentosExpected04.csv");
        File expectedUsuarios = new File("src\\usp\\mac321\\ep2\\ex04\\teste04\\expected\\usuariosExpected04.csv");
        File expectedDespesas = new File("src\\usp\\mac321\\ep2\\ex04\\teste04\\expected\\tiposDespesasExpected04.csv");
        File expectedReceitas = new File("src\\usp\\mac321\\ep2\\ex04\\teste04\\expected\\tiposReceitasExpected04.csv");


        BufferedReader outputLancamentosReader = new BufferedReader(new FileReader(outputLancamentos));
        BufferedReader outputUsuariosReader = new BufferedReader(new FileReader(outputUsuarios));
        BufferedReader outputDespesasReader = new BufferedReader(new FileReader(outputDespesas));
        BufferedReader outputReceitasReader = new BufferedReader(new FileReader(outputReceitas));

        BufferedReader expectedLancamentosReader = new BufferedReader(new FileReader(expectedLancamentos));
        BufferedReader expectedUsuariosReader = new BufferedReader(new FileReader(expectedUsuarios));
        BufferedReader expectedDespesasReader = new BufferedReader(new FileReader(expectedDespesas));
        BufferedReader expectedReceitasReader = new BufferedReader(new FileReader(expectedReceitas));


        String outputLancamentosLine;
        String outputUsuariosLine;
        String outputDespesasLine;
        String outputReceitasLine;

        String expectedLancamentosLine;
        String expectedUsuariosLine;
        String expectedDespesasLine;
        String expectedReceitasLine;

        while((outputLancamentosLine = outputLancamentosReader.readLine()) != null && (expectedLancamentosLine = expectedLancamentosReader.readLine()) != null){
            assertEquals(outputLancamentosLine, expectedLancamentosLine);
        }

        while((outputUsuariosLine = outputUsuariosReader.readLine()) != null && (expectedUsuariosLine = expectedUsuariosReader.readLine()) != null){
            assertEquals(outputUsuariosLine, expectedUsuariosLine);
        }

        while((outputDespesasLine = outputDespesasReader.readLine()) != null && (expectedDespesasLine = expectedDespesasReader.readLine()) != null){
            assertEquals(outputDespesasLine, expectedDespesasLine);
        }

        while((outputReceitasLine = outputReceitasReader.readLine()) != null && (expectedReceitasLine = expectedReceitasReader.readLine()) != null){
            assertEquals(outputReceitasLine, expectedReceitasLine);
        }

        outputLancamentosReader.close();
        outputUsuariosReader.close();
        outputDespesasReader.close();
        outputReceitasReader.close();

        expectedLancamentosReader.close();
        expectedUsuariosReader.close();
        expectedDespesasReader.close();
        expectedReceitasReader.close();
    }
}
