import { Category } from "./category.model";
import { Size } from "./size.model";
import { Store } from "./store.model";


export interface Product{
  id?:number;
  description?:string;
  inventory?:number;
  name?:string;
  price?:number;
  category:Category;
  size:Size;
  store:Store;
  image?:string;
}
