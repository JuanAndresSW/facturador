import getToken from "../../../services/getToken";
import Response from "models/Response";
import ajax from 'ports/ajax';
import jsonToTraderData from '../adapters/jsonToTraderData';

/**Recupera información del comerciante de la cuenta.*/
export default async function getTraderData(): Promise<Response> {
    const response = await ajax("GET", `accounts/${sessionStorage.getItem("username")}`, true);

    if (response.status===200) return {...response, content: jsonToTraderData(response.content)}
    return response;
 
}