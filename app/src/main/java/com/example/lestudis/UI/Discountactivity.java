package com.example.lestudis.UI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lestudis.R;

import java.io.InputStream;


public class Discountactivity extends AppCompatActivity {

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


        new DownloadImage(discountImage)
                .execute(getIntent().getStringExtra("IMG_ID"));

        expDateText.setText(getIntent().getStringExtra("EXP_DATE_ID"));
        detailText.setText(getIntent().getStringExtra("DETAIL_ID"));
        idText.setText(getIntent().getStringExtra("ID_TEXT_ID"));
        titleText.setText(getIntent().getStringExtra("TITLE_ID"));
        offerText.setText(getIntent().getStringExtra("OFFER_ID"));
        schemeText.setText(getIntent().getStringExtra("SCHEME_ID"));

        Button saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
}
