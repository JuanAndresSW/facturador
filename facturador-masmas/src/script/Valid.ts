export default class Valid {
    public static loginPassword(password:string):boolean {
        return (password.length <= 128 && password.length >= 8)
    }
    public static loginUser(user:string):boolean {
        return (user.length <= 324 && user.length >= 3)
    }
    
}