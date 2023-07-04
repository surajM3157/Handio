package AdaperClass.Handio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.handioin.ModelClass.ProductResponseModal;
import com.example.handioin.R;

import java.util.List;

public class OrderProductAdapter extends RecyclerView.Adapter<OrderProductAdapter.myviewholder> {
    private CardClickListener cardClickListener;
    private List<ProductResponseModal> arrayList;
    Context context;

    public OrderProductAdapter(Context context, List<ProductResponseModal> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    public void OrderAdapter(List<ProductResponseModal> arrayList) {
        this.arrayList = arrayList;
    //    Toast.makeText(context, "FindAs1"+arrayList.size(), Toast.LENGTH_SHORT).show();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderProductAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card_store, parent, false);
        return new OrderProductAdapter.myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProductAdapter.myviewholder holder, int position) {

        ProductResponseModal productResponseModal = arrayList.get(position);
         Toast.makeText(context, "ArraySize"+arrayList.size(), Toast.LENGTH_SHORT).show();

        Glide.with(holder.ivImg.getContext()).load(productResponseModal.getProductImage()).placeholder(R.drawable.image1).into(holder.ivImg);
        holder.tvHandiMushuroom.setText(productResponseModal.getProductName());
        holder.tvQuantity.setText(productResponseModal.getQuantity());
        holder.tvPrice.setText(productResponseModal.getTotalItemPrice());
        holder.ivPlusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //         cardClickListener.onPlus_BTN(productResponseModal);
                //       Toast.makeText(v.getContext(), "on click ready"+productResponseModal.getProductPrice(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.ivMinusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "it's Run"+productResponseModal.getProductName(), Toast.LENGTH_SHORT).show();
               cardClickListener.onMine_BTN(productResponseModal);
            }
        });

        holder.ivDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(context, "suraj"+productResponseModal.getProductName(), Toast.LENGTH_SHORT).show();
//                    cardClickListener.noDeleteClick(productResponseModal);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrayList == null) {
            return 0;
        } else {
            return arrayList.size();
        }
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        private LinearLayout llContainer;
        private TextView tvBestseller;
        private TextView tvHandiMushuroom;
        private TextView tvPrice;
        private TextView tvDiscription;
        private ImageView ivDeleteBtn;
        private ImageView ivImg;
        private LinearLayout llBTN;
        private AppCompatButton ivPlusBTN;
        private TextView tvQuantity;
        private AppCompatButton ivMinusBTN;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
            tvBestseller = (TextView) itemView.findViewById(R.id.tv_Bestseller);
            tvHandiMushuroom = (TextView) itemView.findViewById(R.id.tvHandiMushuroom);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvDiscription = (TextView) itemView.findViewById(R.id.tvDiscription);
            ivDeleteBtn = (ImageView) itemView.findViewById(R.id.iv_DeleteBtn);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            llBTN = (LinearLayout) itemView.findViewById(R.id.llBTN);
            ivPlusBTN = (AppCompatButton) itemView.findViewById(R.id.ivPlus_BTN);
            tvQuantity = (TextView) itemView.findViewById(R.id.tvQuantity);
            ivMinusBTN = (AppCompatButton) itemView.findViewById(R.id.ivMines_BTN);
        }
    }

    public interface CardClickListener {
        void noDeleteClick(ProductResponseModal productResponseModal);

        void onPlus_BTN(ProductResponseModal productResponseModal);

        void onMine_BTN(ProductResponseModal productResponseModal);
    }
}
