import fetch from 'api/fetch';

export default class userAvatar {
    /**
     * Recupera un array de grupos a nombre de la cuenta solicitante.
     * Un id puede ser proporcionado en el URL para filtrar la respuesta.
     * @param callback La función que procesará la respuesta. 
     */
    public static getAvatar(callback: Function): void {
        callback(0, "")
    }
}