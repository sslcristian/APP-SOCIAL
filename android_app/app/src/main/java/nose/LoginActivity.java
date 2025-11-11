package nose;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginActivity extends AppCompatActivity {
    EditText txtEmail, txtPassword; Button btnLogin, btnRegister; ApiService api;
    @Override protected void onCreate(Bundle s){ super.onCreate(s); setContentView(R.layout.login);
        txtEmail=findViewById(R.id.txtEmail); txtPassword=findViewById(R.id.txtPassword);
        btnLogin=findViewById(R.id.btnLogin); btnRegister=findViewById(R.id.btnRegister);
        api = ApiClient.getClient().create(ApiService.class);
        btnLogin.setOnClickListener(v -> {
            String email=txtEmail.getText().toString(), pass=txtPassword.getText().toString();
            api.login(new LoginRequest(email, pass)).enqueue(new Callback<LoginResponse>() {
                @Override public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.isSuccessful() && response.body()!=null){ startActivity(new Intent(LoginActivity.this, FeedActivity.class)); finish(); }
                    else Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
                @Override public void onFailure(Call<LoginResponse> call, Throwable t){ Toast.makeText(LoginActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show(); }
            });
        });
        btnRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
    }
}
