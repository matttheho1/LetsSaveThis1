package com.example.lestudis.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class CardModel {
    private String img, expdate, description, id, scheme;

    public CardModel(String img, String expdate, String id, String description, String scheme) {
        this.img = img;
        this.expdate = expdate;
        this.id = id;
        this.description= description;
        this.scheme = scheme;
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

    public String getScheme() {return scheme;}

    public void setScheme(String scheme){this.scheme = scheme;}

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("img", img);
        result.put("expdate", expdate);
        result.put("id", id);
        result.put("description", description);
        result.put("scheme",scheme);

        return result;
    }
}