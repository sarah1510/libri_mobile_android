package com.example.libri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.SQLHelper;
import helpers.DateFormat;

public class CadastroLivro extends AppCompatActivity {

    private EditText txtTitulo;
    private EditText txtDescricao;
    private EditText txtFoto;
    private Button btnCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        txtTitulo = findViewById(R.id.txtNomeTitulo);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtFoto = findViewById(R.id.txtLivroFoto);
        btnCadastrar = findViewById(R.id.btnCadastrarLivro);

        btnCadastrar.setOnClickListener(view->{
            /** PROCESSO DE GRAVAÇÃO DE LIVRO **/
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.titulo_cadastro_livro))
                    .setMessage(getString(R.string.mensagem_cadastro_livro))
                    .setPositiveButton(R.string.salvar, (dialog1, which)->{
                        /** AÇÃO DO POSITIVE BUTTON **/
                        String titulo = txtTitulo.getText().toString();
                        String descricao = txtDescricao.getText().toString();
                        String foto = txtFoto.getText().toString();

                        /** DATA DE INSERÇÃO DO LIVRO **/
                        DateFormat df = new DateFormat();
                        String created_date = df.getDateFormat();

                        boolean cadastroLivro = SQLHelper.getInstance(CadastroLivro.this)
                                .addBook(1, titulo, descricao, foto,
                                        created_date);

                        if(cadastroLivro){
                            Toast.makeText(this, "CADASTRO REALIZADO COM SUCESSO!", Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(this, "HOUVE UM ERRO AO REALIZAR O CADASTRO DO LIVRO", Toast.LENGTH_LONG).show();
                        }

                    })
                    .setNegativeButton(R.string.cancelar, (dialog1, which)->{}).create();

            dialog.show();
        });

    } // FIM DO MÉTODO ON CREATE



    /** INÍCIO DO MENU **/

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

    /** FIM DO MENU **/



    private boolean validate() {

        return (
                !txtTitulo.getText().toString().isEmpty() &&
                        !txtDescricao.getText().toString().isEmpty() &&
                        !txtFoto.getText().toString().isEmpty()

        );
    }

} // FIM DA CLASSE