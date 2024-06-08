package usp.mac321.ep2;

public class Exceptions {

}

class InvalidUserInput extends Exception {
    public InvalidUserInput(String message) {
        super(message);
    }
}

class InvalidDespesaInput extends Exception {
    public InvalidDespesaInput(String message) {
        super(message);
    }
}

class InvalidReceitaInput extends Exception {
    public InvalidReceitaInput(String message) {
        super(message);
    }
}

class InvalidLancamentoInput extends Exception {
    public InvalidLancamentoInput(String message) {
        super(message);
    }
}