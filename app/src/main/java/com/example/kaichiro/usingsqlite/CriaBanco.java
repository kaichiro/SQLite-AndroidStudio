package com.example.kaichiro.usingsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CriaBanco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "livros";
    public static final String ID = "_id";
    public static final String TITULO = "titulo";
    public static final String AUTOR = "autor";
    public static final String EDITORA = "editora";
    public static final int VERSAO = 1;

//    db browser for sqlite = ferramenta para criar os scripts para SQLite

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLEA_LIVROS = "CREATE TABLE " + TABELA + " " +
                "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TITULO + " TEXT, " +
                AUTOR + " TEXT, " +
                EDITORA + " TEXT " +
                ")";

        db.execSQL(CREATE_TABLEA_LIVROS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);

        onCreate(db);
    }
}
