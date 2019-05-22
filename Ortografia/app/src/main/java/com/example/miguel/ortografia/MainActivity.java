package com.example.miguel.ortografia;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText etxtText;
    private TextView txtWords, txtErrors;

    private String text;

//    private final String BAD_WORD = "cion";

    private Handler handlerWords = new Handler();
    private Runnable runnableWords = new Runnable() {
        @Override
        public void run() {

            etxtText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    text = etxtText.getText().toString();

                    int palabras = 1;
                    for (int i = 0; i < text.length(); i++) {
                        if((i+1) < text.length()){
                            if (text.charAt(i) == 32 && text.charAt(i+1) != 32) {
                                palabras++;
                            }
                        }
                    }

                    if (text.length() == 0) {
                        txtWords.setText("Palabras: 0");
                    } else {
                        txtWords.setText("Palabras: " + palabras);
                    }
//                Toast.makeText(MainActivity.this, count(text)+"", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    };

    private Thread threadWords = new Thread() {
        @Override
        public void run() {
            super.run();
            handlerWords.post(runnableWords);
        }
    };

    private Handler handlerErros = new Handler();
    private Runnable runnableErros = new Runnable() {
        @Override
        public void run() {
            etxtText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    text = etxtText.getText().toString();

                    int errors = 0;
                    for(int i = 0; i < text.length(); i++){
                        if(text.charAt(i)== 'c'){
                            if((i+3) < text.length()){
                                if(text.charAt(i+1)=='i'&&text.charAt(i+2)=='o' && text.charAt(i+3)=='n'){
                                    errors++;
                                }
                            }
                        }
//                        if(text.indexOf(BAD_WORD) == i){
//                            errors++;
//                        }
                        txtErrors.setText("Errores: " + errors);
                    }

//                    Log.wtf("ORALE",text.indexOf(BAD_WORD)+"");

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    };

    private Thread threadErrors = new Thread() {
        @Override
        public void run() {
            super.run();
            handlerErros.post(runnableErros);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtText = findViewById(R.id.etxtTexto);
        txtWords = findViewById(R.id.txtWords);
        txtErrors = findViewById(R.id.txtErrors);

        threadWords.start();
        threadErrors.start();
    }
}
