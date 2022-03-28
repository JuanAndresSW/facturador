import traderData from '../models/traderData';

export default function adaptSession(json: string): traderData {
    const traderData = JSON.parse(json);
    return {
        businessName: traderData.businessName,
        vatCategory: traderData.vatCategory,
        uniqueKey: traderData.uniqueKey
    }
}