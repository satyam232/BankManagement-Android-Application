package com.example.bankmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class createPIN extends AppCompatActivity {
    EditText newpin,confirmpin;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_create_pin);
        newpin=findViewById(R.id.newpin);
        confirmpin=findViewById(R.id.confirmpin);
        create=findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!String.valueOf(newpin.getText()).equals("") && !String.valueOf(confirmpin.getText()).equals("")){
                    if(Integer.parseInt(String.valueOf(newpin.getText()))==Integer.parseInt(String.valueOf(confirmpin.getText()))){
                        SharedPreferences sp=getSharedPreferences("rana",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putInt("confirmpin", Integer.parseInt(String.valueOf(confirmpin.getText())));

                        editor.apply();
                        Intent intent=new Intent(createPIN.this,PIN.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "your pin is not matching with cnf pin", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "fill all fields", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}