package usp.mac321.ep2.ex02;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usp.mac321.ep2.ex01.*;

public class TestaEscritaPlanilhas {
    private UsuarioManager usuarioManager;
    private TipoDespesaManager tipoDespesaManager;
    private TipoReceitaManager tipoReceitaManager;
    private LancamentoManager lancamentoManager;
    public LeitorImplementation leitor;

    
    

    @BeforeEach
    public void setup(){
        leitor  = new LeitorImplementation();
    }

    @AfterEach
    public void tearDown(){
        leitor = null;
    }
    
    @Test
    public void testeUsuarioManager(){
        List<Usuario> usuariosExpected = new ArrayList<Usuario>();
        for (Usuario u : leitor.leUsuarios("expectedOutputs\\usuariosExpected.csv")) {
            usuariosExpected.add(u);
        }
        leitor.clear();
        List<Usuario> written;
        Usuario u = new Usuario("Carlinhos", "Carlos Eleutério");
        usuarioManager = new UsuarioManager(leitor.leUsuarios("csv\\usuarios.csv"));
        leitor.clear();
        usuarioManager.add(u);
        usuarioManager.write("outputs\\usuariosOutput.csv");
        written = leitor.leUsuarios("outputs\\usuariosOutput.csv");
        for (int i = 0; i < written.size(); i++) {
            assertTrue(written.get(i).equals(usuariosExpected.get(i)));
        }
    }

    @Test
    public void testeTipoDespesaManager(){
        List<TipoDespesa> tiposDespesasExpected = new ArrayList<TipoDespesa>();
        for (TipoDespesa td : leitor.leTiposDespesas("expectedOutputs\\tiposDespesasExpected.csv")) {
            tiposDespesasExpected.add(td);
        }
        leitor.clear();
        List<TipoDespesa> written;
        TipoDespesa td = new TipoDespesa("Alimentação", "McDonalds");
        tipoDespesaManager = new TipoDespesaManager(leitor.leTiposDespesas("csv\\tiposDespesas.csv"));
        leitor.clear();
        tipoDespesaManager.add(td);
        tipoDespesaManager.write("outputs\\tiposDespesasOutput.csv");
        written = leitor.leTiposDespesas("outputs\\tiposDespesasOutput.csv");
        for (int i = 0; i < written.size(); i++) {
            assertTrue(written.get(i).equals(tiposDespesasExpected.get(i)));
        }
    }

    @Test
    public void testeTipoReceitaManager(){
        List<TipoReceita> tiposReceitasExpected = new ArrayList<TipoReceita>();
        for (TipoReceita tr : leitor.leTiposReceitas("expectedOutputs\\tiposReceitasExpected.csv")) {
            tiposReceitasExpected.add(tr);
        }
        leitor.clear();
        List<TipoReceita> written;
        TipoReceita tr = new TipoReceita("Jogo", "TigerFortune");
        tipoReceitaManager = new TipoReceitaManager(leitor.leTiposReceitas("csv\\tiposReceitas.csv"));
        leitor.clear();
        tipoReceitaManager.add(tr);
        tipoReceitaManager.write("outputs\\tiposReceitasOutput.csv");
        written = leitor.leTiposReceitas("outputs\\tiposReceitasOutput.csv");
        for (int i = 0; i < written.size(); i++) {
            assertTrue(written.get(i).equals(tiposReceitasExpected.get(i)));
        }
    }

    @Test
    public void testeLancamentoManager(){
        List<Lancamento> lancamentosExpected = new ArrayList<Lancamento>();
        leitor.leTiposReceitas("expectedOutputs\\tiposReceitasExpected.csv");
        leitor.leTiposDespesas("expectedOutputs\\tiposDespesasExpected.csv");
        leitor.leUsuarios("expectedOutputs\\usuariosExpected.csv");
        for (Lancamento l : leitor.leLancamentos("expectedOutputs\\lancamentosExpected.csv")) {
            lancamentosExpected.add(l);
        }
        leitor.clear();

        leitor.leTiposDespesas("csv\\tiposDespesas.csv");
		leitor.leTiposReceitas("csv\\tiposReceitas.csv");
		leitor.leUsuarios("csv\\usuarios.csv");
        lancamentoManager = new LancamentoManager();
        /* lancamentoManager = new LancamentoManager(leitor.leLancamentos("csv\\lancamentos.csv")); */
        lancamentoManager = lancamentoManager.fromLancamentoList(leitor.leLancamentos("csv\\lancamentos.csv"));
        leitor.clear();
        
        Lancamento l = new Lancamento(9,"22/06/24","Carlinhos",true,"McDonalds",50,"Big Tasty");
        lancamentoManager.add(l);
        lancamentoManager.write("outputs\\lancamentosOutput.csv");
        
        List<Lancamento> written;
        leitor.leTiposReceitas("expectedOutputs\\tiposReceitasExpected.csv");
        leitor.leTiposDespesas("expectedOutputs\\tiposDespesasExpected.csv");
        leitor.leUsuarios("expectedOutputs\\usuariosExpected.csv");
        written = leitor.leLancamentos("outputs\\lancamentosOutput.csv");
        for (int i = 0; i < written.size(); i++) {
            assertTrue(written.get(i).equals(lancamentosExpected.get(i)));
        }
    }
}
