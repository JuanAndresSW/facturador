import ajax from 'interceptors/ajax';
import setSession from 'services/setSession';
/** Trata de iniciar sesión con los credenciales proporcionados.*/
export default function tryLogin(usernameOrEmail, password, callback) {
    ajax("POST", "auth/login", {
        body: JSON.stringify({
            usernameOrEmail: usernameOrEmail.trim(),
            password: password.trim(),
        })
    }, handleResponse);
    function handleResponse(httpState, content) {
        if (httpState === 200) {
            localStorage.clear();
            setSession(content);
            callback(true);
            window.location.reload();
            return;
        }
        if (httpState === 404) {
            callback(false, "Usuario o contraseña incorrecta");
            return;
        }
        callback(false, content);
    }
}
