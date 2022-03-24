package com.example.libri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import database.SQLHelper;
import model.Item;
import model.Livro;

public class FeedLivros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_livros);

        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        List<Item> item = SQLHelper.getInstance(this).listBook();

        recyclerview.setAdapter(new LivroAdapter(item));
    }

    /** INFLATE DO MENU **/
    //novo método, fora do onCreate

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
                //operador super - acessar algo (um atrubuto, metodo)
        // mas isso pertence a classe mãe AppCompatActivity
    }

    /** AÇÕES DO MENU **/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("MENUITEM-", String.valueOf(item.getItemId()));

        switch (item.getItemId()){
            case R.id.menu_cadastrar_livro:
                startActivity(new Intent(this, CadastroLivro.class));
                break;

            case R.id.menu_feed_livro:
                startActivity(new Intent(this, FeedLivros.class));
                break;

            case R.id.menu_sair:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /****** ADAPTER DO RECYCLERVIEW ******/
    class LivroAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        //ATRIBUTO QUE RECEBER OS OBJETOS DE "ITENS"
        public List<Item> item;

        //CONSTRUTOR DA CLASSE LivroAdapter
        public LivroAdapter(List<Item> item){
            this.item = item;
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            if(viewType == 0){
                return new LivroAdapter.LivroViewHolder(
                        //processo de inflate
                        LayoutInflater.from(parent.getContext()).inflate(
                                R.layout.item_container_livro, parent, false)
                        );
            }

            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            //juntar as duas coisas (dados e interface gráfica)

            if(getItemViewType(position) == 0){
                //TIPO LIVRO

//                esse "(Livro)" serve para convercao, esta sendo convertido para um objeto de livro novamente
                Livro livro = (Livro) item.get(position).getObject();

                //holder representa uma viewHolder
                ((LivroAdapter.LivroViewHolder)holder).setLivroData(livro);

            }

//            else if (getItemViewType(position) == 0){
//                //TIPO HQ
//            }

        }

        /** MÉTODO AUXILIAR DE MANIPULAÇÃO DE POSITION PARA O MÉTODO onBindViewHolder **/
        public int getItemViewType(int position){
            return item.get(position).getType();
            //esse getType é da classe item
        }

        @Override
        public int getItemCount() {
            return item.size();
        }



        /** INÍCIO VIEWHOLDER **/
        //extend = herda
        class LivroViewHolder extends RecyclerView.ViewHolder{

            //para uso posterior
            private TextView textLivroTitulo, textLivroDescricao;
            private int cod_livro;

            /** MÉTODO CONSTRUTOR DA VIEWHOLDER **/
            public LivroViewHolder(@NonNull View itemView) {
                super(itemView);
                //super sozinho, representa o construtor da super classe (livroAdapter)

                textLivroTitulo = itemView.findViewById(R.id.textLivroTitulo);
                textLivroDescricao = itemView.findViewById(R.id.textLivroDescricao);
            }

            /** MÉTODO DE SET DE DADOS NAS TEXTVIEWS **/
            public void setLivroData(Livro livro){

                //colocando valor nas textView
                 textLivroTitulo.setText(livro.getTitulo());
                 textLivroDescricao.setText(livro.getDescricao());

            }



        } /** FIM DA VIEWHOLDER **/


    } /** FIM DA ADAPTER**/

}