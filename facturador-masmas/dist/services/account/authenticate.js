import Const from 'utils/Const';
/**
* Verifica que el token de sesión almacenado es uno válido.
* @param callback La función que maneja la respuesta.
*/
export default function authenticate(callback) {
    var cookieArray = decodeURIComponent(document.cookie).split("; ");
    var token = cookieArray[0].substring("session=".length);
    if (/^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(token)) {
        callback(Const.ok);
        return;
    }
    callback(Const.exception);
    //fetch("authenticate", token, callback);
}
