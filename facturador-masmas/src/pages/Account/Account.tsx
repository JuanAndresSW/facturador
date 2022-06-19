//React.
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
//Servicios.
import getLocalUserAvatar from './services/getLocalUserAvatar';
import getTraderData from './services/getTraderData';
import putAccount from './services/putAccount';
import deleteAccount from './services/deleteAccount';
//Validación.
import Valid from "utilities/Valid";
//GUI.
import { Button, Message, Field, Form, Image, Radio } from "components/formComponents";
import { Retractable, FlexDiv, Section } from "components/wrappers";
import { Loading } from "components/standalone";
import { BiChevronLeft } from "react-icons/bi";
//Modelos.
import editedAccount from "./models/editedAccount";
import traderData from "./models/traderData";


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

        getTraderData((ok:boolean, data:traderData) => {
            if (ok) {
                setBusinessName (data.businessName);
                setVatCategory  (data.VATCategory);
                setCUIT         (data.CUIT);
            }
            else setError(''+data)
        });

    }, []);

    /*VALIDACIÓN***************************************************************/

    function filter():void {
        if (!Valid.image(avatar, setError)) return;
        if (newUsername && !Valid.names(newUsername, setError)) return;
        if (Valid.password(password)) {
            if (!Valid.password(newPassword, setError)) return;
            if (newPassword!==confirmPassword) return setError("Las contraseñas no coinciden");
        }
        if (newBusinessName && !Valid.names(newBusinessName)) return setError("La razón social debe ser de entre 3 y 20 caracteres");
        if (newVATCategory && !Valid.vatCategory(newVATCategory, setError)) return;
        submit();
    }

    /*COMUNICACIÓN***************************************************************/

    //Envía los datos al servidor.
    async function submit() {
        setLoading(true);
        const account: editedAccount = {
            user: {
                username:     sessionStorage.getItem("username"),
                updatedUsername:  newUsername,
                password:     password,
                updatedPassword:  newPassword,
                updatedAvatar:    avatar,
            },
            trader: {
                updatedBusinessName: newBusinessName,
                updatedVATCategory:  newVATCategory,
            }
        } 
        putAccount(account, (ok:boolean, data:string)=>{
            setLoading(false);
            if (!ok) return setError(data);

            setSuccess(true);
            if (newUsername) sessionStorage.setItem("username", newUsername);
        });
    }

    //Envía el código de eliminación ingresado. Si es correcto, la cuenta de usuario es eliminada.
    function requestAccountDeletion() {
        //if (deletionCode?.length !== 5) {setDeleteError("Código inválido"); return;} TODO: remove uncomment
        deleteAccount(deletionCode, (ok:boolean, data:string)=> {
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

            <Section label="Datos del comercio">
                <p style={{textAlign:"center", cursor:"default"}}>C.U.I.T.: {CUIT}</p>

                <Field bind={[newBusinessName, setNewBusinessName]} label="Razón social"
                placeholder={businessName} validator={Valid.names(newBusinessName)} />
                        
                <Radio legend={"Actualmente: "+VATCategory+". Nueva categoría:"} bind={[newVATCategory, setNewVATCategory]}
                options={["Responsable Inscripto", "Responsable Monotributista"]} />
            </Section>

            <Message type="error" message={error} />

            {success? <Message type="success" message="Se han guardado los cambios"/>:
            loading?<Loading />:
            <Button type="submit">Confirmar cambios</Button>}
            

            <p style={{textAlign:"center", color:"#fff", cursor:"default"}}>...</p>
            <Retractable label="Otras opciones" initial={false}>
                
                
                <Field bind={[deletionCode, setDeletionCode]} 
                label={'Escríbe el código de eliminación:'} />
                

                <Message type="error" message={deleteError} />

                {deleteSuccess? <Message type="success" message={`Se ha eliminado la cuenta`}/>:
                loading? <Loading /> :
                
                <FlexDiv>
                    <a href="about:blank" target='_blank'>Solicitar código</a>

                    <Button type="delete" onClick={requestAccountDeletion}>Borrar la cuenta</Button>
                </FlexDiv>}
                

            </Retractable>
        </Form>
        </>
    )
}