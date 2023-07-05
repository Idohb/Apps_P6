import {User} from "../user/user";

export interface Transaction {
  idTransaction: number;
  description: string;
  amount: string;
  timeTransaction: string;
  creditor : number ;
  debtor: number;
  emailLogin:string;
}

export interface TransactionList {
  description: string;
  amountTransaction: string;
  creditor : User ;
  debtor: User;
}

export interface TransactionRequest {
  description: string;
  amountTransaction: string;
  creditor : number ;
  debtor: number;
}

