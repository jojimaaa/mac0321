package usp.mac321.ep2.ex01;

public class Lancamento {
    public final boolean R = false;
    public final boolean D = true;

    private int id = 0;
    protected String data = "";
    protected boolean tipo = R;
    protected String subcategoria;
    protected String descricao = "";
    protected String userResponsable = "";
    protected double valor = 0.0;

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

    public boolean equals(Lancamento l) {
        return this.id == l.getId() && this.data.equals(l.getData()) && this.userResponsable.equals(l.getUserResponsable()) && this.tipo == l.getTipo() && this.subcategoria.equals(l.getSubcategoria()) && this.valor == l.getValor() && this.descricao.equals(l.getDescricao());
    }
}
