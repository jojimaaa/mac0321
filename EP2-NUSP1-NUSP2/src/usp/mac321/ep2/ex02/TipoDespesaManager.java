package usp.mac321.ep2.ex02;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import usp.mac321.ep2.ex01.*;

public class TipoDespesaManager implements WriterDAO<TipoDespesa>, GetterDAO<TipoDespesa>, ModifyDAO<TipoDespesa>{

    private List<TipoDespesa> tiposDespesa;

    public TipoDespesaManager(List<TipoDespesa> tiposDespesa) {
        this.tiposDespesa = new ArrayList<TipoDespesa>();
        for(TipoDespesa tipoDespesa : tiposDespesa) {
            this.tiposDespesa.add(tipoDespesa);
        }
    }

    @Override
    public boolean add(TipoDespesa object) {
        if(object == null)
            return false;
        if(tipoDespesaExiste(object))
            return false;
        tiposDespesa.add(object);
        return true;
    }

    
    @Override
    public boolean remove(TipoDespesa object) {
        if(object == null)
            return false;
        for(TipoDespesa t : tiposDespesa) {
            if(t.equals(object)) {
                tiposDespesa.remove(t);
                return true;
            }
        }
        return false;
    }

    public boolean remove(String Categoria, String Subcategoria){
        if(Categoria == null || Subcategoria == null)
            return false;
        for(TipoDespesa t : tiposDespesa){
            if(t.getCategoria().equals(Categoria) && t.getSubcategoria().equals(Subcategoria)){
                tiposDespesa.remove(t);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public TipoDespesa get(TipoDespesa object) {
        if(object == null)
            return null;
        for(TipoDespesa t : tiposDespesa) {
            if(t.equals(object))
                return t;
        }
        return null;
    }

    @Override
    public List<TipoDespesa> getAll() {
        return tiposDespesa;
    }
    
    @Override
    public void write(String address) {
        try {
            PrintWriter writer = new PrintWriter(address, "UTF-8");
            writer.println("Categoria,SubCategoria");
            for(TipoDespesa t : tiposDespesa) {
                writer.println(t.getCategoria() + "," + t.getSubcategoria());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Encoding não suportado");
        }
    }

    private boolean tipoDespesaExiste(TipoDespesa object) {
        for(TipoDespesa t : tiposDespesa) {
            if(t.equals(object))
                return true;
        }
        return false;
    }
    
}
