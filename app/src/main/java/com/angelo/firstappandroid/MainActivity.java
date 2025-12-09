package com.angelo.firstappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout textInputLayout, textInputLayout2;
    private TextInputEditText editTextUsuario, editTextPassword;
    private Button buttonIngresar;
    private TextView textViewRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textInputLayout = findViewById(R.id.textInputLayout);
        textInputLayout2 = findViewById(R.id.textInputLayout2);
        editTextUsuario = (TextInputEditText) textInputLayout.getEditText();
        editTextPassword = (TextInputEditText) textInputLayout2.getEditText();
        buttonIngresar = findViewById(R.id.button);
        textViewRegistro = findViewById(R.id.textView5); // TextView "¿No te has registrado?"

        buttonIngresar.setOnClickListener(v -> {
            if (validarCampos()) {
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show();
                // Aquí iría tu lógica de login
            }
        });

        textViewRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            // finish();
        });
    }

    private boolean validarCampos() {
        boolean usuarioValido = validarUsuario();
        boolean contrasenaValida = validarContrasena();
        return usuarioValido && contrasenaValida;
    }

    private boolean validarUsuario() {
        String usuario = editTextUsuario.getText().toString().trim();

        if (usuario.isEmpty()) {
            textInputLayout.setError("El usuario no puede estar vacío");
            return false;
        } else if (usuario.length() < 3) {
            textInputLayout.setError("Debe tener más de 3 caracteres");
            return false;
        } else {
            textInputLayout.setError(null);
            return true;
        }
    }

    private boolean validarContrasena() {
        String contrasena = editTextPassword.getText().toString();

        if (contrasena.isEmpty()) {
            textInputLayout2.setError("La contraseña no puede estar vacía");
            return false;
        } else if (contrasena.length() < 3) {
            textInputLayout2.setError("Debe tener más de 3 caracteres");
            return false;
        } else {
            textInputLayout2.setError(null);
            return true;
        }
    }
}