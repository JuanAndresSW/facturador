/**
 * métodos para controlar la sesión
 */

export default class Session {
  //objeto de usuario a comparar, imitando a un registro en la base de datos
  private static tempUser = {
    name: "niño",
    password: "software",
    session: "1234",
    active: "1.55",
    passive: "2",
  };

  public static tryStart(user: string, password: string): boolean {
    //enviar el objeto al servidor, que devuelve una página con un objeto cookie o un mensaje de error
    //por ahora, se establece la cookie localmente (1209600=14d)
    if (user === this.tempUser.name && password === this.tempUser.password) {
      document.cookie = `session=${this.tempUser.session}; max-age=1209600; path=/; Secure`;
      sessionStorage.setItem("active", this.tempUser.active);
      sessionStorage.setItem("passive", this.tempUser.passive);
      sessionStorage.setItem("username", this.tempUser.name);
      return true;
    }
    return false;
  }
  //recupera el valor de código de sesión de las cookies
  public static getSession(): string {
    const cookieArray = decodeURIComponent(document.cookie).split("; ");
    try {
      return cookieArray[0].substring("session=".length);
    } catch (NullPointerException) {
      return "";
    }
  }
  //verifica si existe un código de sesión válido en las cookies
  public static isAuthenticated(): boolean {
    return this.getSession() === "1234";
  }
  //recupera un objeto de la sessión
  public static get(key: string) {
    try {
      return sessionStorage.getItem(key);
    } catch (NullPointerException) {
      return "?";
    }
  }
  //borra la cookie de sesión; la contraparte en la base de datos se debe eliminar tiempo después
  public static close(): void {
    const cookies = document.cookie.split(";");
    for (let cookie of cookies) {
      document.cookie = cookie + "=;expires=" + new Date(0).toUTCString();
    }
    sessionStorage.removeItem("username");
  }
  //renovar el tiempo de expiración de la sesión
  public static renew(): void {
    document.cookie = `session=${this.getSession()}; max-age=1209600; path=/; Secure`;
  }
  //renovar datos de la sesión
  public static refresh(): void {
    sessionStorage.setItem("active", this.tempUser.active);
    sessionStorage.setItem("passive", this.tempUser.passive);
    sessionStorage.setItem("username", this.tempUser.name);
  }
}
