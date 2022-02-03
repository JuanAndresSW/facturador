import { account } from "./Types";
import Format from "./Format";

/**
 * define métodos de comunicación con el servidor.
 */
export default class DocGateway {
  //envía un json con los datos de usuario y blobs con el logo y la foto de perfil
  public static submitAccount(account: account): void {
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
    let url = process.env.REACT_APP_API + 'singUp';
/*
    let response = await fetch(url, {
      "method": 'POST',
      "body": JSON.stringify(account),
      "headers": {
         "Accept": 'application/json',
         "Content-Type": 'application/json'   
       }
    })
 */
    fetch(url, {
      "method": 'POST',
      "body": JSON.stringify(account),
      "headers": {
         "Accept": 'application/json',
         "Content-Type": 'application/json'   
       }
    })
      .then(async response => {
        try{
          const data = await response.body
          console.log("response data?", data)
        } catch(error){
          console.log(error)
        }
      })
  }
}
