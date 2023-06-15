import {Component} from '@angular/core';
import {User} from "./user";
import {UserService} from "./user.service";
import {ActivatedRoute} from "@angular/router";
import {Transaction} from "../transaction/transaction";
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
    route.params.subscribe(val => {
      this.getFriends();
    });
  }

  public listFriends: User[] = [];
  public friends : User = new class implements User {
    first_name: string = "";
    id: number = 0;
    last_name: string = "";
    emailLogin: string = "";
  };

  public UserResponse: User[] = [];
  public userForm : {
    id: number;
    first_name: string;
    last_name: string;
    emailLogin: string;
  } = new class implements User {
    id: number = 0;
    first_name: string = "";
    last_name: string = "";
    emailLogin: string = "";
  }

  private email :string ="";
  private debiteurId: number= 0;



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
    const container = document.getElementById('main-container');
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

  public onAddPersonConnection(addForm: User): void {
    const elementAddPerson = document.getElementById('add-employee-form');
    this.email = addForm.emailLogin;
    if (elementAddPerson === null) {
      alert('oops');
    } else {
      elementAddPerson.click();
    }
    console.log(addForm.emailLogin);
    this.addPersonConnection(this.email);
  }
  public addPersonConnection(personConnection : string) {
    console.log(personConnection);
    // la méthode addPersonConnection a pour but de chercher une personne par email.
    this.userService.searchPersonConnection(personConnection).subscribe( {
      next:(debiteur) => {
        // console.log(this.loginService.getUserId());
        console.log(debiteur.idUser);
        this.setDebiteurId(debiteur.idUser);
        this.getFriends();
      },
      error:() => {
        console.info("error ");
      }
    });
    this.userService.addPersonConnection(this.loginService.getUserId(), this.getDebiteurId());
  }

  public clickToPay(payForm: Transaction) : void {
    payForm.creditor = this.authenticationService.getUserId();
    payForm.description = payForm.amount + " euros";
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
