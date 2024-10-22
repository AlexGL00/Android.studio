package com.AlexGL00.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputLayout registerUsernameTIL = findViewById(R.id.registerUsernameTIL);
        TextInputLayout registerCorreoTIL = findViewById(R.id.registerCorreoTIL);
        TextInputLayout registerPasswordTIL = findViewById(R.id.registerPasswordnameTIL);
        TextInputLayout registerPasswordConfirmTIL = findViewById(R.id.registerPasswordConfirmTIL);
        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = String.valueOf(registerUsernameTIL.getEditText().getText());
                String userCorreo = String.valueOf(registerCorreoTIL.getEditText().getText());
                String userPassword = String.valueOf(registerPasswordTIL.getEditText().getText());
                String userPasswordCheck = String.valueOf(registerPasswordConfirmTIL.getEditText().getText());

                if (!userPassword.equals(userPasswordCheck)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Tus contraseñas no coinciden", Toast.LENGTH_LONG);
                } else {
                    SharedPreferences preferences = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Username", userName);
                    editor.putString("Correo" , userCorreo);
                    editor.putString("password", userPassword);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "¡Sea registrado correctamente!", Toast.LENGTH_LONG).show();
                    volverInicio();
                }



            }
            public void volverInicio() {
                Intent intent = new Intent(Register.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}