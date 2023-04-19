import { Category } from "./category.module";
import { FileHandle } from "./file-handle.module";
import { Size } from "./size.module";
import { StaffGroup } from "./staffgroup.module";
import { Store } from "./store.module";


export interface Staff{
  id?:number;
  name?:string;
  email?:string;
  phone?:string;
  store:Store;
  staffGroup:StaffGroup;
  images:FileHandle[];
}
