import Response from 'models/Response';
import ajax from 'ports/ajax';

/**Recupera el logo de la sucursal especificada, en base 64. */
export default async function getBranchLogo(IDBranch: number): Promise<Response> {
    return await ajax("GET","branches/"+IDBranch+"/get-logo", true);
}