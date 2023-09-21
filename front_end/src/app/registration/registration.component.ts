import { Component } from '@angular/core';
import {Registration} from "./registration";
import {Login} from "../login/login";
import {RegistrationService} from "./registration.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent {
  public registrationForm: Registration = new class implements Registration {
    first_name: string = "";
    last_name: string = "";
    email: string = "";
    password: string = "";
  };

constructor(private registerService : RegistrationService,
            private router : Router) {
}

  onToggleRegistersCheck(registration : Registration) {
    this.registerService.postRegister(registration).subscribe( {
      next: (data) => {
        this.router.navigate(['login/']);
      },
      error :  () => {
        this.router.navigate(['login']);
      },
      complete: () => console.info('complete')
    });

  }

}
