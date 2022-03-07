import fetch from 'api/fetch';
import Session from './Session';
import { base64ToBlob } from 'utils/conversions';

export default class userAvatar {
    /**
     * Recupera un URLOBject correspondiente al avatar de usuario del propietario del token de acceso almacenado.
     * @param callback La función que procesará la respuesta. 
     */
    public static getAvatar(callback: Function): void {
        const returnAsURLObject = async (state:number, base64:string):Promise<string> => {
            if (state !== 200) {callback(400); return;}
            const blob = await base64ToBlob(base64.slice(22));
            callback(200, URL.createObjectURL(blob));
            return;
        }

        fetch("GET","useravatars",{token:Session.getAccessToken()}, returnAsURLObject); //SHOULD BE "GET"
    }
}