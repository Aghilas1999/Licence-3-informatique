import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipesComponentComponent } from './recipes-component.component';

describe('RecipesComponentComponent', () => {
  let component: RecipesComponentComponent;
  let fixture: ComponentFixture<RecipesComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipesComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipesComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
