package com.example.lestudis.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class CardModel {
    private String expdate, id, scheme;

    public CardModel(){
        
    }

    public CardModel(String id, String scheme, String expdate) {
        this.id = id;
        this.scheme = scheme;
        this.expdate = expdate;
    }


    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getId(){ return id;}

    public void setId(String id){this.id = id;}

    public String getScheme(){return scheme;}

    public void setScheme(String scheme){this.scheme = scheme;}

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("scheme",scheme);
        result.put("expdate", expdate);

        return result;
    }
}