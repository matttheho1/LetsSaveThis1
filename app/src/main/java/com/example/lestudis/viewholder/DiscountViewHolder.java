package com.example.lestudis.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lestudis.R;
import com.example.lestudis.models.DiscountModel;

public class DiscountViewHolder extends RecyclerView.ViewHolder {

    public TextView imgView;
    public TextView expdateView;
    public TextView idView;
    public TextView descriptionView;

    public DiscountViewHolder(View itemView) {
        super(itemView);

        imgView = itemView.findViewById(R.id.img);
        expdateView = itemView.findViewById(R.id.expdate);
        idView = itemView.findViewById(R.id.id);
        descriptionView = itemView.findViewById(R.id.description);
    }

    public void bindToPost(DiscountModel discount) {
        imgView.setText(discount.getImg());
        expdateView.setText(discount.getExpdate());
        idView.setText(discount.getId());
        descriptionView.setText(discount.getDescription());
    }
}
