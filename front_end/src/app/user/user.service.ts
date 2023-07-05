import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Friend, User} from "./user";
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../login/login.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServerUrl = "http://localhost:8080/";
  constructor(private http:HttpClient,
              private loginService : LoginService) { }

  public getFriends() : Observable<User[]>{
    return this.http.get<User[]>(this.apiServerUrl + "friend/" + this.loginService.getUserId());
  }

  public addPersonConnection(crediteur : number, debiteur : number) : Observable<User>{
    return this.http.get<User>(this.apiServerUrl + "addFriends?crediteur=" + crediteur + "?debiteur=" + debiteur);
  }

  public searchPersonConnection(personConnection : Friend) : Observable<Friend> {
    return this.http.post<Friend>(this.apiServerUrl + "friends", personConnection);
  }
}
