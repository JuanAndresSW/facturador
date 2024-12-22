import ajax       from 'ports/ajax';
import Response from 'models/Response';
import getToken   from './getToken';
import setSession from './setSession';

/** Trata de iniciar sesión con el token almacenado.*/
export default async function getSessionByToken(): Promise<Response> {

  const access =  getToken("access");
  const refresh = getToken("refresh");

  //Salir si no existe posibilidad de que el token sea válido.
  if (!/^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(access)) 
  return new Response("El token no contiene un formato válido");

  
  const accessTokenResponse = await ajax("GET","auth/init", true);
  if (accessTokenResponse.status === 200) return success(accessTokenResponse.content);
  
  else {
    const refreshTokenResponse = await ajax("GET","auth/refresh", true, undefined, true);

    if (refreshTokenResponse.status === 200) return success(refreshTokenResponse.content)
    else return new Response("Los tokens almacenados son erróneos o han expirado.");
  }

  function success(sessionJSON: string): Response {
    setSession(sessionJSON);
    return new Response("Token validado", '', 200, true);
  }
}