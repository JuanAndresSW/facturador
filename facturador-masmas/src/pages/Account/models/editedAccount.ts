type editedAccount = {
    user: {
        username:     string,
        newUsername:  string,
        password:     string,
        newPassword:  string,
        newAvatar:    File,
    },
    trader: {
        newBusinessName: string,
        newVATCategory:  string,
    }
};
export default editedAccount;