import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class InsightService {

  constructor(public db: AngularFirestore) { }

  getTestData(): Observable<any> {
    return new Observable<any>(() => {
      this.db.collection('/test').valueChanges().subscribe(response => {
        console.log(response);
      });
    });
  }

  addTestData() {

  }
}
