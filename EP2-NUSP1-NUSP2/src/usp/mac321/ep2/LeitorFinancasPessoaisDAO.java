package usp.mac321.ep2;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public interface LeitorFinancasPessoaisDAO {
	public List<Usuario> leUsuarios(String nomeArquivoUsuarios);

	public List<TipoDespesa> leTiposDespesas(String nomeArquivoTiposDespesas);

	public List<TipoReceita> leTiposReceitas(String nomeArquivoTiposReceitas);

	public List<Lancamento> leLancamentos(String nomeArquivoLancamentos);
}

/**
 * InnerLeitorFinancasPessoaisDAO
 */
class LeitorImpl implements LeitorFinancasPessoaisDAO {

	@Override
	public List<Usuario> leUsuarios(String nomeArquivoUsuarios) {

	}

	@Override
	public List<TipoDespesa> leTiposDespesas(String nomeArquivoTiposDespesas) {
		
	}

	@Override
	public List<TipoReceita> leTiposReceitas(String nomeArquivoTiposReceitas) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'leTiposReceitas'");
	}

	@Override
	public List<Lancamento> leLancamentos(String nomeArquivoLancamentos) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'leLancamentos'");
	}

}