package ex04.RefactoredClasses;

public class ValidaAMEX implements VaidaPrefixETamanhoInterface {

    @Override
    public boolean validarPrefixoTamanho(String formatado) {
        return (((formatado.startsWith("34") || formatado.startsWith("37")) && formatado.length() == 16));
    }
}
