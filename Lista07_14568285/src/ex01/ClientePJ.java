package ex01;

public class ClientePJ {
	private String representante;
	private int cnpj;
	
	protected ContaCorrente contaCorrente;
	protected ContaInvestimentos contaInvestimentos;
	protected ContaPoupanca contaPoupanca;

	public ClientePJ(String nome, int cnpj, FabricaAbstrataConta fabricaConta, double saldoCC, double saldoCP, double carteira, int ID) {
		this.cnpj = cnpj;
		this.representante = nome;
		contaCorrente = fabricaConta.criaContaCorrente(representante, cnpj, saldoCC, ID);
		contaPoupanca = fabricaConta.criaContaPoupanca(representante, cnpj, saldoCP, ID, contaCorrente);
		contaInvestimentos = fabricaConta.criaContaInvestimentos(representante, cnpj, carteira, ID, contaCorrente);
	}
}
