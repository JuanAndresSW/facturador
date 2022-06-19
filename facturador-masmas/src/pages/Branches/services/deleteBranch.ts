import ajax from 'ports/ajax';
import getToken from 'services/getToken';

/**Elimina la sucursal especificada por IDBranch. */
export default async function deleteBranch(IDBranch:number, callback: Function): Promise<void> {
    ajax('DELETE', 'branches/'+IDBranch, {token: getToken('access')}, handle);

    function handle(status: number, content: string) {
        if (status === 204) callback(true, 'Se ha eliminado la instalación');
        else callback(false, content);
    }
}