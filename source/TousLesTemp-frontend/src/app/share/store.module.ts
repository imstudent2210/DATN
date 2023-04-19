import { Address } from "./address.module";
import { FileHandle } from "./file-handle.module";

export interface Store{
  id?:number;
  name?:string;
  address:Address;
  phone?:string;
  email?:string;
  addressDetail?:string;
  image?:string
}
