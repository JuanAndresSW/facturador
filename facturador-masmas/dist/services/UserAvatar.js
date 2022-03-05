var userAvatar = /** @class */ (function () {
    function userAvatar() {
    }
    /**
     * Recupera un array de grupos a nombre de la cuenta solicitante.
     * Un id puede ser proporcionado en el URL para filtrar la respuesta.
     * @param callback La función que procesará la respuesta.
     */
    userAvatar.getAvatar = function (callback) {
        callback(0, "");
    };
    return userAvatar;
}());
export default userAvatar;
