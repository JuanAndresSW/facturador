
/**
 * Implementa una capa de abstracción para la API XMLHR.
 * @param {String}                      url        - Sufijo del url del recurso.
 * @param {{body:string, token:string}} content    - Cuerpo y JWT opcionales a enviar en la request.
 * @param {(state:number, data:string)} callback   - Función para manejar la respuesta.  
 */
export default function fetch(url: string, content: {body?:string, token?: string}, callback: Function): void {

  //Definir la request.
  const xhr = new XMLHttpRequest();
  if (!xhr) {
    callback(0, "Las solicitudes XMLHTTP no son soportadas en tu navegador");
    return;
  }

  //Configurar la request.
  url = process.env.REACT_APP_API + url;
  xhr.onreadystatechange = handleResponse;
  xhr.timeout = 15000;
  xhr.ontimeout = () => { callback(0, "Se ha agotado el tiempo de espera del servidor"); return };

  //Abrir la request.
  xhr.open("POST", url, true);
  if (content.body !== undefined) xhr.setRequestHeader("Content-Type", "application/json");
  if (content.token !== undefined) xhr.setRequestHeader("Token", 'Bearer' + content.token);
  xhr.send(content.body);

  //Manejar la respuesta.
  function handleResponse() {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 0) callback(0, "No se ha podido establecer la comunicación con el servidor")
      else callback(xhr.status, xhr.responseText);
      return;
    }
  }
}