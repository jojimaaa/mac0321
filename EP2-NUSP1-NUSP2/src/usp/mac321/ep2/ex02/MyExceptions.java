package usp.mac321.ep2.ex02;

public class MyExceptions {

}

class InvalidFileInput extends RuntimeException{
    public InvalidFileInput(String message) {
        super(message);
    }
}

class InvalidUserInput extends RuntimeException {
    public InvalidUserInput(String message) {
        super(message);
    }
}

class InvalidDespesaInput extends RuntimeException {
    public InvalidDespesaInput(String message) {
        super(message);
    }
}

class InvalidReceitaInput extends RuntimeException {
    public InvalidReceitaInput(String message) {
        super(message);
    }
}

class InvalidLancamentoInput extends RuntimeException {
    public InvalidLancamentoInput(String message) {
        super(message);
    }
}