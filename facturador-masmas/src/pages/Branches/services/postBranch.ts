import ajax from 'interceptors/ajax';
import branch from '../models/branch';
import adaptBranchToSend from '../adapters/adaptBranchToSend';
import getToken from 'services/getToken';

export default async function postBranch(branch: branch, callback: Function): Promise<void> {
    ajax('POST', 'branches', {body: await adaptBranchToSend(branch), token: getToken('access')}, handle);

    function handle(status: number, content: string) {
        if (status === 201) callback(true, 'Se ha creado la instalación');
        else callback(false, content);
    }
}