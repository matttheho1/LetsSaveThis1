package com.example.lestudis.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lestudis.R;


public class Discountactivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discountactivity);
        TextView expDateText  = findViewById(R.id.expdate_text);
        TextView detailText = findViewById(R.id.details_text);
        TextView idText = findViewById(R.id.id_text);
        expDateText.setText(getIntent().getStringExtra("EXP_DATE_ID"));
        detailText.setText(getIntent().getStringExtra("DETAIL_ID"));
        idText.setText(getIntent().getStringExtra("ID_TEXT_ID"));

        Button saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
