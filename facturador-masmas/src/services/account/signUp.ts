import { account } from "../../utils/types";
import fetch from "../fetch";

/**
* Envía los datos de usuario y la foto de perfil.
* @param {account} account Datos de la cuenta del usuario, en forma de objeto.
* @param callback La función que manejará la respuesta.
*/
export default async function signUp(account: account, callback: Function): Promise<void> {
  const formattedPromise = formatAccount(account);
  formattedPromise.then(formattedAccount => fetch("signup", formattedAccount, callback));
}


/**
 * Otorga a la cuenta el formato  esperado por el servidor.
 * @param account Objeto de cuenta a formatear.
 * @returns Un string JSON con el formato correcto.
 */
async function formatAccount(account: account): Promise<string> {
  const c = account.trader.code.replace(/ |\.|-/g, "");
  const g = account.trader.grossIncome.replace(/ |\.|-/g, "");
  const data = JSON.stringify({
    user: {
      username: account.user.username.trim(),
      email: account.user.email.trim(),
      password: account.user.password.trim(),
      avatar: account.user.avatar === null ? "" : await account.user.avatar.text(),
    },
    trader: {
      businessName: account.trader.businessName.trim(),
      vatCategory: account.trader.vatCategory,
      code: (c.length !== 0) ? c.slice(0, 2) + '-' + 
      c.slice(2, 4) + '.' + c.slice(4, 7) + '.' + c.slice(7, 10) + 
      '-' + c.charAt(10) : '',
      grossIncome: (g.length !== 0) ? g.slice(0, 2) + '-' +
      g.slice(2, 4) + '.' + g.slice(4, 7) + '.' + g.slice(7, 10) +
      '-' + g.charAt(10) : '',
    }
  });
  return data;
}