import { Address } from "./address.model";

export interface Store{
  id?:number;
  name?:string;
  address:Address;
  phone?:string;
  email?:string;
  addressDetail?:string;
  image?:string
}
