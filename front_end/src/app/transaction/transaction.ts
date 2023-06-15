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

export interface TransactionRequest {
  idTransaction: number;
  description: string;
  amountTransaction: string;
  timeTransaction: string;
  creditor : User ;
  debtor: User;
  emailLogin:string;
}

