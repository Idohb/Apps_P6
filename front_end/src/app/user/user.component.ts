import {Component} from '@angular/core';
import {Friend, User} from "./user";
import {UserService} from "./user.service";
import {ActivatedRoute} from "@angular/router";
import {TransactionRequest} from "../transaction/transaction";
import {LoginService} from "../login/login.service";
import {AuthService} from "../login/auth.service";
import {TransactionService} from "../transaction/transaction.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent {

  constructor(private userService : UserService,
              private route:ActivatedRoute,
              private loginService : LoginService,
              private authenticationService : AuthService,
              private transactionService : TransactionService) {
    route.params.subscribe(() => {
      this.getFriends();
    });

  }

  public listFriends: User[] = [];
  public friend : Friend = new class implements Friend {
    userCurrent: number = 0;
    email: string = "";
  };

  private debiteurId: number= 0;

  public transactionRequestForm: TransactionRequest = new class implements TransactionRequest {
    amountTransaction: string = "";
    creditor: number = 0;
    debtor: number = 0;
    description: string = "";
  }
  selected(name : number){
    console.log(name);
  }

  private getFriends() {
    this.userService.getFriends().subscribe( {
      next: (data) => {
        this.listFriends = data;
      },
      error : () => {
        console.info('error retrieving friends')
      }
    });
  }

  public onOpenModalToAddFriends(user: User|null, mode: string): void {
    const container = document.getElementById('main-container-friend');
    if (container === null) { return;}
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addPersonConnectionModal');
    }
    if (mode === 'pay') {
      button.setAttribute('data-target', '#addPersonConnectionModal')
    }
    container.appendChild(button);
    button.click();
  }

  public onAddPersonConnection(addForm: Friend): void {
    const elementAddPerson = document.getElementById('add-employee-form');
    if (elementAddPerson === null) {
      alert('oops');
    } else {
      elementAddPerson.click();
    }
    console.log(addForm.email);
    this.friend["userCurrent"] = this.loginService.getUserId();
    this.friend.email = addForm.email;
    this.addPersonConnection(this.friend);
  }
  public addPersonConnection(friend : Friend) {
    console.log(friend);
    // la méthode addPersonConnection a pour but de chercher une personne par email.
    this.userService.searchPersonConnection(friend).subscribe( {
      next:(debiteur) => {
        // console.log(this.loginService.getUserId());
        console.log(debiteur.userCurrent);
        this.setDebiteurId(debiteur.userCurrent);
        this.getFriends();
      },
      error:() => {
        console.info("error ");
      }
    });
    this.userService.addPersonConnection(this.loginService.getUserId(), this.getDebiteurId());
  }

  public clickToPay(payForm: TransactionRequest) : void {
    payForm.creditor = this.loginService.getUserId();
    payForm.description = payForm.amountTransaction + " euros";

    console.log(payForm.debtor);

    //TODO : est ce que le subscribe est utile ? + regarde la manipulation de données localstorage ou cookies
    this.transactionService.setAmount(payForm).subscribe( {
      next:() => {
        this.transactionService.getTransaction();

      },
      error:() => {
        console.info("error ");
      }
    });

  }

  private setDebiteurId(debiteurId:number): void {
    this.debiteurId = debiteurId;
  }

  public getDebiteurId() : number {
    return this.debiteurId;
  }



}
