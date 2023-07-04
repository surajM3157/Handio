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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.handioin.MenuActivity;
import com.example.handioin.ModelClass.ProductResponseModal;
import com.example.handioin.ModelClass.productRequestModal;
import com.example.handioin.R;

import java.util.List;

public class AdaterClass extends RecyclerView.Adapter<AdaterClass.myviewholder> {

    private List<ProductResponseModal> arrayList;
    public ShopClickListeners shopClickListeners;
    Context context;

    int count_no = 0;
    int count = 0;
    int totalprice = 0;

    public AdaterClass(Context context, List<ProductResponseModal> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    public void shopItemAdapter(ShopClickListeners shopClickListeners) {
        this.shopClickListeners = shopClickListeners;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_single_menu_single, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        ProductResponseModal hondios = arrayList.get(position);
        holder.tvBestseller.setText(hondios.getCategory());
        holder.tvHandiMushuroom.setText(hondios.getProductName());
        holder.tvPrize.setText(hondios.getPrice());
        holder.tvDiscription.setText(hondios.getpDescription());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopClickListeners.onAddToCartBtnClicked(hondios);
            }
        });

        holder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_no = count_no + 1;
                count++;
                holder.etNumber.setText("" + count_no);
                MenuActivity.tvItem.setText(count + "");
                int price = Integer.parseInt(hondios.getProductPrice().toString()) + count_no;
                totalprice = totalprice + price;
                MenuActivity.tvTotalPrize.setText(totalprice + "");
            }
        });

        holder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count_no > 0) {
                    count_no = count_no - 1;
                    count--;
                    holder.etNumber.setText(String.valueOf(count_no));
                    MenuActivity.tvItem.setText(count + "");
                    int price = Integer.parseInt(hondios.getProductPrice().toString()) - count_no;
                    totalprice = totalprice - price;
                    MenuActivity.tvTotalPrize.setText(totalprice + "");
                    if (count_no <= 0) {
                        holder.button.setVisibility(View.VISIBLE);
                    }
                }
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

    public void setData(List<ProductResponseModal> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }


    public static class myviewholder extends RecyclerView.ViewHolder {

        private LinearLayout llContainer, llBTN;
        private TextView tvBestseller, tvHandiMushuroom, tvPrize, tvDiscription, etNumber;
        private ImageView ivImg;
        public static AppCompatButton increment, decrement, button;
        private CardView CardContainer;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            llContainer = itemView.findViewById(R.id.llContainer);
            tvBestseller = itemView.findViewById(R.id.tv_Bestseller);
            tvHandiMushuroom = itemView.findViewById(R.id.tvHandiMushuroom);
            tvPrize = itemView.findViewById(R.id.tvPrize);
            tvDiscription = itemView.findViewById(R.id.tvDiscription);
            button = itemView.findViewById(R.id.button1);
            ivImg = itemView.findViewById(R.id.iv_img);
            increment = itemView.findViewById(R.id.increment);
            etNumber = itemView.findViewById(R.id.et_Number);
            decrement = itemView.findViewById(R.id.decrement);
            llBTN = itemView.findViewById(R.id.llBTN);
            CardContainer = itemView.findViewById(R.id.CardContainer);
        }


    }

    public interface ShopClickListeners {
        void onAddToCartBtnClicked(ProductResponseModal requestModal);

    }
}
