import ajax from 'ports/ajax';
import editedAccount from '../models/editedAccount';
import editedAccountToJson from '../adapters/editedAccountToJson';
import Response from 'models/Response';

/**Actualiza uno o más datos de la cuenta. */
export default async function putAccount(account: editedAccount): Promise<Response> {
    const response = await ajax("PUT", "accounts", true, await editedAccountToJson(account));
  
    if (response.status === 204)
    localStorage.removeItem('avatar');
 
    return response;

}

