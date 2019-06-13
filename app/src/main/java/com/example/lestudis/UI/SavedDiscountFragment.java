package com.example.lestudis.UI;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lestudis.R;
import com.example.lestudis.models.DiscountModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedDiscountFragment extends AllDiscountFragment {
//    private RecyclerView discountRv2;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager layoutManager2;
//    private List<DiscountModel> discountModelList2 = new ArrayList<>();
//

    @Override
    public Query getQuery(DatabaseReference databaseReference){
        return databaseReference.child("user-discounts")
                .child(getUid());
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View viewRoot = inflater.inflate(R.layout.fragment_saved_discount, container, false);
//        discountRv2 = viewRoot.findViewById(R.id.disccount_rv2);
//        discountRv2.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), LinearLayoutManager.VERTICAL));
//        discountRv2.addOnItemTouchListener(new DiscountTouchListener(getContext(), discountRv2, new DiscountTouchListener.ClickListener() {
//
//            @Override
//            public void onClick(View view, int position) {
//                Intent intent = new Intent(getContext(), Discountactivity.class);
//                intent.putExtra("EXP_DATE_ID", discountModelList2.get(position).getExpdate());
//                intent.putExtra("DETAIL_ID",discountModelList2.get(position).getDescription());
//                intent.putExtra("ID_TEXT_ID",discountModelList2.get(position).getId());
//
//                startActivity(intent);
//            }
//        }));
//
//        mAdapter = new DiscountAdapter(discountModelList2);
//        layoutManager2 = new LinearLayoutManager(getContext());
//        discountRv2.setLayoutManager(layoutManager2);
//        discountRv2.setItemAnimator(new DefaultItemAnimator());
//        discountRv2.setAdapter(mAdapter);
//
//
//        // Inflate the layout for this fragment
//        prepareDiscountData();
//        return viewRoot;
//    }
//
//    private void prepareDiscountData() {
//        DiscountModel discount = new DiscountModel("img2","expdate2","id2","description2");
//        discountModelList2.add(discount);
//
//        mAdapter.notifyDataSetChanged();
//    }
}
