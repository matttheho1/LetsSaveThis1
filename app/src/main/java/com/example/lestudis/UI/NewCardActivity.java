package com.example.lestudis.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lestudis.R;
import com.example.lestudis.models.CardModel;
import com.example.lestudis.models.DiscountModel;
import com.example.lestudis.models.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewCardActivity extends AppCompatActivity {

    private static final String TAG = "NewCardActivity";
    private static final String REQUIRED = "Required";
    private Spinner dropdown;

    private DatabaseReference mDatabase;
    private EditText mExpDate;
    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);
        dropdown = findViewById(R.id.spinner1);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mExpDate = findViewById(R.id.expTxt);
        mSubmitButton = findViewById(R.id.complete_btn);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitCard();
            }
        });

    }

    private void submitCard(){
        final String expDate = mExpDate.getText().toString();
        final String scheme = dropdown.getSelectedItem().toString();


        // Body is required
        if (TextUtils.isEmpty(expDate)) {
            mExpDate.setError(REQUIRED);
            return;
        }
        setEditingEnabled(false);
        Toast.makeText(this, "Saving...", Toast.LENGTH_SHORT).show();

        final String userId = getUid();
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        UserModel user = dataSnapshot.getValue(UserModel.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Log.e(TAG, "User " + userId + " is unexpectedly null");
                            Toast.makeText(getApplicationContext(),
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            writeNewCard(userId, scheme, expDate);
                        }

                        // Finish this Activity, back to the stream
                        setEditingEnabled(true);
                        finish();
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                }
        );
    }

    private void getDiscountsToUser(String userId, final CardModel newCard){
        mDatabase.child("discounts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DiscountModel> discountList = new ArrayList<>();
                for(DataSnapshot discountSnapshot: dataSnapshot.getChildren()){
                    DiscountModel discount = discountSnapshot.getValue(DiscountModel.class);
                    if(discount.getScheme().equals(newCard.getScheme())){
                        discountList.add(discount);
                    }
                }

                for(DiscountModel discount : discountList){
                    setDiscountsToUser(discount);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setDiscountsToUser(DiscountModel discount){
        String key = mDatabase.child("avail-discounts").push().getKey();
        Map<String , Object> discountValues = discount.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/avail-discounts/" + getUid() + "/" + key, discountValues);
        mDatabase.updateChildren(childUpdates);
    }


    private void writeNewCard(String userId, String scheme, String expDate){
        String key = mDatabase.child("cards").push().getKey();
        CardModel card = new CardModel(userId, scheme, expDate);
        Map<String, Object> cardValues = card.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/cards/" + key, cardValues);
        childUpdates.put("/user-cards/" + userId + "/" + key, cardValues);

        mDatabase.updateChildren(childUpdates);

        getDiscountsToUser(userId, card);
    }

    private void setEditingEnabled(boolean enabled) {
        mExpDate.setEnabled(enabled);
        if (enabled){
            mSubmitButton.setVisibility(View.INVISIBLE);
        } else {
            mSubmitButton.setVisibility(View.VISIBLE);
        }
    }
    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

}

