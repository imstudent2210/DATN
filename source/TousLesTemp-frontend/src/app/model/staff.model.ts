import { StaffGroup } from "./staffgroup.model";
import { Store } from "./store.model";


export interface Staff{
  id?:number;
  name?:string;
  email?:string;
  phone?:string;
  store:Store;
  staffGroup:StaffGroup;
  image?:string;
}
