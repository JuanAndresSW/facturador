export function isUserAuthenticated():boolean {
    if (getCookie('username') === 'niño') {
        return true
    } return false
}
export function setCookie(name:string):void {
    document.cookie = `username=${name}; max-age=1209600; path=/; Secure`;
}
export function getCookie(key:string):string {
    key += '=';
    const cookieArray = decodeURIComponent(document.cookie).split('; ');
    try {return cookieArray[0].substring(key.length);}
    catch (NullPointerException) {return '';}
}
export function clearCookies():void {
    const cookies = document.cookie.split(';');
    for (let cookie of cookies) {
        document.cookie = cookie + "=;expires="+ new Date(0).toUTCString();
    }
}