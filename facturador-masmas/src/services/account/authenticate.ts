import fetch from '../fetch';
import Const from 'utils/Const';

/**
* Verifica que el token de sesión almacenado es uno válido.
* @param callback La función que maneja la respuesta.
*/
export default function authenticate(callback: Function): void {

  const cookieArray = decodeURIComponent(document.cookie).split("; ");
  const token = cookieArray[0].substring("session=".length);

  if (/^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(token)) {
    callback(Const.ok, token);
    return;
  } else {
    callback(Const.exception);
  }
  //fetch("authenticate", token, callback);
}