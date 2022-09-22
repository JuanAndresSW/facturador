import getToken from "../../../services/getToken";
import ajax from 'ports/ajax';
import jsonToTraderData from '../adapters/jsonToTraderData';

/**Recupera información del comerciante de la cuenta.*/
export default function getTraderData(callback: Function): void {
    ajax("GET", `accounts/${sessionStorage.getItem("username")}`, 
    { token: getToken("access") }, respond);

    function respond(status: number, data: string) {
        if (status===200) callback(true, jsonToTraderData(data));
        else              callback(false, data);
    }
}