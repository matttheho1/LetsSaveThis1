package com.example.lestudis.UI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lestudis.R;
import com.example.lestudis.models.CardModel;
import com.example.lestudis.models.DiscountModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class Discountactivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button saveBtn;
    private DiscountModel discount;
    private boolean saveMode = true;
    private DatabaseReference discountRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discountactivity);
        TextView expDateText  = findViewById(R.id.expdate_text);
        TextView detailText = findViewById(R.id.details_text);
        TextView idText = findViewById(R.id.id_text);
        TextView titleText = findViewById(R.id.txtTitle);
        TextView offerText = findViewById(R.id.txtOffer);
        TextView schemeText = findViewById(R.id.txtScheme);
        ImageView discountImage = findViewById(R.id.imageView);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        discount = new DiscountModel(
                getIntent().getStringExtra("IMG_ID"),
                getIntent().getStringExtra("EXP_DATE_ID"),
                getIntent().getStringExtra("ID_TEXT_ID"),
                getIntent().getStringExtra("DETAIL_ID"),
                getIntent().getStringExtra("SCHEME_ID"),
                getIntent().getStringExtra("TITLE_ID"),
                getIntent().getStringExtra("OFFER_ID")
        );

        new DownloadImage(discountImage)
                .execute(discount.getImg());

        expDateText.setText(discount.getExpdate());
        detailText.setText(discount.getDescription());
        idText.setText(discount.getId());
        titleText.setText(discount.getTitle());
        offerText.setText(discount.getOffer());
        schemeText.setText(discount.getScheme());

        saveBtn = findViewById(R.id.save_btn);

        checkDiscount();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveMode) {
                    saveDiscount();
                }
                else{
                    deleteDiscount();
                }

            }
        });
    }

    private void checkDiscount(){
        mDatabase.child("user-cards").child(getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot cardSnapshot: dataSnapshot.getChildren()) {
                    CardModel card = cardSnapshot.getValue(CardModel.class);
                    if(card.getScheme().equals(discount.getScheme())){
                        saveBtn.setEnabled(true);
                        saveBtn.setVisibility(View.VISIBLE);

                        mDatabase.child("user-discounts").child(getUid()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot discountSnapshot: dataSnapshot.getChildren()){
                                    DiscountModel discountSnap = discountSnapshot.getValue(DiscountModel.class);
                                    if(discountSnap.equals(discount)){
                                        saveMode = false;
                                        saveBtn.setText(getResources().getString(R.string.delete_txt));
                                        discountRef = discountSnapshot.getRef();
                                        return;
                                    }
                                }
                                saveMode = true;
                                saveBtn.setText(getResources().getString(R.string.save_txt));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        return;
                    }
                }
                saveBtn.setEnabled(false);
                saveBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void saveDiscount(){
        String key = mDatabase.child("user-discounts").push().getKey();
        Map<String, Object> discountValues = discount.toMap();

        Map<String,Object> childUpdates = new HashMap<>();
        childUpdates.put("/user-discounts/" + getUid() + "/" + key, discountValues);
        mDatabase.updateChildren(childUpdates);
        saveBtn.setText(getResources().getString(R.string.delete_txt));
        saveMode = false;
    }

    private void deleteDiscount(){
        if(discountRef!=null){
            discountRef.removeValue();
        }
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap>{
        ImageView imageView;

        public DownloadImage(ImageView imageView){
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try{
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);
            } catch(Exception e){
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
