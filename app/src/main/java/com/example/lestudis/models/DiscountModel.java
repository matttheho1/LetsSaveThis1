package com.example.lestudis.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class DiscountModel {
    private String img, expdate, id, description, scheme, title, offer;

    public DiscountModel() {
    }
        public DiscountModel(String img, String expdate, String id, String description, String scheme, String title, String offer) {
            this.img = img;
            this.expdate = expdate;
            this.id = id;
            this.description= description;
            this.scheme = scheme;
            this.title = title;
            this.offer = offer;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getExpdate() {
            return expdate;
        }

        public void setExpdate(String expdate) {
            this.expdate = expdate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;

        }
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getScheme(){ return scheme;}

        public void setScheme(String scheme){this.scheme = scheme;}

        public String getTitle(){return title;}

        public void setTitle(String title){this.title = title;}

        public String getOffer(){return this.offer;}

        public void setOffer(String offer){this.offer = offer;}

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("img", img);
        result.put("expdate", expdate);
        result.put("id", id);
        result.put("description", description);
        result.put("scheme",scheme);
        result.put("title",title);
        result.put("offer",offer);

        return result;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o==null || getClass() != o.getClass()){
            return false;
        }

        DiscountModel discount = (DiscountModel)o;
        return discount.getImg().equals(img) &&
                discount.getExpdate().equals(expdate) &&
                discount.getDescription().equals(description) &&
                discount.getScheme().equals(scheme) &&
                discount.getTitle().equals(title) &&
                discount.getOffer().equals(offer);
    }
}



