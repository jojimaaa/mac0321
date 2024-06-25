package usp.mac321.ep2.ex04;

public class TesteGerenciador {
    
    
    public static void main(String[] args) {
        String user = "csv\\usuarios.csv";
        String lancamentos = "csv\\lancamentos.csv";
        String despesas = "csv\\tiposDespesas.csv";
        String receitas = "csv\\tiposReceitas.csv";
        Gerenciador gerenciador = new Gerenciador(user, despesas, receitas, lancamentos);
        gerenciador.printRelatorio("01/05/24", "04/05/24", "outputs\\relatorio.txt", true);
    }
}
