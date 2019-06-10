package com.example.lestudis.models;

public class DiscountModel {
    private String img, expdate, id, description;

    public DiscountModel() {
    }
        public DiscountModel(String img, String expdate, String id, String description) {
            this.img = img;
            this.expdate = expdate;
            this.id = id;
            this.description= description;
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
    }