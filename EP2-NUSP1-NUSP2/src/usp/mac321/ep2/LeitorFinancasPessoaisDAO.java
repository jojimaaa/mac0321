package usp.mac321.ep2;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

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
	public final boolean R = false;
    public final boolean D = true;

	protected ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	protected ArrayList<TipoDespesa> tiposDespesas = new ArrayList<TipoDespesa>();
	protected ArrayList<TipoReceita> tiposReceitas = new ArrayList<TipoReceita>();
	protected ArrayList<Lancamento> lancamentos = new ArrayList<Lancamento>();
	protected HashMap<Integer, Lancamento> idMap = new HashMap<>();

	@Override
	public List<Usuario> leUsuarios(String nomeArquivoUsuarios) {
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(nomeArquivoUsuarios));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				for (Usuario u : usuarios) {
					if (u.getApelido() == data[0])
						throw new InvalidUserInput("Apelido repetido");
				}
				usuarios.add(new Usuario(data[0], data[1]));
			}
			br.close();
		} catch (IOException | InvalidUserInput e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	@Override
	public List<TipoDespesa> leTiposDespesas(String nomeArquivoTiposDespesas) {
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(nomeArquivoTiposDespesas));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				for (TipoDespesa td : tiposDespesas) {
					if (td.getCategoria() == data[0] && td.getSubcategoria() == data[1])
						throw new InvalidDespesaInput("Despesa repetida");
				}
				tiposDespesas.add(new TipoDespesa(data[0], data[1]));
			}
			br.close();
		} catch (IOException | InvalidDespesaInput e) {
			e.printStackTrace();
		}
		return tiposDespesas;
	}

	@Override
	public List<TipoReceita> leTiposReceitas(String nomeArquivoTiposReceitas) {
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(nomeArquivoTiposReceitas));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				for (TipoReceita tr : tiposReceitas) {
					if (tr.getCategoria() == data[0] && tr.getSubcategoria() == data[1])
						throw new InvalidReceitaInput("Receita repetida");
				}
				tiposReceitas.add(new TipoReceita(data[0], data[1]));
			}
			br.close();
		} catch (IOException | InvalidReceitaInput e) {
			e.printStackTrace();
		}
		return tiposReceitas;
	}

	@Override
	public List<Lancamento> leLancamentos(String nomeArquivoLancamentos) {

		String line;
		BufferedReader br;
		int id = 0;
		String date = "";
		String userResponsable = "";
		boolean tipo = D;
		String subcategoria = "";
		double valor = 0.0;
		String descricao = "";

		try {
			br = new BufferedReader(new FileReader(nomeArquivoLancamentos));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");

				id = Integer.parseInt(data[0]);
				date = data[1];
				userResponsable = data[2];
				tipo = Boolean.parseBoolean(data[3]);
				subcategoria = data[4];
				valor = Double.parseDouble(data[5]);
				descricao = data[6];

				if (idMap.containsKey(id)) {
					throw new InvalidLancamentoInput("Lançamento com ID repetido");
				}

				if ((tipo == D && !isSubcategoriaDespesa(subcategoria)) || (tipo == R && !isSubcategoriaReceita(subcategoria))) {
					throw new InvalidLancamentoInput("Categoria de lançamento incompatível");
				}

				if (!usuarioExiste(userResponsable)) {
					throw new InvalidLancamentoInput("Usuário de lançamento não existe");
				}

				if (valor < 0) {
					throw new InvalidLancamentoInput("Valor negativo no lançamento");
				}

				Lancamento lancamento = new Lancamento(id, date, userResponsable, tipo, subcategoria, valor, descricao);
				lancamentos.add(lancamento);
				idMap.put(id, lancamento);
			}
			br.close();
		} catch (IOException | InvalidLancamentoInput e) {
			e.printStackTrace();
		}
		return lancamentos;
	}

	private boolean usuarioExiste(String string) {
		for (Usuario u : usuarios) {
			if (u.getApelido() == string)
				return true;
		}
		return false;
	}

	private boolean isSubcategoriaDespesa(String string) {
		for (TipoDespesa td : tiposDespesas) {
			if (td.getSubcategoria() == string)
				return true;
		}
		return false;
	}

	private boolean isSubcategoriaReceita(String string) {
		for (TipoReceita tr : tiposReceitas) {
			if (tr.getSubcategoria() == string)
				return true;
		}
		return false;
	}

}