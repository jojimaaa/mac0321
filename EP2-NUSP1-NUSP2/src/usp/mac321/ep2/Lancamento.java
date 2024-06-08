package usp.mac321.ep2;


public class Lancamento {
    public final boolean R = false;
    public final boolean D = true;

    private int id = 0;
    private String data = "";
    private boolean tipo = R;
    private String subcategoria;
    private String descricao = "";
    private String userResponsable = "";
    private double valor = 0.0;

    public Lancamento(int id, String data, String userResponsable, boolean tipo, String subcategoria, double valor, String descricao) {
        this.id = id;
        this.data = data;
        this.userResponsable = userResponsable;
        this.tipo = tipo;
        this.subcategoria = subcategoria;
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getUserResponsable() {
        return userResponsable;
    }

    public boolean getTipo() {
        return tipo;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
