import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Login} from "./login";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiServerUrl = "http://localhost:8080/";/*environment.apiBaseUrl;*/

  // BASE_PATH: 'http://localhost:8080'
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

  private _username: string ="";
  private _password: string ="";
  private _test: string ="";
  private userId : number = 0;

  get username(): string {
    return this._username;
  }

  set username(value: string) {
    this._username = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }

  constructor(private http: HttpClient) {

  }

  public setUserId(id : number) {
    this.userId = id;
  }

  public getUserId() {
    return this.userId;
  }

  public authenticationService(login:Login) : Observable<Login>{
    var test = this.createBasicAuthToken(login.email, login.password);
    return this.http.get<Login>(this.apiServerUrl + "login"/*"greeting?name=" + test*/);
  }

  // public authenticationService(username: string, password: string) : Observable<Login>{
  //   var test = this.createBasicAuthToken(username, password);
  //   return this.http.get<Login>(this.apiServerUrl + "greeting?name=" + test);
  // }

  createBasicAuthToken(username: string, password: string) {
    const stringstring = window.btoa(username + ":" + password);
    console.log(stringstring);
    return stringstring;
  }

  registerSuccessfulLogin(username: string, password: string) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username)
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    this._username = '';
    this._password = '';
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    return true
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    return user
  }
}
