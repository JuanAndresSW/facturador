import mainAccount from '../models/mainAccount';
import adaptMainAccount from '../adapters/adaptMainAccount';
import ajax from 'interceptors/ajax';
import setSession from 'services/setSession';


export default async function signup(account: mainAccount, callback: Function): Promise<void> {
    ajax("POST", "auth/mainaccounts", { body: await adaptMainAccount(account) }, respond);

    function respond(status: number, data:string) {
        if (status === 201) {

            setSession(JSON.stringify({
                accessToken: JSON.parse(data).accessToken,
                refreshToken: JSON.parse(data).refreshToken,
                username: account.user.username,
                role: 'MAIN',
                active: '0',
                pasive: '0'
            }))

            callback(true);
            return window.location.reload();
        }
        try {callback(false, JSON.parse(data).mensaje);}
        catch {callback(false, data);}
    }
} 
