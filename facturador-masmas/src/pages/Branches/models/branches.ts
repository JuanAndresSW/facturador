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
        numberAddress: string,
        preferenceColor: string,
        dateOfCreate: string,
        photo: string
    }],
    
    page: number,
    size: number,
    totalElements: number,
    totalPages: number,
    last: boolean
};
export default branches;

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
    numberAddress: string,
    preferenceColor: string,
    dateOfCreate: string,
    photo: string
}