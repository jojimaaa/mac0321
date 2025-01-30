package ex01;

public class Simulacao {
	public static void main(String[] args) {
		
		FabricaAbstrataConta pj = new FabricaConcretaContaPJ();
		FabricaAbstrataConta pf = new FabricaConcretaContaPF();
		
		Pessoa jorge = new Pessoa(
				"Jorge", 
				12345, 
				6789, 
				0, 
				0, 
				0, 
				0, 
				0, 
				1000, 
				1, 
				pf, 
				pj);
	
	showSaldo(jorge);
	
	
	
	
	try {
		jorge.clientepj.contaInvestimentos.resgatar(1000);
	} catch (InvalidTransactionException e) {
		// TODO Auto-generated catch block
		System.err.println(e.toString());
	}
	System.out.println("Resgatou 1000 da Carteira PJ para a conta corrente PJ");
	showSaldo(jorge);
	
	
	
	
	try {
		jorge.clientepj.contaCorrente.PIX(jorge.clientepf.contaCorrente, 1000);
	} catch (InvalidTransactionException e) {
		// TODO Auto-generated catch block
		System.err.println(e.toString());
	}
	System.out.println("Transferiu 1000 reais da conta corrente PJ para a sua conta corrente PF");
	showSaldo(jorge);
	
	
	try {
		jorge.clientepf.contaCorrente.PIX(jorge.clientepf.contaPoupanca, 1000);
	} catch (InvalidTransactionException e) {
		// TODO Auto-generated catch block
		System.err.println(e.toString());
	}
	System.out.println("Transferiu 1000 reais da conta corrente PF para a conta poupança PF");
	showSaldo(jorge);
	}
	
	public static void showSaldo(Pessoa pessoa) {
		System.out.println("Saldo CC PF: "+pessoa.clientepf.contaCorrente.getSaldo());
		System.out.println("Saldo CP PF: "+pessoa.clientepf.contaPoupanca.getSaldo());
		System.out.println("Saldo CI PF: "+pessoa.clientepf.contaInvestimentos.getCarteira());
		System.out.println("Saldo CC PJ: "+pessoa.clientepj.contaCorrente.getSaldo());
		System.out.println("Saldo CP PJ: "+pessoa.clientepj.contaPoupanca.getSaldo());
		System.out.println("Saldo CI PJ: "+pessoa.clientepj.contaInvestimentos.getCarteira());
		
	}
	
}
