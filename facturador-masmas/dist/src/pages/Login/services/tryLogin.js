import fetch from 'interceptors/fetch';
/** Trata de iniciar sesión con los credenciales proporcionados.*/
export default function tryLogin(usernameOrEmail, password, callback) {
    fetch("POST", "auth/login", {
        body: JSON.stringify({
            usernameOrEmail: usernameOrEmail.trim(),
            password: password.trim(),
        })
    }, handleResponse);
    function handleResponse(httpState, content) {
        if (httpState === 200) {
            localStorage.clear();
            setSession(JSON.parse(content));
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
