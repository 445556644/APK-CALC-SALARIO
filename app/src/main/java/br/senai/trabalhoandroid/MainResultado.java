package br.senai.trabalhoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainResultado extends AppCompatActivity {

    private TextView resutado;

    private double salario = 0;

    private Button btnVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_resultado);

        resutado = findViewById(R.id.resultadoSalario);

        btnVoltar = findViewById(R.id.btnVoltar);


        if (getIntent().hasExtra("salario")){

            salario = getIntent().getDoubleExtra("salario", 0);

            resutado.setText(String.format("R$ " +"%5.2f", salario));


        }

        btnVoltar.setOnClickListener(view -> {

            onBackPressed();
        });
    }
}