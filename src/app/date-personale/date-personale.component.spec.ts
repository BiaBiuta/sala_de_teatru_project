import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatePersonaleComponent } from './date-personale.component';

describe('DatePersonaleComponent', () => {
  let component: DatePersonaleComponent;
  let fixture: ComponentFixture<DatePersonaleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DatePersonaleComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DatePersonaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
