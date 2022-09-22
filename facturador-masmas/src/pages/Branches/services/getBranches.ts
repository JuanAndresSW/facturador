import ajax from 'ports/ajax';
import getToken from 'services/getToken';

const baseUrl = 'branches/trader/'+sessionStorage.getItem('IDTrader')+'?index=';

/**Recupera la lista de sucursales de un comerciante.
 * @param index - La página de la lista.
 * @param sort  - El campo en base al cual ordenar la lista.
 * @param order - El modo de ordenar, ascendente o descendente.
 * @param callback  - La función que procesará la respuesta. El argumento es un boolean (ok) y un objeto branches.
*/
export default function getBranches(index:number, sort: ("createdAt"|"name"|"street"),
order:("asc"|"desc"), callback: Function): void {

    ajax('GET', baseUrl+`${index}&size=10&sort=${sort}&order=${order}`,
    {token: getToken('access')}, handle);

    function handle(status: number, content: string) {
        if (status === 200) callback(true, JSON.parse(content));
        else callback(false, content);
    }
}