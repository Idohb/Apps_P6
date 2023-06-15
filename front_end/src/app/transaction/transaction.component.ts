import {Component, OnInit} from '@angular/core';
import {Transaction, TransactionRequest} from "./transaction";
import {TransactionService} from "./transaction.service";
import {User} from "../user/user";
import {AuthService} from "../login/auth.service";

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

  public transactionRequest: TransactionRequest[] = [];
  public transactionRequestForm: TransactionRequest = new class implements TransactionRequest {
    idTransaction: number = 0;
    description: string = "";
    amountTransaction: string= "";
    timeTransaction: string= "";
    creditor!: User;
    debtor!: User;
    emailLogin: string = "";
  }

  constructor(private transactionService: TransactionService,
              private authenticationService : AuthService) { }
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
