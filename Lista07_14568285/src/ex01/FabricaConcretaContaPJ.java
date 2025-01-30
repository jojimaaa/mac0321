package ex01;

public class FabricaConcretaContaPJ implements FabricaAbstrataConta{

	@Override
	public ContaCorrente criaContaCorrente(String nome, int documento, double saldo, int ID) {
		return new ContaCorrentePJ(nome, documento, saldo, ID);
	}

	@Override
	public ContaPoupanca criaContaPoupanca(String nome, int documento, double saldo, int ID, ContaCorrente contaCC) {
		return new ContaPoupancaPJ(nome, documento, saldo, ID, contaCC);
	}

	@Override
	public ContaInvestimentos criaContaInvestimentos(String nome, int documento, double carteira, int ID,
			ContaCorrente contaCC) {
		return new ContaInvestimentosPJ(nome, documento, carteira, ID, contaCC);
	}


}

class ContaPoupancaPJ implements ContaPoupanca{
	private String representante;
	private int cnpj = -1;
	private ContaCorrente contaCorrente;
	private String ID = "";
	protected double saldo = -1;
	
	public ContaPoupancaPJ(String representante, int cnpj, double saldo, int ID, ContaCorrente contaCC) {
		this.representante = representante;
		this.cnpj = cnpj;
		this.ID = "PJCP"+ID;
		this.contaCorrente = contaCC;
		this.saldo = saldo;
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
		if(valor <= this.saldo && valor >= 0) {
			contaCorrente.recebePIX(valor);
			this.saldo -= valor;
		} else throw new InvalidTransactionException();
	}

	@Override
	public double getSaldo() {
		
		return this.saldo;
	}
}


class ContaCorrentePJ implements ContaCorrente{
	private String representante;
	private int cnpj = -1;
	protected double saldo = -1;
	private String ID = "";
	
	
	public ContaCorrentePJ(String representante, int cnpj, double saldo, int ID) {
		this.representante = representante;
		this.cnpj = cnpj;
		this.saldo = saldo;
		this.ID = "PJCC"+ID;
	}
	
	@Override
	public void PIX(ContaPoupanca destino, double valor) throws InvalidTransactionException {
		if(valor <= saldo && valor > 0 && destino != null) {
				destino.recebePIX(valor);
			this.saldo -= valor;
		} else throw new InvalidTransactionException();
	}
	@Override
	public void PIX(ContaCorrente destino, double valor) throws InvalidTransactionException {
		if(valor <= saldo && valor > 0 && destino != null) {
			destino.recebePIX(valor);
			this.saldo -= valor;
		}else throw new InvalidTransactionException();
	}
	
	@Override
	public void investir(ContaInvestimentos destino, double valor) throws InvalidTransactionException {
		if(valor <= this.saldo && valor > 0 && destino != null) {
			destino.recebePIX(valor);
			this.saldo -= valor;
		}else throw new InvalidTransactionException();
		
	}
	
	@Override
	public String getID() {
		return this.ID;		
	}


	@Override
	public void recebePIX(double valor) throws InvalidTransactionException {
		if(valor < 0) throw new InvalidTransactionException();
		else this.saldo += valor;
	}

	@Override
	public double getSaldo() {
		return this.saldo;
	}

}


class ContaInvestimentosPJ implements ContaInvestimentos{
	private String representante;
	private int cnpj = -1;
	private ContaCorrente contaCorrente;
	private String ID = "";
	protected double carteira;
	
	public ContaInvestimentosPJ(String representante, int cnpj,double valorInicial, int ID, ContaCorrente contaCC) {
		this.representante = representante;
		this.cnpj = cnpj;
		this.ID = "PJCP"+ID;
		this.contaCorrente = contaCC;
		this.carteira = valorInicial;
	}
	
	@Override
	public String getID() {
		return this.ID;		
	}

	@Override
	public void recebePIX(double valor) throws InvalidTransactionException {
		if(valor < 0) throw new InvalidTransactionException();
		else this.carteira += valor;
	}
	
	@Override
	public void resgatar(double valor) throws InvalidTransactionException{
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

