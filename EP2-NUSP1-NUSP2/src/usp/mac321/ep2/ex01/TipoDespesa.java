package usp.mac321.ep2.ex01;

public class TipoDespesa {
    private String categoria;
    private String subcategoria;

    public TipoDespesa(String categoria, String subcategoria) {
        this.categoria = categoria;
        this.subcategoria = subcategoria;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public String getSubcategoria() {
        return this.subcategoria;
    }

    public boolean equals(TipoDespesa t) {
        return this.categoria.equals(t.getCategoria()) && this.subcategoria.equals(t.getSubcategoria());
    }
}
