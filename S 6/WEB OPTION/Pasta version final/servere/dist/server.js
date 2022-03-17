"use strict";

var _express = _interopRequireDefault(require("express"));

var _recetteRoutage = _interopRequireDefault(require("./services/recette-routage"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

const app = (0, _express.default)();
app.use(_express.default.json());
app.use('/recettes', _recetteRoutage.default);
app.use(_express.default.static('./app/dist/angular-recipe-project'));
app.get('/', function (req, res) {
  res.sendFile('index.html');
});
app.listen(8080);