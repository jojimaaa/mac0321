package ex01;

public class FabricaConcretaContaPF implements FabricaAbstrataConta {

	@Override
	public ContaCorrente criaContaCorrente(String nome, int documento, double saldo, int ID) {
		return new ContaCorrentePF(nome, documento, saldo, ID);
	}

	@Override
	public ContaPoupanca criaContaPoupanca(String nome, int documento, double saldo, int ID, ContaCorrente contaCC) {
		return new ContaPoupancaPF(nome, documento, saldo, ID, contaCC);
	}

	@Override
	public ContaInvestimentos criaContaInvestimentos(String nome, int documento, double carteira, int ID,
			ContaCorrente contaCC) {
		return new ContaInvestimentosPF(nome, documento, carteira, ID, contaCC);
	}


}

class ContaPoupancaPF implements ContaPoupanca{
	private String nomeTitular;
	private int cpf = -1;
	private ContaCorrente contaCorrente;
	protected double saldo = -1;
	private String ID = "";
	
	public ContaPoupancaPF(String nomeTitular, int CPF, double saldo, int ID, ContaCorrente contaCC) {
		this.nomeTitular = nomeTitular;
		this.cpf = CPF;
		this.saldo = saldo;
		this.ID = "PFCP"+ID;
		this.contaCorrente = contaCC;
	}

	@Override
	public void recebePIX(double valor) throws InvalidTransactionException{
		if(valor < 0) throw new InvalidTransactionException();
		else this.saldo += valor;
	}

	@Override
	public String getID() {
		return this.ID;		
	}

	@Override
	public void resgatar(double valor) throws InvalidTransactionException {
		if(valor <= this.saldo && valor > 0) {
			this.contaCorrente.recebePIX(valor);
			this.saldo -= valor;
		}
		else throw new InvalidTransactionException();
	}

	@Override
	public double getSaldo() {
		return this.saldo;
	}
}


class ContaCorrentePF implements ContaCorrente{
	private String nomeTitular;
	private int cpf = -1;
	protected double saldo = -1;
	private String ID = "";
	
	
	public ContaCorrentePF(String nomeTitular, int cpf, double saldo, int ID) {
		this.nomeTitular = nomeTitular;
		this.cpf = cpf;
		this.saldo = saldo;
		this.ID = "PFCC" + ID;
	}
	
	@Override
	public void PIX(ContaPoupanca destino, double valor) throws InvalidTransactionException {
		if(valor <= saldo && valor > 0 && destino != null) {
			destino.recebePIX(valor);
		} else throw new InvalidTransactionException();
	}
	@Override
	public void PIX(ContaCorrente destino, double valor) throws InvalidTransactionException {
		if(valor <= saldo && valor > 0 && destino != null) {
			destino.recebePIX(valor);
		} else throw new InvalidTransactionException();
	}
	
	@Override
	public String getID() {
		return this.ID;		
	}


	@Override
	public void recebePIX(double valor) throws InvalidTransactionException{
		if(valor<0) throw new InvalidTransactionException();
		else this.saldo += valor;
	}

	@Override
	public double getSaldo() {
		return this.saldo;
	}

	@Override
	public void investir(ContaInvestimentos destino, double valor) throws InvalidTransactionException {
		if(valor <= this.saldo && valor > 0 && destino != null) {
			destino.recebePIX(valor);
			this.saldo -= valor;
		} else throw new InvalidTransactionException();
		
	}


}


class ContaInvestimentosPF implements ContaInvestimentos{
	private String nomeTitular;
	private int cpf = -1;
	protected double carteira;
	private String ID = "";
	private ContaCorrente contaCorrente;
	
	public ContaInvestimentosPF(String nomeTitular, int cpf,double valorInicial, int ID, ContaCorrente contaCC) {
		this.nomeTitular = nomeTitular;
		this.carteira = valorInicial;
		this.cpf = cpf;
		this.ID = "PFCI"+ID;
		this.contaCorrente = contaCC;
	}
	
	@Override
	public String getID() {
		return this.ID;		
	}

	@Override
	public void recebePIX(double valor) throws InvalidTransactionException{
		if(valor < 0) throw new InvalidTransactionException();
		else this.carteira += valor;
	}
	
	@Override
	public void resgatar(double valor) throws InvalidTransactionException {
		if(valor <= this.carteira && valor > 0) {
			this.carteira -= valor;
			this.contaCorrente.recebePIX(valor);
		}else throw new InvalidTransactionException();
		
	}

	@Override
	public double getCarteira() {
		// TODO Auto-generated method stub
		return this.carteira;
	}
}
