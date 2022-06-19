import ajax from 'ports/ajax';
import getToken from 'services/getToken';
import branch from '../models/branch';
import adaptUpdatedBranchToSend from '../adapters/adaptUpdatedBranchToSend';

/**Envía los datos de una sucursal con uno o más datos actualizados.
 * @param IDBranch - Identificador de la sucursal a actualizar.
 * @param updatedBranch - Objeto de sucursal actualizada.
 * @param callback  - La función que procesará la respuesta.
*/
export default async function updateBranch(IDBranch:number, updatedBranch: branch,  callback: Function): Promise<void> {
    ajax('PUT', "branches/"+IDBranch, {token: getToken('access'), body: await adaptUpdatedBranchToSend(updatedBranch)}, handle);

    function handle(status: number, content: string) {
        if (status === 204) callback(true, "La sucursal fue actualizada correctamente");
        else callback(false, content);
    }
}