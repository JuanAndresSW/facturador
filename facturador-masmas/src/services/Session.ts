import fetch from 'api/fetch';

export type session = {
  accessToken: string;    //El JWT usado para autenticar peticiones.
  refreshToken: string;   //El JWT usado para autenticar una solicitud de renovación de token de acceso.
  username: string;       //El nombre del usuario.
  rol: ("MAIN"|"BRANCH"); //El rol del usuario.
  active: string;         //La cantidad numérica de patrimonio activo del comerciante.
  pasive: string;         //La cantidad numérica de patrimonio pasivo del comerciante.
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

    if (/^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(accessToken)) {
      fetch("GET","auth/init", { token: accessToken }, handleAccessTokenResponse);
    } else callback(400);

    function handleAccessTokenResponse(status:number, data:string) { 
      if (status === 200) callback(200, data);
      else fetch("POST","auth/refresh", { token: refreshToken }, handleRefreshTokenResponse);
    };

    function handleRefreshTokenResponse(status:number, data:string) {  
      callback(status, data);
    };

  }

  /** Trata de iniciar sesión con los credenciales proporcionados.*/
  public static getByCredentials(usernameOrEmail: string, password: string, callback: Function): void {
    fetch("POST", "auth/login", {
      body: JSON.stringify({
        usernameOrEmail: usernameOrEmail.trim(),
        password: password.trim(),
      })
    }, callback);
  }

  /**Establece los valores obtenidos de una solicitud de inicio de sesión exitosa.*/
  public static setSession(session: session): void {
    if (session.accessToken)  document.cookie = `accessToken=${session.accessToken}; max-age=1209600; path=/; Secure`;
    if (session.refreshToken) document.cookie = `refreshToken=${session.refreshToken}; max-age=1209600; path=/; Secure`;
    if (session.username)     sessionStorage.setItem("username", session.username);
    if (session.rol)          sessionStorage.setItem("role", session.rol);
    if (session.active !== undefined)   sessionStorage.setItem("actives", session.active);
    if (session.pasive !== undefined)   sessionStorage.setItem("passives", session.pasive);
  }

  /** Forza la expiración de los tokens de sesión. Recarga la ubicación actual al finalizar.*/
  public static close(): void {
    for (let cookie of document.cookie.split(";")) {
      document.cookie = cookie + "=; Secure; path=/; expires=" + new Date(0).toUTCString();
    }
    localStorage.removeItem("avatar");
    window.location.reload(); //SHOULD STORE OLD TOKENS FOR SECURITY
  }

  //## GETTERS ##//

  public static getAccessToken(): string {
    const cookieArray = decodeURIComponent(document.cookie).split("; ");
    return cookieArray[0].substring("accessToken=".length);
  }

  private static getRefreshToken(): string {
    const cookieArray = decodeURIComponent(document.cookie).split("; ");
    return cookieArray[0].substring("refreshToken=".length);
  }
}



//TODO: Access-Token => accessToken; POST => GET.
//      activos => actives; pasivos => passives;
//      rol => role; 


/**{"Access-Token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYW1ldyIsImlzcyI6Ii9hcGkvYXV0a
 * C9yZWZyZXNoIiwiZXhwIjoxNjQ3NTQ3OTk3LCJpYXQiOjE2NDc1MzM1OTcsInJvbCI6Ik1BSU4ifQ.fxacPfsQKmfemidJR
 * Gh9TpfvdtsG1_BSkFlqYygW9y4",
 * 
 * "Refresh-Token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYW1ldyIsImlzcyI6Ii9hcGkvYXV0a
 * C9yZWZyZXNoIiwiZXhwIjoxNjQ3ODA3MTk3LCJpYXQiOjE2NDc1MzM1OTd9.L6KwVZgQSiIPzW3e0aUQtwPeIaSmzJC9Fvh
 * j4HixcHI"} */