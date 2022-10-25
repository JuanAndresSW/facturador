import Response from "models/Response";
import getToken from "services/getToken";
type HTTPMethod = ("GET"|"POST"|"PUT"|"DELETE");

/**
 * Implementa una capa de abstracción para la API XMLHR.
 * @param {HTTPMethod} [method]         - El método HTTP a ser utilizado.
 * @param {string}     [url]            - Sufijo del url del recurso.
 * @param {boolean}    [needsAuth]      - Si se debe incluir un token JWT. Por defecto es falso.
 * @param {string}     [body]           - Cuerpo opcional de la petición.
 * @param {boolean}    [refresh]        - Si el token a enviar es de refrescamiento.
 * @return {Response}                   - La respuesta de la petición.
 */
const ajax = (method: HTTPMethod, url: string, needsAuth: boolean=false, body?:string, refresh=false): Promise<Response> =>
new Promise<Response>( resolve => {

  //Definir la request.
  const xhr = new XMLHttpRequest();
  if (!xhr) resolve (new Response("Las solicitudes XMLHTTP no son soportadas en tu navegador"));

  //Configurar la request.
  xhr.timeout =             15000;
  xhr.onreadystatechange =  handleResponse;
  xhr.ontimeout = () =>     resolve (new Response("Se ha agotado el tiempo de espera del servidor"))

  //Abrir la request.
  xhr.open(method, process.env.REACT_APP_API + url, true);
  if (body  !== undefined)  xhr.setRequestHeader("Content-Type", "application/json");
  if (needsAuth)            xhr.setRequestHeader("Authorization", 'Bearer ' + getToken("access"));
  xhr.send(body);

  //Manejar la respuesta.
  function handleResponse(): void {
    if (xhr.readyState === XMLHttpRequest.DONE) 

    switch (xhr.status) {
      case 0:   resolve(new Response("No se ha podido establecer la comunicación con el servidor")); break;
      case 400: resolve(new Response("Error al procesar la respuesta", ',', 400)); break;
      case 404: resolve(new Response("No se ha encontrado el recurso solicitado", '', 404)); break;
      case 401: resolve(new Response("No tienes permiso para esta operación", '', 401)); break;
      case 500: resolve(new Response("Error del servidor", '', 500)); break;
      default:  resolve(new Response("", xhr.responseText, xhr.status, true)); break;
    } 
    
  }
});

export default ajax;