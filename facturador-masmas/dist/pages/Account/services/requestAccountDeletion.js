import ajax from 'interceptors/ajax';
import getToken from '../../../services/getToken';
import closeSession from 'services/closeSession';
/**Solicita que un código de eliminación de cuenta sea enviado por email al propietario de la cuenta.*/
export default function requestAccountDeletion(code, callback) {
    ajax("DELETE", "mainaccounts/".concat(sessionStorage.getItem("username")), { token: getToken('access') }, respond);
    //TODO: add {body: code}
    function respond(state, data) {
        if (state === 200) {
            closeSession();
            callback(true);
            window.location.reload();
        }
        else
            callback(false, data);
    }
}
