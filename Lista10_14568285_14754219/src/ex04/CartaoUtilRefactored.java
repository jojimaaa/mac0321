package ex04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ex04.RefactoredClasses.*;

public class CartaoUtilRefactored {
    public static final int VISA = 1;
    public static final int MASTERCARD = 2;
    public static final int AMEX = 3;
    public static final int DINERS = 4;
    public static final String CARTAO_OK = "Cartão válido";
    public static final String CARTAO_ERRO = "Cartão inválido";

    public boolean validarValidade(String validade) {
        Date dataValidade = null;
        try {
            dataValidade = new SimpleDateFormat("MM/yyyy").parse(validade);
        } catch (ParseException e) {
            return false;
        }
        Calendar calValidade = new GregorianCalendar();
        Calendar calHoje = new GregorianCalendar();
        calValidade.setTime(dataValidade);
        return calHoje.before(calValidade);
    }

    public String formatNumber(String numero) {
        String formatado = "";
        for (int i = 0; i < numero.length(); i++) {
            char temp = numero.charAt(i);
            if (Character.isDigit(temp)) {
                formatado += temp;
            }
        }
        return formatado;
    }

    public boolean validarNumero(String numero) {
        int somaAlternada = 0;
        int somaRemanescentes = 0;
        for (int i = 0; i < numero.length(); i+=2) {
            int digitoAtual = Integer.parseInt(Character.toString(numero.charAt(i)));
            if(2*digitoAtual >= 10) somaAlternada += (2*digitoAtual - 9);
            else somaAlternada += 2*digitoAtual;
            somaRemanescentes += Integer.parseInt(Character.toString(numero.charAt(i + 1)));
        }

        return (somaAlternada + somaRemanescentes) % 10 == 0;
    }

    public String validar(int bandeira, String numero, String validade) {
        VaidaPrefixETamanhoInterface validador = null;
        String formatado = formatNumber(numero);
        switch (bandeira) {
            case VISA:
                validador = new ValidaVISA();
            case MASTERCARD:
                validador = new ValidaMASTERCARD();
            case AMEX:
                validador = new ValidaAMEX();
            case DINERS:
                validador = new ValidaDINERS();
            default:
        }
        if(validador.validarPrefixoTamanho(formatado) && validarNumero(formatado) && validarValidade(validade))
        return CARTAO_OK;
    else
        return CARTAO_ERRO;
    }
}
