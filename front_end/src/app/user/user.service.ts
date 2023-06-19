import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Friend, User} from "./user";
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../login/login.service";
import {Login} from "../login/login";
import {AuthService} from "../login/auth.service";
import {Transaction} from "../transaction/transaction";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServerUrl = "http://localhost:8080/";
  constructor(private http:HttpClient,
              private loginService : LoginService,
              private authenticationService: AuthService) { }

  public getFriends() : Observable<User[]>{
    return this.http.get<User[]>(this.apiServerUrl + "friend/" + this.loginService.getUserId());
  }

  public addPersonConnection(crediteur : number, debiteur : number) : Observable<User>{
    return this.http.get<User>(this.apiServerUrl + "addFriends?crediteur=" + crediteur + "?debiteur=" + debiteur);
  }

  public setAmount(amount : User) : Observable<Transaction> {
    return this.http.post<Transaction>(this.apiServerUrl + "transactionInternal", amount);
  }

  public searchPersonConnection(personConnection : Friend) : Observable<Friend> {
    return this.http.post<Friend>(this.apiServerUrl + "friends", personConnection);
  }
}
