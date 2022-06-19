/**Objeto con datos de una sucursal. */
type branch = {
    name: string,
    email: string,
    phone: string,
    address: {
        province: string,
        department: string,
        locality: string,
        postalCode: string,
        street: string,
        addressNumber: number,
    },
    logo: File,
    photo: File,
    preferenceColor: string,
};
export default branch;