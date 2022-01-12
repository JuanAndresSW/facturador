export function isUserAuthenticated() {
    if (getCookie('username') === 'niño') {
        return true;
    }
    return false;
}
export function setCookie(name) {
    document.cookie = "username=".concat(name, "; max-age=1209600; path=/; Secure");
}
export function getCookie(key) {
    key += '=';
    var cookieArray = decodeURIComponent(document.cookie).split('; ');
    try {
        return cookieArray[0].substring(key.length);
    }
    catch (NullPointerException) {
        return '';
    }
}
export function clearCookies() {
    var cookies = document.cookie.split(';');
    for (var _i = 0, cookies_1 = cookies; _i < cookies_1.length; _i++) {
        var cookie = cookies_1[_i];
        document.cookie = cookie + "=;expires=" + new Date(0).toUTCString();
    }
}
