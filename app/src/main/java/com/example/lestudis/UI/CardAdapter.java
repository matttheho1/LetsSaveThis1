package com.example.lestudis.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lestudis.R;
import com.example.lestudis.models.CardModel;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder>{
    private List<CardModel>cardModelList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView img, expdate, description, id;

        public MyViewHolder(View view) {
            super(view);
            img = (TextView) view.findViewById(R.id.img);
            expdate = (TextView) view.findViewById(R.id.expdate);
            id = (TextView) view.findViewById(R.id.id);
            description = (TextView) view.findViewById(R.id.description);
        }
    }
    public CardAdapter(List<CardModel>cardModelList){
        this.cardModelList=cardModelList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_row, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CardModel cardmodel= cardModelList.get(position);
        holder.img.setText(cardmodel.getImg());
        holder.expdate.setText(cardmodel.getExpdate());
        holder.id.setText(cardmodel.getId());
        holder.description.setText(cardmodel.getDescription());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cardModelList.size();
    }
}
