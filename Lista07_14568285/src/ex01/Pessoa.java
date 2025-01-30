package ex01;

public class Pessoa {
	private String nome;
	private int cpf;
	
	ClientePF clientepf;
	ClientePJ clientepj;
	
	public Pessoa(
			String nome, 
			int cpf, 
			int cnpj, 
			double saldoCCpf, 
			double saldoCPpf, 
			double saldoCIpf, 
			double saldoCCpj, 
			double saldoCPpj,
			double saldoCIpj,
			int ID,
			FabricaAbstrataConta pf,
			FabricaAbstrataConta pj) {
		this.nome = nome;
		this.cpf = cpf;
		clientepf = new ClientePF(nome, cpf, pf, saldoCCpf, saldoCPpf, saldoCIpf, ID);
		clientepj = new ClientePJ(nome, cnpj, pj, saldoCCpj, saldoCPpj, saldoCIpj, ID);
	}
}