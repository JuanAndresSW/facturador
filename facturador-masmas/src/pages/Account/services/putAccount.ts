import ajax from 'ports/ajax';
import editedAccount from '../models/editedAccount';
import adaptAccount from '../adapters/adaptAccount';
import getToken from 'services/getToken';

/**Actualiza uno o más datos de la cuenta. */
export default async function putAccount(account: editedAccount, callback:Function): Promise<void> {
    ajax("PUT", "accounts", 
    { body: await adaptAccount(account), token: getToken("access") }, respond)

    function respond(status: number, data: string): void {
        if (status === 204) {
            localStorage.removeItem('avatar');
            callback(true);
        }
        else callback(false, data);
    }
}

