package br.senai.trabalhoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     private EditText salario, dependentes, valor;

     private RadioButton simVa, naoVa, simVr, naoVr, simVt, naoVt;
     private RadioGroup rgVa, rgVr, rgVt;
    private Button enviar, limpar;
    private Spinner spinner;

    String tipoPlano, valeRefei, valeTran, valeAli;



    double salarioLiqui;
    double inss, vat, va, vr, irrf , resultado,  vps = 0;
    int depen;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        salario = findViewById(R.id.txtSalario);
        dependentes = findViewById(R.id.numDependentes);
        simVa = findViewById(R.id.simVa);
        naoVa = findViewById(R.id.naoVa);
        simVr = findViewById(R.id.simVr);
        naoVr = findViewById(R.id.naoVr);
        simVt = findViewById(R.id.simVt);
        naoVt = findViewById(R.id.naoVt);
        enviar = findViewById(R.id.enviar);
        valor = findViewById(R.id.edtexResult);
        rgVa = findViewById(R.id.rgVa);
        rgVr = findViewById(R.id.rgVr);
        rgVt = findViewById(R.id.rgVt);
        limpar = findViewById(R.id.limpar);

        enviar.setOnClickListener(v ->{




            if(salario.getText().toString().isEmpty()){
                salario.setError(getString(R.string.validaSalario));
                Toast.makeText(getBaseContext(), R.string.validaSalario, Toast.LENGTH_SHORT).show();

            }
           else if (dependentes.getText().toString().isEmpty()){
                dependentes.setError(getString(R.string.validaDependentes));
                Toast.makeText(getBaseContext(),R.string.validaDependentes, Toast.LENGTH_SHORT).show();
            }else {
                salarioLiqui = Double.parseDouble(salario.getText().toString());
                depen = Integer.parseInt(dependentes.getText().toString());


                if (spinner.getSelectedItemPosition() == 0) {
                    tipoPlano = "standard";
                    if (salarioLiqui <= 3000) {
                        vps = 60;
                    } else {
                        vps = 80;
                    }

                } else if (spinner.getSelectedItemPosition() == 1) {
                    tipoPlano = "basico";

                    if (salarioLiqui <= 3000) {
                        vps = 80;
                    } else {
                        vps = 110;
                    }

                } else if (spinner.getSelectedItemPosition() == 2) {
                    tipoPlano = "Super";
                    if (salarioLiqui <= 3000) {
                        vps = 95;
                    } else {
                        vps = 135;
                    }


                } else if (spinner.getSelectedItemPosition() == 3) {
                    tipoPlano = "Master";
                    if (salarioLiqui <= 3000) {
                        vps = 130;

                    } else {
                        vps = 180;
                    }

                }
                if (salarioLiqui <= 1212.00) {
                    inss = salarioLiqui * 0.075;

                } else if (salarioLiqui <= 2427.35) {
                    inss = (salarioLiqui * 0.09) - 18.18;

                } else if (salarioLiqui <= 3641.03) {
                    inss = (salarioLiqui * 0.12) - 91.00;

                } else if (salarioLiqui <= 7087.22) {
                    inss = (salarioLiqui * 0.14) - 163.827;

                } else {
                    inss = 7087.22 * 0.14 - 163.827;
                }


                switch (rgVt.getCheckedRadioButtonId()) {
                    case R.id.simVt:

                        vat = salarioLiqui * (6.0 / 100);

                        break;
                    case R.id.naoVt:

                        vat = 0;
                        break;
                }
                switch (rgVa.getCheckedRadioButtonId()) {
                    case R.id.simVa:
                        if (salarioLiqui <= 3000) {
                            va = 15.00;
                        } else if (salarioLiqui <= 5000) {
                            va = 25.00;
                        } else {
                            va = 35.00;
                        }
                        break;
                    case R.id.naoVa:
                        va = 0;
                        break;
                }
                switch (rgVr.getCheckedRadioButtonId()) {
                    case R.id.simVr:
                        if (salarioLiqui <= 3000) {

                            vr = 2.60 * 22;

                        } else if (salarioLiqui <= 5000) {

                            vr = 3.65 * 22;

                        } else {
                            vr = 6.50 * 22;
                        }
                        break;
                    case R.id.naoVr:
                        vr = 0;

                }
                irrf = salarioLiqui - inss - (189.59 * depen);
                if (irrf <= 1903.98) {
                    irrf = 0;
                } else if (irrf <= 2826.65) {
                    irrf = irrf * 0.075 - 142.80;
                } else if (irrf <= 3751.05) {
                    irrf = irrf * 0.15 - 354.80;
                } else if (irrf <= 4664.68) {
                    irrf = irrf * 0.225 - 636.13;
                } else if (irrf >= 4664.68) {
                    irrf = irrf * 0.275 - 869.36;
                }
                resultado = salarioLiqui - inss - vat - vr - va - irrf - vps;

                valor.setText("R$ " + String.format("%5.2f", resultado));

                Intent intent = new Intent(this, MainResultado.class);

                intent.putExtra("salario",resultado);
                startActivity(intent);

            }




            
        });
        limpar.setOnClickListener(view -> {

            salario.setText("");
            dependentes.setText("");
            rgVr.clearCheck();
            rgVa.clearCheck();
            rgVt.clearCheck();
            valor.setText("");

            spinner.setSelection(0);


        });
}
    }
