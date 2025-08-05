package com.example;

public class Costs {
    private Integer id;
    private String name;
    private Integer price;
    private String category;
    private String comment;

    public Costs() {}

    public Costs(Integer id, String name, Integer price, String category, String comment) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}