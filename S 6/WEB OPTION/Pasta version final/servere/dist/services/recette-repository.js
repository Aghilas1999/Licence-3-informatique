"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

var _esRecette = _interopRequireDefault(require("./es-recette"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

const index = 'local_users';

const handleElasticsearchError = error => {
  if (error.status === 404) {
    throw new Error('User Not Found', 404);
  }

  throw new Error(error.msg, error.status || 500);
};

const getAll = () => _esRecette.default.search({
  index
}).then(response => response).catch(error => {
  handleElasticsearchError(error);
});

const store = user => _esRecette.default.index({
  index,
  refresh: 'true',
  body: user
}).then(response => response.status).catch(error => {
  handleElasticsearchError(error);
});

const getUser = recipeName => _esRecette.default.search({
  index,
  body: {
    "query": {
      "match": {
        "firstName": {
          "query": recipeName
        }
      }
    }
  }
}).then(response => {
  response;
}).catch(error => {
  handleElasticsearchError(error);
});

var _default = {
  getUser,
  store,
  getAll
};
exports.default = _default;