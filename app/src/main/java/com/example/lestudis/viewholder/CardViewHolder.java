package com.example.lestudis.viewholder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lestudis.R;
import com.example.lestudis.models.CardModel;

import java.io.InputStream;

public class CardViewHolder extends RecyclerView.ViewHolder {


    private TextView img;
    private TextView expdate;
    private TextView id;
    private TextView description;

    public CardViewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
        expdate = itemView.findViewById(R.id.expdate);
        id = itemView.findViewById(R.id.id);
        description = itemView.findViewById(R.id.description);
    }

    public void bindToPost(CardModel card) {
        expdate.setText(card.getExpdate());
        id.setText(card.getId());
        description.setText(card.getDescription());
        img.setText(card.getImg());

//        new DiscountViewHolder.DownloadImage(imgView)
//                .execute(card.getImg());
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
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
