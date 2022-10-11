package com.samvabya.expensist;

import static com.samvabya.expensist.R.id.textView2;
import static com.samvabya.expensist.R.id.textView5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView t;
    private TextView tp;
    private EditText e;
    private Button btn, btn2;
    private String a = "Number of Friends:", b = "Name of Friend:", c = "Friend's Contribution:";
    private String f[];
    private int p[], i=0, sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e = findViewById(R.id.editTextTextPersonName);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        t = findViewById(textView5);
        tp = findViewById(textView2);
        t.setMovementMethod(new ScrollingMovementMethod());

        t.setText(a);
        btn.setOnClickListener(view -> {
            String m = t.getText().toString();
            String n = e.getText().toString();
            if (n.isEmpty()) {
                Toast.makeText(getApplicationContext(),"Empty Field!",Toast.LENGTH_SHORT).show();
                return;
            }
            else if (m.equals(a)) {
                try {
                    int l = Integer.parseInt(n);
                    f = new String[l];
                    p = new int[l];
                }catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(),"Input type not valid!",Toast.LENGTH_SHORT).show();
                    return;
                }
                t.setText(b);
            }
            else if (m.equals(b)) {
                f[i] = n;
                t.setText(c);
            }
            else if (m.equals(c)) {
                try {
                    p[i] = Integer.parseInt(n);
                    sum+= p[i];
                }catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(),"Input type not valid!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (i==p.length-1) {
                    int hlf = sum/p.length;
                    String fizz="Total = "+sum+"\nIndividual = "+hlf+"\n";
                    for (int k=0;k<p.length;k++) {
                        if(hlf>p[k]) {
                            fizz+=(f[k]+" Give ₹"+(hlf-p[k]));
                        }
                        else if(hlf<p[k]) {
                            fizz+=(f[k]+" Get ₹"+(p[k]-hlf));
                        }
                        else {
                            fizz+=(f[k]+" Balanced");
                        }
                        fizz+="\n";
                    }
                    tp.setText(fizz);
                    t.setText(a);
                    i = 0;
                    View v = this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
                else {
                    t.setText(b);
                    i++;
                }
            }
            e.setText("");
        });

        btn2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });
    }
}