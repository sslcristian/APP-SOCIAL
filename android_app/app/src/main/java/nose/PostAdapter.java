package nose;
import android.view.LayoutInflater; import android.view.View; import android.view.ViewGroup; import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList; import java.util.List;
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.VH> {
    List<Post> data = new ArrayList<>();
    public void setData(List<Post> d){ data = d; notifyDataSetChanged(); }
    @Override public VH onCreateViewHolder(ViewGroup p,int vt){ View v = LayoutInflater.from(p.getContext()).inflate(R.layout.item_post,p,false); return new VH(v); }
    @Override public void onBindViewHolder(VH h,int pos){ Post p = data.get(pos); h.txt.setText(p.usuario_nombre+": "+p.texto); }
    @Override public int getItemCount(){ return data.size(); }
    static class VH extends RecyclerView.ViewHolder{ TextView txt; VH(View v){ super(v); txt=v.findViewById(R.id.txtPost); } }
}
