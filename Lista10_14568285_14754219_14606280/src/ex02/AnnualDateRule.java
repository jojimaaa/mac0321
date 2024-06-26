package ex02;

/* O código abaixo pode ser considerado ruim por uma série de razões:
 * 
 * 1. O construtor da classe AnnualDateRule() não faz nada, o que é um desperdício de recursos.
 * 2. Nada é feito para atribuir um valor ao atributo dayOfMonth, o que pode causar problemas.
 * 3. Há muitos comentários inúteis e redundantes.
 * 4. O construtor é protected, mas a classe é pública. Queremos, usualmente um construtor público.
 * 5. A declaração da classe está com () no final, isso é algo que encontramos nos métodos. Deste modo, o programa não compilaria.
 * No geral deve-se evitar muitos comentários, a fim de manter o código o mais limpo possível.
 * Vale lembrar a máxima: "Don't comment bad code, rewrite it."
 * 
 */

public class AnnualDateRule() {
    /*
    * Construtor padr~ao.
    */
    protected AnnualDateRule() {
    }
    /** Dia do m^es. */
    private int dayOfMonth;
    /**
    * Retorna o dia do m^es.
    *
    * @return o dia do m^es
    */
    public int getDayofMonth() {
        return dayOfMonth;
    }
}
