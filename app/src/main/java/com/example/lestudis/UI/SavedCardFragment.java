package com.example.lestudis.UI;


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
import com.example.lestudis.models.CardModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedCardFragment extends Fragment {
    private RecyclerView cardRv;
    private CardAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager3;
    private List<CardModel> cardModelList = new ArrayList<>();


    public SavedCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_saved_card, container, false);
        cardRv = viewRoot.findViewById(R.id.card_rv);
        cardRv.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), LinearLayoutManager.VERTICAL));


        mAdapter = new CardAdapter(cardModelList);
        layoutManager3 = new LinearLayoutManager(getContext());
        cardRv.setLayoutManager(layoutManager3);
        cardRv.setItemAnimator(new DefaultItemAnimator());
        cardRv.setAdapter(mAdapter);

        prepareCardData();
        return viewRoot;
    }

    private void prepareCardData() {
        cardModelList.add(new CardModel("cardimg", "cardexpdate", "cardid", "carddescription"));

        cardModelList.add(new CardModel("test","test","test","test"));
        cardModelList.add(new CardModel("test","test","test","test"));
        cardModelList.add(new CardModel("test","test","test","test"));
        cardModelList.add(new CardModel("test","test","test","test"));
        cardModelList.add(new CardModel("test","test","test","test"));
        cardModelList.add(new CardModel("test","test","test","test"));
        cardModelList.add(new CardModel("test","test","test","test"));
        cardModelList.add(new CardModel("test","test","test","test"));
        cardModelList.add(new CardModel("test","test","test","test"));
        cardModelList.add(new CardModel("test","test","test","test"));

        mAdapter.notifyDataSetChanged();
    }

}
