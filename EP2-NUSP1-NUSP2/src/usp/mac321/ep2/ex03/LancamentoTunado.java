package usp.mac321.ep2.ex03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import usp.mac321.ep2.ex01.*;

public class LancamentoTunado extends Lancamento {

    private String status;

    public LancamentoTunado(int id, String data, String userResponsable, boolean tipo, String subcategoria,
            double valor, String descricao) {
        super(id, data, userResponsable, tipo, subcategoria, valor, descricao);
        Date dataFormatada = null;
        try {
            dataFormatada = new SimpleDateFormat("dd/MM/YY").parse(data);
        } catch (ParseException e) {
            System.err.println("Data inválida");
        }

        Calendar hoje = new GregorianCalendar();
        Calendar dataLancamento = new GregorianCalendar();
        dataLancamento.setTime(dataFormatada);
        if(hoje.compareTo(dataLancamento) >= 0){
            this.status = "Executado";
        } else {
            this.status = "Planejado";
        }
    }

    public LancamentoTunado(int id, String data, String userResponsable, boolean tipo, String subcategoria,
            double valor, String descricao, String status) {
        super(id, data, userResponsable, tipo, subcategoria, valor, descricao);
        this.status = status;
    }

    public boolean setStatus(String status) {
        if (status.equals("Executado") || status.equals("Planejado") || status.equals("Inválido")) {
            this.status = status;
            return true;
        }
        return false;
    }

    public boolean setUsuario(String usuario){
        if(usuario != null){
            this.userResponsable = usuario;
            return true;
        }
        return false;
    }

    public boolean setSubcategoria(String subcategoria){
        if(subcategoria != null){
            this.subcategoria = subcategoria;
            return true;
        }
        return false;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public boolean setData(String data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        sdf.setLenient(false);
        try {
            sdf.parse(data);
            this.data = data;
            return true;
        } catch (ParseException e) {
            return false;
        }
    }



    public String getStatus() {
        return status;
    }

    public boolean equals(LancamentoTunado l) {
        return super.equals(l) && this.status.equals(l.getStatus());
    }
    
}
