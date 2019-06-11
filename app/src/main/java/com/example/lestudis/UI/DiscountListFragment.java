package com.example.lestudis.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lestudis.R;
import com.example.lestudis.models.DiscountModel;
import com.example.lestudis.viewholder.DiscountViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

public abstract class DiscountListFragment extends Fragment {
    private RecyclerView discountRv;
    private LinearLayoutManager mManager;
    //    private DiscountAdapter mAdapter;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<DiscountModel, DiscountViewHolder> mAdapter;
//    private FirebaseFirestore mFirestore;
//    private Query mQuery;
//    private List<DiscountModel> discountModelList = new ArrayList<>();

    public DiscountListFragment() {
    }

//    private void initFirestore() {
//        mFirestore = FirebaseFirestore.getInstance();
//        mQuery = mFirestore.collection("Discount");
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        initFirestore();
        View viewRoot = inflater.inflate(R.layout.fragment_all_discount, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        discountRv = viewRoot.findViewById(R.id.disccount_rv);
        discountRv.setHasFixedSize(true);
        discountRv.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), LinearLayoutManager.VERTICAL));
//        discountRv.addOnItemTouchListener(new DiscountTouchListener(getContext(), discountRv, new DiscountTouchListener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Intent intent = new Intent(getContext(), Discountactivity.class);
//                intent.putExtra("EXP_DATE_ID", discountModelList.get(position).getExpdate());
//                intent.putExtra("DETAIL_ID",discountModelList.get(position).getDescription());
//                intent.putExtra("ID_TEXT_ID",discountModelList.get(position).getId());
//
//                startActivity(intent);
//
//            }
//
//        }));

//        mAdapter = new DiscountAdapter(discountModelList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        discountRv.setLayoutManager(layoutManager);
//        discountRv.setItemAnimator(new DefaultItemAnimator());
//        discountRv.setAdapter(mAdapter);
//
//
//        // Inflate the layout for this fragment
//        prepareDiscountData();
        return viewRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        discountRv.setLayoutManager(mManager);

        //Set up firebase recycler adapter with the query
        Query discountQuery = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<DiscountModel>()
                .setQuery(discountQuery, DiscountModel.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<DiscountModel, DiscountViewHolder>(options) {

            @Override
            public DiscountViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new DiscountViewHolder(inflater.inflate(R.layout.discount_row, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(DiscountViewHolder viewHolder, int position, @NonNull DiscountModel model) {

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), Discountactivity.class);
//                        intent.putExtra("EXP_DATE_ID", discountModelList.get(position).getExpdate());
//                        intent.putExtra("DETAIL_ID",discountModelList.get(position).getDescription());
//                        intent.putExtra("ID_TEXT_ID",discountModelList.get(position).getId());

                        startActivity(intent);
                    }
                });

                viewHolder.bindToPost(model);

            }
        };
        discountRv.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }
    //
//    private void prepareDiscountData() {
//        DiscountModel discount = new DiscountModel("img","expdate","id","description");
//        discountModelList.add(discount);
//
//        discount = new DiscountModel("test","test","test","test");
//        discountModelList.add(discount);
//
//        mAdapter.notifyDataSetChanged();
//    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);
}
