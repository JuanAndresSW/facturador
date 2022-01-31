import { account, pointOfSale } from "./Types";

/**
 * Aplica el formato necesario a los datos antes de enviarlos al servidor
 */

export default class Format {
  public static account(account:account): string {
    const data = JSON.stringify({
      user: {
        username: account.user.username,
        email: account.user.email,
        password: account.user.password,
      },
      trader: {
        businessName: account.trader.businessName,
        vatCategory: account.trader.vatCategory,
        code: account.trader.code,
        grossIncome: account.trader.grossIncome,
      }
    });
    return data;
  }
  public static pointOfSale(pointOfSale:pointOfSale):string {
    return JSON.stringify({
      name: pointOfSale.name,
      address: pointOfSale.address,
      locality: pointOfSale.locality,
      postalCode: pointOfSale.postalCode,
      email: pointOfSale.email,
      phone: pointOfSale.phone,
      website: pointOfSale.website,
      color: pointOfSale.color,
    },)
  }
}
