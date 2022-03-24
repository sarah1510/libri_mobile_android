package com.example.libri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.SQLHelper;
import helpers.DateFormat;

public class CadastroUsuario extends AppCompatActivity {

    /*** REPRESENTAÇÃO DOS CAMPOS DA ACTIVITY ***/
    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtEmail;
    private EditText txtLogin;
    private EditText txtSenha;
    private Button btnCadastrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        /** CAPTURA DOS COMPONENTES GRÁFICOS DA ACTIVITY **/
        //pegar as reprpesentacoes feitas acima (editis e btns) e

        txtNome = findViewById(R.id.txtNome);
        txtSobrenome = findViewById(R.id.txtSobrenome);
        txtEmail = findViewById(R.id.txtEmail);
        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        btnCadastrarUsuario = findViewById(R.id.btnCadastrarUsuario);

        /** TRATAMENTO DO EVENTO DE CLIQUE DO BOTÃO**/
        btnCadastrarUsuario.setOnClickListener(view->{
            if (!validate()){
                Toast.makeText(this, "PREENCHA TODOS OS CAMPOS", Toast.LENGTH_SHORT).show();
            }

            /** PROCESSO DE GRAVAÇÃO DE USUÁRIO **/
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.titulo_cadastro_usuario))
                    .setMessage(getString(R.string.mensagem_cadastro_usuario))
                    .setPositiveButton(R.string.salvar, (dialog1, which)->{
                        /** AÇÃO DO POSITIVE BUTTON **/
                        String nome = txtNome.getText().toString();
                        String sobrenome = txtSobrenome.getText().toString();
                        String email = txtEmail.getText().toString();
                        String login = txtLogin.getText().toString();
                        String senha = txtSenha.getText().toString();

                        /** DATA DE INSERÇÃO DO USUÁRIO **/
                        DateFormat df = new DateFormat();
                        String created_date = df.getDateFormat();

                        boolean cadastroUsuario = SQLHelper.getInstance(CadastroUsuario.this)
                        .addUser(nome, sobrenome, email, login, senha,
                                created_date);

                        if(cadastroUsuario){
                            Toast.makeText(this, "CADASTRO REALIZADO COM SUCESSO!", Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(this, "HOUVE UM ERRO AO REALIZAR O CADASTRO DE USUÁRIO", Toast.LENGTH_LONG).show();
                        }

                    })
                    .setNegativeButton(R.string.cancelar, (dialog1, which)->{}).create();

            dialog.show();
        });

    } //Final do método ONCREATE

    /** MÉTODO DE VALIDAÇÃO **/
    private boolean validate(){
        return(
                !txtNome.getText().toString().isEmpty() &&
                        !txtSobrenome.getText().toString().isEmpty() &&
                        !txtEmail.getText().toString().isEmpty() &&
                        !txtLogin.getText().toString().isEmpty() &&
                        !txtSenha.getText().toString().isEmpty()

        );
    }
}//Final da classe