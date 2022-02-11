import Gateway from "../script/Gateway";

/**
 * métodos para controlar la sesión
 */

export default class Session {

  /**
   * Intenta iniciar sesión
   * @param user el nombre de usuario o email
   * @param password la contraseña proveída
   * @param callback la función que procesará la respuesta
   * @returns si el inicio fue exitoso o no
   */
  public static tryStart(
    user: string,
    password: string,
    callback: Function
  ): void {
    Gateway.tryLogin(user, password, callback);
  }
  /**
   * establece los valores obtenidos de un login exitoso
   * @param {String} code: el código de la sesión
   * @param {String} name: el nombre de usuario
   * @param {String} passive: la cantidad de patrimonio pasivo
   * @param {String} active: la cantidad de patrimonio activo
   */
  public static setSession(
    code: string,
    name: string,
    active: number,
    passive: number
  ): void {
    document.cookie = `session=${code}; max-age=1209600; path=/; Secure`;
    sessionStorage.setItem("username", name);
    sessionStorage.setItem("active", active.toString());
    sessionStorage.setItem("passive", passive.toString());
  }

  /**
   * obtiene el código de sesión de las cookies
   * @returns {String} el código de sesión, si existe
   */
  public static getSession(): string {
    const cookieArray = decodeURIComponent(document.cookie).split("; ");
    try {
      return cookieArray[0].substring("session=".length);
    } catch (NullPointerException) {
      return "?";
    }
  }
  /**
   * @returns si existe un código de sesión válido en las cookies
   */
  public static isAuthenticated(): boolean {
    return Gateway.authenticate(this.getSession());
  }
  /**
   * intermediario para recuperar el objeto `key` de sessionStorage
   */
  public static get(key: string) {
    try {
      return sessionStorage.getItem(key);
    } catch (NullPointerException) {
      return "?";
    }
  }
  /**
   * borra la cookie de sesión; la contraparte en la base de datos se debe eliminar tiempo después
   */
  public static close(): void {
    const cookies = document.cookie.split(";");
    for (let cookie of cookies) {
      document.cookie = cookie + "=;expires=" + new Date(0).toUTCString();
    }
    sessionStorage.removeItem("username");
  }
  /**
   * renovar el tiempo de expiración de la sesión
   */
  public static renew(): void {
    document.cookie = `session=${this.getSession()}; max-age=1209600; path=/; Secure`;
  }
  /**
   * renovar datos de la sesión
   */
  public static refresh(): void {
    /* sessionStorage.setItem("active", this.tempUser.active);
    sessionStorage.setItem("passive", this.tempUser.passive);
    sessionStorage.setItem("username", this.tempUser.name); */
  }
}
