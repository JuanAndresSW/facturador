import account from '../models/account';
import {fileToBase64, toFormattedCUIT} from 'utilities/conversions';

/**Modifica el objeto de cuenta, dándole el formato esperado por el servidor para crearla. */
export default async function adaptPostAccount(account: account): Promise<string> {
    return JSON.stringify({
        user: {
          username:     account.user.username.trim(),
          email:        account.user.email.trim(),
          password:     account.user.password.trim(),
          avatar:       "" + await fileToBase64(account.user.avatar),
        },
        trader: {
          businessName: account.trader.businessName.trim(),
          vatCategory:  account.trader.VATCategory.trim(),
          cuit:         toFormattedCUIT(account.trader.CUIT),
        }
    });
}