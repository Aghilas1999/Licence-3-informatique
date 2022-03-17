import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecipesComponentComponent } from './recipes-component/recipes-component.component';
import { ShoppinglistComponentComponent } from './shoppinglist-component/shoppinglist-component.component';
import { RecipeStartComponent } from './recipes-component/recipe-start/recipe-start.component';
import { RecipeDetailsComponent } from './recipes-component/recipe-details/recipe-details.component';
import { RecipeEditComponent } from './recipes-component/recipe-edit/recipe-edit.component';

const routes: Routes = [
  { path: '', redirectTo: '/recipes', pathMatch: 'full' },
  {
    path: 'recipes',
    component: RecipesComponentComponent,
    children: [
      { path: '', component: RecipeStartComponent },
      { path: 'new', component: RecipeEditComponent },
      { path: ':id', component: RecipeDetailsComponent },
      { path: ':id/edit', component: RecipeEditComponent },
    ],
  },
  {
    path: 'shopping-list',
    component: ShoppinglistComponentComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
