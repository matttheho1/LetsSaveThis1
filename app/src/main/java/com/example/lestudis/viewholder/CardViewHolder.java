package com.example.lestudis.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lestudis.R;
import com.example.lestudis.models.CardModel;

public class CardViewHolder extends RecyclerView.ViewHolder {

    private TextView expdate;
    private TextView scheme;

    public CardViewHolder(@NonNull View itemView) {
        super(itemView);
        expdate = itemView.findViewById(R.id.expdate);
        scheme = itemView.findViewById(R.id.scheme);
    }

    public void bindToPost(CardModel card) {
        expdate.setText(card.getExpdate());
        scheme.setText(card.getScheme());
    }
}
