import {FormControl} from "@angular/forms";

export class CheckboxItem {
  id: number;
  name: string;
  description: string;
  control: FormControl;

  constructor( id: number, name: string, description: string, defaultValue?: boolean) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.control = new FormControl(defaultValue || false);
  }

  get selected(): boolean {
    return Boolean(this.control.value);
  }
  getId():number{
    return this.id
}
}
