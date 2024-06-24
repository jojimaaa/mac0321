package usp.mac321.ep2.ex04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import usp.mac321.ep2.ex01.*;
import usp.mac321.ep2.ex03.*;
import usp.mac321.ep2.ex02.*;

public class Gerenciador {

	public final boolean R = false;
	public final boolean D = true;

    private LeitorTunadoImplementation leitor = new LeitorTunadoImplementation();
    private LancamentoTunadoManager lancamentoManager;
    private UsuarioManager usuarioManager;
    private TipoDespesaManager tipoDespesaManager;
    private TipoReceitaManager tipoReceitaManager;

    public Gerenciador(String nomeArquivoUsuarios, String nomeArquivoTiposDespesas, String nomeArquivoTiposReceitas,
            String nomeArquivoLancamentos) {
        usuarioManager = new UsuarioManager(leitor.leUsuarios(nomeArquivoUsuarios));
        tipoDespesaManager = new TipoDespesaManager(leitor.leTiposDespesas(nomeArquivoTiposDespesas));
        tipoReceitaManager = new TipoReceitaManager(leitor.leTiposReceitas(nomeArquivoTiposReceitas));
        lancamentoManager = new LancamentoTunadoManager();
        lancamentoManager = lancamentoManager.fromLancamentoTunadoList(leitor.leLancamentosTunados(nomeArquivoLancamentos));
    }

    public boolean criaUsuario(String apelido, String nome) {
        Usuario usuario = new Usuario(apelido, nome);
        return usuarioManager.add(usuario);
    }

    public boolean criaTipoDespesa(String Categoria, String Subcategoria) {
        TipoDespesa tipoDespesa = new TipoDespesa(Categoria, Subcategoria);
        return tipoDespesaManager.add(tipoDespesa);
    }

    public boolean criaTipoReceita(String Categoria, String Subcategoria) {
        TipoReceita tipoReceita = new TipoReceita(Categoria, Subcategoria);
        return tipoReceitaManager.add(tipoReceita);
    }

    public boolean criaLancamento(int id, String data, String userResponsable, boolean tipo, String subcategoria,
            double valor, String descricao) {
        LancamentoTunado lancamento = new LancamentoTunado(id, data, userResponsable, tipo, subcategoria, valor, descricao);
        if(!isLancamentoValido(lancamento) || lancamentoManager.doesIDAlreadyExist(id)) lancamento.setStatus("Inválido");
        return lancamentoManager.add(lancamento);
    }

    public boolean isLancamentoValido(LancamentoTunado lancamento){
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
        for (TipoDespesa td : this.tipoDespesaManager.getAll()) {
            if (td.getSubcategoria().equals(subcategoria)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSubcategoriaReceita(String subcategoria) {
        for (TipoReceita tr : this.tipoReceitaManager.getAll()) {
            if (tr.getSubcategoria().equals(subcategoria)) {
                return true;
            }
        }
        return false;
    }

    public boolean usuarioExiste(String apelido) {
        for (Usuario usuario : this.usuarioManager.getAll()) {
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
     * see https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html#compareTo(java.util.Calendar)
     */
    public int DateComparison(String data){
        Date dataFormatada = null;
        try {
            dataFormatada = new SimpleDateFormat("dd/MM/YY").parse(data);
        } catch (ParseException e) {
            System.err.println("Data inválida");
        }
        Calendar hoje = new GregorianCalendar();
        Calendar dataLancamento = new GregorianCalendar();
        dataLancamento.setTime(dataFormatada);
        return hoje.compareTo(dataLancamento);
    }

    public boolean statusFix(LancamentoTunado lancamento){
        if(lancamento == null) return false;
        if(!isLancamentoValido(lancamento)){
            lancamento.setStatus("Inválido");
            return true;
        }
        if(DateComparison(lancamento.getData()) >= 0){
            lancamento.setStatus("Executado");
            return true;
        }
        lancamento.setStatus("Planejado");
        return true;
    }

    public boolean removeLancamento(int id){
        return lancamentoManager.remove(id);
    }

    public boolean removeUsuario(String apelido){
        if(usuarioManager.remove(apelido)){
            for(Lancamento l : lancamentoManager.getAll()){
                if(l instanceof LancamentoTunado)
                    statusFix((LancamentoTunado) l);
            }
            return true;
        }
        return false;
    }

    public boolean removeTipoDespesa(String Categoria, String Subcategoria){
        if(tipoDespesaManager.remove(Categoria, Subcategoria)){
            for(Lancamento l : lancamentoManager.getAll()){
                if(l instanceof LancamentoTunado)
                    statusFix((LancamentoTunado) l);
            }
            return true;
        }
        return false;
    }

    public boolean removeTipoReceita(String Categoria, String Subcategoria){
        if(tipoReceitaManager.remove(Categoria, Subcategoria)){
            for(Lancamento l : lancamentoManager.getAll()){
                if(l instanceof LancamentoTunado)
                    statusFix((LancamentoTunado) l);
            }
            return true;
        }
        return false;
    }

    public boolean mudaValor(int id, double valor){
        Lancamento l = lancamentoManager.get(id);
        if(l == null) return false;
        if(l instanceof LancamentoTunado){
            ((LancamentoTunado) l).setValor(valor);
            statusFix((LancamentoTunado) l);
        return true;
        }
        return false;
    }
}
