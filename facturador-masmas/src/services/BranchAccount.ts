import fetch from 'api/fetch';
import { fileToBase64 } from 'utils/conversions';
import Session from './Session';

export type branchAccount = {
    username: string;
    email: string;
    password: string;
    avatar: File;
    pointOfSale: number;
}

export default class MainAccount {

    /**
     * Envía los datos de usuario para ser registrados.
     * @param {mainAccount} account  - Datos de la cuenta del usuario, en forma de objeto.
     * @param {Function}    callback - La función que manejará la respuesta.
     */
    public static async create(account: branchAccount, callback: Function): Promise<void> {
        const formattedPromise = formatAccount(account);
        formattedPromise.then(formattedAccount => {
            fetch("POST", "auth/branchaccounts", { body: formattedAccount }, callback);
        });
    }

    public static retrieve(callback: Function): void {
        fetch("GET", `auth/branchaccounts/${sessionStorage.getItem("username")}`, { token: Session.getAccessToken() }, callback);
    }

    public static update(account:any, callback:Function) {
        fetch("PUT", "auth/branchaccounts", 
        { body: JSON.stringify(account), token:Session.getAccessToken() }, callback);
    }

    public static delete(code:string, callback:Function) {
        fetch("DELETE", "auth/branchaccounts", { body: code, token:Session.getAccessToken() }, callback);
    }
    
    /**Solicita que un código de eliminación de cuenta sea enviado por email al propietario de la cuenta.*/
    public static requestDeletePermission(callback:Function) {
        fetch("HEAD", "auth/branchaccounts", { token:Session.getAccessToken() }, callback);
    }
}



/**
 * Otorga a la cuenta el formato  esperado por el servidor.
 * @param account Objeto de cuenta a formatear.
 * @returns Un string JSON con el formato correcto.
 */
async function formatAccount(account: branchAccount): Promise<string> {
    const data = JSON.stringify({
        username: account.username.trim(),
        email: account.email.trim(),
        password: account.password.trim(),
        avatar: await fileToBase64(account.avatar),
        pointOfSale: account.pointOfSale,
    });
    return data;
}