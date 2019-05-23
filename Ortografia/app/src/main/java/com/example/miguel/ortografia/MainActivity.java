package com.example.miguel.ortografia;

import android.os.AsyncTask;
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

    private String text1;
    private String text2;

//    private final String BAD_WORD = "cion";

    private Handler handlerWords = new Handler();
    private Runnable runnableWords = new Runnable() {
        @Override
        public void run() {

            while(true){
                text1 = etxtText.getText().toString();
//                text = "";
                txtWords.setText("RUN");
                int palabras = 1;
                for (int i = 0; i < text1.length(); i++) {
                    if((i+1) < text1.length()){
                        if (text1.charAt(i) == 32 && text1.charAt(i+1) != 32) {
                            palabras++;
                        }
                    }
                }

                if (text1.length() == 0) {
                    txtWords.setText("Palabras: 0");
                } else {
                    txtWords.setText("Palabras: " + palabras);
                }
            }

        }
    };

    private Thread threadWords = new Thread() {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(500);
                handlerWords.post(runnableWords);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Handler handlerErros = new Handler();
    private Runnable runnableErros = new Runnable() {
        @Override
        public void run() {
        }
    };

    private Thread threadErrors = new Thread() {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(500);
                handlerErros.post(runnableErros);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtText = findViewById(R.id.etxtTexto);
        txtWords = findViewById(R.id.txtWords);
        txtErrors = findViewById(R.id.txtErrors);

//        threadWords.start();
//        threadErrors.start();

        MyHilo myHilo = new MyHilo();
        myHilo.execute(null, null, null);
    }

    class MyHilo extends AsyncTask<Void,Integer,Void>{
        int palabras=1;
        int errors = 0;

        @Override
        protected Void doInBackground(Void... voids) {
            while (true){
                try {
                    Thread.sleep(800);

                    publishProgress();



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            text1 = etxtText.getText().toString();

            for (int i = 0; i < text1.length(); i++) {
                if((i+1) < text1.length()){
                    if (text1.charAt(i) == 32 && text1.charAt(i+1) != 32) {
                        palabras++;
                    }
                }
            }


            text2 = etxtText.getText().toString();


            for(int i = 0; i < text2.length(); i++){
                if((text2.charAt(i)== 'c') || (text2.charAt(i)== 'C')){
                    if((i+3) < text2.length()){
                        if((text2.charAt(i+1)=='i'&&text2.charAt(i+2)=='o' && text2.charAt(i+3)=='n')|| (text2.charAt(i+1)=='I'&&text2.charAt(i+2)=='O' && text2.charAt(i+3)=='N')){
                            errors++;
                        }
                    }
                }
                txtErrors.setText("Errores: " + errors);
            }

            if (text1.length() == 0) {
                txtWords.setText("Palabras: 0");
            } else {
                txtWords.setText("Palabras: " + palabras);
            }

            palabras =1;
            errors = 0;

        }
    }
}
