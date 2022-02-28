import fetch from 'api/fetch';

export type session = {
  accessToken: string;
  refreshToken: string;
};

/**
 * Servicio de sesión de usuario. Permite obtener y solicitar los tokens
 * de acceso y de refrescamiento.
 */
export default class Session {

  /**
  * Trata de iniciar sesión con el token almacenado.
  * @param {Function} callback - La función que maneja la respuesta.
  */
  public static getByToken(callback: Function): void {

    const token = this.getAccessToken();
    if (/^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(token)) {
      fetch("GET","/auth", { token: token }, callback);
      return;
    } else callback(400);
  }

  /**
  * Trata de iniciar sesión con los credenciales proporcionados.
  * @param {string}   [usernameOrEmail] - Nombre o email.
  * @param {string}   [password]        - Contraseña.
  * @param {Function} [callback]        - Función de manejo de respuesta.
  */
  public static getByCredentials(usernameOrEmail: string, password: string, callback: Function): void {
    fetch("POST", "auth/login", {
      body: JSON.stringify({
        usernameOrEmail: usernameOrEmail.trim(),
        password: password.trim(),
      })
    }, callback);
  }

  /**
   * Establece los valores obtenidos de una solicitud de login exitosa.
   * @param {string} [session.accessToken] - El JWT usado para autenticar peticiones.
   * @param {string} [session.refreshToken] - El JWT usado para autenticar una solicitud de renovación de token de accesso.
   */
  private static setSession({ accessToken, refreshToken }: session): void {
    if (accessToken === undefined || refreshToken === undefined) return;
    document.cookie = `accessToken=${accessToken}; max-age=1209600; path=/; Secure`;
    document.cookie = `refreshToken=${refreshToken}; max-age=1209600; path=/; Secure`;
  }

  /** Forza la expiración de los tokens de sesión. Recarga la ubicación actual al finalizar.*/
  public static close(): void {
    for (let cookie of document.cookie.split(";")) {
      document.cookie = cookie + "=; Secure; expires=" + new Date(0).toUTCString();
    }
    window.location.reload();
  }

  /** Recupera el token de acceso del array de cookies. */
  public static getAccessToken(): string {
    const cookieArray = decodeURIComponent(document.cookie).split("; ");
    return cookieArray[0].substring("accessToken=".length);
  }

  /** Recupera el token de refrescamiento del array de cookies. */
  public static getRefreshToken(): string {
    const cookieArray = decodeURIComponent(document.cookie).split("; ");
    return cookieArray[0].substring("refreshToken=".length);
  }


  //GETTERS



  /* public static getUsername():string {
    return sessionStorage.getItem('username') === null ?
    '?' : sessionStorage.getItem('username');
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
  }*/
} 
