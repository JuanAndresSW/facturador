//React.
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
//Servicios.
import getLocalUserAvatar from './services/getLocalUserAvatar';
import getTraderData from './services/getTraderData';
import { updateMainAccount, updateBranchAccount } from './services/updateAccounts';
import requestDeletionCode from './services/requestDeletionCode';
import requestAccountDeletion from './services/requestAccountDeletion';
//Validación.
import Valid from "utilities/Valid";
//GUI.
import { Button, ErrorMessage, Field, Form, Image, Radio } from "components/formComponents";
import { Retractable } from "components/layout";
import { Loading, Section } from "styledComponents";
import { BiChevronLeft } from "react-icons/bi";
//Modelos.
import editedAccount from "./models/editedAccount";


//## Funciones de implementación condicional. ##//
const hasRootAccess = sessionStorage.getItem("role") === "MAIN";

const updateAccount = hasRootAccess?
(data:any, handler:Function)=> updateMainAccount(data, handler):
(data:any, handler:Function)=> updateBranchAccount(data, handler);


/**Un formulario que permite cambiar datos de la cuenta / eliminar la cuenta de usuario y el comerciante. */
export default function Account(): JSX.Element {

    /*DATOS***************************************************************/

    const navigate =                useNavigate(); 
    const [loading, setLoading] =   useState(false);

    //Errores.
    const [error, setError] =                       useState("");
    const [deleteError, setDeleteError] =           useState("");
    //Datos de eliminación.
    const [deletePermissionGranted, setDeletePermissionGranted] = useState(false);
    const [deletionCode, setDeletionCode] =                       useState("");
    //Datos del usuario.
    const [avatar, setAvatar] =                     useState(undefined);
    const [newUsername, setNewUsername] =                 useState('');
    const [password, setPassword] =                 useState("");
    const [newPassword, setNewPassword] =           useState("");
    const [confirmPassword, setConfirmPassword] =   useState("");
    //Datos del comerciante.
    const [businessName, setBusinessName] =         useState("");
    const [newBusinessName, setNewBusinessName] =   useState("");
    const [vatCategory, setVatCategory] =           useState("");
    const [newVatCategory, setNewVatCategory] =     useState("");
    const [code, setCode] =                         useState("");
    const [newCode, setNewCode] =                   useState("");

    //Pedir los datos actuales en el primer renderizado.
    useEffect(() => {
        getLocalUserAvatar((ok:boolean, file: Blob) => {
            if (ok && !avatar) setAvatar(file);
        });

        if (hasRootAccess) {
            getTraderData((ok:boolean, data:any):void => {
                if (!ok) { setError(data); return; }
                setBusinessName (data.businessName);
                setVatCategory  (data.vatCategory);
                setCode         (data.uniqueKey);
            });
        }
    }, []);

    /*VALIDACIÓN***************************************************************/

    function filter():void {
        if (avatar && !Valid.image(avatar))           { setError("La imágen no debe superar los 2MB");                  return; }
        if (newUsername && !Valid.names(newUsername)) { setError("El nombre debe ser de entre 3 y 20 caracteres");      return; }
        if (password && Valid.password(password))     {
            if (!Valid.password(newPassword))         { setError("La contraseña debe ser de entre 8 y 40 caracteres");  return; }
            if (newPassword!==confirmPassword)        { setError("Las contraseñas no coinciden");                       return; }
        }
        if (hasRootAccess)                                            {
            if (newBusinessName && !Valid.names(newBusinessName))     { setError("La razón social debe ser de entre 3 y 20 caracteres");                        return; }
            if (newVatCategory && !Valid.vatCategory(newVatCategory)) { setError("Seleccione una categoría");                                                   return; }
            if (newCode && !Valid.code(code))                         { setError(`C.U.I.${newVatCategory === "Monotributista"? "L. inválido": "T. inválida"}`); return; }
        }
        submit();
    }

    /*COMUNICACIÓN***************************************************************/

    //Envía los datos al servidor.
    async function submit() {
        setLoading(true);
        const account: editedAccount = {
            user: {
                username:     sessionStorage.getItem("username"),
                newUsername:  newUsername,
                password:     password,
                newPassword:  newPassword,
                newAvatar:    avatar,
            },
            trader: {
                newBusinessName: newBusinessName,
                newVatCategory:  newVatCategory,
                newCode:         newCode,
            }
        } 
        updateAccount(account, (ok:boolean, data:string)=>{
            setLoading(false);
            if (!ok) setError(data);
        });
    }

    //Solicita un código de eliminación a ser enviado al email del usuario.
    function RequestDeletePermission() {
        setLoading(true);
        requestDeletionCode((ok:boolean, data:string)=> {
            setLoading(false);
            if (ok) setDeletePermissionGranted(true);
            else setDeleteError(data);
        });
    }

    //Envía el código de eliminación ingresado. Si es correcto, la cuenta de usuario es eliminada.
    function deleteAccount() {
        //if (deletionCode?.length !== 5) {setDeleteError("Código inválido"); return;} TODO: remove uncomment
        requestAccountDeletion(deletionCode, (ok:boolean, data:string)=> {
            if (!ok) setDeleteError(data);
        });
    }


    /*FORMULARIO***************************************************************/

    return (
        <>
   
        <Form title="Opciones de la cuenta" onSubmit={filter}>
            <BiChevronLeft onClick={() => navigate("/inicio")} style={{margin:"1rem", fontSize:"2rem", color:"rgb(44,44,44)",cursor:"pointer"}} />
                
            <Image label='' setter={setAvatar} img={avatar} />

            <Field bind={[newUsername, setNewUsername]} label="Nombre"
            placeholder={sessionStorage.getItem('username')} />

            <Field bind={[password, setPassword]} label="Para cambiar tu contraseña, introduce la contraseña actual:" type="password" />
            {!Valid.password(password) ? null:
            <>
            <Field bind={[newPassword, setNewPassword]} label="Nueva contraseña" type="password" />
            <Field bind={[confirmPassword, setConfirmPassword]} label="Confirmar nueva contraseña" type="password" />
            </>}

            {!hasRootAccess?null:
                <Section label="Datos del comercio">
                    <Field bind={[newBusinessName, setNewBusinessName]} label="Razón social"
                    placeholder={businessName} />
                        
                    <Radio legend={"Actualmente: "+vatCategory+". Nueva categoría:"} bind={[newVatCategory, setNewVatCategory]}
                    options={["Responsable Inscripto", "Monotributista", "Sujeto Exento"]} />

                    <Field label={"C.U.I." + (newVatCategory === "Monotributista" ? "L. " : "T. ")}
                    bind={[newCode, setNewCode]} placeholder={code} />
                </Section>
            }

            <ErrorMessage message={error} />

            {loading?<Loading />:
            <Button text="Confirmar cambios" type="submit" />}
            

            <p style={{textAlign:"center", color:"#fff", cursor:"default"}}>...</p>
            <Retractable label="Otras opciones" initial={false}>
                
                {!deletePermissionGranted? null :
                    <Field bind={[deletionCode, setDeletionCode]} 
                    label={'Se ha enviado por correo electrónico el código de eliminación. Escríbelo a continuación para confirmar:'} />
                }   

                <ErrorMessage message={deleteError} />

                {loading? <Loading /> :
                <Button type="delete"
                text=   {!deletePermissionGranted?"Borrar la cuenta" : "Solicitar código para eliminar cuenta"}
                onClick={!deletePermissionGranted?deleteAccount      :  RequestDeletePermission} />}  {/*TODO: remove !: delete only after permission */}

            </Retractable>
        </Form>
        </>
    )
}