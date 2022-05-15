import { base64ToBlob } from 'utilities/conversions';

export default function getLocalUserAvatar(callback: Function): void {

    if (localStorage.getItem("avatar")) returnAsFile();
    else callback(false);

    async function returnAsFile():Promise<void> {
        const blob = await base64ToBlob(localStorage.getItem('avatar')); //"data:image/png;base64,".length === 22
        callback(true, blob);
    }
}
