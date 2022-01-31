import { account, pointOfSale } from "./Types";
import Format from "./Format";

/**
 * define métodos de comunicación con el servidor.
 */

export default class DocGateway {
  //envía un json con los datos de usuario y la foto de perfil
  public static submitAccount(account: account, url: string): void {
    //definir datos a enviar
    const data = new FormData();
    data.append("account", Format.account(account));
    if (account.user.avatar !== null) {
      data.append("photo", account.user.avatar);
    }

    //enviar y esperar por el código de sesión o el error
    fetch(url, {
      method: "POST",
      body: data,
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Success:", data);

        /** DATA:
         * -codigo de sesión
         * -nombre de usuario
         * -activos, pasivos, patrimonio neto (sólo la cuenta principal)
         */
        //Session.open(data)
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }
  public static submitPoint(pointOfSale: pointOfSale, url: string): void {
    //definir datos a enviar
    const data = new FormData();
    data.append("account", Format.pointOfSale(pointOfSale));
    if (pointOfSale.logo !== null) {
      data.append("logo", pointOfSale.logo);
    }

    //enviar y esperar por el código de sesión o el error
    fetch(url, {
      method: "POST",
      body: data,
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Success:", data);

        /** DATA:
         * -nueva lista de puntos
         */
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }
}
