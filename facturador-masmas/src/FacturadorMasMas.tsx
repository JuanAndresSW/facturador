import React, { Suspense, lazy, useEffect, useState } from "react";
import {SplashScreen, Error404} from 'components/standalone';

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
const Manual =   lazy(() => import("pages/Manual/Manual"));

/**El componente global de la aplicación.*/
export default function FacturadorMasMas(): JSX.Element {

    const needToTestAppWithoutServer = false;

    //Determina si se le debe dar permisos de sesión al usuario.
    const [auth, setAuth] = useState(needToTestAppWithoutServer || undefined);

    //Comprobar la sesión con el servidor en el primer renderizado.
    useEffect(tryGettinAuthorization);
    function tryGettinAuthorization() {
        if (!needToTestAppWithoutServer)
        getSessionByToken().then(response => setAuth(response.ok))
    }
    
  
    


    return (
        (auth === undefined) ? <SplashScreen /> :
        
        <Suspense fallback={<SplashScreen />}>
            <Routes>

                <Route index               element={!auth? <Start />  : <Navigate to="/inicio"     />} />   
                <Route path="/ingresar"    element={!auth? <Login />  : <Navigate to="/inicio"     />} />
                <Route path="/registrarse" element={!auth? <SignUp /> : <Navigate to="/inicio"     />} />


                <Route path="/inicio/*"    element={auth?  <Home />   : <Navigate to={"/ingresar"} />} />
                <Route path="/cuenta"      element={auth?  <Account />: <Navigate to={"/ingresar"} />} />

                
                <Route path="/manual/*"    element={ <Manual    />   } />
                <Route path="*"            element={ <Error404  />   } />      

            </Routes>
        </Suspense>
    );
}