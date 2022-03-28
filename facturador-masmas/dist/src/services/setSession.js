/**Establece los valores obtenidos de una solicitud de inicio de sesión exitosa.*/
export default function setSession(any) {
    if (session.accessToken)
        document.cookie = "accessToken=".concat(session.accessToken, "; max-age=1209600; path=/; Secure");
    if (session.refreshToken)
        document.cookie = "refreshToken=".concat(session.refreshToken, "; max-age=1209600; path=/; Secure");
    if (session.username)
        sessionStorage.setItem("username", session.username);
    if (session.role)
        sessionStorage.setItem("role", session.role);
    if (session.active !== undefined)
        sessionStorage.setItem("actives", session.active);
    if (session.pasive !== undefined)
        sessionStorage.setItem("passives", session.pasive);
}
