import { account, pointOfSale } from "./Types";
import Format from "./Format";
import Session from "./Session";

/**
 * Define métodos de comunicación con el servidor.
 */
export default class Gateway {
  /**
   * implementa una capa de abstracción para la API XMLHR
   * @param {String} url el sufijo del url del recurso
   * @param {String} content el contenido (json) del request
   * @param {(state:number, data:string)} callback función para evaluar parámetro de estado
   */
  private static fetch(url: string, content: string, callback: Function): void {
    const xhr = new XMLHttpRequest();
    url = process.env.REACT_APP_API + url;
    if (!xhr) {
      callback(0);
      return;
    }

    //iniciar el request
    xhr.onreadystatechange = handleResponse;
    xhr.timeout = 15000;
    xhr.ontimeout = () => {
      return callback(0);
    };
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(content);

    /**invoca al callback cuando se recibe la respuesta*/
    function handleResponse() {
      if (xhr.readyState === XMLHttpRequest.DONE) {
        callback(xhr.status, xhr.responseText);
        return;
      } else {
        callback(0, null);
        return;
      }
    }
    callback(0, null);
    return;
  }
  /*******************************************************************************
   ************************** MÉTODOS ESPECÍFICOS *********************************
   ********************************************************************************/

  /**
   * envía los datos de usuario y la foto de perfil
   * @param {account} account los datos de la cuenta del usuario, en forma de objeto
   */
  public static submitAccount(account: account, callback: Function): void {
    this.fetch("recibe", JSON.stringify(account), callback);
  }
  /**
   * trata de iniciar sesión con los datos proporcionados
   * @param name el nombre o email
   * @param password la contraseña
   * @param callback función a llamar cuando se resuelve la respuesta
   * @returns si el login fue exitoso o no
   */
  public static tryLogin(
    name: string,
    password: string,
    callback: Function
  ): void {
    /*
      this.fetch(
        "login",
        JSON.stringify({
          name: name,
          password: password,
        }),
        callback
      )*/
    if (name === "niño" && password === "software") {
      callback(
        200,
        JSON.stringify({
          code: "1234",
          name: name,
          active: 123,
          passive: 234.66,
        })
      );
    } else {
      callback(400, "");
    }
  } //objeto de usuario a comparar, imitando a un registro en la base de datos
  private static tempUser = {
    name: "niño",
    password: "software",
    session: "1234",
    active: "1.55",
    passive: "2",
  };

  /**
   * Verifica que el código de sesión almacenado es uno válido.
   * Similar a tryLogin pero sin requerir nombre o contraseña
   * @param code el código de sesión
   * @returns si la autenticación fue exitosa o no
   */
  public static authenticate(code: string): boolean {
    /* const response = JSON.parse(
      this.fetch(
        "auth",
        JSON.stringify({
          code: code,
        }),
        "POST"
      )
    ); */
    return code === "1234";
    //return response.ok;
  }
}
