"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

var _express = _interopRequireDefault(require("express"));

var _recetteHandler = _interopRequireDefault(require("./recette-handler"));

var _expressAsyncHandler = _interopRequireDefault(require("express-async-handler"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

const recetteRouter = _express.default.Router();

let recettes = [{
  recipeName: 'Will',
  recipeImagePath: 'Alexander',
  recipeDescription: 'will@will.com',
  recipeInstructions: 'License 3 Informatique',
  recipeIngredients: ['web applicationbaby-foot']
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

recetteRouter.get('/', (0, _expressAsyncHandler.default)(_recetteHandler.default.getRecette));
recetteRouter.post('/', (0, _expressAsyncHandler.default)(_recetteHandler.default.create));
var _default = recetteRouter;
exports.default = _default;