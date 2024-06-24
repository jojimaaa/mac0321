package usp.mac321.ep2.ex03;
import java.util.List;

import usp.mac321.ep2.ex02.*;
import usp.mac321.ep2.exceptions.*;
public class LancamentoTunadoManager extends LancamentoManager {

    public LancamentoTunadoManager() {
        super();
    }

    public LancamentoTunadoManager fromLancamentoTunadoList(List<LancamentoTunado> lancamentos) {
        LancamentoTunadoManager manager = new LancamentoTunadoManager();
        for(LancamentoTunado lancamento : lancamentos) {
            if(!manager.idMap.containsKey(lancamento.getId())){
                manager.idMap.put(lancamento.getId(), lancamento);
                manager.getAll().add(lancamento);
            }
            else throw new InvalidLancamentoInput("Lançamento com ID repetido");
        }
        return manager;
    }
    

}
