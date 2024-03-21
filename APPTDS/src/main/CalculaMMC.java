package src.main;

import src.main.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;   
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.graphics.Color;

public class CalculaMMC extends Activity {
    private EditText etNumero1;
    private EditText etNumero2;
    private TextView tvMMC;
    private Button btCalcular;
    private Button btResetar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero1 = (EditText) findViewById(R.id.etNumero1);
        etNumero2 = (EditText) findViewById(R.id.etNumero2);
        tvMMC = (TextView) findViewById(R.id.tvMMC);
        btCalcular = (Button) findViewById(R.id.btCalcular);
        btResetar = (Button) findViewById(R.id.btResetar);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btCalcular();
            }
        });

        btResetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btResetar();
            }
        });
    }

    protected void btCalcular() {
        if (etNumero1.getText().toString().equals("") || etNumero2.getText().toString().equals("")) {
            Toast.makeText(this, "Preencha os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String numero1Str = etNumero1.getText().toString();
        String numero2Str = etNumero2.getText().toString();

        if (!numero1Str.matches("\\d+") || !numero2Str.matches("\\d+")) {
            Toast.makeText(this, "Por favor, insira apenas números", Toast.LENGTH_SHORT).show();
            return;
        }

        int numero1 = Integer.parseInt(etNumero1.getText().toString());
        int numero2 = Integer.parseInt(etNumero2.getText().toString());
        int mmc = 1;
        int divisor = 2;

        while (numero1 != 1 || numero2 != 1) {
            if (numero1 % divisor == 0 || numero2 % divisor == 0) {
                mmc *= divisor;
                if (numero1 % divisor == 0) {
                    numero1 /= divisor;
                }
                if (numero2 % divisor == 0) {
                    numero2 /= divisor;
                }
            } else {
                divisor++;
            }
        }
        tvMMC.setVisibility(View.VISIBLE);
        tvMMC.setText("MMC: " + mmc);
    }

    protected void btResetar() {
        etNumero1.setText("");
        etNumero2.setText("");
        tvMMC.setVisibility(View.INVISIBLE);
    }
}