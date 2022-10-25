import Response from 'models/Response';
import ajax from 'ports/ajax';

/**Elimina la sucursal especificada por IDBranch. */
export default async function deleteBranch(IDBranch:number, callback: Function): Promise<Response> {
    return await ajax('DELETE', 'branches/'+IDBranch, true);
}