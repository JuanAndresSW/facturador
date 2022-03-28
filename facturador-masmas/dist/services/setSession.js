import adaptSession from 'adapters/adaptSession';
/**Establece los valores obtenidos de una solicitud de inicio de sesión exitosa.*/
export default function setSession(json) {
    var session = adaptSession(json);
    if (session.accessToken)
        document.cookie = "accessToken=".concat(session.accessToken, "; max-age=1209600; path=/; Secure");
    if (session.refreshToken)
        document.cookie = "refreshToken=".concat(session.refreshToken, "; max-age=1209600; path=/; Secure");
    if (session.username)
        sessionStorage.setItem("username", session.username);
    if (session.role)
        sessionStorage.setItem("role", session.role);
    if (session.actives !== undefined)
        sessionStorage.setItem("actives", session.actives);
    if (session.passives !== undefined)
        sessionStorage.setItem("passives", session.passives);
}
