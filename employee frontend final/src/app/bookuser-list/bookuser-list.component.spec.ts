import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookUserListComponent } from './bookuser-list.component';

describe('BookUserListComponent', () => {
  let component: BookUserListComponent;
  let fixture: ComponentFixture<BookUserListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookUserListComponent]
    });
    fixture = TestBed.createComponent(BookUserListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
