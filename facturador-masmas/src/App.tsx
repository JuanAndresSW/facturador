import React, { Suspense, lazy, useEffect, useState } from "react";
import SplashScreen from 'components/SplashScreen/SplashScreen';

//Controladores de la sesión.
import Session from "services/Session";

//Routing.
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";

//Estilos globales.
import "styles/normalize.css";
import "styles/outer.css";

//Importar diferidamente los componentes.
const Home =     lazy(() => import('pages/Home/Home'));
const Start =    lazy(() => import('pages/Start/Start'));
const SignUp =   lazy(() => import("pages/SignUp/SignUp"));
const Login =    lazy(() => import("pages/Login/Login"));
const Account =  lazy(() => import("pages/Account/Account"));
const About =    lazy(() => import("pages/About/About"));
const Error404 = lazy(() => import("pages/Error/Error404"));

/**El componente global de la aplicación. */
export default function App(): JSX.Element {

    //Determina si se le debe dar permisos de sesión al usuario.
    const [auth, setAuth] = useState(undefined);

    //Comprobar la sesión con el servidor en el primer renderizado.
    useEffect(() => Session.getByToken(handleResponse), []);

    //Almacenar la respuesta final del servidor.
    function handleResponse(status: number, data:string) {
        try   { Session.setSession(JSON.parse(data)); }
        catch { console.error(data)                   }
        setAuth(status === 200);
    }

    return (
        (auth === undefined) ? <SplashScreen /> :
            <BrowserRouter>
                <Suspense fallback={<SplashScreen />}>
                    <Routes>

                        <Route path="/"            element={!auth? <Home />   : <Navigate to={"/inicio"} />}   />   
                        <Route path="/ingresar"    element={!auth? <Login />  : <Navigate to={"/inicio"} />}   />

                        <Route path="/inicio/*"    element={!auth? <Start />   : <Navigate to={"/ingresar"} />} />
                        <Route path="/cuenta"      element={auth? <Account /> : <Navigate to={"/ingresar"} />} />

                        <Route path="/registrarse" element={ <SignUp />  } />
                        <Route path="/acerca-de/*" element={ <About />   } />
                        <Route path="*"            element={ <Error404 />} />      

                    </Routes>
                </Suspense>
            </BrowserRouter>
    );
}