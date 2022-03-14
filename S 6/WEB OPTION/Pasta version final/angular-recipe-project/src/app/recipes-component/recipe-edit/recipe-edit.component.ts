import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { RecipeServices } from '../recipe.service';
import { Recipe } from '../recipe.model';
@Component({
  selector: 'app-recipe-edit',
  templateUrl: './recipe-edit.component.html',
  styleUrls: ['./recipe-edit.component.css'],
})
export class RecipeEditComponent implements OnInit {
  id: number;
  editMode = false;
  recipeForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private recipeService: RecipeServices,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.editMode = params['id'] != null;
      this.initForm();
    });
  }
  onSubmit() {
    if (this.editMode) {
      this.recipeService.updateRecette(this.id, this.recipeForm.value);
    } else {
      this.recipeService.ajouterRecette(this.recipeForm.value);
    }
    this.onAnnul();
  }
  onSuppIngredient(index: number) {
    (<FormArray>this.recipeForm.get('ingredients')).removeAt(index);
  }

  onAnnul() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

  // Une methode qui contient les validatore au moment de saisir de l'information.
  onAjoutIngredient() {
    (<FormArray>this.recipeForm.get('ingredients')).push(
      new FormGroup({
        name: new FormControl(null, Validators.required),
        amount: new FormControl(null, [
          Validators.required,
          Validators.pattern(/^(0|[1-9]\d*)(\.\d+)?$/),
        ]),
        uom: new FormControl(null, Validators.required),
      })
    );
  }

  get controls() {
    // a getter!
    return (<FormArray>this.recipeForm.get('ingredients')).controls;
  }

  private initForm() {
    let recipeName = '';
    let recipeImagePath = '';
    let recipeDescription = '';
    let recipeInstructions = '';
    let recipeIngredients = new FormArray([]);
    if (this.editMode) {
      const recipe = this.recipeService.getRecette(this.id);
      recipeName = recipe.nom;
      recipeImagePath = recipe.imagePath;
      recipeDescription = recipe.description;
      recipeInstructions = recipe.instructions;
      if (recipe['ingredients']) {
        for (let ingredient of recipe.ingredients) {
          recipeIngredients.push(
            new FormGroup({
              name: new FormControl(ingredient.name, Validators.required),
              amount: new FormControl(ingredient.montante, [
                Validators.required,
                Validators.pattern(/^(0|[1-9]\d*)(\.\d+)?$/),
              ]),
              uom: new FormControl(ingredient.uom, Validators.required),
            })
          );
        }
      }
    }
    this.recipeForm = new FormGroup({
      name: new FormControl(recipeName, Validators.required),
      imagePath: new FormControl(recipeImagePath, Validators.required),
      description: new FormControl(recipeDescription, Validators.required),
      ingredients: recipeIngredients,
      instructions: new FormControl(recipeInstructions, Validators.required),
    });
  }
}
