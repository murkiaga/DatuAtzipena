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


## GeoSpatial queries

### $near (hurbiltasunagatik bilatu)

```
db.COLLECTION.createIndex({geometry: "2dsphere"})
db.kanpinak.find({geometry:{$near:{$geometry:{type: "Point", coordinates: [-2.4203279,43.3211948]},$maxDistance:1000}}}).count()
```


### $geoWithin (eremu barruan bilatu)

Bilatu eremu honetan:

<img src="img/06-NoSQL/area.png" alt= "Bilaketa eremua" width="500px">

4 puntu sortuko ditugu, bilaketan, lehen eta azken puntua, puntu bera izan behar dute.
```
p1 = [-2.434813468508409, 43.334478736696035]
p2 = [-2.4376489364561156, 43.302827294032724]
p3 = [-2.4053246018522607, 43.29528038271553]
p4= [-2.3530709782445247, 43.308545813855275]
db.kanpinak.find({geometry:{$geoWithin:{$geometry:{type:"Polygon", coordinates:[[p1,p2,p3,p4,p1]]}}}})
``` 