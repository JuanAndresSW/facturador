import ajax from 'interceptors/ajax';
import getToken from './getToken';
import setSession from './setSession';

/**
 * Trata de iniciar sesión con el token almacenado.
 * @param {Function} callback - La función que implementa efectos adicionales al recibir la respuesta.
 */
export default function getSessionByToken(callback: Function): void {

  const access = getToken("access");

  //Salir si no existe posibilidad de que el token sea válido.
  if (!/^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(access)) 
  return callback(false, "El token no contiene un formato válido.");

  else ajax("GET","auth/init", { token: access }, handleAccessTokenResponse);

  function handleAccessTokenResponse(status:number, data:string): void { 
    if (status === 200) success(data);
    else ajax("POST","auth/refresh", { token: getToken("refresh") }, handleRefreshTokenResponse);
  };

  function handleRefreshTokenResponse(status:number, data:string): void {  
    if (status !== 200) callback(false, "Los tokens almacenados son erróneos o han expirado")
    else success(data);
  };
  
  function success(data: string): void {
    setSession(data);
    callback(true, 'Token validado')
  }
}