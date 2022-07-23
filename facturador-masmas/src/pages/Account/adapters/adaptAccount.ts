import { fileToBase64 } from 'utilities/conversions';
import editedAccount from '../models/editedAccount';

/**Entrega a una cuenta el formato esperado por el servidor para actualizarla. */
export default async function adaptAccount(acc: editedAccount): Promise<string> {
  
    return JSON.stringify({
        user: {
            username:         sessionStorage.getItem("username"),
            updatedUsername:  acc.user.updatedUsername?     acc.user.updatedUsername.trim()       : null,
            password:         acc.user.password?            acc.user.password.trim()              : null,
            updatedPassword:  acc.user.updatedPassword?     acc.user.updatedPassword.trim()       : null,
            updatedAvatar:    acc.user.updatedAvatar? await fileToBase64(acc.user.updatedAvatar)  : "undefined"
        },
        trader: {
            updatedBusinessName: acc.trader.updatedBusinessName? acc.trader.updatedBusinessName.trim()   : null,
            updatedVatCategory:  acc.trader.updatedVATCategory?  acc.trader.updatedVATCategory.trim()    : null,
        }
    });
}