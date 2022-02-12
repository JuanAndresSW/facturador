import { session } from './types';

/**
* Controlador local de la sesión de usuario.
* No se comunica directa ni indirectamente con el servidor.
*/

export default class Session {

  /**
   * Establece los valores obtenidos de un login exitoso.
   * @param token El token de la sesión.
   * @param name El nombre de usuario.
   * @param passive La cantidad de patrimonio pasivo.
   * @param active La cantidad de patrimonio activo.
   */
  public static setSession({token, name, active, passive}: session): void {
    document.cookie = `session=${token}; max-age=1209600; path=/; Secure`;
    sessionStorage.setItem("username", name);
    sessionStorage.setItem("active", active.toString());
    sessionStorage.setItem("passive", passive.toString());
  }
  
  /** Evalúa si existe una sesión iniciada sin comprobar la legitimidad del token.*/
  public static isAuthenticated(): boolean {
    const cookieArray = decodeURIComponent(document.cookie).split("; ");
    const token = cookieArray[0].substring("session=".length);
    return /^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(token);
  }

  /** Borra la sesión.*/
  public static close(): void {
    const cookies = document.cookie.split(";");
    for (let cookie of cookies) {
      document.cookie = cookie + "=; Secure; expires=" + new Date(0).toUTCString();
    }
    sessionStorage.clear();
  }


  //GETTERS

  public static getUsername():string {
    return sessionStorage.getItem('username') === null ?
    '?' : sessionStorage.getItem('username');
  }
  public static getActive():string {
    return sessionStorage.getItem('active') === null ?
    '?' : '$'+sessionStorage.getItem('active');
  }
  public static getPassive():string {
    return sessionStorage.getItem('passive') === null ?
    '?' : '$'+sessionStorage.getItem('passive');
  }
  public static getNetWorth():string {
    if (sessionStorage.getItem('passive') === null || sessionStorage.getItem('active') === null) {
      return '?';
    }
    return '$'+(parseFloat(Session.getActive()) - 
    parseFloat(Session.getPassive()))
    .toPrecision(2).toString();
  }
}
