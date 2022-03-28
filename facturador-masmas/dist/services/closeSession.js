/** Forza la expiración de los tokens de sesión. Recarga la ubicación actual al finalizar.*/
export default function closeSession() {
    for (var _i = 0, _a = document.cookie.split(";"); _i < _a.length; _i++) {
        var cookie = _a[_i];
        document.cookie = cookie + "=; Secure; path=/; expires=" + new Date(0).toUTCString();
    }
    localStorage.clear();
    window.location.reload(); //SHOULD STORE OLD TOKENS FOR SECURITY
}
