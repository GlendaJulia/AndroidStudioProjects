package tecsup.edu.pe.progapl001saludo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SaludoActivity extends AppCompatActivity {

    EditText num_1;
    EditText num_2;
    Button multi;
    Button resta;
    Button limpiar;
    Button dividir;
    Button suma;
    String rpta;
    int n1= 0;
    int n2= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);

        num_1 = (EditText) findViewById(R.id.num_1);
        num_2 = (EditText) findViewById(R.id.num_2);

        suma= (Button) findViewById(R.id.suma);
        resta= (Button) findViewById(R.id.resta);
        multi= (Button) findViewById(R.id.multi);
        dividir= (Button) findViewById(R.id.dividir);
        limpiar= (Button) findViewById(R.id.limpiar);



        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_1.setText(null);
                num_2.setText(null);
            }
        });
        suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n1= Integer.parseInt(num_1.getText().toString());
                n2= Integer.parseInt(num_2.getText().toString());
                rpta=String.valueOf(n1+n2);

                Toast.makeText(getApplicationContext(), "La suma es:" + rpta,Toast.LENGTH_LONG).show();
            }
        });
        resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n1= Integer.parseInt(num_1.getText().toString());
                n2= Integer.parseInt(num_2.getText().toString());
                rpta=String.valueOf(n1-n2);

                Toast.makeText(getApplicationContext(), "La resta es:" + rpta,Toast.LENGTH_LONG).show();
            }
        });
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rpta=String.valueOf(n1*n2);

                Toast.makeText(getApplicationContext(), "La multiplicacion es:" + rpta,Toast.LENGTH_LONG).show();
            }
        });
        dividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rpta=String.valueOf(n1/n2);

                Toast.makeText(getApplicationContext(), "La division es:" + rpta,Toast.LENGTH_LONG).show();
            }
        });
    }
}
