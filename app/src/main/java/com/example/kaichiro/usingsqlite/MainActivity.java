package com.example.kaichiro.usingsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final ListView lista = (ListView) findViewById(R.id.lvLivros);
//        String[] equipes = new String[]{"kaichir", "fukuda"};
//        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, equipes);
//        lista.setAdapter(adaptador);


        ControllerDB ctrl = new ControllerDB(this);

        final ListView lista = (ListView) findViewById(R.id.lvLivros);
        final ArrayList<Livro> livros = ctrl.getAll();

        LivroAdapter adapter = new LivroAdapter(this, livros);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("ActionType", Utils.ACTION_TYPE_CRUD.UPDATE.toString());
                intent.putExtra("objLivro", new Livro(0, "", "", ""));
                startActivity(intent);
            }
        });
    }

    public void cadastrarLivro(View v) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("ActionType", Utils.ACTION_TYPE_CRUD.CREATE.toString());
        intent.putExtra("objLivro", new Livro(0, "", "", ""));
        startActivity(intent);
    }

}
