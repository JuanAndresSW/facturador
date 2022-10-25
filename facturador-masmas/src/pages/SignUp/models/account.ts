/**Una cuenta de usuario. */
type account = {
    user: {
        username: string;
        email: string;
        password: string;
        avatar: File;
    };
    trader: {
        businessName: string;
        VATCategory: string;
        CUIT: string;
    };
}
export default account;