import fetch from "services/fetch";
/**
* Trata de iniciar sesión con los datos proporcionados.
* @param name Nombre o email.
* @param password Contraseña.
*/
export default function login(name, password, callback) {
    fetch("login", JSON.stringify({
        name: name,
        password: password,
    }), callback);
}
