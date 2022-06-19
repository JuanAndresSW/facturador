import account from '../models/account';
import adaptPostAccount from '../adapters/adaptPostAccount';
import ajax from 'ports/ajax';
import setSession from 'services/setSession';

const url = "auth/accounts";
const method = "POST";

/**Intenta enviar un nuevo usuario al servidor para registrarlo. */
export default async function postAccount(account: account, callback: Function): Promise<void> {
    ajax(method, url, { body: await adaptPostAccount(account) }, respond);

    function respond(status: number, data:string) {

        if (status === 201) {

            setSession(JSON.stringify({
                accessToken:    JSON.parse(data).accessToken,
                refreshToken:   JSON.parse(data).refreshToken,
                username:       account.user.username,
                actives:        '0',
                passives:       '0'
            }));

            callback(true);
            return window.location.reload();
        }

        try {callback(false, JSON.parse(data).mensaje);} //TODO: arreglar cómo se accede a esto.
        catch {callback(false, data);}
    }
} 
