package com.example.lestudis.viewholder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lestudis.R;
import com.example.lestudis.models.DiscountModel;

import java.io.InputStream;

public class DiscountViewHolder extends RecyclerView.ViewHolder {

    private ImageView imgView;
    private TextView expdateView;
    private TextView title;
    private TextView offer;


    public DiscountViewHolder(View itemView) {
        super(itemView);

        imgView = itemView.findViewById(R.id.img);
        title = itemView.findViewById(R.id.title);
        offer = itemView.findViewById(R.id.offer);
        expdateView = itemView.findViewById(R.id.expdate);
    }

    public void bindToPost(DiscountModel discount) {
        expdateView.setText(discount.getExpdate());
        offer.setText(discount.getOffer());
        title.setText(discount.getTitle());

        new DownloadImage(imgView)
            .execute(discount.getImg());
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
