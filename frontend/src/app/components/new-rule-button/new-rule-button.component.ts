import { Component, OnInit } from '@angular/core';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { RuleServiceService } from 'src/app/service/rule-service.service';
import { Rule, RuleCardComponent } from '../rule-card/rule-card.component';

@Component({
  selector: 'app-new-rule-button',
  templateUrl: './new-rule-button.component.html',
  styleUrls: ['./new-rule-button.component.css']
})
export class NewRuleButtonComponent implements OnInit {

  closeResult !: string;
  ruleName !: string;
  service !: string;
  services: any[] = [
    { value: 1, name: 'CPU' },
    { value: 2, name: 'File' },
    { value: 3, name: 'Temperature' },
    { value: 4, name: 'User management' },
  ];
  severities: any[] = [
    { value: 1, name: 'Critical' },
    { value: 2, name: 'High' },
    { value: 3, name: 'Normal' },
    { value: 4, name: 'Low' },
  ];
  types: any[] = [
    { value: 1, name: 'Error logs' },
    { value: 2, name: 'Temperature' },
  ];
  timeUnits: any[] = [
    { value: 1, name: 'minutes' },
    { value: 2, name: 'seconds' },
  ];
  selectedService !: string;
  selectedSeverity !: string;
  selectedType !: string;
  selectedUnit !: string;
  severity !: string;
  type !: string;
  limit !: number;
  timePeriod !: number;
  timeUnit !: string;
  inARow !: number;
  
  //rule : Rule | undefined;

  constructor(
    private modalService: NgbModal,
    private ruleService: RuleServiceService,
   // public ruleCard: RuleCardComponent,
  ) { }

  ngOnInit(): void {
  }
  
  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  submitRule(){
    console.log(this.ruleName);
    this.ruleService.createRule(new Rule(this.ruleName, 
      this.service, this.severity, this.limit, 
      this.timePeriod, this.timeUnit, this.limit, this.service)).subscribe(
      data => {
        console.log(data);
      }
    );
    this.modalService.dismissAll();
  }

  selectService(id: number) {
    this.selectedService = this.services[id-1]['name'];
    this.service = this.services[id-1]['name'];
  //  console.log(this.selectedService);
  }

  selectSeverity(id: number) {
    this.selectedSeverity = this.severities[id-1]['name'];
    this.severity = this.severities[id-1]['name'];
  //  console.log(this.selectedSeverity)
  //  console.log(this.selectedService);
  }

  selectType(id: number){
    this.selectedType = this.types[id-1]['name'];
    this.type = this.types[id-1]['name'];
  }

  selectTimeUnit(id: number){
    this.selectedUnit = this.timeUnits[id-1]['name'];
    this.timeUnit = this.timeUnits[id-1]['name'];
  }


}
