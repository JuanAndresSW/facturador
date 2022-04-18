type mainAccount = {
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
export default mainAccount;