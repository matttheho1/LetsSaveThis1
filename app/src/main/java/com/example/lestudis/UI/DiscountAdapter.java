package com.example.lestudis.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lestudis.R;
import com.example.lestudis.models.DiscountModel;
import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.MyViewHolder> {
    private List<DiscountModel>discountModelListList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView img, expdate, id, description;

        public MyViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.img);
            expdate = view.findViewById(R.id.expdate);
            id = view.findViewById(R.id.id);
            description = view.findViewById(R.id.description);
        }
    }

    public DiscountAdapter(List<DiscountModel> discountModelList) {
        this.discountModelListList=discountModelList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.discount_row, parent,false);
        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DiscountModel discountmodel = discountModelListList.get(position);
        holder.img.setText(discountmodel.getImg());
        holder.expdate.setText(discountmodel.getExpdate());
        holder.id.setText(discountmodel.getId());
        holder.description.setText(discountmodel.getDescription());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return discountModelListList.size();
    }
}
