/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pqm.pojo;

import java.sql.Date;

/**
 *
 * @author user
 */
public class BorrowDetails {

    /**
     * @return the bookName
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * @param bookName the bookName to set
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * @return the userSurname
     */
    public String getUserSurname() {
        return userSurname;
    }

    /**
     * @param userSurname the userSurname to set
     */
    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    /**
     * @return the userFirstname
     */
    public String getUserFirstname() {
        return userFirstname;
    }

    /**
     * @param userFirstname the userFirstname to set
     */
    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the returnDate
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return the fine
     */
    public double getFine() {
        return fine;
    }

    /**
     * @param fine the fine to set
     */
    public void setFine(double fine) {
        this.fine = fine;
    }
    private String bookName;
    private String userSurname;
    private String userFirstname;
    private Date startDate;
    private Date endDate;
    private Date returnDate;
    private double fine;
    
    public BorrowDetails(String bookName, String userSurname, String userFirstname
            , Date startDate, Date endDate, Date returnDate, double fine){
        this.bookName = bookName;
        this.userSurname = userSurname;
        this.userFirstname = userFirstname;
        this.startDate = startDate;
        this.endDate = endDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }
}
