import { Ingredient } from '../shared/ingredient.model';

export class Recipe {
  public nom: string;
  public description: string;
  public imagePath: string;
  public ingredients: Ingredient[];
  public uom: string;
  public instructions: string;

  constructor(
    nom: string,
    desc: string,
    imagePath: string,
    ingredients: Ingredient[],
    instructions: string
  ) {
    this.nom = nom;
    this.description = desc;
    this.imagePath = imagePath;
    this.ingredients = ingredients;
    this.instructions = instructions;
  }
}
