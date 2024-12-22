import Response from 'models/Response';
import ajax from 'ports/ajax';
import setSession from 'services/setSession';

/** Trata de iniciar sesión con los credenciales proporcionados.*/
export default async function tryLogin(usernameOrEmail: string, password: string): Promise<Response> {
  const response = await ajax("POST", "auth/accounts/log-in", false, 
  JSON.stringify({
    usernameOrEmail: usernameOrEmail.trim(),
    password:        password.trim(),
  }))

  if (response.status === 200) {
    setSession(response.content);
    
    window.location.reload();
  }
  if (response.status === 400)
  return {...response, message: "Usuario o contraseña incorrecta"};

  return response;
  
}