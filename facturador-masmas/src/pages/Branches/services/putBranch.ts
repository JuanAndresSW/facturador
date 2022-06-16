import ajax from 'interceptors/ajax';
import getToken from 'services/getToken';
import branch from '../models/branch';
import adaptUpdatedBranchToSend from '../adapters/adaptUpdatedBranchToSend';


export default async function updateBranch(id:number, updatedBranch: branch,  callback: Function): Promise<void> {
    ajax('PUT', "branches/"+id, {token: getToken('access'), body: await adaptUpdatedBranchToSend(updatedBranch)}, handle);

    function handle(status: number, content: string) {
        if (status === 200) callback(true, "La sucursal fue actualizada correctamente");
        else callback(false, content);
    }
}