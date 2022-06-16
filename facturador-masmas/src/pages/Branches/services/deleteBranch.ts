import ajax from 'interceptors/ajax';
import getToken from 'services/getToken';

export default async function deleteBranch(ID:number, callback: Function): Promise<void> {
    ajax('DELETE', 'branches/'+ID, {token: getToken('access')}, handle);

    function handle(status: number, content: string) {
        if (status === 200) callback(true, 'Se ha eliminado la instalación');
        else callback(false, content);
    }
}