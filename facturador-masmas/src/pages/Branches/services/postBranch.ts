import ajax from 'ports/ajax';
import branch from '../models/branch';
import branchToJson from '../adapters/branchToJson';
import Response from 'models/Response';

/**Envía una sucursal para ser creada.
 * @param branch    - La sucursal a ser creada.
*/
export default async function postBranch(branch: branch): Promise<Response> {
    return await ajax('POST', 'branches', true, await branchToJson(branch));
}