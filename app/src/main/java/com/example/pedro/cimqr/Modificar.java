package com.example.pedro.cimqr;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {
    private EditText et_nombre,et_apellido;
    private Button bt_eliminar,bt_modificar;
    int id;
    String nombre,apellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b= getIntent().getExtras();
        if(b!=null)
        {
            id=b.getInt("Id");
            nombre=b.getString("Nombre");
            apellido=b.getString("Apellido");
        }

        setContentView(R.layout.activity_modificar);

        et_nombre=(EditText)findViewById(R.id.et_nombre);
        et_apellido=(EditText)findViewById(R.id.et_apellido);

        et_apellido.setText(apellido);
        et_nombre.setText(nombre);

        bt_modificar=(Button)findViewById(R.id.bt_modificar);
        bt_eliminar=(Button)findViewById(R.id.bt_eliminar);

        bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar(id,et_nombre.getText().toString(),et_apellido.getText().toString());
                onBackPressed();
            }
        });
        bt_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar(id);
                onBackPressed();
            }
        });


    }
    private void modificar(int Id, String Nombre, String Apellido){
        BaseHelper helper=new BaseHelper(this, "Demo",null,1);
        SQLiteDatabase db=helper.getWritableDatabase();

        String sql="update PERSONAS set Nombre ='" + Nombre + "',Apellido='" + Apellido + "' where ID= "+Id;
        db.execSQL(sql);
        db.close();

    }
    private void eliminar(int Id){
        BaseHelper helper=new BaseHelper(this, "Demo",null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="delete from PERSONAS where ID= "+Id;
        db.execSQL(sql);
        db.close();

    }
}
