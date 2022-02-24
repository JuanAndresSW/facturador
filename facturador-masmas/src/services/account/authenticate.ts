import fetch from '../fetch';
import Session from 'utils/Session';

/**
* Verifica que el token de sesión almacenado es uno válido.
* @param {Function} callback - La función que maneja la respuesta.
*/
export default function authenticate(callback: Function): void {

  const token = Session.getToken();

  if (/^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(token)) {
    callback(200, token);
    return;
  } else callback(400);

  fetch("", {token:token}, callback);
}