package com.example.projeto.minhaagenda;

import android.app.NativeActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getBaseContext(),FormularioActivity.class);
                startActivity(it);
            }
        });

        mListView = (ListView)findViewById(R.id.listView);

    }

    @Override
    protected void onResume() {
        loadListContato();
        super.onResume();
    }

    private void loadListContato() {

        ContatoDAO dao = new ContatoDAO(this);

        List<Contato> contatos = dao.getContatos();
        ContatoAdaptador contatoAdaptador = new ContatoAdaptador(contatos,this);

        this.mListView.setAdapter(contatoAdaptador);

    }
}
