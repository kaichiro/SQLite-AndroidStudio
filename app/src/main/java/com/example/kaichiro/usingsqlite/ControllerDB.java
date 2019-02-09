package com.example.kaichiro.usingsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ControllerDB {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public ControllerDB(Context context) {
        banco = new CriaBanco(context);
    }

    /**
     * Método para inserção de registro
     * titulo = atributo
     * autor = atributo
     * editora = atributo
     */
    public String insert(Livro livro) {

        long resultado;

        // Modo de escrita na base de dados
        db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CriaBanco.TITULO, livro.getTitulo());
        valores.put(CriaBanco.AUTOR, livro.getAutor());
        valores.put(CriaBanco.EDITORA, livro.getEditora());

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    /**
     * Carregar dados
     */
    public List<Livro> getAll() {
        List<Livro> livros = new ArrayList<>();

        String sqlSelect = "select * from " + CriaBanco.TABELA;

        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Livro livro = new Livro(
                        Integer.parseInt(cursor.getString(0))
                        , cursor.getString(1)
                        , cursor.getString(2)
                        , cursor.getString(3)
                );
            } while (cursor.moveToNext());
        }

        db.close();

        return livros;
    }

    public Livro findById(int id_) {
        Livro livro = null;

        db = banco.getReadableDatabase();

        Cursor cursor =
                db.query(
                        CriaBanco.TABELA,
                        new String[]{CriaBanco.ID},
                        "ID = ?",
                        new String[]{String.valueOf(id_)},
                        null,
                        null,
                        null
                );

        if (cursor != null) {
            cursor.moveToFirst();
            livro = new Livro(
                    Integer.parseInt(cursor.getString(0))
                    , cursor.getString(1)
                    , cursor.getString(2)
                    , cursor.getString(3)
            );

        } else {
            throw new RuntimeException("Não existe registro com ID (" + String.valueOf(id_) + ")");
        }

        db.close();
        return livro;
    }

    public void delete(Livro livro) {
        db = banco.getWritableDatabase();
        db.delete(CriaBanco.TABELA, CriaBanco.ID + " = ?", new String[]{String.valueOf(livro.getId())});
    }

    public Livro update(Livro livro) {
        db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(CriaBanco.TITULO, livro.getTitulo());
        valores.put(CriaBanco.AUTOR, livro.getAutor());
        valores.put(CriaBanco.TITULO, livro.getEditora());
        valores.put(CriaBanco.TITULO, livro.getTitulo());

        db.update(
                CriaBanco.TABELA
                , valores
                , CriaBanco.ID + " = ?"
                , new String[]{String.valueOf(livro.getId())}
        );

        db.close();

        return this.findById(livro.getId());
    }

    public Livro persist(Livro livro) {
        Livro returnLivro = null;

        db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(CriaBanco.TITULO, livro.getTitulo());
        valores.put(CriaBanco.AUTOR, livro.getAutor());
        valores.put(CriaBanco.TITULO, livro.getEditora());
        valores.put(CriaBanco.TITULO, livro.getTitulo());

        if (livro.getId() > 0) {
            db.update(CriaBanco.TABELA, valores, CriaBanco.ID + " = ?", new String[]{String.valueOf(livro.getId())});
            returnLivro = this.findById(livro.getId());
        } else {
            valores.put(CriaBanco.ID, livro.getId());
            db.insert(CriaBanco.TABELA, null, valores);

            Cursor cursor = db.query(CriaBanco.TABELA, null, null, null, null, null, "ID desc", "1");
            if (cursor != null) {
                cursor.moveToFirst();
                returnLivro = new Livro(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            } else {
                returnLivro = null;
            }
        }

        db.close();

        return returnLivro;
    }
}
