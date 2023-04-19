import { StaffGroup } from "./staffgroup.module";
import { Store } from "./store.module";


export interface Staff{
  id?:number;
  name?:string;
  email?:string;
  phone?:string;
  store:Store;
  staffGroup:StaffGroup;
  image?:string;
}
