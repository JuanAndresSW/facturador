import ajax from 'ports/ajax';
import setSession from 'services/setSession';

/** Trata de iniciar sesión con los credenciales proporcionados.*/
export default function tryLogin(usernameOrEmail: string, password: string, callback: Function): void {
    ajax("POST", "auth/accounts/log-in", 
    {
      body: JSON.stringify({
        usernameOrEmail: usernameOrEmail.trim(),
        password:        password.trim(),
      })
    }, 
    handleResponse);

    function handleResponse(httpStatus: number, content: string): void {
      if (httpStatus === 200) {
        localStorage.clear();
        setSession(content);
        callback(true);
        return window.location.reload();
      }
      if (httpStatus >= 404)
      return callback(false, "Usuario o contraseña incorrecta");
    }
}