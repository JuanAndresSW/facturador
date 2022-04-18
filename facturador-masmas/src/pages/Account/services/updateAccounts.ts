import ajax from 'interceptors/ajax';
import editedAccount from '../models/editedAccount';
import adaptAccount from '../adapters/adaptAccount';
import getToken from 'services/getToken';

export async function updateMainAccount(account: editedAccount, callback:Function): Promise<void> {
    ajax("PUT", "mainaccounts", 
    { body: await adaptAccount(account), token: getToken("access") }, 
    (status: number, data: string)=>respond(status, data, callback));
}

export async function updateBranchAccount(account: editedAccount, callback:Function) {
    ajax("PUT", "branchaccounts", 
    { body: await adaptAccount(account), token: getToken("access") }, 
    (status: number, data: string)=>respond(status, data, callback));
}

function respond(status: number, data: string, callback: Function): void {
    if (status === 200) {
        localStorage.removeItem('avatar');
        callback(true);
    }
    else callback(false, data);
}