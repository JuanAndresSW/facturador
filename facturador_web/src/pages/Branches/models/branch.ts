/**Objeto con datos de una sucursal. */
type branch = {
    ID?: number,
    name: string,
    email: string,
    phone: string,
    address: {
        province: string,
        city: string,
        postalCode: string,
        street: string,
        addressNumber: number,
    },
    logo?: Blob,
    photo: Blob,
    preferenceColor: string,
};
export default branch;