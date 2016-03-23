package com.example.projeto.minhaagenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class FormularioActivity extends AppCompatActivity {


    private FormularioHelper helper;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Editar");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        helper = new FormularioHelper(this);
        Intent intent = this.getIntent();
        this.contato = (Contato) intent.getSerializableExtra("ContatoSelecionado");

        if(contato != null){
            this.helper.setContatoFormulario(contato);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(FormularioActivity.this);
                break;
            case R.id.action_confirm:

                Contato contato = helper.getContatoFormulario();
                ContatoDAO dao = new ContatoDAO(FormularioActivity.this);

                if(contato.getId() == null){
                    dao.insertContato(contato);
                }else {
                    dao.alterContato(contato);
                }
                dao.close();

                finish();
                break;
            default:
                return false;

        }
        return super.onOptionsItemSelected(item);
    }

}
