/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.malsolo.mongodb.humongous.main;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import com.malsolo.mongodb.humongous.config.Application;
import com.malsolo.mongodb.humongous.news.Article;
import com.malsolo.mongodb.humongous.news.Comment;
import com.malsolo.mongodb.humongous.repositories.ArticleRepository;
import java.util.Date;
//import java.util.Date;
//import java.util.UUID;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

/**
 * $ ps wuax | grep mongo
 * $ sudo service mongod stop
 * 
 * $ mongod --dbpath=/home/jbeneito/Documents/MongoDB/Products/data/node1
 * 
 * $ mongod --dbpath=/home/jbeneito/Documents/MongoDB/Products/data/node1 --replSet=spring --port 27017
 * $ mongod --dbpath=/home/jbeneito/Documents/MongoDB/Products/data/node2 --replSet=spring --port 27018
 * $ mongod --dbpath=/home/jbeneito/Documents/MongoDB/Products/data/node3 --replSet=spring --port 27019
 * 
 * $ mongo
MongoDB shell version: 2.6.3
connecting to: test
> rs.initiate()
{
	"info2" : "no configuration explicitly specified -- making one",
	"me" : "jbeneito-Latitude-3540:27017",
	"info" : "Config now saved locally.  Should come online in about a minute.",
	"ok" : 1
}
> rs.status()
{
	"set" : "spring",
	"date" : ISODate("2014-07-03T13:34:25Z"),
	"myState" : 1,
	"members" : [
		{
			"_id" : 0,
			"name" : "jbeneito-Latitude-3540:27017",
			"health" : 1,
			"state" : 1,
			"stateStr" : "PRIMARY",
			"uptime" : 325,
			"optime" : Timestamp(1404394454, 1),
			"optimeDate" : ISODate("2014-07-03T13:34:14Z"),
			"electionTime" : Timestamp(1404394454, 2),
			"electionDate" : ISODate("2014-07-03T13:34:14Z"),
			"self" : true
		}
	],
	"ok" : 1
}
spring:PRIMARY> 
spring:PRIMARY> rs.add("jbeneito-Latitude-3540:27018")
{ "ok" : 1 }
spring:PRIMARY> rs.add("jbeneito-Latitude-3540:27019")
{ "ok" : 1 }
spring:PRIMARY> 
spring:PRIMARY> rs.status()
{
	"set" : "spring",
	"date" : ISODate("2014-07-03T13:39:27Z"),
	"myState" : 1,
	"members" : [
		{
			"_id" : 0,
			"name" : "jbeneito-Latitude-3540:27017",
			"health" : 1,
			"state" : 1,
			"stateStr" : "PRIMARY",
			"uptime" : 627,
			"optime" : Timestamp(1404394639, 1),
			"optimeDate" : ISODate("2014-07-03T13:37:19Z"),
			"electionTime" : Timestamp(1404394454, 2),
			"electionDate" : ISODate("2014-07-03T13:34:14Z"),
			"self" : true
		},
		{
			"_id" : 1,
			"name" : "jbeneito-Latitude-3540:27018",
			"health" : 1,
			"state" : 2,
			"stateStr" : "SECONDARY",
			"uptime" : 133,
			"optime" : Timestamp(1404394639, 1),
			"optimeDate" : ISODate("2014-07-03T13:37:19Z"),
			"lastHeartbeat" : ISODate("2014-07-03T13:39:26Z"),
			"lastHeartbeatRecv" : ISODate("2014-07-03T13:39:26Z"),
			"pingMs" : 0,
			"syncingTo" : "jbeneito-Latitude-3540:27017"
		},
		{
			"_id" : 2,
			"name" : "jbeneito-Latitude-3540:27019",
			"health" : 1,
			"state" : 2,
			"stateStr" : "SECONDARY",
			"uptime" : 128,
			"optime" : Timestamp(1404394639, 1),
			"optimeDate" : ISODate("2014-07-03T13:37:19Z"),
			"lastHeartbeat" : ISODate("2014-07-03T13:39:27Z"),
			"lastHeartbeatRecv" : ISODate("2014-07-03T13:39:27Z"),
			"pingMs" : 0,
			"syncingTo" : "jbeneito-Latitude-3540:27017"
		}
	],
	"ok" : 1
}
spring:PRIMARY> 
spring:PRIMARY> ^C
bye
$ mongo --port 27018

 * @author jbeneito
 */
public class Main {
    
    public static void main(String... args) {
        
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        
        MongoOperations ops = context.getBean("mongoTemplate", MongoOperations.class);
        
        //Create article
        /*
        Article article = new Article();
        article.setAuthorId(UUID.randomUUID());
        article.setAuthor("Javier");
        article.setDate(new Date());
        article.setTitle("Título");
        
        //Inserts
        ops.insert(article);
        */
        
        //Find one article
        Article article = ops.findOne(query(where("author").is("Javier")), Article.class);
        
        System.out.println(article);
        
        ArticleRepository articleRepository = context.getBean("articleRepository", ArticleRepository.class);
        article = articleRepository.findByAuthor("Javier");
               
        System.out.println(article);
        
        Comment comment = new Comment();
        comment.setAuthor("David el Gnomo");
        comment.setDate(new Date());
        comment.setText("Another comment");
        
        ops.upsert(query(where("author").is("Javier"))
                , new Update().push("comments", comment), Article.class);
        
    }
    
}
