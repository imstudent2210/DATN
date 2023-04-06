import { Category } from "./category.module";
import { FileHandle } from "./file-handle.module";
import { Size } from "./size.module";
import { Store } from "./store.module";


export interface Product{
  id?:number;
  description?:string;
  inventory?:number;
  name?:string;
  price?:number;
  category:Category;
  size:Size;
  store:Store;
  images:FileHandle[];
}
