import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponentComponent } from './header-component/header-component.component';
import { ShoppinglistComponentComponent } from './shoppinglist-component/shoppinglist-component.component';
import { FooterComponentComponent } from './footer-component/footer-component.component';
import { RecipesComponentComponent } from './recipes-component/recipes-component.component';
import { RecipeListComponent } from './recipes-component/recipe-list/recipe-list.component';
import { RecipeDetailsComponent } from './recipes-component/recipe-details/recipe-details.component';
import { ShoppingEditComponent } from './shoppinglist-component/shopping-edit/shopping-edit.component';
import { RecipeItemComponent } from './recipes-component/recipe-list/recipe-item/recipe-item.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DropdownDirective } from './shared/dropdown.directive';
import { ShoppingListService } from './shoppinglist-component/shoppinglist.service';
import { RecipeStartComponent } from './recipes-component/recipe-start/recipe-start.component';
import { RecipeEditComponent } from './recipes-component/recipe-edit/recipe-edit.component';
import { RecipeServices } from './recipes-component/recipe.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponentComponent,
    ShoppinglistComponentComponent,
    FooterComponentComponent,
    RecipesComponentComponent,
    RecipeListComponent,
    RecipeDetailsComponent,
    RecipeItemComponent,
    ShoppingEditComponent,
    DropdownDirective,
    RecipeStartComponent,
    RecipeEditComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [ShoppingListService, RecipeServices],
  bootstrap: [AppComponent],
})
export class AppModule {}
