import { base64ToBlob } from 'utilities/conversions';

/**Recupera un Blob a partir de un string almacenado en localStorage, correspondiente a el avatar de usuario. */
export default function getLocalUserAvatar(callback: Function): void {

    if (localStorage.getItem("avatar")) returnAsFile();
    else callback(false);

    async function returnAsFile():Promise<void> {
        const blob = await base64ToBlob(localStorage.getItem('avatar'));
        callback(true, blob);
    }
}
