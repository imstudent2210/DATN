import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { AddressService } from 'src/app/services/address.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {
  addressList= [{
    id:"",
    name:""
  }];

  constructor(private address:AddressService, private route:Router){}



  ngOnInit():void{
    this.address.getAllAddress().subscribe((data:any)=>{
     this.addressList = data;
      console.log(data);
      // console.log(this.addressList[0]);
    },
    (error)=>{
      Swal.fire("error")
    })
  }

}
