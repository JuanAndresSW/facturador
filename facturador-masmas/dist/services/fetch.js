import Const from 'utils/Const';
/**
  * Implementa una capa de abstracción para la API XMLHR.
  * @param {String} url Sufijo del url del recurso.
  * @param {String} content Contenido del request.
  * @param {(state:number, data:string)} callback Función para manejar la respuesta.
  */
export default function fetch(url, content, callback) {
    var xhr = new XMLHttpRequest();
    url = process.env.REACT_APP_API + url;
    if (!xhr) {
        callback(Const.error);
        return;
    }
    //Iniciar el request.
    xhr.onreadystatechange = handleResponse;
    xhr.timeout = Const.timeout;
    xhr.ontimeout = function () {
        return callback(0);
    };
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(content);
    /**Invoca al callback cuando se recibe la respuesta.*/
    function handleResponse() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            callback(xhr.status, xhr.responseText);
            return;
        }
    }
}
