package com.example.libri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.SQLHelper;
import helpers.Login;

public class MainActivity extends AppCompatActivity {

    //area de atributos
    //captura de tudo que eu preciso
    //private -> protege os atributos

    private EditText txtLogin;
    private EditText txtSenha;
    private Button btnLogar;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        btnLogar = findViewById(R.id.btnEntrar);
        btnCadastrar = findViewById(R.id.btnCadastrarUsuario);

        btnCadastrar.setOnClickListener(view->{
            //nos parentese, colocar: um contexto e a tela que queremos ir
            //intent exige dois parametros, estamos na main activity, e queremos ir para cadastro usuário
            //é CLASS, porque ela que monta a tela

            //a primeira forma é ideal para quando utilizarmos o `tela cadastro` em outras vezes
//                    Intent telaCadastro = new Intent(
//                            MainActivity.this,
//                            CadastroUsuario.class
//                    );
//
//                    startActivity(telaCadastro);

            //outra forma: pode chamar o startActivity direto
            startActivity(new Intent(
                    MainActivity.this,
                    CadastroUsuario.class
            ));

        }); //FIM DO BOTÃO CADASTRAR

        btnLogar.setOnClickListener(view -> {

            //Log.d("TESTE-", "OI"); 
            String login = txtLogin.getText().toString();
            String senha = txtSenha.getText().toString();

            int cod_usuario = SQLHelper
                    .getInstance(this)
                    .login(login, senha);

            if(cod_usuario > 0){
                Login.setCod_usuario(cod_usuario);

                startActivity(new Intent(
                        MainActivity.this,
                        FeedLivros.class
                ));

            }else{
                Toast.makeText(
                        this,
                        "DADOS DE LOGIN INCORRETOS",
                        Toast.LENGTH_LONG).show();
            }

        });
    }
}