package AdaperClass.Handio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.handioin.ModelClass.BlogRequest;
import com.example.handioin.ModelClass.HomeResponseModal;
import com.example.handioin.ModelClass.HomeproductModal;
import com.example.handioin.R;

import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.myviewholder> {

    private List<HomeResponseModal> arrayList;
    Context context;

    public HomeAdapter(Context context, List<HomeResponseModal> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_single_home, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        final HomeResponseModal h = arrayList.get(position);
        Glide.with(holder.cvImg.getContext()).load(h.getProductImage()).placeholder(R.drawable.image1).into(holder.cvImg);
        holder.tvHandi.setText(h.getProductName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class myviewholder extends RecyclerView.ViewHolder {


        private LinearLayout llContainer;
        private ImageView cvImg;
        private TextView tvHandi;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            llContainer = itemView.findViewById(R.id.llContainer);
            cvImg = itemView.findViewById(R.id.cv_img);
            tvHandi = itemView.findViewById(R.id.handi);


        }
    }
}
