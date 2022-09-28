import Response from 'models/Response';
import ajax from 'ports/ajax';
import { base64ToBlob } from 'utilities/conversions';


/**
* Recupera un File correspondiente al avatar de usuario del propietario del token de acceso almacenado.
*/
export default async function getUserAvatar(): Promise<Response> {

    if (localStorage.getItem("avatar")) 
        return new Response('', await base64ToBlob(localStorage.getItem("avatar")), 200, true);
    else {
        const response = await ajax("GET","users/"+sessionStorage.getItem('username'), true);

        return {...response, content: base64ToBlob(response.content), ok: (response.status === 200 && response.content !== 'undefined')}
    }
}
