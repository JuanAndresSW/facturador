import { account } from "./Types";
import Format from "./Format";

/**
 * define métodos de comunicación con el servidor.
 */
export default class DocGateway {
  //envía un json con los datos de usuario y blobs con el logo y la foto de perfil
  public static async submitAccount(account: account) {
    //definir datos a enviar
    const data = new FormData();
    data.append("account", Format.account(account));
    if (account.user.avatar !== null) {
      data.append("photo", account.user.avatar);
    }
    /*
    if (account.pointOfSale.logo !== null) {
      data.append("logo", account.pointOfSale.logo);
    }
    */
    //enviar y esperar por el código de sesión o el error
    let url = process.env.REACT_APP_API + 'recibe';
    
    alert(JSON.stringify(data));
    alert(data);
    alert(JSON.stringify(account));

    let response = await fetch(url, {
      "method": 'POST',
      "body": JSON.stringify(account),
      "headers": {
         "Content-Type": 'application/json'
       }
    })
    //let result = await response.json;
    alert(JSON.stringify(response.json));
  }
}
