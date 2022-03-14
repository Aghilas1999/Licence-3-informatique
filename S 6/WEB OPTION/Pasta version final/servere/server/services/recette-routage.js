import express from 'express';
import recetteHandler from './recette-handler';

import asyncHandler from 'express-async-handler';

const recetteRouter = express.Router();


let recettes = [{recipeName :'Will',
recipeImagePath : 'Alexander',
recipeDescription : 'will@will.com',
recipeInstructions : 'License 3 Informatique',
recipeIngredients : ['web applicationbaby-foot']
}];
/*

router.get('/', function (req, res) {
 res.send(recettes);
})

router.post('/', function (req, res) {
    const recipeE = recettes.find(recipe => recipe.recipeName === req.body.recipeName)
    ;
   
    if (recipeE) {
    res.send({});
    } else {
        recipe.push(req.body);
    res.send({ recipeName : 'ok' });
    }
   }
)*/
   
recetteRouter.get('/', asyncHandler(recetteHandler.getRecette));
recetteRouter.post('/', asyncHandler (recetteHandler.create));

export default recetteRouter;