import { base64ToBlob   } from 'utilities/conversions';
import branch from '../models/branch';
import listOfBranches from "../models/listOfBranches";

/**Adapta un json de lista de sucursales a un objeto de lista de sucursales. */
export default async function jsonToListOfBranches(json: string): Promise<listOfBranches> {
    const listOfBranches = JSON.parse(json);

    return {
        branches: await Promise.all(listOfBranches.content.map(async (branch: any): Promise<branch> => { return {
            ID:    branch.branchId,
            name:  branch.fantasyName,
            email: branch.email,
            phone: branch.phone,
            address: {
                province:      branch.province,
                department:    branch.department,
                locality:      branch.locality,
                postalCode:    branch.postalCode,
                street:        branch.street,
                addressNumber: branch.addressNumber,
            },
            photo:  await base64ToBlob(branch.photo),
            preferenceColor:  branch.preferenceColor
        }})),
        totalPages: listOfBranches.totalPages,
        last: listOfBranches.last
    }
}