/**
 * métodos para controlar la sesión
 */

import { useNavigate } from "react-router-dom";

export default class Session {
  //objeto de usuario a comparar, imitando a un registro en la base de datos
  private static tempUser = {
    name: "niño",
    password: "software",
  };

  public static authenticate(user: string, password: string): boolean {
    //enviar el objeto al servidor, que devuelve una página con un objeto cookie o un mensaje de error
    //por ahora, se establece la cookie localmente (1209600=14d)
    if (user === this.tempUser.name && password === this.tempUser.password) {
      return true;
    }
    return false;
  }

  //establecer la sesión con el código de sesión recibido
  public static open(session: string):void {
    document.cookie = `username=${session}; max-age=1209600; path=/; Secure`;
  }
  //verifica si existe un código de sesión válido en las cookies
  public static isAuthenticated(): boolean {
    if (this.getUsername() === "niño") {
      return true;
    }
    return false;
  }
  //recupera el valor de nombre de usuario de las cookies
  public static getUsername(): string {
    const cookieArray = decodeURIComponent(document.cookie).split("; ");
    try {
      return cookieArray[0].substring("username=".length);
    } catch (NullPointerException) {
      return "";
    }
  }
  //borra la cookie de sesión; la contraparte en la base de datos se debe eliminar tiempo después
  public static close(): void {
    const cookies = document.cookie.split(";");
    for (let cookie of cookies) {
      document.cookie = cookie + "=;expires=" + new Date(0).toUTCString();
    }
  }
  //renovar el tiempo de expiración de la sesión local y en la BD
  public static renew(): void {
    document.cookie = `username=${this.getUsername()}; max-age=1209600; path=/; Secure`;
  }
}
