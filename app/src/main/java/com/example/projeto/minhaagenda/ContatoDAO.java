package com.example.projeto.minhaagenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juarez on 22/03/2016.
 */
public class ContatoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA = "contatos";
    private static final String DATABASE = "MinhaAgenda";


    public ContatoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCreateTB = "CREATE TABLE " + TABELA
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome TEXT NOT NULL,"
                + "email TEXT,"
                + "site TEXT,"
                + "telefone TEXT,"
                + "endereco TEXT,"
                + "localphoto TEXT);";

        db.execSQL(queryCreateTB);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1) {
            String sql = "ALTER TABLE " + TABELA + "ADD COLUMN localphoto TEXT;";
            db.execSQL(sql);
        }
    }

    public void insertContato(Contato contat) {

        ContentValues values = new ContentValues();

        values.put("nome", contat.getNome());
        values.put("email", contat.getEmail());
        values.put("site", contat.getSite());
        values.put("telefone", contat.getTelefone());
        values.put("endereco", contat.getEndereco());
        values.put("localphoto", contat.getFoto());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public void deletContato(Contato contato) {

        SQLiteDatabase database = getWritableDatabase();
        String args[] = {String.valueOf(contato.getId())};
        database.delete("contatos", "id=?", args);

    }

    public void alterContato(Contato contato) {

        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());
        values.put("site", contato.getSite());
        values.put("telefone", contato.getTelefone());
        values.put("endereco", contato.getEndereco());
        values.put("localphoto", contato.getFoto());

        String[] id = {String.valueOf(contato.getId())};
        getWritableDatabase().update(TABELA, values, "id=?", id);

    }

    private boolean isContato(String telefone){
        String[] params = {telefone};
        Cursor cursor = getReadableDatabase().rawQuery("SELECT telefone FROM "+TABELA+"WHERE telefone=?",params);
        int total = cursor.getCount();
        return total>0;
    }

    public List<Contato> getContatos() {
        List<Contato> contatos = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA + ";", null);

        while (cursor.moveToNext()) {
            Contato contato = new Contato();
            contato.setId(cursor.getLong(cursor.getColumnIndex("id")));
            contato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            contato.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            contato.setSite(cursor.getString(cursor.getColumnIndex("site")));
            contato.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            contato.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            contatos.add(contato);
        }
        cursor.close();
        return contatos;
    }
}
