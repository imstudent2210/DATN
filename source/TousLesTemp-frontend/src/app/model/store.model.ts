import { Address } from "./address.model";
import { FileHandle } from "./file-handle.model";

export interface Store{
  id?:number;
  name?:string;
  address:Address;
  phone?:string;
  email?:string;
  addressDetail?:string;
  image?:string
}
