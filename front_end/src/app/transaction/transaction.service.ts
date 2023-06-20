import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../user/user.service";
import {LoginService} from "../login/login.service";
import {Transaction, TransactionList, TransactionRequest} from "./transaction";
import {User} from "../user/user";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private apiServerUrl = "http://localhost:8080/";


  constructor(private http:HttpClient,
              private personService : UserService,
              private loginService : LoginService) { }


  public getTransaction() : Observable<TransactionList[]>{
    return this.http.get<TransactionList[]>(this.apiServerUrl + "transactionByCreditor/" + this.loginService.getUserId());
  }

  public setAmount(amount : TransactionRequest) : Observable<Transaction> {
    return this.http.post<Transaction>(this.apiServerUrl + "balance", amount);
  }

}
