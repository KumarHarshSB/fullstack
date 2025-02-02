import {Employee} from "./employee";

export class Department {
  id: number | undefined;
  name: string | undefined;
  readOnly: boolean | undefined;
  mandatory: boolean | undefined;
  employeeList: Employee[] | undefined;
}
