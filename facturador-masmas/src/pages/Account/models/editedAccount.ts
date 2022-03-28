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
        newVatCategory:  string,
        newCode:         string,
    }
};
export default editedAccount;