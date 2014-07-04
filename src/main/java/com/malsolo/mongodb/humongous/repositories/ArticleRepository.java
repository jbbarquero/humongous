/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.malsolo.mongodb.humongous.repositories;

import com.malsolo.mongodb.humongous.news.Article;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jbeneito
 */
public interface ArticleRepository extends CrudRepository<Article, ObjectId> {
    
    public Article findByAuthor(String author);
    
}
