import { session } from './types';

/**
* Controlador local de la sesión de usuario.
* No se comunica directa ni indirectamente con el servidor.
*/

export default class Session {

  /**
   * Establece los valores obtenidos de un login exitoso.
   * @property `token` El token de la sesión.
   * @property `name` El nombre de usuario.
   * @property `passive` La cantidad de patrimonio pasivo.
   * @property `active` La cantidad de patrimonio activo.
   */
  public static setSession({token, name, active, passive}: session): void {
    if (token === undefined) return;
    document.cookie = `session=${token}; max-age=1209600; path=/; Secure`;
    sessionStorage.setItem("username", name);
    sessionStorage.setItem("active", active.toString());
    sessionStorage.setItem("passive", passive.toString());
  }

  /**
   * Establece la imagen de avatar de usuario en las cookies.
   * @param usv 
   */
  public static setAvatar(usv: string):void {
    localStorage.setItem('avatar', usv);
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

  public static getToken():string {
    const cookieArray = decodeURIComponent(document.cookie).split("; ");
    return cookieArray[0].substring("session=".length);
  }

  public static getUsername():string {
    return sessionStorage.getItem('username') === null ?
    '?' : sessionStorage.getItem('username');
  }
  public static getActive():string {
    return sessionStorage.getItem('active') === null ?
    '?' : sessionStorage.getItem('active');
  }
  public static getPassive():string {
    return sessionStorage.getItem('passive') === null ?
    '?' :  sessionStorage.getItem('passive');
  }
  public static getNetWorth():string {
    if (sessionStorage.getItem('passive') === undefined || sessionStorage.getItem('active') === undefined) {
      return '?';
    }
    return '$'+ ((parseFloat(Session.getActive())) - 
                (parseFloat(Session.getPassive())))
    .toPrecision(2).toString();
  }
  public static getAvatar():string {
    const USVString = localStorage.getItem("avatar");
    if (USVString === null) return '';
    try {
      const blob = new Blob([USVString]);
      return URL.createObjectURL(blob);
    } catch (invalidUSVString) {
      return '';
    }
  }
}
