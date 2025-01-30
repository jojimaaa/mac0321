package ex01;

public interface FabricaAbstrataConta {
	ContaCorrente criaContaCorrente(String nome, int documento, double saldo, int ID);
	ContaPoupanca criaContaPoupanca(String nome, int documento, double saldo, int ID, ContaCorrente contaCC);
	ContaInvestimentos criaContaInvestimentos(String nome, int documento, double carteira, int ID, ContaCorrente contaCC);
}

interface ContaCorrente{
	void PIX(ContaPoupanca destino, double valor) throws InvalidTransactionException;
	void PIX(ContaCorrente destino, double valor) throws InvalidTransactionException;
	void recebePIX(double valor) throws InvalidTransactionException;
	String getID();
	double getSaldo();
	void investir(ContaInvestimentos destino, double valor) throws InvalidTransactionException;
}

interface ContaPoupanca{
	void recebePIX(double valor) throws InvalidTransactionException;
	void resgatar(double valor) throws InvalidTransactionException;
	String getID();
	double getSaldo();
}

interface ContaInvestimentos{
	void resgatar(double valor) throws InvalidTransactionException;
	void recebePIX(double valor) throws InvalidTransactionException;
	String getID();
	double getCarteira();
}



