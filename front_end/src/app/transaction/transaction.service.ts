import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../user/user.service";
import {LoginService} from "../login/login.service";
import {Transaction, TransactionRequest} from "./transaction";
import {User} from "../user/user";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private apiServerUrl = "http://localhost:8080/";


  constructor(private http:HttpClient,
              private personService : UserService,
              private loginService : LoginService) { }




  // public getTransactionInternal() : Observable<TransactionInternal[]>{
  //   return this.http.get<TransactionInternal[]>("http://localhost:8080/transactionInternalByCrediteur/" + this.loginService.getUserId());
  // }
  public getTransaction() : Observable<TransactionRequest[]>{
    return this.http.get<TransactionRequest[]>(this.apiServerUrl + "transactionByCreditor/" + this.loginService.getUserId());
  }

  public searchUserConnection(userConnection : string) : Observable<User> {
    return this.http.get<User>(this.apiServerUrl + "loginSearch?emailLogin="+ userConnection + "&crediteur=" + this.loginService.getUserId());
  }

  public addPersonConnection(creditor : number, debtor : number) : Observable<User>{
    return this.http.get<User>(this.apiServerUrl + "addFriends?crediteur=" + creditor + "?debiteur=" + debtor);
  }

  public setAmount(amount : Transaction) : Observable<Transaction> {
    return this.http.post<Transaction>(this.apiServerUrl + "transaction", amount);
  }


}
