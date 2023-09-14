import { Component } from '@angular/core';
import {Registration} from "./registration";

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
}
