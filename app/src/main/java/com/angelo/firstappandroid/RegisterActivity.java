package com.angelo.firstappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout textInputLayout3, textInputLayout4, textInputLayout6, textInputLayout5;
    private TextInputEditText editTextUsuario, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button buttonRegistro;

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

        // Inicializar vistas
        textInputLayout3 = findViewById(R.id.textInputLayout3);
        textInputLayout4 = findViewById(R.id.textInputLayout4);
        textInputLayout6 = findViewById(R.id.textInputLayout6);
        textInputLayout5 = findViewById(R.id.textInputLayout5);

        editTextUsuario = (TextInputEditText) textInputLayout3.getEditText();
        editTextEmail = (TextInputEditText) textInputLayout4.getEditText();
        editTextPassword = (TextInputEditText) textInputLayout6.getEditText();
        editTextConfirmPassword = (TextInputEditText) textInputLayout5.getEditText();

        buttonRegistro = findViewById(R.id.button2);

        buttonRegistro.setOnClickListener(v -> {
            if (validarCampos()) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                // 1. Aquí iría tu lógica de guardar datos (base de datos, API, etc.)
                // guardarUsuario();

                // 2. Ir al login después de registrar
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);

                // 3. Cerrar esta actividad para que no pueda volver atrás
                finish();
            }
        });
    }

    private boolean validarCampos() {
        boolean usuarioValido = validarUsuario();
        boolean emailValido = validarEmail();
        boolean passwordValido = validarPassword();
        boolean confirmValido = validarConfirmPassword();

        return usuarioValido && emailValido && passwordValido && confirmValido;
    }

    private boolean validarUsuario() {
        String usuario = editTextUsuario.getText().toString().trim();

        if (usuario.isEmpty()) {
            textInputLayout3.setError("El usuario no puede estar vacío");
            return false;
        } else if (usuario.length() < 3) {
            textInputLayout3.setError("Debe tener más de 3 caracteres");
            return false;
        } else {
            textInputLayout3.setError(null);
            return true;
        }
    }

    private boolean validarEmail() {
        String email = editTextEmail.getText().toString().trim();

        if (email.isEmpty()) {
            textInputLayout4.setError("El email no puede estar vacío");
            return false;
        } else if (email.length() < 3) {
            textInputLayout4.setError("Debe tener más de 3 caracteres");
            return false;
        } else if (!email.contains("@")) {
            textInputLayout4.setError("Email no válido");
            return false;
        } else {
            textInputLayout4.setError(null);
            return true;
        }
    }

    private boolean validarPassword() {
        String password = editTextPassword.getText().toString();

        if (password.isEmpty()) {
            textInputLayout6.setError("La contraseña no puede estar vacía");
            return false;
        } else if (password.length() < 3) {
            textInputLayout6.setError("Debe tener más de 3 caracteres");
            return false;
        } else {
            textInputLayout6.setError(null);
            return true;
        }
    }

    private boolean validarConfirmPassword() {
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        if (confirmPassword.isEmpty()) {
            textInputLayout5.setError("Debes confirmar la contraseña");
            return false;
        } else if (!password.equals(confirmPassword)) {
            textInputLayout5.setError("Las contraseñas no coinciden");
            return false;
        } else {
            textInputLayout5.setError(null);
            return true;
        }
    }
}