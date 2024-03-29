/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.malsolo.mongodb.humongous.news;

import java.util.Date;

/**
 *
 * @author jbeneito
 */
public class Comment {
    
    private String author, text;
    private Date date;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .addValue(this.author)
                .addValue(this.date)
                .addValue(this.text)
                .toString();
    }    
    
}
