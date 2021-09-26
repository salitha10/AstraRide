package com.example.astraride.ui.home;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.astraride.R;
import com.example.astraride.models.Item;
import com.example.astraride.ui.products.ViewItem;
import com.example.astraride.ui.reviews.AddReview;
import com.example.astraride.ui.reviews.AllReviews;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeViewAdapter extends RecyclerView.Adapter<HomeViewAdapter.ViewHolder> {

    ArrayList<Item> itemList = new ArrayList<>();
    Context context;


    public HomeViewAdapter(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public HomeViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.home_item_card, parent, false);
        HomeViewAdapter.ViewHolder viewHolder = new HomeViewAdapter.ViewHolder(listItem);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewAdapter.ViewHolder holder, int position) {

        //Display values
        holder.itemName.setText(itemList.get(position).getTitle());
        holder.itemLocation.setText(itemList.get(position).getLocation());
        holder.itemPrice.setText("Rs."+itemList.get(position).getRentalFee());
        Glide.with(holder.itemImage.getContext()).load(itemList.get(position).getItemImage()).error(R.drawable.ic_launcher_foreground).into(holder.itemImage);


        //Handle clicks
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewItem.class);
                intent.putExtra("itemID", itemList.get(position).getItemID());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;
        public CardView cardView;
        public TextView itemLocation, itemPrice, itemName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.image);
            itemLocation = itemView.findViewById(R.id.location);
            itemPrice = itemView.findViewById(R.id.price);
            itemName = itemView.findViewById(R.id.title);
            cardView = itemView.findViewById(R.id.itemCard);
        }
    }

}

