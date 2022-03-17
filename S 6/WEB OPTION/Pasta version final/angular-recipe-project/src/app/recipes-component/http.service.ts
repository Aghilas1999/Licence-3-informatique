import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Recipe } from './recipe.model';
@Injectable({providedIn : 'root'})
export class HttpService {
 private serverUrl = 'http://localhost:8080/';
 constructor (private http: HttpClient) {
 }
 private httpOptions = {
 headers: new HttpHeaders ({
 'Content-Type': 'application/json'
 })
 }

 public getRecettes() : Observable<any>{
  return this.http.get(this.serverUrl+'recettes') ;
  }


 public createRecepe(recipe): Observable<Recipe> {
 return this.http.post<Recipe>(this.serverUrl+'recipe', recipe, this.httpOptions);
 }
}



