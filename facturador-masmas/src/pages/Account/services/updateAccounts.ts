import ajax from 'interceptors/ajax';
import editedAccount from '../models/editedAccount';
import adaptAccount from '../adapters/adaptAccount';
import getToken from 'services/getToken';

export async function updateMainAccount(account: editedAccount, callback:Function): Promise<void> {
    ajax("PUT", "mainaccounts", 
    { body: await adaptAccount(account), token: getToken("access") }, 
    (state: number, data: string)=>respond(state, data, callback));
}

export async function updateBranchAccount(account: editedAccount, callback:Function) {
    ajax("PUT", "auth/branchaccounts", 
    { body: await adaptAccount(account), token: getToken("access") }, 
    (state: number, data: string)=>respond(state, data, callback));
}

function respond(state: number, data: string, callback: Function): void {
    if (state === 200) {
        localStorage.removeItem('avatar');
        callback(true);
    }
    else callback(false, data);
}