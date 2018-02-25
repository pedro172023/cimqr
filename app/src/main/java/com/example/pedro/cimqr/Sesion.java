package com.example.pedro.cimqr;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sesion extends AppCompatActivity {

    String txt_user;
    String txt_pass;
    Button btn_aceptar;
    @Override
    protected void onPostResume() {
        super.onPostResume();
        Cargar();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);
        btn_aceptar = (Button) findViewById(R.id.btn_aceptar);


        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_user = ((EditText) findViewById(R.id.txt_usuario)).getText().toString();
                txt_pass = ((EditText) findViewById(R.id.txt_contraseña)).getText().toString();
                comprobarcuenta(txt_user,txt_pass);
            }
        });

    }

    private void comprobarcuenta(String u , String p){
                BaseHelper helper=new BaseHelper(this, "Demo",null,1);
            SQLiteDatabase db=helper.getReadableDatabase();
            String sql= "SELECT * FROM CUENTA WHERE NOMBRE = '"+ u +"' AND CONTRASEÑA= '"+ p +"'";
            Cursor c= db.rawQuery(sql,null);
            if (c.moveToFirst()){
                String idd = Integer.toString(c.getInt(0));
                String upd="update CUENTA set ESTADO = '1' where ID= '"+ idd +"' ";
                db.execSQL(upd);
                Intent n=new Intent(this, MainActivity.class);
                startActivity(n);
            }else {
                Toast.makeText(this, "Usuario incorrecto",Toast.LENGTH_SHORT).show();
            }
            db.close();

}

    public void Cargar(){
        BaseHelper helper=new BaseHelper(this, "Demo",null,1);
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql= "SELECT * FROM CUENTA WHERE ESTADO = '1'";
        Cursor c= db.rawQuery(sql,null);
        if (c.moveToFirst()){
            Intent n=new Intent(this, MainActivity.class);
            startActivity(n);
        }
        db.close();
    }
}
