import { fileToBase64, toFormattedCUIT } from 'utilities/conversions';
import editedAccount from '../models/editedAccount';

export default async function adaptAccount(acc: editedAccount): Promise<string> {
  
    return JSON.stringify({
        user: {
            username:     acc.user.username,
            newUsername:  acc.user.newUsername?     acc.user.newUsername.trim()       : null,
            password:     acc.user.password?        acc.user.password.trim()          : null,
            newPassword:  acc.user.newPassword?     acc.user.newPassword.trim()       : null,
            newAvatar:    acc.user.newAvatar? await  fileToBase64(acc.user.newAvatar)             : null
        },
        trader: {
            newBusinessName: acc.trader.newBusinessName? acc.trader.newBusinessName.trim()   : null,
            newVATCategory:  acc.trader.newVATCategory?  acc.trader.newVATCategory.trim()    : null,
        }
    });
}