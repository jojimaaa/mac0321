package usp.mac321.ep2;

public class TipoDespesa {
    private String categoria;
    private String subcategoria;

    public TipoDespesa(String categoria, String subcategoria) {
        this.categoria = categoria;
        this.subcategoria = subcategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }
}
