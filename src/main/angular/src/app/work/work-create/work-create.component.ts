import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {formatDate} from "@angular/common";
import {finalize} from "rxjs";
import {WorkService} from "../work.service";
import {IWorkDTO} from "../../entity/IWorkDTO";
import {Router} from "@angular/router";
import {AlertService} from "../alert.service";


@Component({
  selector: 'app-work-create',
  templateUrl: './work-create.component.html',
  styleUrls: ['./work-create.component.css']
})
export class WorkCreateComponent implements OnInit {
  // public formCreateWork: FormGroup | undefined;
  workLists = [{no: 0, status: "Planning"}, {no: 1, status: "Doing"}, {no: 2, status: "Complete"}]
  workStatus = ''

  constructor(private workService: WorkService, private router: Router,
              private alertService: AlertService) {
  }

  validation_messages = {
    nameWork: [
      {type: 'required', message: 'Please input Work name'},
      {type: 'minlength', message: 'name required 8 character or more'},
      {type: 'pattern', message: 'Name same'}
    ],
    dayReceive: [
      {type: 'required', message: 'Please input Day'},
    ],
  };

  ngOnInit(): void {
    // this.formCreateWork = new FormGroup({
    //   workName: new FormControl('',[Validators.required]),
    //   startingDate: new FormControl(''),
    //   endingDate: new FormControl(''),
    //   status: new FormControl('')
    // });
  }

  save() {
    // this.workService.createWorkDTO(this.formCreateWork.value).subscribe(() => {
    //   this.router.navigateByUrl('work-list').then(r => this.alertService.showMessage("Add work successFull!"));
    // })
  }

  getCurrentDateTime(): string {
    return formatDate(new Date(), 'ddMMyyyy', 'en-US');
  }

}
