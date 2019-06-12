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
import android.widget.Toast;

import com.example.lestudis.R;
import com.example.lestudis.models.DiscountModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AvailableDiscountFragment extends Fragment {
    private RecyclerView discountRv1;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager1;
    private List<DiscountModel> discountModelList1 = new ArrayList<>();

    public AvailableDiscountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_available_discount, container, false);
        discountRv1 = viewRoot.findViewById(R.id.disccount_rv1);
        discountRv1.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), LinearLayoutManager.VERTICAL));
        //discountRv1.addOnItemTouchListener(new DiscountTouchListener(getContext(), discountRv1, new DiscountTouchListener.ClickListener() {

//            @Override
//            public void onClick(View view, int position) {
//
//                Intent intent = new Intent(getContext(), Discountactivity.class);
//                intent.putExtra("EXP_DATE_ID", discountModelList1.get(position).getExpdate());
//                intent.putExtra("DETAIL_ID",discountModelList1.get(position).getDescription());
//                intent.putExtra("ID_TEXT_ID",discountModelList1.get(position).getId());
//
//                startActivity(intent);
//
//            }
//        }));
//        mAdapter = new DiscountAdapter(discountModelList1);
//        layoutManager1 = new LinearLayoutManager(getContext());
//        discountRv1.setLayoutManager(layoutManager1);
//        discountRv1.setItemAnimator(new DefaultItemAnimator());
//        discountRv1.setAdapter(mAdapter);
//

        // Inflate the layout for this fragment
     //   prepareDiscountData();
        return viewRoot;
    }

//    private void prepareDiscountData() {
//        DiscountModel discount = new DiscountModel("img1","expdate1","id1","description1");
//        discountModelList1.add(discount);
//
//        mAdapter.notifyDataSetChanged();
//    }


}
