export default function getToken(token) {
    var cookieArray = decodeURIComponent(document.cookie).split("; ");
    return cookieArray[0].substring("".concat(token, "Token=").length);
}
