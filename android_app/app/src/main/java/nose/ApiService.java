package nose;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface ApiService {
    @POST("api/auth/login") Call<LoginResponse> login(@Body LoginRequest body);
    @POST("api/auth/register") Call<User> register(@Body RegisterRequest body);
    @GET("api/posts") Call<List<Post>> getPosts();
}
