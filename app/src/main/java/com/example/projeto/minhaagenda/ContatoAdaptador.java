package com.example.projeto.minhaagenda;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Juarez on 22/03/2016.
 */
public class ContatoAdaptador extends BaseAdapter {

    private final List<Contato> mContatos;
    private final Activity activity;

    public ContatoAdaptador(List<Contato> mContatos, Activity activity) {
        this.mContatos = mContatos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.mContatos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mContatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.mContatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        Contato contato = mContatos.get(position);
        Bitmap bitmap;

        if(view == null){
            view = this.activity.getLayoutInflater().inflate(R.layout.item_contato,parent,false);
        }

        TextView tvName = (TextView)view.findViewById(R.id.tvNameContato);
        TextView tvEmail = (TextView)view.findViewById(R.id.tvEmailContato);
        TextView tvTelefone = (TextView)view.findViewById(R.id.tvTelefoneContato);
        TextView tvEdereco = (TextView)view.findViewById(R.id.tvEnderecoContato);

        ImageView imgContato = (ImageView)view.findViewById(R.id.imgContato);

        tvName.setText(contato.getNome());
        if(contato.getFoto() != null){
            bitmap = BitmapFactory.decodeFile(contato.getFoto());
            imgContato.setImageBitmap(bitmap);
        }
        if(tvEmail != null){
            tvEmail.setText(contato.getEmail());
        }
        if(tvTelefone != null){
            tvTelefone.setText(contato.getTelefone());
        }
        if(tvEdereco != null){
            tvEdereco.setText(contato.getEndereco());
        }

        return view;
    }
}
