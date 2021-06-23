import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Rule } from '../components/rule-card/rule-card.component';

@Injectable({
  providedIn: 'root'
})
export class RuleServiceService {

  constructor(private http: HttpClient) {
  }

  createRule(rule: Rule){
    return this.http.post(`http://localhost:8080/api/v1/rules`, rule);
  }
}
