import adaptSession from 'adapters/adaptSession';
import session from 'models/session';

/**Establece en memoria local y cookies los valores obtenidos de una solicitud de inicio de sesión exitosa.*/
export default function setSession(json:string): void {
    const session: session = adaptSession(json);

    if (session.accessToken  !== undefined)
    document.cookie = `accessToken=${session.accessToken}; max-age=1209600; path=/; Secure`;

    if (session.refreshToken !== undefined)
    document.cookie = `refreshToken=${session.refreshToken}; max-age=1209600; path=/; Secure`;

    if (session.username     !== undefined)
    sessionStorage.setItem("username", session.username);

    if (session.actives      !== undefined)
    sessionStorage.setItem("actives", session.actives);

    if (session.passives     !== undefined)
    sessionStorage.setItem("passives", session.passives);
    
    if (session.IDTrader     !== undefined)                
    sessionStorage.setItem("IDTrader", session.IDTrader);
}