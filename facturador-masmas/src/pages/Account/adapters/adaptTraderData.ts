import traderData from '../models/traderData';

/**Adapta los datos recibidos del comerciante a un formato esperado por el front-end. */
export default function adaptTraderData(json: string): traderData {
    const traderData = JSON.parse(json);
    return {
        businessName:   traderData.businessName,
        VATCategory:    traderData.vatCategory,
        CUIT:           traderData.cuit
    }
}