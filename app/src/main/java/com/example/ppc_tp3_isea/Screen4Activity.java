package com.example.ppc_tp3_isea;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Screen4Activity extends AppCompatActivity {

    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private RadioButton rad1;
    private RadioButton rad2;
    private Boolean selected_radiob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);

        ed1 = (EditText)findViewById(R.id.pacienteEditText);
        ed2 = (EditText)findViewById(R.id.riesgoEditText);
        ed3 = (EditText)findViewById(R.id.riesgoRecurrenteEditText);
        rad1 = (RadioButton)findViewById(R.id.radioButtonYes);
        rad2 = (RadioButton)findViewById(R.id.radioButtonNo);

        InputFilter limitFilter = new MinMaxInputFilter(1, 10);
        ed2.setFilters(new InputFilter[] { limitFilter });
        ed3.setFilters(new InputFilter[] { limitFilter });

    }

    public void calculateRisk(View view){

        String[] archivos = fileList();

        if (rad1.isChecked()){
            selected_radiob = true;
        } else if (rad2.isChecked()){
            selected_radiob = false;
        }

        Bundle extras = new Bundle(); // Pasamos los datos necesarios para la siguiente activity.
        extras.putDouble("riesgoProg", Double.parseDouble(ed2.getText().toString()));
        extras.putDouble("riesgoRec", Double.parseDouble(ed3.getText().toString()));
        extras.putBoolean("esquema", rad1.isChecked());

        checkTexts(archivos, ed1);
        checkTexts(archivos, ed2);
        checkTexts(archivos, ed3);
        Guardar(view);

        Intent goact5 = new Intent(this, Screen5Activity.class);
        goact5.putExtras(extras);
        startActivity(goact5);
    }

    public void checkTexts(String archivos[], EditText aux){
        if(ArchivoExiste(archivos, "datos.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("datos.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                aux.setText(linea);
                br.close();
                archivo.close();
            }catch (IOException e){

            }
        }
    }

    private boolean ArchivoExiste(String archivos[], String NombreArchivo){
        for (String archivo : archivos)
            if (NombreArchivo.equals(archivo))
                return true;
        return false;
    }

    //Método para el botón Guardar
    public void Guardar(View view){
        try {
                OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("datos.txt", Activity.MODE_PRIVATE));
                archivo.write(ed1.getText().toString());
                archivo.write(ed2.getText().toString());
                archivo.write(ed3.getText().toString());
                archivo.flush();
                archivo.close();
        }catch (IOException e){

        }
        Toast.makeText(this, "Datos almacenados correctamente", Toast.LENGTH_SHORT).show();
    }
}