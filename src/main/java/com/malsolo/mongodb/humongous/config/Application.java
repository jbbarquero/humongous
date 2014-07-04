/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malsolo.mongodb.humongous.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 *
 * @author jbeneito
 */
@Configuration
@ComponentScan
@EnableMongoRepositories("com.malsolo.mongodb.humongous.repositories")
public class Application {

    @Bean
    public Mongo mongo() throws Exception {
        Mongo mongo = new MongoClient();
        mongo.setWriteConcern(WriteConcern.SAFE);
        return mongo;
    }
    
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "news");
    }

}
