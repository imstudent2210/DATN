import { Category } from "./category.module";
import { FileHandle } from "./file-handle.module";
import { Size } from "./size.module";
import { Store } from "./store.module";

// export class Product{
//   id?:number;
//   description?:string;
//   image?:string;
//   inventory?:number;
//   name?:string;
//   price?:number;
//   category?:string;
//   size?:string;
//   store?:string;
// }

export interface Product{
  id?:number;
  description?:string;
  inventory?:number;
  name?:string;
  price?:number;
  category:Category;
  size:Size;
  store:Store;
  image:FileHandle[];
}
