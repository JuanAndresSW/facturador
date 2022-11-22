import branch from "../models/branch";
import Valid from "utilities/Valid";

export default function isValidBranch(branch: branch, setError: Function): boolean {
    setError('');


    if (!Valid.names(branch.name, setError))                           return false;
    if (!Valid.address(branch.address.city))                           return no("La localidad debe ser de entre 4 y 40 caracteres");
    if (!Valid.postalCode(branch.address.postalCode, setError))        return false;
    if (!Valid.address(branch.address.street))                         return no("La calle debe ser de entre 4 y 40 caracteres");
    if (!Valid.addressNumber(branch.address.addressNumber, setError))  return false;
    if (!Valid.email(branch.email, setError))                          return false;
    if (!Valid.phone(branch.phone, setError))                          return false;
    if (!Valid.image(branch.photo, setError))                          return false;
    if (!Valid.image(branch.logo))                                     return no("El logo no puede superar los 2MB");
    if (!Valid.hexColor(branch.preferenceColor, setError))             return false;

    return true;

    function no(messaje: string): false {setError(messaje);return false;}
}