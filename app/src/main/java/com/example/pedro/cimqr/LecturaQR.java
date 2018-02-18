package com.example.pedro.cimqr;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LecturaQR extends AppCompatActivity {
    private Button scan_btn;
    private TextView resultado;
    private EditText et_nombre,et_apellido;
    private Button bt_guardar,bt_mostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura_qr);
        scan_btn =(Button)findViewById(R.id.scan_btn);

        et_nombre=(EditText)findViewById(R.id.et_nombre);
        et_apellido=(EditText)findViewById(R.id.et_apellido);

        bt_guardar=(Button)findViewById(R.id.bt_guardar);
        bt_mostrar=(Button)findViewById(R.id.bt_mostrar);


        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(et_nombre.getText().toString(),et_apellido.getText().toString());
            }
        });

        bt_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LecturaQR.this, Listado.class));
            }
        });

        resultado=(TextView)findViewById(R.id.resultado);
        final Activity activity=this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator =new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Escaneando");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    private void guardar(String Nombre, String Apellido){
        BaseHelper helper=new BaseHelper(this, "Demo",null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
        try{
            ContentValues c=new ContentValues();
            c.put("Nombre",Nombre);
            c.put("Apellido",Apellido);
            db.insert("PERSONAS",null,c);
            db.close();
            Toast.makeText(this, "Registro Insertado",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Error:"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if (result.getContents()==null){
                Toast.makeText(this, "Tu cancelaste el escaneo",Toast.LENGTH_LONG).show();

            }else{
                resultado.setText(result.getContents());
            }
        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
