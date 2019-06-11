package com.example.lestudis.UI;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class AllDiscountFragment extends DiscountListFragment{

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child("discounts");
    }
}
