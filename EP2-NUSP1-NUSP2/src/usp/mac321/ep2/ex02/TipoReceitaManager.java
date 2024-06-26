package usp.mac321.ep2.ex02;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import usp.mac321.ep2.ex01.*;

public class TipoReceitaManager implements WriterDAO<TipoReceita>, GetterDAO<TipoReceita>, ModifyDAO<TipoReceita>{

    private List<TipoReceita> tiposReceita;

    public TipoReceitaManager(List<TipoReceita> tiposReceita) {
        this.tiposReceita = new ArrayList<TipoReceita>();
        for(TipoReceita tipoReceita : tiposReceita) {
            this.tiposReceita.add(tipoReceita);
        }
    }

    @Override
    public boolean add(TipoReceita object) {
        if(object == null)
            return false;
        if(tipoReceitaExiste(object))
            return false;
        tiposReceita.add(object);
        return true;
    }

    @Override
    public boolean remove(TipoReceita object) {
        if(object == null)
            return false;
        for(TipoReceita t : tiposReceita) {
            if(t.equals(object)) {
                tiposReceita.remove(t);
                return true;
            }
        }
        return false;
    }

    public boolean remove(String Categoria, String Subcategoria){
        if(Categoria == null || Subcategoria == null)
            return false;
        for(TipoReceita t : tiposReceita){
            if(t.getCategoria().equals(Categoria) && t.getSubcategoria().equals(Subcategoria)){
                tiposReceita.remove(t);
                return true;
            }
        }
        return false;
    }

    @Override
    public TipoReceita get(TipoReceita object) {
        if(object == null)
            return null;
        for(TipoReceita t : tiposReceita) {
            if(t.equals(object))
                return t;
        }
        return null;
    }

    @Override
    public List<TipoReceita> getAll() {
        return tiposReceita;
    }

    @Override
    public void write(String address) {
        try {
            PrintWriter writer = new PrintWriter(address, "UTF-8");
            writer.println("Categoria,SubCategoria");
            for(TipoReceita t : tiposReceita) {
                writer.println(t.getCategoria() + "," + t.getSubcategoria());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Encoding não suportado");
        }
    }

    public boolean tipoReceitaExiste(TipoReceita tipoReceita) {
        for (TipoReceita t : tiposReceita) {
            if (t.equals(tipoReceita))
                return true;
        }
        return false;
    }
    
}
