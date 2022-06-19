/**Objeto con una lista de sucursales e información de la lista. */
type branches = {
    content: [{
        branchId: number,
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
        createdAt: string,
        photo: string
    }],
    page: number,
    size: number,
    totalElements: number,
    totalPages: number,
    last: boolean
};
export default branches;

/**Sucursal con los datos en el formato recuperado de una lista de sucursales. */
export type branchesContent = {
    branchId: number,
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
    createdAt: string,
    photo: string
}