import fetch from 'api/fetch';
import { fileToBase64 } from 'utils/conversions';
import Session from './Session';

export type mainAccount = {
    user: {
        username: string;
        newUsername?: string;
        email: string;
        password: string;
        newPassword?: string,
        avatar: File;
    };
    trader: {
        businessName: string;
        vatCategory: string;
        code?: string;
        newCode?: string;
        grossIncome: string;
    };
}

export default class MainAccount {

    public static async create(account: mainAccount, callback: Function): Promise<void> {
        const formattedAccount = await formatAccount(account);
        fetch("POST", "mainaccounts", { body: formattedAccount }, callback);
    }

    public static retrieve(callback: Function): void {
        fetch("GET", `mainaccounts/${sessionStorage.getItem("username")}`, { token: Session.getAccessToken() }, callback);
    }

    public static async update(account:mainAccount, callback:Function) {
        const formattedAccount = await formatAccount(account);
        fetch("PUT", "mainaccounts", 
        { body: formattedAccount, token:Session.getAccessToken() }, callback);
    }

    public static delete(code:string, callback:Function) {
        fetch("DELETE", `mainaccounts/${sessionStorage.getItem("username")}`, { body: code, token:Session.getAccessToken() }, callback);
    }

    /**Solicita que un código de eliminación de cuenta sea enviado por email al propietario de la cuenta.*/
    public static requestDeletePermission(callback:Function) {
        fetch("HEAD", "mainaccounts", { token:Session.getAccessToken() }, callback);
    }
}



/**
 * Otorga a la cuenta el formato  esperado por el servidor.
 * @param account Objeto de cuenta a formatear.
 * @returns Un string JSON con el formato correcto.
 */
async function formatAccount(account: mainAccount): Promise<string> {

    const c = account.trader.code?.replace(/ |\.|-/g, "");
    const nc = account.trader.newCode?.replace(/ |\.|-/g, "");
    const g = account.trader.grossIncome?.replace(/ |\.|-/g, "");

    const data = JSON.stringify({
        user: {
            newUsername: account.user.newUsername.trim(),
            username: account.user.username.trim(),
            email: account.user.email?.trim(),
            password: account.user.password.trim(),
            newPassword: account.user.newPassword.trim(),
            avatar: await fileToBase64(account.user.avatar)
        },
        trader: {
            businessName: account.trader.businessName.trim(),
            vatCategory: account.trader.vatCategory,
            code: c? c.slice(0, 2) + '-' +
                c.slice(2, 4) + '.' + c.slice(4, 7) + '.' + c.slice(7, 10) +
                '-' + c.charAt(10) : '',
            newCode: nc? nc.slice(0, 2) + '-' +
            nc.slice(2, 4) + '.' + nc.slice(4, 7) + '.' + nc.slice(7, 10) +
            '-' + nc.charAt(10): '',
            grossIncome: g? g.slice(0, 2) + '-' +
                g.slice(2, 4) + '.' + g.slice(4, 7) + '.' + g.slice(7, 10) +
                '-' + g.charAt(10): '',
        }
    });
    return data;
}