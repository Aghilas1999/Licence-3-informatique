import express from 'express';
import recetteRouter from './services/recette-routage';

const app = express();
app.use(express.json());

app.use('/recettes', recetteRouter) 

app.use(express.static('./app/dist/angular-recipe-project'))
app.get('/', function (req, res) {
 res.sendFile('index.html');
});
app.listen(8080);