import React, { Suspense, lazy, useEffect, useState } from "react";
import FallBack from 'components/Fallback/Fallback';

//Controladores de la sesión.
import authenticate from "services/account/authenticate";
import Session from "utils/Session";

//Routing.
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";

//Estilos globales.
import "styles/normalize.css";
import "styles/outer.css";

//Importar diferidamente los componentes.
const Home = lazy(() => import('pages/Home/Home'));
const Start = lazy(() => import('pages/Start/Start'));
const SignUp = lazy(() => import("pages/SignUp/SignUp"));
const Login = lazy(() => import("pages/Login/Login"));
const Account = lazy(() => import("pages/Account/Account"));
const About = lazy(() => import("pages/About/About"));
const Error404 = lazy(() => import("pages/Error/Error404"));

/**El componente global de la aplicación. */
export default function App() {

    const [auth, setAuth] = useState(undefined);

    //Comprobar la sesión con el servidor en el primer renderizado.
    useEffect(() => {
        authenticate(handleResponse);
    }, []);

    function handleResponse(status: number, data?: string) {
        if (status === 200) {
            setAuth(true);
            Session.setSession({token:data, name:"test", active: "0", passive: "0"});
        } else setAuth(false);
    }

    return (
        (auth === undefined) ? <FallBack /> :
            <BrowserRouter>
                <Suspense fallback={<FallBack />}>
                    <Routes>

                        <Route path="/" element={auth? <Home /> : <Navigate to={"/inicio"} />} />   
                        <Route path="/login" element={!auth? <Login /> : <Navigate to={"/inicio"} />} />

                        <Route path="/inicio/*" element={!auth? <Start /> : <Navigate to={"/login"} />} />
                        <Route path="/cuenta" element={auth? <Account /> : <Navigate to={"/login"} />} />

                        <Route path="/signup" element={<SignUp />} />
                        <Route path="/acerca-de/*" element={<About />} />
                        <Route path="*" element={<Error404 />} />      

                    </Routes>
                </Suspense>
            </BrowserRouter>
    );

}
