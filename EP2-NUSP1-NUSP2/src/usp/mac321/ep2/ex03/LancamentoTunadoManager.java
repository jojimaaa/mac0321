package usp.mac321.ep2.ex03;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import usp.mac321.ep2.ex01.Lancamento;
import usp.mac321.ep2.ex02.*;
import usp.mac321.ep2.exceptions.*;

public class LancamentoTunadoManager extends LancamentoManager {

    public LancamentoTunadoManager() {
        super();
    }

    public LancamentoTunadoManager fromLancamentoTunadoList(List<LancamentoTunado> lancamentos) {
        LancamentoTunadoManager manager = new LancamentoTunadoManager();
        for (LancamentoTunado lancamento : lancamentos) {
            if (!manager.idMap.containsKey(lancamento.getId())) {
                manager.idMap.put(lancamento.getId(), lancamento);
                manager.getAll().add(lancamento);
            } else
                throw new InvalidLancamentoInput("Lançamento com ID repetido");
        }
        return manager;
    }

    @Override
    public boolean add(Lancamento object) {
        if (object == null)
            return false;
        if (doesIDAlreadyExist(object.getId())) {
            if (object instanceof LancamentoTunado) {
                ((LancamentoTunado) object).setStatus("Inválido");
                return false;
            }
        }
        if (lancamentoExiste(object))
            return false;
        lancamentos.add(object);
        idMap.put(object.getId(), object);
        return true;
    }

    @Override
    public void write(String address) {
        try {
            PrintWriter writer = new PrintWriter(address, "UTF-8");
            writer.println("ID,Data,Responsável,Despesa?,SubCategoria,Valor,Descrição,Status");
            for(Lancamento t : lancamentos) {
                if(t instanceof LancamentoTunado && !((LancamentoTunado)t).getStatus().equals("Inválido")) {
                    writer.println(t.getId() + "," + t.getData() + "," + t.getUserResponsable() + "," + t.getTipo() + "," + t.getSubcategoria() + "," + t.getValor() + "," + t.getDescricao()+ "," + ((LancamentoTunado)t).getStatus());
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Encoding não suportado");
        }
    }
}
