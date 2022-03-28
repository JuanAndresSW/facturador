import mainAccount from '../models/mainAccount';
import ajax from 'interceptors/ajax';
import setSession from 'services/setSession';


export default async function signup(account: mainAccount, callback: Function): Promise<void> {
    ajax("POST", "mainaccounts", { body: JSON.stringify(account) }, respond);

    function respond(state: number, data:string) {
        if (state === 201) {
            setSession({
              ...JSON.parse(data),
              username: account.user.username,
              role: 'MAIN',
              active: '0',
              pasive: '0'
            })
            callback(true);
            window.location.reload();
        } else callback(false, data);
    }
} 
