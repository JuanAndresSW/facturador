import { fileToBase64 } from 'utilities/conversions';
import branch from '../models/branch';

export default async function adaptUpdatedBranchToSend(branch: branch): Promise<string> {
    return JSON.stringify({
        updatedName:  branch.name,
        updatedEmail: branch.email,
        updatedPhone: branch.phone,
            updatedProvince:      branch.address.province,
            updatedDepartment:    branch.address.department,
            updatedLocality:      branch.address.locality,
            updatedPostalCode:    branch.address.postalCode,
            updatedStreet:        branch.address.street,
            updatedNumberAddress: branch.address.addressNumber,
        updatedPhoto:  await fileToBase64(branch.photo),
        updatedLogo:   await fileToBase64(branch.logo),
        updatedColor:  branch.color,
    })
}