import { Adress } from './adress.model';

export class Client {
    clientId: number;
    code: String;
    firstName: String;
    lastName: String;
    email: String;
    numberPhone: String;
    dateCreation: Date;
    adress: Adress;
}
