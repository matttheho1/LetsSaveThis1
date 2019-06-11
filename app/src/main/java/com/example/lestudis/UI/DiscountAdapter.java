package com.example.lestudis.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lestudis.R;
import com.example.lestudis.models.DiscountModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.MyViewHolder>
implements EventListener<QuerySnapshot> {
    private List<DiscountModel>discountModelListList;

    private ArrayList<DocumentSnapshot> mSnapshots = new ArrayList<>();

    @Override
    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
        // Handle errors
        if (e != null) {
            return;
        }
        // Dispatch the event
        assert queryDocumentSnapshots != null;
        for (DocumentChange change : queryDocumentSnapshots.getDocumentChanges()) {
            // Snapshot of the changed document
            DocumentSnapshot snapshot = change.getDocument();

            switch (change.getType()) {
                case ADDED:
                    // TODO: handle document added
                    break;
                case MODIFIED:
                    // TODO: handle document modified
                    break;
                case REMOVED:
                    // TODO: handle document removed
                    break;
            }
        }

       // onDataChanged();
    }

    protected void onDocumentAdded(DocumentChange change) {
        mSnapshots.add(change.getNewIndex(), change.getDocument());
        notifyItemInserted(change.getNewIndex());
    }

    protected void onDocumentModified(DocumentChange change) {
        if (change.getOldIndex() == change.getNewIndex()) {
            // Item changed but remained in same position
            mSnapshots.set(change.getOldIndex(), change.getDocument());
            notifyItemChanged(change.getOldIndex());
        } else {
            // Item changed and changed position
            mSnapshots.remove(change.getOldIndex());
            mSnapshots.add(change.getNewIndex(), change.getDocument());
            notifyItemMoved(change.getOldIndex(), change.getNewIndex());
        }
    }

    protected void onDocumentRemoved(DocumentChange change) {
        mSnapshots.remove(change.getOldIndex());
        notifyItemRemoved(change.getOldIndex());
    }


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
