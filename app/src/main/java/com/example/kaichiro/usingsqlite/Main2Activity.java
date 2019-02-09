package com.example.kaichiro.usingsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String tipoAcaoCRUD = getIntent().getStringExtra("ActionType");
        Livro objLivro = (Livro) getIntent().getSerializableExtra("objLivro");

        EditText edtTitulo = (EditText)findViewById(R.id.txtTitulo);
        EditText edtAutor = (EditText)findViewById(R.id.txtAutor);
        EditText edtEditora = (EditText)findViewById(R.id.txtEditora);

        edtTitulo.setText(objLivro.getTitulo());
        edtAutor.setText(objLivro.getAutor());
        edtEditora.setText(objLivro.getEditora());

        Toast.makeText(getApplicationContext(), tipoAcaoCRUD, Toast.LENGTH_SHORT).show();


        Button btnCadastro = (Button) findViewById(R.id.btnGravar);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerDB crud = new ControllerDB(getBaseContext());

                Livro livro = new Livro(
                        0
                        , ((EditText) findViewById(R.id.txtTitulo)).getText().toString()
                        , ((EditText) findViewById(R.id.txtAutor)).getText().toString()
                        , ((EditText) findViewById(R.id.txtEditora)).getText().toString()
                );

                String resultado = crud.insert(livro);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
