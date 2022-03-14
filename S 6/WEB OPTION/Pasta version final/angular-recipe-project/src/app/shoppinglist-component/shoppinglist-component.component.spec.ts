import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppinglistComponentComponent } from './shoppinglist-component.component';

describe('ShoppinglistComponentComponent', () => {
  let component: ShoppinglistComponentComponent;
  let fixture: ComponentFixture<ShoppinglistComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShoppinglistComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppinglistComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
