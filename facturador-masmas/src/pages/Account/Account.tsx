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
import { Button, Message, Field, Form, Image, Radio } from "components/formComponents";
import { Retractable } from "components/layout";
import { FlexDiv, Loading, Section } from "styledComponents";
import { BiChevronLeft } from "react-icons/bi";
//Modelos.
import editedAccount from "./models/editedAccount";


//## Funciones de implementación condicional. ##//
const hasRootAccess = sessionStorage.getItem("role") !== "MAIN";

const updateAccount = hasRootAccess?
(data:any, handler:Function)=> updateMainAccount(data, handler):
(data:any, handler:Function)=> updateBranchAccount(data, handler);


/**Un formulario que permite cambiar datos de la cuenta / eliminar la cuenta de usuario y el comerciante. */
export default function Account(): JSX.Element {

    /*DATOS***************************************************************/

    const navigate =                useNavigate(); 
    const [loading, setLoading] =   useState(false);
    const [success, setSuccess] =   useState(false);
    const [deleteSuccess, setDeleteSuccess] =   useState(false); 
    

    //Errores.
    const [error, setError] =                       useState("");
    const [deleteError, setDeleteError] =           useState("");
    //Datos de eliminación.
    const [deletionCode, setDeletionCode] =         useState("");
    //Datos del usuario.
    const [avatar, setAvatar] =                     useState(undefined);
    const [newUsername, setNewUsername] =                 useState('');
    const [password, setPassword] =                 useState("");
    const [newPassword, setNewPassword] =           useState("");
    const [confirmPassword, setConfirmPassword] =   useState("");
    //Datos del comerciante.
    const [businessName, setBusinessName] =         useState("");
    const [newBusinessName, setNewBusinessName] =   useState("");
    const [VATCategory, setVatCategory] =           useState("");
    const [newVATCategory, setNewVATCategory] =     useState("");
    const [CUIT, setCUIT] =                         useState("");

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
                setCUIT         (data.uniqueKey);
            });
        }
    }, []);

    /*VALIDACIÓN***************************************************************/

    function filter():void {
        if (!Valid.image(avatar, setError)) return;
        if (newUsername && !Valid.names(newUsername, setError)) return;
        if (Valid.password(password)) {
            if (!Valid.password(newPassword, setError)) return;
            if (newPassword!==confirmPassword) return setError("Las contraseñas no coinciden");
        }
        if (hasRootAccess)                                            {
            if (newBusinessName && !Valid.names(newBusinessName)) return setError("La razón social debe ser de entre 3 y 20 caracteres");
            if (newVATCategory && !Valid.vatCategory(newVATCategory, setError)) return;
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
                newVATCategory:  newVATCategory,
            }
        } 
        updateAccount(account, (ok:boolean, data:string)=>{
            setLoading(false);
            if (ok) {
                setSuccess(true);
                if (newUsername) sessionStorage.setItem("username", newUsername);
            }
            else setError(data);
        });
    }

    //Envía el código de eliminación ingresado. Si es correcto, la cuenta de usuario es eliminada.
    function deleteAccount() {
        //if (deletionCode?.length !== 5) {setDeleteError("Código inválido"); return;} TODO: remove uncomment
        requestAccountDeletion(deletionCode, (ok:boolean, data:string)=> {
            if (!ok) setDeleteError(data);
            else setDeleteSuccess(true);
        });
    }


    /*FORMULARIO***************************************************************/

    return (
        <>
   
        <Form title="Opciones de la cuenta" onSubmit={filter}>
            <BiChevronLeft onClick={() => navigate("/inicio")} style={{margin:"1rem", fontSize:"2rem", color:"rgb(44,44,44)",cursor:"pointer"}} />
                
            <Image label='' setter={setAvatar} img={avatar} />

            <Field bind={[newUsername, setNewUsername]} label="Nombre"
            placeholder={sessionStorage.getItem('username')}
            validator={Valid.names(newUsername)} />

            <Field bind={[password, setPassword]} type="password"
            label="Para cambiar tu contraseña, introduce la contraseña actual:"
            validator={Valid.password(password)} />
            {!Valid.password(password) ? null:
            <>
            <Field bind={[newPassword, setNewPassword]} label="Nueva contraseña" type="password"
            validator={Valid.password(newPassword)} />
            <Field bind={[confirmPassword, setConfirmPassword]} 
            label="Confirmar nueva contraseña" type="password"
            validator={confirmPassword === newPassword} />
            </>}

            {!hasRootAccess?null:
                <Section label="Datos del comercio">
                    <p style={{textAlign:"center", color:"#fff", cursor:"default"}}>C.U.I.T.: {CUIT}</p>

                    <Field bind={[newBusinessName, setNewBusinessName]} label="Razón social"
                    placeholder={businessName} validator={Valid.names(newBusinessName)} />
                        
                    <Radio legend={"Actualmente: "+VATCategory+". Nueva categoría:"} bind={[newVATCategory, setNewVATCategory]}
                    options={["Responsable Inscripto", "Responsable Monotributista"]} />
                </Section>
            }

            <Message type="error" message={error} />

            {success? <Message type="success" message="Se han guardado los cambios"/>:
            loading?<Loading />:
            <Button text="Confirmar cambios" type="submit" />}
            

            <p style={{textAlign:"center", color:"#fff", cursor:"default"}}>...</p>
            <Retractable label="Otras opciones" initial={false}>
                
                
                <Field bind={[deletionCode, setDeletionCode]} 
                label={'Escríbe el código de eliminación:'} />
                

                <Message type="error" message={deleteError} />

                {deleteSuccess? <Message type="success" message={`Se ha eliminado la cuenta`}/>:
                loading? <Loading /> :
                
                <FlexDiv>
                    <a href="about:blank" target='_blank'>Solicitar código</a>

                    <Button type="delete"
                    text="Borrar la cuenta"
                    onClick={deleteAccount} />
                </FlexDiv>}
                

            </Retractable>
        </Form>
        </>
    )
}