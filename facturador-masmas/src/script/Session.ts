class SessionClass {
    name: string;
    constructor() {}
    open (name:string) {
        document.cookie = `username=${name}; max-age=1209600; path=/; Secure`;
        this.name = name;
    }
    isAuthenticated():boolean {
        if (this.getByKey('username') === 'niño') {
            return true
        } return false
    }
    getByKey(key:string):string {
        key += '=';
        const cookieArray = decodeURIComponent(document.cookie).split('; ');
        try {return cookieArray[0].substring(key.length);}
        catch (NullPointerException) {return '';}
    }
    close():void {
        const cookies = document.cookie.split(';');
        for (let cookie of cookies) {
            document.cookie = cookie + "=;expires="+ new Date(0).toUTCString();
        }
    }
    renew():void {
        document.cookie = `username=${this.name}; max-age=1209600; path=/; Secure`;
    }
}
const Session = new SessionClass();
export default Session;