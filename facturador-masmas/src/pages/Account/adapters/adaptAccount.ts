import { fileToBase64, toFormattedCUIT } from 'utilities/conversions';
import editedAccount from '../models/editedAccount';

export default async function adaptAccount(acc: editedAccount) {
  
    return JSON.stringify({
        user: {
            username:     acc.user.username,
            newUsername:  acc.user.newUsername?     acc.user.newUsername.trim()       : null,
            password:     acc.user.password?        acc.user.password.trim()          : null,
            newPassword:  acc.user.newPassword?     acc.user.newPassword.trim()       : null,
            newAvatar:    await  fileToBase64(acc.user.newAvatar)
        },
        trader: {
            newBusinessName: acc.trader.newBusinessName? acc.trader.newBusinessName.trim()   : null,
            newVATCategory:  acc.trader.newVATCategory?  acc.trader.newVATCategory.trim()    : null,
            newCUIT:         acc.trader.newCUIT?         toFormattedCUIT(acc.trader.newCUIT) : null,
        }
    });
}