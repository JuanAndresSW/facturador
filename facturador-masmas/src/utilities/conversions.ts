//Métodos de conversión y formateo.

/**Devuelve un string base 64 a partir de un valor tipo File. */
export const fileToBase64 = (file: Blob) => new Promise<string>((resolve, reject): void => {
    const reader = new FileReader();
    if (!file) return resolve(undefined);
    reader.onerror = reject;
    reader.onload = () => resolve(reader.result+'');
    reader.readAsDataURL(file);
});


/**Devuelve un Blob a partir de un string en base 64.*/
export async function base64ToBlob(base64String:string): Promise<Blob> {
    const base64Response = await window.fetch(base64String);
    return await base64Response.blob();
}


/**Convierte un string de CUIT al formato AA-BB.BBB.BBB-A. */
export const toFormattedCUIT = (code:string) => {
    const c = code.replace(/ |\.|-/g, "");

    return c? c.slice(0, 2) + '-' +
    c.slice(2, 4) + '.' + c.slice(4, 7) + '.' + c.slice(7, 10) +
    '-' + c.charAt(10) : '';
}

export function toFourDigitNumber(number: number): string {
    switch(number.toString().length) {
        case 1: return "000"+number;
        case 2: return "00"+number;
        case 3: return "0"+number;
        default: return number.toString().slice(0,number.toString().length-4); 
    }
}