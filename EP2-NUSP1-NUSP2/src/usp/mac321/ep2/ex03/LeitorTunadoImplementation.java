package usp.mac321.ep2.ex03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import usp.mac321.ep2.ex01.*;
import usp.mac321.ep2.exceptions.*;

public class LeitorTunadoImplementation extends LeitorImplementation {
    
	public LeitorTunadoImplementation() {
		super();
	}

	List<LancamentoTunado> lancamentosTunados = new ArrayList<LancamentoTunado>();

	@SuppressWarnings("resource")
	public List<LancamentoTunado> leLancamentosTunados(String nomeArquivoLancamentos) {

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
			try {
				br = new BufferedReader(new FileReader(nomeArquivoLancamentos));
			} catch (FileNotFoundException e) {
				throw new InvalidFileInput("Arquivo de lançamentos não encontrado");
			}
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

				try {
					if (idMap.containsKey(id)) {
						throw new InvalidLancamentoInput("Lançamento com ID repetido");
					}

					if ((tipo == D && !isSubcategoriaDespesa(subcategoria)) ){
						throw new InvalidLancamentoInput(
								"Subcategoria incompatível com a categoria DESPESA");
					}
					
					if((tipo == R && !isSubcategoriaReceita(subcategoria))) {
						throw new InvalidLancamentoInput(
								"Subcategoria incompatível com a categoria RECEITA");
					}

					if (!usuarioExiste(userResponsable)) {
						throw new InvalidLancamentoInput("Usuário de lançamento não existe");
					}

					if (valor < 0) {
						throw new InvalidLancamentoInput("Valor negativo no lançamento");
					}

					LancamentoTunado lancamento = new LancamentoTunado(id, date, userResponsable, tipo, subcategoria, valor, descricao);
					lancamentosTunados.add(lancamento);
					idMap.put(id, lancamento);
				} catch (RuntimeException e) {
					LancamentoTunado lancamento = new LancamentoTunado(id, date, userResponsable, tipo, subcategoria, valor, descricao, "Inválido");
					lancamentosTunados.add(lancamento);
					idMap.put(id, lancamento);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lancamentosTunados;
	}

	public void clear(){
		lancamentosTunados.clear();
		idMap.clear();
	}
}
