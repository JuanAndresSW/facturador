import ajax from 'ports/ajax';
import getToken from 'services/getToken';

/**Recupera el logo de la sucursal especificada, en base 64. */
export default function getBranchLogo(IDBranch: number, callback:Function) {
    ajax("GET","branches/"+IDBranch,{token: getToken('access')}, respond);

    function respond(status:number, content:string) {
        if (status === 200) callback(true, content);
        else callback(false, "Error");
    }   
}

