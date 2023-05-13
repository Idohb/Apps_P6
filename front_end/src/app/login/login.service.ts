import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Login} from "./login";
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiServerUrl = "http://localhost:8080/";//environment.apiBaseUrl;
  private userId : number = 0;
  constructor(private http:HttpClient) { }

  public getLogin(login : Login): Observable<Login> {
    return this.http.get<Login>(this.apiServerUrl + "login?email=" + login.email + "&password=" + login.password);
  }
  public setUserId(id : number) {
    this.userId = id;
  }

  public getUserId() {
    return this.userId;
  }
}
