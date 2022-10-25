import Response from 'models/Response';
import { base64ToBlob } from 'utilities/conversions';

/**Recupera un Blob a partir de un string almacenado en localStorage, correspondiente a el avatar de usuario. */
export default async function getLocalUserAvatar(): Promise<Response> {

    if (localStorage.getItem("avatar")) {
        const blob = await base64ToBlob(localStorage.getItem('avatar'));
        return new Response("", blob, 200);
    }   
}
