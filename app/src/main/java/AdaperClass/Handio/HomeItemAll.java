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
import com.example.handioin.ModelClass.HomeAll_itemRequest;
import com.example.handioin.ModelClass.HomeAll_itemResponse;
import com.example.handioin.R;

import java.util.List;

public class HomeItemAll extends RecyclerView.Adapter<HomeItemAll.myviewholder> {
    private List<HomeAll_itemResponse> arrayList;
    Context context;

    public HomeItemAll(Context context, List<HomeAll_itemResponse> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public HomeItemAll.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_all, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemAll.myviewholder holder, int position) {
        final HomeAll_itemResponse h = arrayList.get(position);
        Glide.with(holder.ivFoodImage.getContext()).load(h.getProductImage()).placeholder(R.drawable.egg).into(holder.ivFoodImage);
        holder.tvFoodName.setText(h.getProductName());
        holder.tvFoodDescription.setText(h.getpDescription());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        private LinearLayout llContainer;
        private TextView tvFoodName;
        private ImageView ivFoodImage;
        private TextView tvFoodDescription;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
            tvFoodName = (TextView) itemView.findViewById(R.id.tvFoodName);
            ivFoodImage = (ImageView)itemView. findViewById(R.id.ivFoodImage);
            tvFoodDescription = (TextView) itemView.findViewById(R.id.tvFoodDescription);

        }
    }
}
