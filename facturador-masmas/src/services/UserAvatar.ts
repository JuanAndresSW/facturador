import fetch from 'api/fetch';
import Session from './Session';
import { base64ToBlob } from 'utils/conversions';

export default class UserAvatar {
    /**
     * Recupera un URLObject correspondiente al avatar de usuario del propietario del token de acceso almacenado.
     * @param callback La función que procesará la respuesta. 
     */
    public static retrieve(callback: Function): void {
        const returnAsFile = async (state:number, base64:string):Promise<string> => {
            if (state !== 200) {callback(state); return;}
            if (base64 === 'undefined') {callback(400); return;}
            //Devolver la interpretación URL del string.
            const blob = await base64ToBlob(base64.slice(22));
            callback(200, blob);
            //Almacenar en localstorage si no está almacenado.
            if (!localStorage.getItem("avatar")) localStorage.setItem("avatar", base64);
            return;
        }

        if (localStorage.getItem("avatar")) returnAsFile(200, localStorage.getItem("avatar"));
        else fetch("GET","useravatars",{token:Session.getAccessToken()}, returnAsFile);
    }
}