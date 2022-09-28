/**La respuesta a la llamada de un servicio. */
export default class Response {
    public ok:      boolean;
    public message: string;
    public content: any;
    public status:  number;

    public constructor(message: string, content: any = null, status: number = 0, ok: boolean = false) {
        this.ok = ok;
        this.message = message;
        this.content = content;
    }
}