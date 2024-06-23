package usp.mac321.ep2.ex02;
import java.util.List;

import usp.mac321.ep2.ex01.*;

public interface GeneralDAO {

    public boolean addTipoDespesa(TipoDespesa tipoDespesa);
    public boolean addTipoReceita(TipoReceitaManager tipoReceita);
    public boolean addUsuario(Usuario usuario);
    public boolean addLancamento(Lancamento lancamento);

    public boolean removeTipoDespesa(TipoDespesa tipoDespesa);
    public boolean removeTipoReceita(TipoReceitaManager tipoReceita);
    public boolean removeUsuario(Usuario usuario);
    public boolean removeLancamento(Lancamento lancamento);

    public List<TipoDespesa> getAllTiposDespesas();
    public List<TipoReceitaManager> getAllTiposReceitas();
    public List<Usuario> getAllUsuarios();
    public List<Lancamento> getAllLancamentos();

    public boolean writeTiposDespesas(List<TipoDespesa> tiposDespesas, String address);
    public boolean writeTiposReceitas(List<TipoReceitaManager> tiposReceitas, String address);
    public boolean writeUsuarios(List<Usuario> usuarios, String address);
    public boolean writeLancamentos(List<Lancamento> lancamentos, String address);
}
    