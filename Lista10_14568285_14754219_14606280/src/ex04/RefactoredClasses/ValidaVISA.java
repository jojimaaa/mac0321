package ex04.RefactoredClasses;


public class ValidaVISA implements VaidaPrefixETamanhoInterface {

    @Override
    public boolean validarPrefixoTamanho(String numero) {
        return (numero.startsWith("4") && (numero.length() == 13 || numero.length() == 16));
    }
    
}
