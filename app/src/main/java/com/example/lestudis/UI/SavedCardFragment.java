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
import android.widget.Button;

import com.example.lestudis.R;
import com.example.lestudis.models.CardModel;
import com.example.lestudis.viewholder.CardViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedCardFragment extends Fragment {
    private RecyclerView cardRv;
    private LinearLayoutManager mManager;
    //    private DiscountAdapter mAdapter;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<CardModel, CardViewHolder> mAdapter;



    public SavedCardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_saved_card, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        cardRv = viewRoot.findViewById(R.id.card_rv);
        cardRv.setHasFixedSize(true);
        cardRv.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), LinearLayoutManager.VERTICAL));

        Button addCardBtn = viewRoot.findViewById(R.id.add_card_btn);

        addCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NewCardActivity.class));
            }
        });

        return viewRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        cardRv.setLayoutManager(mManager);



        //Set up firebase recycler adapter with the query
        Query cardQuery = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<CardModel>()
                .setQuery(cardQuery, CardModel.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<CardModel, CardViewHolder>(options) {

            @Override
            public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new CardViewHolder(inflater.inflate(R.layout.card_row, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(CardViewHolder viewHolder, int position, @NonNull CardModel model) {
                viewHolder.bindToPost(model);

            }
        };
        cardRv.setAdapter(mAdapter);
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

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public Query getQuery(DatabaseReference databaseReference){
        return databaseReference.child("user-cards").child(getUid());
    }
}
