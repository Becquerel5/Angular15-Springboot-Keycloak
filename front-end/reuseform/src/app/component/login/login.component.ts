import { Component,Input,OnInit,Output,EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  

  @Input() title?:string
  @Output() login: EventEmitter<any> = new EventEmitter()
  loginForm!: FormGroup
   constructor(){}

    ngOnInit(){
      this.createForm();
    }

    createForm(){
      this.loginForm = new FormGroup({
        firstname: new FormControl('',[Validators.required]),
        lastname: new FormControl('',[Validators.required]),
        email: new FormControl('',[Validators.required]),
       // username: new FormControl('',[Validators.required]),
        //password: new FormControl('',[Validators.required])
      })
    }

    onSave(){
      this.login?.emit(this.loginForm.value)
    }

}
