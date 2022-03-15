//React.
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
//Servicios y utilidades.
import UserAvatar from "services/UserAvatar";
import MainAccount from "services/MainAccount";
import BranchAccount from "services/BranchAccount";
import Session from "services/Session";
import Valid from "utils/Valid";
//GUI.
import { Button, ErrorMessage, Field, Form, Image, Radio } from "components/formComponents";
import { Loading, Retractable, Section } from "components/layout";
import Header from "components/layout/Header/Header";
import Footer from "components/layout/Footer/Footer";
import { BiChevronLeft } from "react-icons/bi";


//## Funciones de implementación condicional. ##//
const hasRootAccess = sessionStorage.getItem("role") !== "Main"; //%% ===

const retrieveAccount = hasRootAccess?
(handler:Function)=> MainAccount.retrieve(handler):
(handler:Function)=> BranchAccount.retrieve(handler);

const updateAccount = hasRootAccess?
(data:any, handler:Function)=> MainAccount.update(data, handler):
(data:any, handler:Function)=> BranchAccount.update(data, handler);

const requestDeletionCode = hasRootAccess?
(handler:Function)=> MainAccount.requestDeletePermission(handler):
(handler:Function)=> BranchAccount.requestDeletePermission(handler);

const requestAccountDeletion= hasRootAccess?
(code:string, handler:Function)=> MainAccount.delete(code, handler):
(code:string, handler:Function)=> BranchAccount.delete(code, handler);



/**Un formulario que permite cambiar datos de la cuenta / eliminar la cuenta de usuario y el comerciante. */
export default function Account() {

    const navigate =                useNavigate(); 
    const [loading, setLoading] =   useState(false);

    //Errores.
    const [error, setError] =                                     useState("");
    const [deleteError, setDeleteError] =                         useState("");
    //Datos de eliminación.
    const [deletePermissionGranted, setDeletePermissionGranted] = useState(false);
    const [deletionCode, setDeletionCode] =                       useState("");
    //Datos del usuario.
    const [avatar, setAvatar] =                     useState(undefined);
    const [email, setEmail] =                       useState('...');
    const [username, setUsername] =                 useState(sessionStorage.getItem("username"));
    const [password, setPassword] =                 useState("");
    const [newPassword, setNewPassword] =           useState("");
    const [confirmPassword, setConfirmPassword] =   useState("");
    //Datos del comerciante.
    const [businessName, setBusinessName] =         useState("");
    const [vatCategory, setVatCategory] =           useState("");
    const [code, setCode] =                         useState("");

    //Pedir los datos actuales en el primer renderizado.
    useEffect(() => {
        UserAvatar.retrieve((HTTPState: number, URLObject: string) => {
            if (HTTPState === 200 && !avatar) setAvatar(URLObject);
        });

        retrieveAccount((state:number, data:string):void => {
            if (state !== 200) {setError(data);return;}
    
            const obj = JSON.parse(data);
            setEmail(obj.email);
            if (businessName !== "") setBusinessName(obj.businessName);
            if (vatCategory !== "")  setVatCategory(obj.category);
            if (code !== "")         setCode(obj.code);
        });

    }, []);

    //Comprobar la validez de los datos a enviar.
    function filter():void {
        if (!Valid.names(username)) {                    setError("El nombre debe ser de entre 3 y 20 caracteres");                            return; }
        if (Valid.password(password)) {
            if (!Valid.password(newPassword)) {          setError("La contraseña debe ser de entre 8 y 40 caracteres");                        return; }
            if (newPassword!==confirmPassword) {         setError("Las contraseñas no coinciden");                                             return; }
        }
        if (hasRootAccess) {
            if (!Valid.names(businessName)) {            setError("La razón social debe ser de entre 3 y 20 caracteres");                      return; }
            if (!Valid.vatCategory(vatCategory)) {       setError("Seleccione una categoría");                                                 return; }
            if (code?.length < 1 || !Valid.code(code)) { setError(`C.U.I.${vatCategory === "Monotributista"? "L. inválido": "T. inválida"}`);  return; }
        }
        submit();
    }

    //Envía los datos al servidor.
    function submit() {
        setLoading(true);
        const account = {
            user: {
                username: sessionStorage.getItem("username"),
                newUsername: username,
                password: password,
                newPassword: newPassword,
                avatar: avatar,
            },
            trader: {
                businessName: businessName,
                vatCategory: vatCategory,
                code: code,
            }
        } 
        updateAccount(account, (state:number, data:string)=>{
            setLoading(false);
            if (state===200) window.location.reload();
            setError(data);
        });
    }

    //Solicita un código de eliminación a ser enviado al email del usuario.
    function RequestDeletePermission() {
        setLoading(true);
        requestDeletionCode((state:number, data:string)=> {
            setLoading(false);
            if (state===200) setDeletePermissionGranted(true);
            else setDeleteError(data);
        });
    }

    //Envía el código de eliminación ingresado. Si es correcto, la cuenta de usuario es eliminada.
    function deleteAccount() {
        if (deletionCode?.length !== 5) {setDeleteError("Código inválido"); return;}
        requestAccountDeletion(deletionCode, (state:number, data:string)=> {
            if (state!==200) {setDeleteError(data); return;}
            Session.close();
            window.location.reload();
        });
    }


    return (
        <>
        <Header isAuthenticated={true} />
   
        <Form title="Opciones de la cuenta" onSubmit={filter}>
            <BiChevronLeft onClick={() => navigate("/inicio")} style={{margin:"1rem", fontSize:"2rem", color:"rgb(44,44,44)",cursor:"pointer"}} />
                
            <Image label="" setter={setAvatar} img={avatar} />
            <h5 style={{width: "min-content",margin:"0 auto",display:"block",fontSize: "1.2rem",color:"rgb(212, 212, 212)", cursor:"default"}}>
                {email}
            </h5>

            <Field bind={[username, setUsername]} label="Cambiar el nombre de usuario" validator={Valid.names(username)} />

            <Field bind={[password, setPassword]} label="Para cambiar tu contraseña, introduce la contraseña actual:" type="password" validator={Valid.password(password)} />
            {!Valid.password(password) ? null:
            <>
            <Field bind={[newPassword, setNewPassword]} label="Nueva contraseña" type="password" validator={Valid.password(password)} />
            <Field bind={[confirmPassword, setConfirmPassword]} label="Confirmar nueva contraseña" type="password" validator={Valid.password(password)} />
            </>}

            {!hasRootAccess?null:
                <Section label="Datos del comercio">
                    <Field bind={[businessName, setBusinessName]} label="Razón social" validator={Valid.names(businessName)} />
                        
                    <Radio legend="Categoría:" bind={[vatCategory, setVatCategory]}
                    options={["Responsable Inscripto", "Monotributista", "Sujeto Exento"]} />

                    <Field label={"C.U.I." + (vatCategory === "Monotributista" ? "L." : "T.")}
                    bind={[code, setCode]}
                    validator={Valid.code(code)} />
                </Section>
            }

            <ErrorMessage message={error} />

            {loading?<Loading />:
            <Button text="Confirmar cambios" type="submit" />}
            

            <p style={{textAlign:"center", color:"#fff", cursor:"default"}}>...</p>
            <Retractable label="Otras opciones" initial={false}>
                
                {!deletePermissionGranted? null :
                    <Field bind={[deletionCode, setDeletionCode]} 
                    label={`Se ha enviado a ${email} el código de eliminación. Escríbelo a continuación para confirmar:`} />
                }   

                <ErrorMessage message={deleteError} />

                {loading? <Loading /> :
                <Button type="delete"
                text=   {deletePermissionGranted?"Borrar la cuenta" : "Solicitar código para eliminar cuenta"}
                onClick={deletePermissionGranted?deleteAccount      :  RequestDeletePermission} />}

            </Retractable>
        </Form>
        <Footer />
        </>
    )
}