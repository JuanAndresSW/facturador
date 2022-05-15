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
    color: string,
};
export default branch;