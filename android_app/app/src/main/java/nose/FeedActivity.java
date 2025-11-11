package nose;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class FeedActivity extends AppCompatActivity {
    RecyclerView recycler; PostAdapter adapter; ApiService api;
    @Override protected void onCreate(Bundle s){ super.onCreate(s); setContentView(R.layout.feed);
        recycler=findViewById(R.id.recyclerFeed); recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter=new PostAdapter(); recycler.setAdapter(adapter);
        api = ApiClient.getClient().create(ApiService.class);
        api.getPosts().enqueue(new Callback<List<Post>>() {
            @Override public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()) adapter.setData(response.body());
            }
            @Override public void onFailure(Call<List<Post>> call, Throwable t) { }
        });
    }
}
