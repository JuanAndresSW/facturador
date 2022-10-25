import ajax from 'ports/ajax';
import branch from '../models/branch';
import updatedBranchToJson from '../adapters/updatedBranchToJson';
import Response from 'models/Response';

/**Envía los datos de una sucursal con uno o más datos actualizados.
 * @param IDBranch      - Identificador de la sucursal a actualizar.
 * @param updatedBranch - Objeto de sucursal actualizada.
*/
export default async function updateBranch(IDBranch:number, updatedBranch: branch): Promise<Response> {
    return await ajax('PUT', "branches/"+IDBranch, true, await updatedBranchToJson(updatedBranch));
}