import fetch from 'api/fetch';

export type session = {
  accessToken: string;
  refreshToken: string;
  //username: string;
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
    const accessToken = this.getAccessToken();
    const refreshToken = this.getRefreshToken();

    const handleRefreshTokenResponse = (status:number, data:string) => {  
      if (status === 200) {this.setSession(JSON.parse(data)); callback(200);}
      callback(status);
    };
    const handleAccessTokenResponse = (status:number) => { 
      if (status === 200) callback(200);
      else fetch("HEAD","auth/refresh", { token: refreshToken }, handleRefreshTokenResponse); //Volver a intentar con el otro token.
    };

    if (/^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(accessToken)) {
      fetch("HEAD","auth/init", { token: accessToken }, handleAccessTokenResponse);//should be "HEAD"
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
   * @param {string} [session.refreshToken] - El JWT usado para autenticar una solicitud de renovación de token de acceso.
   */
  public static setSession(session: session): void {
    if (session.accessToken === undefined || session.refreshToken === undefined) return;
    document.cookie = `accessToken=${session.accessToken}; max-age=1209600; path=/; Secure`;
    document.cookie = `refreshToken=${session.refreshToken}; max-age=1209600; path=/; Secure`;
  }

  /** Forza la expiración de los tokens de sesión. Recarga la ubicación actual al finalizar.*/
  public static close(): void {
    for (let cookie of document.cookie.split(";")) {
      document.cookie = cookie + "=; Secure; path=/; expires=" + new Date(0).toUTCString();
    }
    localStorage.removeItem("avatar");
    window.location.reload(); //SHOULD STORE OLD TOKENS FOR SECURITY
  }

  //GETTERS

  /** Recupera el token de acceso del array de cookies. */
  public static getAccessToken(): string {
    const cookieArray = decodeURIComponent(document.cookie).split("; ");
    return cookieArray[0].substring("accessToken=".length);
  }

  /** Recupera el token de refrescamiento del array de cookies. */
  private static getRefreshToken(): string {
    const cookieArray = decodeURIComponent(document.cookie).split("; ");
    return cookieArray[0].substring("refreshToken=".length);
  }
}



/**
 * username	"test1"
 * activos	0
 * pasivos	0
 * accessToken	
 * refreshToken	
 */
