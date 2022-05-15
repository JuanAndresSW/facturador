import ajax from 'interceptors/ajax';
import getToken from 'services/getToken';

const baseUrl = 'branches/trader/'+sessionStorage.getItem('IDTrader')+'?index=';

export default function getBranches( callback: Function): void {
    ajax('GET', baseUrl+'0&size=10&sort=dateOfCreate&order=asc', {token: getToken('access')}, handle);

    function handle(status: number, content: string) {
        if (status === 200) callback(true, JSON.parse(content));
        else callback(false, content);
    }
}