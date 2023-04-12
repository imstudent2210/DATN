import { Address } from "./address.module";

export interface Store{
  id?:number;
  name?:string;
  address:Address;
  phone?:string;
  email?:string;
  addressDetail?:string;
}
