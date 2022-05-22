import ajax from 'interceptors/ajax';
import getToken from 'services/getToken';

/**Retrieves the branch's logo, as a string. */

export default function getBranchLogo(id: number, callback:Function) {
    ajax("GET",id,{token: getToken('access')}, respond);

    function respond(status:number, content:string) {
        if (status === 200) callback(true, content);
        else callback(false, "Error");
    }   
}

