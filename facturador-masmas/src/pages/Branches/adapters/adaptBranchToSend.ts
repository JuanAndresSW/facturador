import { fileToBase64 } from 'utilities/conversions';
import branch from '../models/branch';

export default async function adaptBranchToSend(branch: branch): Promise<string> {
    console.log("ID is: "+sessionStorage.getItem('IDTrader'));
    return JSON.stringify({
        IDTrader: sessionStorage.getItem('IDTrader'),
        name: branch.name,
        email: branch.email,
        phone: branch.phone,
        address: {
            province: branch.address.province,
            department: branch.address.department,
            locality: branch.address.locality,
            postalCode: branch.address.postalCode,
            street: branch.address.street,
            numberAddress: branch.address.addressNumber,
        },
        photo: await fileToBase64(branch.photo),
        logo: await fileToBase64(branch.logo),
        color: branch.color,
    })
}