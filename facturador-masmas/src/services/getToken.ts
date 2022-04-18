export default function getToken(token:"access"|"refresh"): string {
    const cookieArray = decodeURIComponent(document.cookie).split("; ");
    return cookieArray[0].substring(`${token}Token=`.length);
}