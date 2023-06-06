import {Component, OnInit} from '@angular/core';
import {Transaction, TransactionRequest} from "./transaction";
import {TransactionService} from "./transaction.service";
import {User} from "../user/user";

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit{

  public transactionResponse: Transaction[] = [];
  public transactionForm : {
    amount: string;
    creditor: number;
    debtor: number;
    description: string;
    emailLogin: string;
    idTransaction: number;
    timeTransaction: string
  } = new class implements Transaction {
    amount: string= "";
    creditor: number = 0;
    debtor: number = 0;
    description: string = "";
    emailLogin: string = "";
    idTransaction: number = 0;
    timeTransaction: string= "";
  }

  public listFriends: User[] = [];
  public friends : User = new class implements User {
    firstName: string = "";
    id: number = 0;
    lastName: string = "";
  };

  public transactionRequest: TransactionRequest[] = [];
  public transactionRequestForm: TransactionRequest = new class implements TransactionRequest {
    idTransaction: number = 0;
    description: string = "";
    amount: string= "";
    timeTransaction: string= "";
    creditor!: User;
    debtor!: User;
    emailLogin: string = "";
  }

  constructor(private transactionService: TransactionService) { }
  ngOnInit(): void {
    this.getTransactions();
  }

  private getTransactions() {
    this.transactionService.getTransaction().subscribe( {
        next: (data) => {
          console.log("1");
          this.transactionRequest = data;
          console.log("2");
          console.log(this.transactionRequest);
        },
        error : () => {
          console.info('error transaction')
        },
        complete: () => console.info('show transaction complete')
      }
    );
  }
}
