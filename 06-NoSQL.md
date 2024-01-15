# 6. NoSQL (*Not Only SQL*)

## Lehen pausuak

Instalatu [MongoDB](https://www.mongodb.com/try/download/community) eta [MongoCompass](https://www.mongodb.com/products/tools/compass) (GUI).

## MongoDB Queryak:
[Query Dokumentazio ofiziala](https://www.mongodb.com/docs/manual/tutorial/query-documents/).

```mongosh
db.createCollection("new_collection")

db.COLLECTION.insertOne(DOCUMENT) //_id automatiko, edo eskuz

db.instrumentuak.find({"mota": { $in: ["haizezkoa", "Soka"]}})

//WHERE ... AND ...
db.instrumentuak.find({"mota": { $in: ["haizezkoa", "Soka"]}, "prezioa": {$eq: 500}})

//WHERE ... OR ...
db.instrumentuak.find({$or: [ 
							{"mota": { $in: ["haizezkoa", "Soka"]}}, 
							{"prezioa": {$eq: 700}}
							]
						})
						
						
						
//UPDATE
db.instrumentuak.updateOne({_id:"hodei"}, {$set: {"izena":"pianoa2"}})

//DELETE
db.instrumentuak.deleteOne({"_id":"hodei"})
db.instrumentuak.deleteMany({"mota": {$in: ["haizezkoa", "soka"]}})
```

## Eclipsetik MongoDB atzitu

MongoDB SpringBoot aplikaziotik ere erabil daiteke, baina unitate honetan, errazago egiteko, Maven proiektu soil bat erabiliko da.

Mavenen [MongoDBko dependentzia](https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-sync) gehitu behar dugu.

Behin hori eginda, hasierako froga bat egin dezakegu.

[Hemen dago dokumentazio guztia, adibideekin](https://www.mongodb.com/docs/drivers/java/sync/current/quick-start/).

```java
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
public class QuickStart {
    public static void main( String[] args ) {
        // Zure konexio Stringa jarri. Adib: "mongodb://localhost:27017"
        String uri = "<connection string uri>";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("zure_db_izena");
            MongoCollection<Document> collection = database.getCollection("zure_kolekzioa");
			//Bilatu "title" "John Doe" duten dokumentuak
            Document doc = collection.find(eq("title", "John Doe")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
    }
}
```


## GeoSpacial queries

### $near

```
db.COLLECTION.createIndex({geometry: "2dsphere"})
db.campings.find({geometry:{$near:{$geometry:{type: "Point", coordinates: []},$maxDistance:1000}}}).count()
```