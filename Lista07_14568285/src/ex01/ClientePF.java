package ex01;

public class ClientePF{
	private String nomeTitular;

	protected ContaCorrente contaCorrente;
	protected ContaInvestimentos contaInvestimentos;
	protected ContaPoupanca contaPoupanca;
	
	public ClientePF(String nome, int cpf, FabricaAbstrataConta fabricaConta, double saldoCC, double saldoCP, double carteira, int ID) {
		this.nomeTitular = nome;
		contaCorrente = fabricaConta.criaContaCorrente(nomeTitular, cpf, saldoCC, ID);
		contaPoupanca = fabricaConta.criaContaPoupanca(nomeTitular, cpf, saldoCP, ID, contaCorrente);
		contaInvestimentos = fabricaConta.criaContaInvestimentos(nomeTitular, cpf, carteira, ID, contaCorrente);
	}
}
