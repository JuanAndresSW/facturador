//Métodos de conversión.

/**Devuelve un string base 64 a partir de un archivo tipo File. */
export const fileToBase64 = (file: File) => new Promise((resolve, reject): void => {
    const reader = new FileReader();
    if (!file) {resolve(undefined);return};
    reader.onerror = reject;
    reader.onload = () => resolve(reader.result);
    reader.readAsDataURL(file);
});

/**Devuelve un Blob a partir de un string en base 64. */
export const base64ToBlob = async (base64String:string): Promise<Blob> => {
    console.log("exe 14")
    const base64Response = await fetch(`data:image/jpeg;base64,${base64String}`);
    console.log("exe 16")
    return await base64Response.blob();
}