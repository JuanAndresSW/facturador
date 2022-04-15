import adaptSession from 'adapters/adaptSession';
import session from 'models/session';

/**Establece los valores obtenidos de una solicitud de inicio de sesión exitosa.*/
export default function setSession(json:string): void {
    const session: session = adaptSession(json);

    if (session.accessToken)  document.cookie = `accessToken=${session.accessToken}; max-age=1209600; path=/; Secure`;
    if (session.refreshToken) document.cookie = `refreshToken=${session.refreshToken}; max-age=1209600; path=/; Secure`;
    if (session.username)     sessionStorage.setItem("username", session.username);
    if (session.role)         sessionStorage.setItem("role", session.role);
    if (session.actives !== undefined)   sessionStorage.setItem("actives", session.actives);
    if (session.passives !== undefined)  sessionStorage.setItem("passives", session.passives);
    if (session.IDTrader) sessionStorage.setItem("IDTrader", session.IDTrader);
}