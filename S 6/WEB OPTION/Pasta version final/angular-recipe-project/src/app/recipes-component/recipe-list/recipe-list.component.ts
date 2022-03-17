import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { Recipe } from '../recipe.model';
import { RecipeServices } from '../recipe.service';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css'],
})
export class RecipeListComponent implements OnInit, OnDestroy {
  recettes: Recipe[];
  subscription: Subscription;

  constructor(
    private recipeService: RecipeServices,
    private router: Router,
    private route: ActivatedRoute,
    private http : HttpService
  ) {}

  ngOnInit() {
       // this.subscription = this.recipeService.recipesChanged.subscribe(
    this.subscription = this.http.getRecettes().subscribe(
      (recipes: Recipe[]) => {
        this.recettes = recipes;
      }
    );
    this.recettes = this.recipeService.getRecettes();
  }

  onNouvelleRecette() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
