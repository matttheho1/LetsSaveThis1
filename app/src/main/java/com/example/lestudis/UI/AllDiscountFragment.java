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
import android.widget.TextView;
import android.widget.Toast;

import com.example.lestudis.R;
import com.example.lestudis.models.DiscountModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllDiscountFragment extends Fragment {
    private RecyclerView discountRv;
    private DiscountAdapter mAdapter;
    private List<DiscountModel> discountModelList = new ArrayList<>();

    public AllDiscountFragment() {
        // Required empty public constructor
    }


    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_all_discount, container, false);
        discountRv = viewRoot.findViewById(R.id.disccount_rv);
        discountRv.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), LinearLayoutManager.VERTICAL));
        discountRv.addOnItemTouchListener(new DiscountTouchListener(getContext(), discountRv, new DiscountTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getContext(), Discountactivity.class);
                intent.putExtra("EXP_DATE_ID", discountModelList.get(position).getExpdate());
                intent.putExtra("DETAIL_ID",discountModelList.get(position).getDescription());
                intent.putExtra("ID_TEXT_ID",discountModelList.get(position).getId());

                startActivity(intent);

            }

        }));

        mAdapter = new DiscountAdapter(discountModelList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        discountRv.setLayoutManager(layoutManager);
        discountRv.setItemAnimator(new DefaultItemAnimator());
        discountRv.setAdapter(mAdapter);


        // Inflate the layout for this fragment
        prepareDiscountData();
        return viewRoot;
    }

    private void prepareDiscountData() {
        DiscountModel discount = new DiscountModel("img","expdate","id","description");
        discountModelList.add(discount);

        discount = new DiscountModel("test","test","test","test");
        discountModelList.add(discount);

        mAdapter.notifyDataSetChanged();
    }
}
