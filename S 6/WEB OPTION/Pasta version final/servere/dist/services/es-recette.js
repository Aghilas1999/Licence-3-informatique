"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

var _elasticsearch = _interopRequireDefault(require("@elastic/elasticsearch"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

const esRecette = new _elasticsearch.default.Client({
  node: 'http://localhost:9200',
  log: 'info',
  apiVersion: '7.5',
  maxSockets: 20
});
var _default = esRecette;
exports.default = _default;