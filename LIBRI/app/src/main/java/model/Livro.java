package model;

public class Livro {

    private String titulo, descricao;

    public Livro(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    //set -> colocar valor ap[os a criacao do objeto
    // entao nesse caso os et não é obrigatório
}
