/**Una cuenta de usuario con uno o más datos actualizados. */
type editedAccount = {
    user: {
        username:     string,
        updatedUsername:  string,
        password:     string,
        updatedPassword:  string,
        updatedAvatar:    File,
    },
    trader: {
        updatedBusinessName: string,
        updatedVATCategory:  string,
    }
};
export default editedAccount;