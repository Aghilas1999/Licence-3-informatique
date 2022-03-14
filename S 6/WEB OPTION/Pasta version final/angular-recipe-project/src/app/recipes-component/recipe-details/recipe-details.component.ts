import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Recipe } from '../recipe.model';
import { RecipeServices } from '../recipe.service';
@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css'],
})
export class RecipeDetailsComponent implements OnInit {
  recipe: Recipe;
  id: number;
  constructor(
    private recipeService: RecipeServices,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.recipe = this.recipeService.getRecette(this.id);
    });
  }

  onAjoutalaliste() {
    this.recipeService.ajouterdesingrédientsàlaliste(this.recipe.ingredients);
  }
  onModifierRecette() {
    this.router.navigate(['edit'], { relativeTo: this.route });
  }
  onSupprimeRecette() {
    this.recipeService.supprRecette(this.id);
    this.router.navigate(['/recipes']);
  }
}
