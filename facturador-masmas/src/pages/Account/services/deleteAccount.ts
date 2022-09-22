import ajax from 'ports/ajax';
import getToken from 'services/getToken';

/**Solicita la eliminación de la cuenta.*/
export default function deleteAccount(deletionCode:string, callback:Function): void {
    ajax("DELETE", `accounts/${sessionStorage.getItem("username")}`, { token: getToken('access') }, respond);
    function respond(status:number, data:string) {
        if (status !== 204) return callback(false, data);
        localStorage.clear();
        sessionStorage.clear();
        callback(true); 
    }
}