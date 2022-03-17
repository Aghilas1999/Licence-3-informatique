import recetteRep from "./recette-repository.js";

async function getRecette(req, res) {
  try {
    const result = await recetteRep.getAll();
    const finalArray = [];
    for (let obj of result.hits.hits) {
      finalArray.push(obj._source);
    }
    res.send(finalArray);
  } catch (e) {
    res.status(400).end();
  }
}

async function create(req, res) {
  res.set("Content-Type", "application/json");
  try {
    const userBool = await recetteExist(req.body.recipeName);
    if (userBool) {
      res.send({});
    } else {
      await recetteRep.store(req.body);
      res.send((recipeName = "ok"));
    }
  } catch (e) {
    res.status(400).end();
  }
}

async function recetteExist(recipeName) {
  try {
    const result = await usersRep.getUser(recipeName);
    return result ? true : false;
  } catch (e) {
    console.log("error getting recette", e);
    return false;
  }
}

export default {
  getRecette,
  create,
  recetteExist,
};