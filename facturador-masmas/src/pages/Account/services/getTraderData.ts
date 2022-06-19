import getToken from "../../../services/getToken";
import ajax from 'ports/ajax';
import adaptTraderData from '../adapters/adaptTraderData';

/**Recupera información del comerciante de la cuenta.*/
export default function getTraderData(callback: Function): void {
    ajax("GET", `accounts/${sessionStorage.getItem("username")}`, 
    { token: getToken("access") }, respond);

    function respond(status: number, data: string) {
        if (status===200) callback(true, adaptTraderData(data));
        else              callback(false, data);
    }
}