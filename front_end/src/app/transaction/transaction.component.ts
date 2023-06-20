import {Component, OnInit} from '@angular/core';
import {Transaction, TransactionList} from "./transaction";
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

  public transactionList: TransactionList[] = [];
  public transactionListForm: TransactionList = new class implements TransactionList {
    description: string = "";
    amountTransaction: string= "";
    creditor!: User;
    debtor!: User;
  }

  constructor(private transactionService: TransactionService) { }
  ngOnInit(): void {
    this.getTransactions();
  }



  private getTransactions() {
    this.transactionService.getTransaction().subscribe( {
        next: (data) => {
          this.transactionList = data;
        },
        error : () => {
          console.info('error transaction')
        },
        complete: () => console.info('show transaction complete')
      }
    );
  }




}
