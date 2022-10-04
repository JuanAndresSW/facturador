import Response from 'models/Response';
import ajax from 'ports/ajax';
import { IDTrader } from 'utilities/constants';
import jsonToListOfBranches from '../adapters/jsonToListOfBranches';

const baseUrl = 'branches/traders/'+IDTrader+'?index=';
type sort = ("createdAt"|"name"|"street");
type order= ("asc"|"desc");

/**Recupera la lista de sucursales de un comerciante.
 * @param index - La página de la lista.
 * @param sort  - El campo en base al cual ordenar la lista.
 * @param order - El modo de ordenar, ascendente o descendente.
*/
export default async function getBranches(index:number, sort:sort, order:order): Promise<Response> {

    const response = await ajax('GET', baseUrl+`${index}&size=10&sort=${sort}&order=${order}`, true);

    if (response.ok) return {...response, content: await jsonToListOfBranches(response.content)}
    return {...response, content: null};
}