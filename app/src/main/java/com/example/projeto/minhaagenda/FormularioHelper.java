package com.example.projeto.minhaagenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Juarez on 22/03/2016.
 */
public class FormularioHelper {

    private Contato contato;

    private EditText edtName;
    private EditText edtEmail;
    private EditText edtSite;
    private EditText edtTelefone;
    private EditText edtEndereco;
    private Button btnPhoto;
    private ImageView imageContato;

    public FormularioHelper(FormularioActivity activity) {
        this.contato = new Contato();

        this.edtName = (EditText) activity.findViewById(R.id.edtName);
        this.edtEmail = (EditText) activity.findViewById(R.id.edtEmail);
        this.edtSite = (EditText) activity.findViewById(R.id.edtSite);
        this.edtTelefone = (EditText) activity.findViewById(R.id.edtTelefone);
        this.edtEndereco = (EditText) activity.findViewById(R.id.edtEndereco);
        this.btnPhoto = (Button) activity.findViewById(R.id.btnPhoto);
        this.imageContato = (ImageView) activity.findViewById(R.id.imageView);
    }

    public Button getBtnPhoto() {
        return btnPhoto;
    }

    public Contato getContatoFormulario(){

        contato.setNome(edtName.getText().toString());
        contato.setEmail(edtEmail.getText().toString());
        contato.setSite(edtSite.getText().toString());
        contato.setTelefone(edtTelefone.getText().toString());
        contato.setEndereco(edtEndereco.getText().toString());
        contato.setFoto((String) imageContato.getTag());

        return contato;
    }

    public void setContatoFormulario(Contato contato){

        edtName.setText(contato.getNome());
        edtEmail.setText(contato.getEmail());
        edtSite.setText(contato.getSite());
        edtTelefone.setText(contato.getTelefone());
        edtEndereco.setText(contato.getEndereco());

        imageContato.setTag(contato.getFoto());
        loadImage(contato.getFoto());

    }

    public void loadImage(String LocalPhoto){
        if(LocalPhoto != null){
            Bitmap imagePhoto = BitmapFactory.decodeFile(LocalPhoto);
            imageContato.setImageBitmap(imagePhoto);
            imageContato.setTag(LocalPhoto);
        }
    }
}
