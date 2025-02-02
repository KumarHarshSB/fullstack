import {Department} from "./department";

export class Employee {
  id: number | undefined;
  name: string | undefined;
  email: string | undefined;
  departmentList: Department[] | undefined;
}
