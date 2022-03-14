import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

import { Ingredient } from '../shared/ingredient.model';
import { ShoppingListService } from '../shoppinglist-component/shoppinglist.service';
import { Recipe } from './recipe.model';

@Injectable()
export class RecipeServices {
  recipesChanged = new Subject<Recipe[]>();

  private recettes: Recipe[] = [
    new Recipe(
      'Pâtes au poulet',
      'Un repas délicieux et réconfortant pour toute la famille.',
      'https://images.immediate.co.uk/production/volatile/sites/30/2020/08/slow-cooker-spanish-chicken-4b787a1.jpg?quality=90&webp=true&resize=375,341',
      [
        new Ingredient('Poulet haché', 1, 'kg'),
        new Ingredient('Pâtes', 1, 'paquet'),
        new Ingredient('Tomate hachée', 1, 'part'),
        new Ingredient('Oignon haché', 1, 'part'),
        new Ingredient('Ail', 1, 'demi-clef'),
        new Ingredient('Persil', 1, 'onces'),
        new Ingredient('sel', 1, 'cuillère à café'),
        new Ingredient('Poivre', 0.5, 'cuillère à café'),
      ],
      'Dans une grande poêle, chauffer l"huile à feu moyen-vif. Ajouter le poulet et cuire jusqu"à ce qu"il ne soit plus rosé, environ 5 minutes, Ajouter les pâtes et cuire jusqu"à ce qu elles soient al dente, environ 5 minutes. Ajouter la tomate, l oignon, l ail, le persil, le sel et le poivre aux pâtes et cuire jusqu"à ce que les pâtes soient bien cuites, environ 5 minutes. Servir les pâtes avec le poulet.'
    ),
    new Recipe(
      'Soupe aux haricots d avocat',
      'Ce repas frais et rafraîchissant est un favori parmi les gourmets à la recherche d un repas riche en nutriments.',
      'https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fimages.media-allrecipes.com%2Fuserphotos%2F7273647.jpg&w=596&h=596&c=sc&poi=face&q=85',
      [
        new Ingredient('Avocate', 1, 'part'),
        new Ingredient('haricots', 2, 'part'),
        new Ingredient('sel', 1, 'cuillère à café'),
        new Ingredient('Poivre', 1, 'cuillère à café'),
        new Ingredient('Oignon', 1, 'part'),
        new Ingredient('Oignon Poudre', 1, 'clef'),
      ],
      'Dans une grande casserole, porter l`eau à ébullition. Ajouter les haricots et cuire jusqu`à ce qu`ils soient tendres, environ 10 minutes. Égouttez les haricots et rincez-les sous l`eau froide.'
    ),
  ];

  constructor(private slService: ShoppingListService) {}

  getRecettes() {
    return this.recettes.slice();
  }

  getRecette(index: number) {
    return this.recettes[index];
  }

  ajouterdesingrédientsàlaliste(ingredients: Ingredient[]) {
    this.slService.addIngredients(ingredients);
  }
  ajouterRecette(recipe: Recipe) {
    this.recettes.push(recipe);
    this.recipesChanged.next(this.recettes.slice());
  }
  updateRecette(index: number, newRecipe: Recipe) {
    this.recettes[index] = newRecipe;
    this.recipesChanged.next(this.recettes.slice());
  }
  supprRecette(index: number) {
    this.recettes.splice(index, 1);
    this.recipesChanged.next(this.recettes.slice());
  }
}
