type mainAccount = {
    user: {
        username: string;
        email: string;
        password: string;
        avatar: File;
    };
    trader: {
        businessName: string;
        vatCategory: string;
        code: string;
        grossIncome: string;
    };
}
export default mainAccount;