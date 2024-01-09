# 6. NoSQL (*Not Only SQL*)

## MongoDB Queryak:
[Query Dokumentazio ofiziala](https://www.mongodb.com/docs/manual/tutorial/query-documents/).

```
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