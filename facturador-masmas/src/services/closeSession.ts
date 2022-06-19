/** Forza la expiración de los tokens de sesión. Elimina la memoria local. Recarga la ubicación actual al finalizar.*/
export default function closeSession(): void {
    for (let cookie of document.cookie.split(";")) {
      document.cookie = cookie + "=; Secure; path=/; expires=" + new Date(0).toUTCString();
    }
    localStorage.clear();
    window.location.reload();
}