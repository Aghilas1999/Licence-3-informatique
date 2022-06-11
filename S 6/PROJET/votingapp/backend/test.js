const dotenv = require('dotenv')
dotenv.config()

const url = process.env.DATABASE_ACCESS
MongoClient.connect(url, function(err, db) {
    if (err) throw err;
    var dbo = db.db("myTable");
    //Find the first document in the customers collection:
    dbo.collection("myTable").findOne({}, function(err, result) {
      if (err) throw err;
      console.log(result.name);
      db.close();
    });
  });
