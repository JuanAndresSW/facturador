import ajax from 'interceptors/ajax';
import getToken from '../../../services/getToken';
import closeSession from 'services/closeSession';

/**Solicita que un código de eliminación de cuenta sea enviado por email al propietario de la cuenta.*/
export default function requestAccountDeletion(code:string, callback:Function): void {
    ajax("DELETE", `mainaccounts/${sessionStorage.getItem("username")}`, { token: getToken('access') }, respond);
    //TODO: add {body: code}
    function respond(state:number, data:string) {
        if (state === 200) {
            localStorage.clear();
            sessionStorage.clear();
            callback(true);
        }
        else callback(false, data);
    }
}