import { fileToBase64 } from 'utilities/conversions';
import branch from '../models/branch';

/**Adapta un objeto de sucursal actualizada a un formato esperado por la API. */
export default async function updatedBranchToJson(branch: branch): Promise<string> {
    return JSON.stringify({
        updatedName:          branch.name,
        updatedEmail:         branch.email,
        updatedPhone:         branch.phone,
        updatedProvince:      branch.address.province,
        updatedCity:          branch.address.city,
        updatedPostalCode:    branch.address.postalCode,
        updatedStreet:        branch.address.street,
        updatedAddressNumber: branch.address.addressNumber ?? "S/N",
        updatedPhoto:         await fileToBase64(branch.photo),
        updatedLogo:             await fileToBase64(branch.logo),
        updatedPreferenceColor:  branch.preferenceColor,
    })
}