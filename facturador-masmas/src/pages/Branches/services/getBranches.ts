import Response from 'models/Response';
import ajax from 'ports/ajax';

const baseUrl = 'branches/trader/'+sessionStorage.getItem('IDTrader')+'?index=';

/**Recupera la lista de sucursales de un comerciante.
 * @param index - La página de la lista.
 * @param sort  - El campo en base al cual ordenar la lista.
 * @param order - El modo de ordenar, ascendente o descendente.
*/
export default async function getBranches(index:number, sort: ("createdAt"|"name"|"street"),
order:("asc"|"desc")): Promise<Response> {

    const response = await ajax('GET', baseUrl+`${index}&size=10&sort=${sort}&order=${order}`, true);

    if (response.ok) return {...response, content: JSON.parse(response.content)}
    return response;
}