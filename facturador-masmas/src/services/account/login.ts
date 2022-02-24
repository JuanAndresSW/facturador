import fetch from "services/fetch"

/**
  * Trata de iniciar sesión con los datos proporcionados.
  * @param name       - Nombre o email.
  * @param password   - Contraseña.
  */
export default function login(name: string, password: string, callback: Function): void {
  fetch("auth/login", {
    body: JSON.stringify({
      usernameOrEmail: name.trim(),
      password: password.trim(),
    })
  }, callback);
}