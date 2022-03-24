package model;

public class Item {

    //várias categorias de livros

    /*
    types:
    0 - Livros
    1- HQ
    2 - Mangá
    */

    //classe item guarda tipo de categoriaqs que podem existir no app

    private int type;
    private Object object;


    public Item(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public Object getObject() {
        return object;
    }
}
