import account from '../models/account';
import accountToJson from '../adapters/accountToJson';
import ajax from 'ports/ajax';
import setSession from 'services/setSession';
import Response from 'models/Response';

const url = "auth/accounts";
const method = "POST";

/**Intenta enviar un nuevo usuario al servidor para registrarlo. */
export default async function postAccount(account: account): Promise<Response> {
    const response = await ajax(method, url, true, await accountToJson(account));

    if (response.status === 201) {
        
        setSession(JSON.stringify({
            accessToken:    JSON.parse(response.content).accessToken,
            refreshToken:   JSON.parse(response.content).refreshToken,
            username:       account.user.username,
            actives:        '0',
            passives:       '0'
        }));

        window.location.reload();
    }

    return response
} 
