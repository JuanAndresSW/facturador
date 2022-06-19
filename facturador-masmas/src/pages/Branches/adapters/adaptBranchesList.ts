import branches from '../models/branches';

/**Adapta un objeto de sucursal a un formato esperado por el servidor para crear un nueva sucursal. */
export default async function adaptBranchToSend(json: string): Promise<branches> {
    return {
        content: [{
            IDBranch: number,
            name: string,
            email: string,
            phone: string,
            province: string,
            department: string,
            locality: string,
            postalCode: string,
            street: string,
            addressNumber: string,
            preferenceColor: string,
            dateOfCreation: string,
            photo: string
        }],
        
        page: number,
        size: number,
        totalElements: number,
        totalPages: number,
        last: boolean
    }
}