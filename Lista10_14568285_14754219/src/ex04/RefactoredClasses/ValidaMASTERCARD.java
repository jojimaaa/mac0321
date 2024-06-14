package ex04.RefactoredClasses;

public class ValidaMASTERCARD implements VaidaPrefixETamanhoInterface {

    @Override
    public boolean validarPrefixoTamanho(String formatado) {
        return ((formatado.startsWith("51") 
                || formatado.startsWith("52")
                || formatado.startsWith("53")
                || formatado.startsWith("54")
                || formatado.startsWith("55")
                ) && formatado.length() == 16);
    }

}
