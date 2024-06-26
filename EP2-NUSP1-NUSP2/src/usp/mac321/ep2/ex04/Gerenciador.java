package usp.mac321.ep2.ex04;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import usp.mac321.ep2.ex01.*;
import usp.mac321.ep2.ex03.*;
import usp.mac321.ep2.exceptions.InvalidLancamentoInput;
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
        lancamentoManager = lancamentoManager
                .fromLancamentoTunadoList(leitor.leLancamentosTunados(nomeArquivoLancamentos));
    }

    public boolean isLancamentoValido(LancamentoTunado lancamento) {
        String userResponsable = lancamento.getUserResponsable();
        boolean tipo = lancamento.getTipo();
        String subcategoria = lancamento.getSubcategoria();
        double valor = lancamento.getValor();
        if ((tipo == D && !isSubcategoriaDespesa(subcategoria)) ||
                (tipo == R && !isSubcategoriaReceita(subcategoria) ||
                        (!usuarioExiste(userResponsable)) ||
                        (valor < 0)))
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
     * > 0 = date is in the past (date is before today)
     * 0 = date is today
     * < 0 = date is in the future (date is after today)
     * see
     * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html#compareTo(
     * java.util.Calendar)
     */
    public int DateComparison(String data) {
        Date dataFormatada = null;
        try {
            dataFormatada = new SimpleDateFormat("dd/MM/yy").parse(data);
        } catch (ParseException e) {
            System.err.println("Data inválida, insira a data no formato dd/MM/yy");
            return -1;
        }
        Calendar hoje = new GregorianCalendar();
        Calendar dataLancamento = new GregorianCalendar();
        dataLancamento.setTime(dataFormatada);
        return hoje.compareTo(dataLancamento);
    }

    /*
     * returns:
     * > 0 = date1 is after date2
     * 0 = date1 is equal to date2
     * < 0 = date1 is before date2
     * see
     * https://docs.oracle.com/javase/7/docs/api/java/util/Date.html#compareTo(java.
     * util.Date)
     */
    public int DateComparison(String data1, String data2) {
        Date data1Formatada = null;
        Date data2Formatada = null;
        try {
            data1Formatada = new SimpleDateFormat("dd/MM/yy").parse(data1);
            data2Formatada = new SimpleDateFormat("dd/MM/yy").parse(data2);

        } catch (ParseException e) {
            System.err.println("Data inválida, insira a data no formato dd/MM/yy");
            return -1;
        }
        return data1Formatada.compareTo(data2Formatada);
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

    public void statusFixAll(){
        for (Lancamento l : lancamentoManager.getAll()) {
            if (l instanceof LancamentoTunado)
                statusFix((LancamentoTunado) l);
        }
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
        LancamentoTunado lancamento = new LancamentoTunado(id, data, userResponsable, tipo, subcategoria, valor,
                descricao);
        try {
            if (!isLancamentoValido(lancamento)) {
                throw new InvalidLancamentoInput("Lançamento Inválido");
            }
        } catch (Exception e) {
            System.err.println("Lancamento " + id + " inválido");
            lancamento.setStatus("Inválido");
        }
        return lancamentoManager.add(lancamento);
    }

    public boolean removeLancamento(int id) {
        return lancamentoManager.remove(id);
    }

    public boolean removeUsuario(String apelido) {
        if (usuarioManager.remove(apelido)) {
            for (Lancamento l : lancamentoManager.getAll()) {
                if (l instanceof LancamentoTunado)
                    statusFix((LancamentoTunado) l);
            }
            return true;
        }
        return false;
    }

    public boolean removeTipoDespesa(String Categoria, String Subcategoria) {
        if (tipoDespesaManager.remove(Categoria, Subcategoria)) {
            for (Lancamento l : lancamentoManager.getAll()) {
                if (l instanceof LancamentoTunado)
                    statusFix((LancamentoTunado) l);
            }
            return true;
        }
        return false;
    }

    public boolean removeTipoReceita(String Categoria, String Subcategoria) {
        if (tipoReceitaManager.remove(Categoria, Subcategoria)) {
            for (Lancamento l : lancamentoManager.getAll()) {
                if (l instanceof LancamentoTunado)
                    statusFix((LancamentoTunado) l);
            }
            return true;
        }
        return false;
    }

    public boolean mudaValor(int id, double valor) {
        Lancamento l = lancamentoManager.get(id);
        if (l == null)
            return false;
        if (l instanceof LancamentoTunado) {
            ((LancamentoTunado) l).setValor(valor);
            statusFix((LancamentoTunado) l);
            return true;
        }
        return false;
    }

    public boolean mudaDescricao(int id, String descricao) {
        Lancamento l = lancamentoManager.get(id);
        if (l == null)
            return false;
        if (l instanceof LancamentoTunado) {
            ((LancamentoTunado) l).setDescricao(descricao);
            return true;
        }
        return false;
    }

    public boolean mudaData(int id, String data) {
        Lancamento l = lancamentoManager.get(id);
        if (l == null)
            return false;
        if (l instanceof LancamentoTunado) {
            ((LancamentoTunado) l).setData(data);
            statusFix((LancamentoTunado) l);
            return true;
        }
        return false;
    }

    public boolean mudaSubcategoria(int id, String subcategoria) {
        Lancamento l = lancamentoManager.get(id);
        if (l == null)
            return false;
        if (l instanceof LancamentoTunado) {
            ((LancamentoTunado) l).setSubcategoria(subcategoria);
            statusFix((LancamentoTunado) l);
            return true;
        }
        return false;
    }

    public boolean mudaTipo(int id, boolean tipo) {
        Lancamento l = lancamentoManager.get(id);
        if (l == null)
            return false;
        if (l instanceof LancamentoTunado) {
            ((LancamentoTunado) l).setTipo(tipo);
            statusFix((LancamentoTunado) l);
            return true;
        }
        return false;
    }

    public boolean mudaUsuario(int id, String usuario) {
        Lancamento l = lancamentoManager.get(id);
        if (l == null)
            return false;
        if (l instanceof LancamentoTunado) {
            ((LancamentoTunado) l).setUsuario(usuario);
            statusFix((LancamentoTunado) l);
            return true;
        }
        return false;
    }

    public String getStatus(int id) {
        Lancamento l = lancamentoManager.get(id);
        if (l == null)
            return "Lançamento não encontrado";
        if (l instanceof LancamentoTunado)
            return ((LancamentoTunado) l).getStatus();
        return "Lançamento não é do tipo LancamentoTunado";
    }

    public void currentState(String parentAddress) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy@HH_mm");
        String formattedNow = now.format(formatter);

        lancamentoManager.write(parentAddress + "\\lancamentos" + formattedNow + ".csv");
        usuarioManager.write(parentAddress + "\\usuarios" + formattedNow + ".csv");
        tipoDespesaManager.write(parentAddress + "\\tiposDespesas" + formattedNow + ".csv");
        tipoReceitaManager.write(parentAddress + "\\tiposReceitas" + formattedNow + ".csv");
    }

    public double totalValidValue(String initialDate, String finalDate) {
        double total = 0;
        total += totalCategoryValue(initialDate, finalDate, R);
        total -= totalCategoryValue(initialDate, finalDate, D);
        return total;
    }

    public double totalCategoryValue(String initialDate, String finalDate, boolean tipo) {
        double total = 0;
        if (tipo == R)
            for (TipoReceita tr : tipoReceitaManager.getAll()) {
                total += totalSubcategoryValue(initialDate, finalDate, tipo, tr.getSubcategoria());
            }
        else
            for (TipoDespesa td : tipoDespesaManager.getAll()) {
                total += totalSubcategoryValue(initialDate, finalDate, tipo, td.getSubcategoria());
            }
        return total;
    }

    public double totalSubcategoryValue(String initialDate, String finalDate, boolean tipo, String subcategoria) {
        if ((tipo == R && !isSubcategoriaReceita(subcategoria)) ||
                (tipo == D && !isSubcategoriaDespesa(subcategoria)))
            return -1;

        double total = 0;
        for (Lancamento l : lancamentoManager.getAll()) {
            if (l instanceof LancamentoTunado) {
                if (l.getTipo() == tipo && l.getSubcategoria().equals(subcategoria)
                        && !((LancamentoTunado) l).getStatus().equals("Inválido")
                        && DateComparison(l.getData(), initialDate) >= 0
                        && DateComparison(l.getData(), finalDate) <= 0) {
                    total += l.getValor();
                }
            }
        }
        return total;
    }

    public void printRelatorio(String initialDate, String finalDate, String address, boolean verbose) {
        if (!verbose) {
            try {
                PrintWriter writer = new PrintWriter(address, "UTF-8");
                writer.println("Relatório de " + initialDate + " até " + finalDate);
                writer.println("Receitas: " + totalCategoryValue(initialDate, finalDate, R));
                writer.println("Despesas: " + totalCategoryValue(initialDate, finalDate, D));
                writer.println("Total: " + totalValidValue(initialDate, finalDate));
                writer.close();
            } catch (FileNotFoundException e) {
                System.err.println("Arquivo não encontrado");
            } catch (UnsupportedEncodingException e) {
                System.err.println("Encoding não suportado");
            }
        } else {
            ArrayList<String> CategoriasR = new ArrayList<String>();
            ArrayList<String> CategoriasD = new ArrayList<String>();
            ArrayList<String> SubcategoriasD;
            ArrayList<String> SubcategoriasR;

            try {
                PrintWriter writer = new PrintWriter(address, "UTF-8");
                writer.println("Relatório de " + initialDate + " até " + finalDate);
                writer.println("Receitas:");
                for (TipoReceita tr : tipoReceitaManager.getAll()) {
                    if (!CategoriasR.contains(tr.getCategoria())) {
                        writer.println("    Categoria: " + tr.getCategoria());
                        CategoriasR.add(tr.getCategoria());
                        SubcategoriasR = new ArrayList<String>();
                        for (TipoReceita trr : tipoReceitaManager.getAll()) {
                            if (!SubcategoriasR.contains(trr.getSubcategoria())
                                    && trr.getCategoria().equals(tr.getCategoria())) {
                                SubcategoriasR.add(trr.getSubcategoria());
                                writer.println("        Subcategoria: " + trr.getSubcategoria());
                                for (Lancamento lancamento : lancamentoManager.getAll()) {
                                    if (lancamento instanceof LancamentoTunado &&
                                            lancamento.getTipo() == R
                                            && lancamento.getSubcategoria().equals(trr.getSubcategoria())
                                            && !((LancamentoTunado) lancamento).getStatus().equals("Inválido")
                                            && DateComparison(lancamento.getData(), initialDate) >= 0
                                            && DateComparison(lancamento.getData(), finalDate) <= 0){
                                        writer.println("            " + lancamento.getData() + " | "
                                                + lancamento.getUserResponsable() + " | " + lancamento.getValor());
                                    }
                                }
                            }
                        }
                    }
                }

                writer.println("");
                writer.println("Despesas:");
                for (TipoDespesa td : tipoDespesaManager.getAll()) {
                    if (!CategoriasD.contains(td.getCategoria())) {
                        writer.println("    Categoria: " + td.getCategoria());
                        CategoriasD.add(td.getCategoria());
                        SubcategoriasD = new ArrayList<String>();
                        for (TipoDespesa tdd : tipoDespesaManager.getAll()) {
                            if (!SubcategoriasD.contains(tdd.getSubcategoria())
                                    && tdd.getCategoria().equals(td.getCategoria())) {
                                writer.println("        Subcategoria: " + tdd.getSubcategoria());
                                SubcategoriasD.add(tdd.getSubcategoria());
                                for (Lancamento lancamento : lancamentoManager.getAll()) {
                                    if (lancamento instanceof LancamentoTunado &&
                                            lancamento.getTipo() == D
                                            && lancamento.getSubcategoria().equals(tdd.getSubcategoria())
                                            && !(((LancamentoTunado) lancamento).getStatus().equals("Inválido"))
                                            && DateComparison(lancamento.getData(), initialDate) >= 0
                                            && DateComparison(lancamento.getData(), finalDate) <= 0) {
                                        writer.println("            " + lancamento.getData() + " | "
                                                + lancamento.getUserResponsable() + " | " + lancamento.getValor());
                                    }
                                }
                            }
                        }
                    }
                }
                writer.println("");
                writer.println("Saldo: " + totalValidValue(initialDate, finalDate));
                writer.close();
                CategoriasD = null;
                CategoriasR = null;
                SubcategoriasD = null;
                SubcategoriasR = null;
            } catch (FileNotFoundException e) {
                System.err.println("Arquivo não encontrado");
            } catch (UnsupportedEncodingException e) {
                System.err.println("Encoding não suportado");
            }
        }
    }
}
