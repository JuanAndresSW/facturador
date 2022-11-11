import ajax from 'ports/ajax';
import closeSession from 'services/closeSession';
import Response from 'models/Response';

/**Solicita la eliminación de la cuenta.*/
export default async function deleteAccount(): Promise<Response> {
    const response = await ajax("DELETE", `accounts/${sessionStorage.getItem("username")}`, true);
       
    if (response.status === 204) closeSession();
    return response;
}