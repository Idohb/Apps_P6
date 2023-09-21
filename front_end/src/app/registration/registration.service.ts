import { Injectable } from '@angular/core';
import {Registration} from "./registration";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private apiServerUrl = "http://localhost:8080/";//environment.apiBaseUrl;
  constructor(private http:HttpClient) { }

  postRegister(registration: Registration) {
    return this.http.post<Registration>(this.apiServerUrl + "signon?", {
      first_name:registration.first_name,
      last_name:registration.last_name,
      email:registration.email,
      password:registration.password
    });
  }
}
