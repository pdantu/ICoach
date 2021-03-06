import { Component, OnInit } from '@angular/core';
import { InsightService } from "./services/insight.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'icoach-web';
  data: any;

  constructor(private insightService: InsightService) {
  }

  ngOnInit() {
  }

  showTestData() {
    this.data = this.insightService.getTestData();
    console.log(this.data);
  }

}
