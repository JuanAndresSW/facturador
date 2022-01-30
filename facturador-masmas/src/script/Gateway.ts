import { account } from "./Types";
import Format from "./Format";

/**
 * define métodos de comunicación con el servidor.
 */

  /*
  Crea la funcion con la palabra async entonces la funcion se vuelve asincronica
    de esta forma podras usar los "await" y con esto dejar de usar el .then
  Si decis porque no usarias el then es porque el then se complica mas ademas
    de esta forma el codigo es mas limpio, mas legible
  Nota el process llama al archivo .env es una archivo de variables para desarrollo
    tambien para la produccion se tendria que cambiar de .env a .env.production
    export async function nameFunction(){
      Let url = process.env.REACT_APP_API + 'nameMetodo';
      Let response = await fetch(url,{
        "method": 'POST',
        "headers": {
         "Content-Type": 'application/json'
       }
    })

     return await response.json();
    }

    Nota cada vez que llames a esta funcion debes usar await para llamarla y al usar este
      en la funcion donde la llamas debes transformarla en asincronica
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
    if (account.pointOfSale.logo !== null) {
      data.append("logo", account.pointOfSale.logo);
    }

    //enviar y esperar por el código de sesión o el error
    let url = process.env.REACT_APP_API + 'recibe';
    
    let response = await fetch(url, {
      "method": 'POST',
      "body": JSON.stringify(data),
      "headers": {
         "Content-Type": 'application/json'
       }
    })

    
  }
}
