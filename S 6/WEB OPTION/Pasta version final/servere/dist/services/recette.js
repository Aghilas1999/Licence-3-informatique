"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

var _express = _interopRequireDefault(require("express"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

const router = _express.default.Router();

let recettes = [{
  recipeName: 'Will',
  recipeImagePath: 'Alexander',
  recipeDescription: 'will@will.com',
  recipeInstructions: 'License 3 Informatique',
  recipeIngredients: ['web applicationbaby-foot']
}];
router.get('/', function (req, res) {
  res.send(recettes);
});
router.post('/', function (req, res) {
  const recipeE = recettes.find(recipe => recipe.recipeName === req.body.recipeName);

  if (recipeE) {
    res.send({});
  } else {
    recipe.push(req.body);
    res.send({
      recipeName: 'ok'
    });
  }
});
var _default = router;
exports.default = _default;