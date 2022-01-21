/**
 * specific-use server gateway for managing session
 */

export default class Session {
 
    //request start of session to server and get session key
    public static open (session:string) {
        //document.cookie = `session=${session}; max-age=1209600; path=/; Secure`;
        document.cookie = `username=${session}; max-age=1209600; path=/; Secure`;
    }
    //check for the existance of valid session key in cookies
    public static isAuthenticated():boolean {
        if (this.getUsername() === 'niño') {
            return true
        } return false
    }
    //get the value of username cookie
    public static getUsername():string {
        const cookieArray = decodeURIComponent(document.cookie).split('; ');
        try {return cookieArray[0].substring("username=".length);}
        catch (NullPointerException) {return '';}
    }
    //delete server and local side key session
    public static close():void {
        const cookies = document.cookie.split(';');
        for (let cookie of cookies) {
            document.cookie = cookie + "=;expires="+ new Date(0).toUTCString();
        }
    }
    //set session key expire date to initial value
    public static renew():void {
        document.cookie = `username=${this.getUsername()}; max-age=1209600; path=/; Secure`;
    }
}