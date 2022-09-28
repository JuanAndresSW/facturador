import Response from 'models/Response';
import ajax from 'ports/ajax';
import {IDTrader} from 'utilities/constants';

/**Elimina el punto de venta especificado por IDPoint. */
export default async function deletePointOfSale(IDPoint:number): Promise<Response> {
    const response = await ajax('DELETE', `pointsofsale/${IDPoint}/trader/${IDTrader}`, true);
    return {...response, message: response.status === 204? "Se ha eliminado el punto de venta" : "Error al eliminar el punto de venta"};
}