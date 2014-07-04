/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malsolo.mongodb.humongous.driver;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author jbeneito
 */
public class Main {

    public static void main(String... args) throws UnknownHostException {

        MongoClient mongoClient = new MongoClient();
        DB db = mongoClient.getDB("news");

        Set<String> colls = db.getCollectionNames();

        for (String s : colls) {
            System.out.println(s);
        }

        DBCollection coll = db.getCollection("article");

        BasicDBObject doc = new BasicDBObject("authorId", UUID.randomUUID())
                .append("author", "Driver")
                .append("date", new Date())
                .append("title", "Title");
        coll.insert(doc);

        DBObject myDoc = coll.findOne();
        System.out.println(myDoc);

        System.out.println(coll.getCount());

        try (DBCursor cursor = coll.find()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        }

    }

}
