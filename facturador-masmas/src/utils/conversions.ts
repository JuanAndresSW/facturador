//Métodos de conversión y formateo.

/**Devuelve un string base 64 a partir de un valor tipo File. */
export const fileToBase64 = (file: File) => new Promise((resolve, reject): void => {
    const reader = new FileReader();
    if (!file) {resolve(undefined);return};
    reader.onerror = reject;
    reader.onload = () => resolve(reader.result);
    reader.readAsDataURL(file);
});

/**Devuelve un Blob a partir de un string en base 64. */
export const base64ToBlob = async (base64String:string) => {
    const base64Response = await window.fetch(`data:image/jpeg;base64,${base64String}`);
    return await base64Response.blob();
}

/**Convierte un string de CUIT/CUIL al formato AA-BB.BBB.BBB-A. */
export const toFormattedCode = (code:string) => {
    const c = code.replace(/ |\.|-/g, "");

    return c? c.slice(0, 2) + '-' +
    c.slice(2, 4) + '.' + c.slice(4, 7) + '.' + c.slice(7, 10) +
    '-' + c.charAt(10) : '';
}