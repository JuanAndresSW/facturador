import fetch from 'api/fetch';
import Session from './Session';

export type mainAccount = {
    user: {
        username: string;
        email: string;
        password: string;
        avatar: string;
    };
    trader: {
        businessName: string;
        vatCategory: string;
        code: string;
        grossIncome: string;
    };
}

export default class MainAccount {

    public static async create(account: mainAccount, callback: Function): Promise<void> {
        fetch("POST", "mainaccounts", { body: JSON.stringify(account) }, callback);
    }

    public static retrieve(callback: Function): void {
        fetch("GET", `mainaccounts/${sessionStorage.getItem("username")}`, { token: Session.getAccessToken() }, callback);
    }

    public static async update(account:any, callback:Function): Promise<void> {
        fetch("PUT", "mainaccounts", 
        { body: JSON.stringify(account), token:Session.getAccessToken() }, callback);
    }

    public static delete(code:string, callback:Function): void {
        fetch("DELETE", `mainaccounts/${sessionStorage.getItem("username")}`, { token:Session.getAccessToken() }, callback);
    } //TODO: add {body: code}

    /**Solicita que un código de eliminación de cuenta sea enviado por email al propietario de la cuenta.*/
    public static requestDeletePermission(callback:Function): void {
        fetch("HEAD", "mainaccounts", { token:Session.getAccessToken() }, callback);
    }
}