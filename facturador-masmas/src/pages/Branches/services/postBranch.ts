import ajax from 'ports/ajax';
import branch from '../models/branch';
import adaptBranchToSend from '../adapters/adaptBranchToSend';
import getToken from 'services/getToken';

/**Envía una sucursal para ser creada.
 * @param branch    - La sucursal a ser creada.
 * @param callback  - La función que procesará la respuesta.
*/
export default async function postBranch(branch: branch, callback: Function): Promise<void> {
    ajax('POST', 'branches', {body: await adaptBranchToSend(branch), token: getToken('access')}, handle);

    function handle(status: number, content: string) {
        if (status === 201) callback(true, 'Se ha creado la instalación');
        else callback(false, content);
    }
}