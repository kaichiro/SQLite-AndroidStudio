package com.example.kaichiro.usingsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LivroAdapter extends ArrayAdapter<Livro> {
    private final Context context;
    private final ArrayList<Livro> livros;

    public LivroAdapter(Context c, ArrayList<Livro> e) {
        super(c, R.layout.activity_main, e);
        this.context = c;
        this.livros = e;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.livro_item, parent, false);

        TextView id_ = (TextView) rowView.findViewById(R.id.txtViewId);
        TextView nome = (TextView) rowView.findViewById(R.id.txtViewTitulo);
        TextView editora = (TextView) rowView.findViewById(R.id.txtViewEditora);
        TextView autor = (TextView) rowView.findViewById(R.id.txtViewAutor);

        id_.setText(String.valueOf(livros.get(position).getId()));
        nome.setText(livros.get(position).getTitulo());
        editora.setText(livros.get(position).getEditora());
        autor.setText(livros.get(position).getAutor());

        return rowView;
    }

}
