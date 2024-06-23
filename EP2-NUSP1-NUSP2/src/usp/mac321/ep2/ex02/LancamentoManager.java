package usp.mac321.ep2.ex02;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import usp.mac321.ep2.ex01.*;

public class LancamentoManager implements WriterDAO<Lancamento>, GetterDAO<Lancamento>, ModifyDAO<Lancamento>{

    private List<Lancamento> lancamentos;

    public LancamentoManager(List<Lancamento> lancamentos) {
        this.lancamentos = new ArrayList<Lancamento>();
        for(Lancamento lancamento : lancamentos) {
            this.lancamentos.add(lancamento);
        }
    }

    @Override
    public boolean add(Lancamento object) {
        if(object == null)
            return false;
        if(lancamentoExiste(object))
            return false;
        lancamentos.add(object);
        return true;
    }

    @Override
    public boolean remove(Lancamento object) {
        if(object == null)
            return false;
        for(Lancamento l : lancamentos) {
            if(l.equals(object)) {
                lancamentos.remove(l);
                return true;
            }
        }
        return false;
    }

    @Override
    public Lancamento get(Lancamento object) {
        if(object == null)
            return null;
        for(Lancamento l : lancamentos) {
            if(l.equals(object))
                return l;
        }
        return null;
    }

    @Override
    public List<Lancamento> getAll() {
        return lancamentos;
    }

    @Override
    public void write(String address) {
        try {
            PrintWriter writer = new PrintWriter(address, "UTF-8");
            writer.println("ID,Data,Responsável,Despesa?,SubCategoria,Valor,Descrição");
            for(Lancamento t : lancamentos) {
                writer.println(t.getId() + "," + t.getData() + "," + t.getUserResponsable() + "," + t.getTipo() + "," + t.getSubcategoria() + "," + t.getValor() + "," + t.getDescricao());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Encoding não suportado");
        }
    }
    
    private boolean lancamentoExiste(Lancamento lancamento) {
        for(Lancamento l : lancamentos) {
            if(l.equals(lancamento))
                return true;
        }
        return false;
    }

}
