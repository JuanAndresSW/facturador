import ajax from 'interceptors/ajax';
import getToken from 'services/getToken';

export default function postPointOfSale(callback: Function): void {
    ajax('POST', 'pointsofsale', {token: getToken('access')}, handle);

    function handle(status: number, content: string) {
        if (status === 201) callback(true, 'Se ha creado el punto de venta');
        else callback(false, content);
    }
}