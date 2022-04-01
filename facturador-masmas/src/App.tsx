import React, { Suspense, lazy, useEffect, useState } from "react";
import {SplashScreen, Error404} from 'styledComponents';

//Controladores de la sesión.
import getSessionByToken from 'services/getSessionByToken';

//Routing.
import { Navigate, Route, Routes } from "react-router-dom";

//Importar diferidamente los componentes.
const Start =    lazy(() => import('pages/Login/Start/Start'));
const Home =     lazy(() => import('pages/Home/Home'));
const SignUp =   lazy(() => import("pages/SignUp/SignUp"));
const Login =    lazy(() => import("pages/Login/Login"));
const Account =  lazy(() => import("pages/Account/Account"));
const About =    lazy(() => import("pages/About/About"));

/**El componente global de la aplicación. */
export default function App(): JSX.Element {

    //Determina si se le debe dar permisos de sesión al usuario.
    const [auth, setAuth] = useState(undefined);

    //Comprobar la sesión con el servidor en el primer renderizado.
    useEffect(() => getSessionByToken((ok: boolean) => { 
        setAuth(ok);
    }), []);

    return (
        (auth === undefined) ? <SplashScreen /> :
        
        <Suspense fallback={<SplashScreen />}>
            <Routes>

                <Route index               element={!auth? <Start />  : <Home />}   />   
                <Route path="/ingresar"    element={!auth? <Login />  : <Navigate to={"/"} />}   />

                <Route path="/cuenta"      element={auth? <Account /> : <Navigate to={"/ingresar"} />} />

                <Route path="/registrarse" element={ <SignUp />  } />
                <Route path="/acerca-de/*" element={ <About />   } />
                <Route path="*"            element={ <Error404 />} />      

            </Routes>
        </Suspense>
    );
}