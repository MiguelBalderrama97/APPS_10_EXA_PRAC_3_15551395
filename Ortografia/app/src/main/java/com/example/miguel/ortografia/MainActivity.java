package com.example.miguel.ortografia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText etxtText;
    private Button btnGo;
    private TextView txtWords, txtErrors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtText = findViewById(R.id.etxtTexto);
        btnGo = findViewById(R.id.btnComprobar);
        txtWords = findViewById(R.id.txtWords);
        txtErrors = findViewById(R.id.txtErrors);
    }
}
