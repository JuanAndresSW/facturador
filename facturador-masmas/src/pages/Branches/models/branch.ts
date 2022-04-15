type branch = {
    IDTrader: string,
    name: string,
    address: {
        province: string,
        department: string,
        locality: string,
        postalCode: string,
        street: string,
        addressNumber: number,
    },
    email: string,
    phone: string,
    logo: File,
    photo: File,
    color: string,
};
export default branch;