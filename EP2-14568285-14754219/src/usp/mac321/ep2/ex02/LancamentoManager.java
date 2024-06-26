package usp.mac321.ep2.ex02;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import usp.mac321.ep2.ex01.*;
import usp.mac321.ep2.ex03.LancamentoTunado;
import usp.mac321.ep2.exceptions.InvalidLancamentoInput;
public class LancamentoManager implements WriterDAO<Lancamento>, GetterDAO<Lancamento>, ModifyDAO<Lancamento>{

    protected List<Lancamento> lancamentos = new ArrayList<Lancamento>();
    protected HashMap<Integer, Lancamento> idMap = new HashMap<>();

    public LancamentoManager fromLancamentoList(List<Lancamento> lancamentos){
        LancamentoManager manager = new LancamentoManager();
        for(Lancamento lancamento : lancamentos) {
            if(!manager.idMap.containsKey(lancamento.getId())) {
                manager.idMap.put(lancamento.getId(), lancamento);
                manager.getAll().add(lancamento);
            }
            else throw new InvalidLancamentoInput("Lançamento com ID repetido");
        }
        return manager;
    }

    public LancamentoManager fromLancamentoTunadoList(List<LancamentoTunado> lancamentos) {
        LancamentoManager manager = new LancamentoManager();
        for(LancamentoTunado lancamento : lancamentos) {
            if(!manager.idMap.containsKey(lancamento.getId())){
                manager.idMap.put(lancamento.getId(), lancamento);
                manager.getAll().add(lancamento);
            }
            else throw new InvalidLancamentoInput("Lançamento com ID repetido");
        }
        return manager;
    }

    @Override
    public boolean add(Lancamento object) {
        if(object == null)
            return false;
        if(lancamentoExiste(object))
            return false;
        lancamentos.add(object);
        idMap.put(object.getId(), object);
        return true;
    }

    @Override
    public boolean remove(Lancamento object) {
        if(object == null)
            return false;
        for(Lancamento l : lancamentos) {
            if(l.equals(object)) {
                lancamentos.remove(l);
                idMap.remove(l.getId());
                return true;
            }
        }
        return false;
    }

    public boolean remove(int ID){
        if(!doesIDAlreadyExist(ID))
            return false;
        Lancamento l = idMap.get(ID);
        if(l == null)
            return false;
        lancamentos.remove(l);
        idMap.remove(ID);
        return true;
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

    public Lancamento get(int ID){
        return idMap.get(ID);
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
            System.err.println("Arquivo não encontrado");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Encoding não suportado");
        }
    }
    
    protected boolean lancamentoExiste(Lancamento lancamento) {
        for(Lancamento l : lancamentos) {
            if(l.equals(lancamento))
                return true;
        }
        return false;
    }

    public void clear(){
        lancamentos.clear();
        idMap.clear();
    }

    public HashMap<Integer, Lancamento> getIDMap(){
        return idMap;
    }

    public boolean doesIDAlreadyExist(int id){
        return this.idMap.containsKey(id);
    }

}
