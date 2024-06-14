package ex04.RefactoredClasses;

public class ValidaDINERS implements VaidaPrefixETamanhoInterface {
    @Override
    public boolean validarPrefixoTamanho(String formatado) {
        return (((formatado.startsWith("300") ||
                formatado.startsWith("305") ||
                formatado.startsWith("36") ||
                formatado.startsWith("38")) 
                && formatado.length() == 14));
    }
}
