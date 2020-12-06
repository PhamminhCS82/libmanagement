/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pqm.pojo;

import java.sql.Date;

/**
 *
 * @author pminh
 */
public class Book {

    /**
     * @return the dayAdded
     */
    public Date getDayAdded() {
        return dayAdded;
    }

    /**
     * @param dayAdded the dayAdded to set
     */
    public void setDayAdded(Date dayAdded) {
        this.dayAdded = dayAdded;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the describe
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * @param describe the describe to set
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    private int id;
    private String name;
    private String author;
    private String describe;
    private String publisher;
    private String year;
    private String category;
    private String location;
    private Date dayAdded;
    public Book(int id, String name, String author,
            String describe, String publisher, String category,String location ,String year, Date d){
        this.id = id;
        this.name = name;
        this.author = author;
        this.describe = describe;
        this.publisher = publisher;
        this.category = category;
        this.location = location;
        this.year = year;
        this.dayAdded = d;
    }
    public Book(String name, String author,
            String describe, String publisher, String category,String location, String year){
        this.name = name;
        this.author = author;
        this.describe = describe;
        this.publisher = publisher;
        this.category = category;
        this.location = location;
        this.year = year;
        this.dayAdded = new Date(System.currentTimeMillis());
    }
}
