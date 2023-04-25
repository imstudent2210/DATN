import { Salary } from "./salary.model";
import { Staff } from "./staff.model";

export interface TimeKeeping{
  id?:number;
  month?:number;
  numOfShift?:number;
  salary:Salary;
  staff:Staff;
}
