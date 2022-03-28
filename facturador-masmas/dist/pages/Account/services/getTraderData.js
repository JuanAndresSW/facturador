import getToken from "../../../services/getToken";
import ajax from 'interceptors/ajax';
import adaptTraderData from '../adapters/adaptTraderData';
export default function getTraderData(callback) {
    ajax("GET", "mainaccounts/".concat(sessionStorage.getItem("username")), { token: getToken("access") }, respond);
    function respond(state, data) {
        if (state === 200)
            callback(true, adaptTraderData(data));
        else
            callback(false, data);
    }
}
