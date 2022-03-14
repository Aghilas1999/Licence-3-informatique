"use strict";

var _express = _interopRequireDefault(require("express"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

const router = _express.default.Router();

let recipe = [{
  recipeName: 'Chicken Pasta',
  recipeImagePath: 'https://images.immediate.co.uk/production/volatile/sites/30/2020/08/slow-cooker-spanish-chicken-4b787a1.jpg?quality=90&webp=true&resize=375,341',
  recipeDescription: 'will@will.com',
  recipeInstructions: 'License 3 Informatique',
  recipeIngredients: ['web applicationbaby-foot']
}];
router.get('*', function (req, res) {
  res.send(recipe);
});