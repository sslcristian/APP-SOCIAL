package nose;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class RegisterActivity extends AppCompatActivity {
    EditText txtNombre, txtEmail, txtPassword; Button btnRegister; ApiService api;
    @Override protected void onCreate(Bundle s){ super.onCreate(s); setContentView(R.layout.register);
        txtNombre=findViewById(R.id.txtNombre); txtEmail=findViewById(R.id.txtEmail); txtPassword=findViewById(R.id.txtPassword);
        btnRegister=findViewById(R.id.btnRegister); api = ApiClient.getClient().create(ApiService.class);
        btnRegister.setOnClickListener(v -> {
            String nombre=txtNombre.getText().toString(), email=txtEmail.getText().toString(), pass=txtPassword.getText().toString();
            api.register(new RegisterRequest(nombre,email,pass)).enqueue(new Callback<User>() {
                @Override public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){ Toast.makeText(RegisterActivity.this, "Registrado", Toast.LENGTH_SHORT).show(); finish(); }
                    else Toast.makeText(RegisterActivity.this, "Error registro", Toast.LENGTH_SHORT).show();
                }
                @Override public void onFailure(Call<User> call, Throwable t){ Toast.makeText(RegisterActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show(); }
            });
        });
    }
}
