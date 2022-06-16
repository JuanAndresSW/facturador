import mainAccount from '../models/mainAccount';
import {fileToBase64, toFormattedCUIT} from 'utilities/conversions';

export default async function   adaptMainAccount(account: mainAccount): Promise<string> {
    return JSON.stringify({
        user: {
          username: account.user.username.trim(),
          email: account.user.email.trim(),
          password: account.user.password.trim(),
          avatar: "" + await fileToBase64(account.user.avatar),
        },
        trader: {
          businessName: account.trader.businessName.trim(),
          vatCategory: account.trader.VATCategory.trim(),
          code: toFormattedCUIT(account.trader.CUIT),
        }
    });
}