package usp.mac321.ep2.ex01;

public class Usuario {
    private String apelido;
    private String nome;

    public Usuario(String apelido, String nome) {
        this.apelido = apelido;
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getNome() {
        return nome;
    }

    public boolean equals(Usuario u) {
        return this.apelido.equals(u.getApelido()) && this.nome.equals(u.getNome());
    }
}
