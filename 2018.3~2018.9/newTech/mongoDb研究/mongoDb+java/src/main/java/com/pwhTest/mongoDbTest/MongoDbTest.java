package com.pwhTest.mongoDbTest;
import com.pwhTest.util.MongoDBUtil;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;


public class MongoDbTest{
	public static void main(String args[]){



	//获取集合
	MongoCollection<Document> collection = MongoDBUtil.getConnect().getCollection("blog");


	//创建文档
	Document document = new Document("name","张三")
	.append("sex", "男")
	.append("age", 18);
	//插入一个文档
	collection.insertOne(document);



	document = new Document("name","李四")
	.append("sex", "女")
	.append("age", 22);
	collection.insertOne(document);



  //申明删除条件
//    Bson filter = Filters.eq("age",18);
//    //删除与筛选器匹配的所有文档
//    collection.deleteMany(filter);




  //修改过滤器
    Bson filter2 = Filters.eq("name", "张三");
    //指定修改的更新文档
    Document document2 = new Document("$set", new Document("age", 100));
    //修改单个文档
    collection.updateOne(filter2, document2);




//查找集合中的所有文档
//    FindIterable findIterable = collection.find();
//    MongoCursor cursor = findIterable.iterator();
//    while (cursor.hasNext()) {
//        System.out.println(cursor.next());
//    }


		System.out.println("afsadfg");
	}
}


