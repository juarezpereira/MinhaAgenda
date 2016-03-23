package com.example.projeto.minhaagenda;

import android.app.NativeActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
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
        registerForContextMenu(mListView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contato = (Contato)parent.getItemAtPosition(position);
                Intent it = new Intent(MainActivity.this,FormularioActivity.class);
                it.putExtra("ContatoSelecionado",contato);
                startActivity(it);
            }
        });


    }

    @Override
    protected void onResume() {
        loadListContato();
        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        final Contato contatoSelected = (Contato)mListView.getAdapter().getItem(info.position);

        MenuItem itemSMS = menu.add("Enviar SMS");
        MenuItem itemCall = menu.add("Ligar para Contato");
        MenuItem itemSite = menu.add("Visitar Site");
        MenuItem itemDelet = menu.add("Apagar Contato");


        itemDelet.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Apagar contato?")
                        .setMessage("Deseja realmente apagar o contato "+contatoSelected.getNome()+"?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ContatoDAO dao = new ContatoDAO(MainActivity.this);
                                dao.deletContato(contatoSelected);
                                dao.close();
                                loadListContato();
                            }
                        })
                        .setNegativeButton("Não",null).show();

                return false;
            }
        });




        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private void loadListContato() {

        ContatoDAO dao = new ContatoDAO(this);

        List<Contato> contatos = dao.getContatos();
        ContatoAdaptador contatoAdaptador = new ContatoAdaptador(contatos,this);

        this.mListView.setAdapter(contatoAdaptador);

    }
}
