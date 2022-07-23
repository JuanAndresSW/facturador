import ajax from 'ports/ajax';
import getToken from 'services/getToken';

let url = "pointsofsale/?/trader/?"

/**Elimina el punto de venta especificado por IDPoint. */
export default async function deletePointOfSale(IDPoint:number, callback: Function): Promise<void> {
    ajax('DELETE', `pointsofsale/${IDPoint}/trader/${sessionStorage.getItem("IDTrader")}`,
    {token: getToken('access')}, handle);

    function handle(status: number) {
        if (status === 204) callback(true, 'Se ha eliminado el punto de venta');
        else callback(false, "Error al eliminar el punto de venta");
    }
}