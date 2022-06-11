const jwt = require('jsonwebtoken');
const MongoClient = require('mongodb').MongoClient;
const dotenv = require('dotenv')
const PORT = 3001
const express = require('express');
const app = express();
const mongoose = require('mongoose');
const routesUrls = require('./routes/routes');
dotenv.config()
const url = process.env.DATABASE_ACCESS
const organiserTemplate = require('./models/organiset')


app.use(express.json());

app.use(express.urlencoded(
    {extended: true}
    )
);
const cors = require('cors')
app.use(cors())

function generateAccessToken(user) {
    return jwt.sign(user, process.env.ACCESS_TOKEN_SECRET, {expiresIn: '1800S'});
}


function generateRefreshToken(user) { // POur pas quE chaQUE FOIS ON CHANGE DE TOKEN
    console.log("REFRESH DONE")
    return jwt.sign(user, process.env.REFRESH_TOKEN_SECRET, {expiresIn: '1y'});
}

mongoose.connect(process.env.DATABASE_ACCESS, () => console.log("Database connected "))
MongoClient.connect(url, function (err, db) {
    if (err) throw err;
    const dbo = db.db("organiserTable");
    dbo.collection("organisertables").findOne({}, function (err, result) {
        if (err)
            throw err;
        console.log(result);
        db.close();
    });
});
app.post('/api/validateCandidates', (req, res) => {
    let a = JSON.parse(req.body.candidates);

})

app.use('', routesUrls)


app.post('/api/login', (req, res) => {

        // TODO: checker en BDD le user par rapport à l'email

        let email = req.body.email.toUpperCase();
        let password = req.body.password
    }
<<<<<<< HEAD
   /* db.get("SELECT * FROM USERS WHERE EMAIL = ?", [email],
        (err, result) => {
            if (err) {
                res.status(500).send('Error dans la base de données');

            } else {
                if (result === undefined) {
                    res.status(401).send('Email inexistant');
                } else if (bcrypt.compare(password, result.password)) {
                    username = {username: result.name};
                    const accessToken = generateAccessToken(username);
                    const refreshToken = generateRefreshToken(username);
                    //localstorage.setItem("JWT", accessToken)
                    res.status(200).send({
                        accessToken,
                        refreshToken,
                    })
                } else {
                    res.status(401).send("Mot de passe incorrect ")
                }
            }
        }

    */

)

=======
    /* db.get("SELECT * FROM USERS WHERE EMAIL = ?", [email],
         (err, result) => {
             if (err) {
                 res.status(500).send('Error dans la base de données');

             } else {
                 if (result === undefined) {
                     res.status(401).send('Email inexistant');
                 } else if (bcrypt.compare(password, result.password)) {
                     username = {username: result.name};
                     const accessToken = generateAccessToken(username);
                     const refreshToken = generateRefreshToken(username);
                     //localstorage.setItem("JWT", accessToken)
                     res.status(200).send({
                         accessToken,
                         refreshToken,
                     })
                 } else {
                     res.status(401).send("Mot de passe incorrect ")
                 }
             }
         }

     */
)
>>>>>>> 03638620061d253c9b639d23c24063ad751a9bfe


app.post('/api/refreshToken', (req, res) => {
    const authHeader = req.headers['authorization'];
    const token = authHeader && authHeader.split(' ')[1];
    if (!token) {
        return res.sendStatus(401);
    }
    jwt.verify(token, process.env.REFRESH_TOKEN_SECRET, (err, user) => {
        if (err) {
            return res.sendStatus(401);
        }
        // TODO : check en bdd que le user a toujours les droit et qu'il existe toujours
        delete user.iat;
        delete user.exp;
        const refreshedToken = generateAccessToken(user);
        res.send({
            accessToken: refreshedToken,
        });
    });
});

function authenticateToken(req, res, next) {
    const authHeader = req.headers['authorization'];
    const token = authHeader && authHeader.split(' ')[1];

    if (!token) {
        //res.redirect("/api/login")
        return res.sendStatus(401);

    }

    jwt.verify(token, process.env.ACCESS_TOKEN_SECRET, (err, user) => {
        if (err) {
            return res.sendStatus(401);
        }
        req.user = user;
        next();
    });

}

app.post("/api/register", (req, res) => {
    let name = req.body.name;
    let email = req.body.email.toUpperCase();
    let password = req.body.password
    /*
    db.get("SELECT * FROM USERS WHERE EMAIL = ?", [email],
     /*
        (err, result) => {
            if (result) {
                res.send("Email déja utilisé ")
                console.log("email")
            } else if (result === undefined) {
                bcrypt.genSalt(saltRound, (err, salt) => {
                        bcrypt.hash(password, salt, (err, hash) => {
                            if (err) {
                                res.send("error")
                            } else {
                                db.run("INSERT INTO USERS (NAME, EMAIL, PASSWORD)  VALUES (?,?,?)", [name, email, hash], (err, resu) => {
                                    if (err) console.log(err)
                                    console.log(resu)
                                    res.send("ACCOUNT CREATED SUCCESSFULLY")

                                })
                            }

                        });
                    }
                )
            }


        })
<<<<<<< HEAD


     */
})
=======
>>>>>>> 03638620061d253c9b639d23c24063ad751a9bfe


     */
})


app.get('/api/me', authenticateToken, (req, res) => {
    res.send(req.user);
});
app.post('/api/addOrganisator' , (req,res)=>{
    console.log(req.body.fullName);
    console.log(req.body.address)
    console.log(req.body.email)
    console.log(req.body.password)

    const registerOrganisator = new organiserTemplate({
        fullName : req.body.fullName,
        address : req.body.address,
        Email : req.body.email ,
        password : req.body.password
    })
    registerOrganisator.save()
        .then((r)=>{
            console.log("Done")
            res.json(r)
        }).catch(err =>{
        console.log("ErROR")
            res.json(err)
    })





})

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`)
})