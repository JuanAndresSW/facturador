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
const Home = lazy(() => import('pages/Home/Home'));
const Start = lazy(() => import('pages/Start/Start'));
const SignUp = lazy(() => import("pages/SignUp/SignUp"));
const Login = lazy(() => import("pages/Login/Login"));
const Account = lazy(() => import("pages/Account/Account"));
const About = lazy(() => import("pages/About/About"));
const Error404 = lazy(() => import("pages/Error/Error404"));

/**El componente global de la aplicación. */
export default function App() {

    //Determina si se le debe dar permisos de sesión al usuario.
    const [auth, setAuth] = useState(undefined);

    //Comprobar la sesión con el servidor en el primer renderizado.
    useEffect(() => Session.getByToken(handleResponse), []);

    //Comprobar la respuesta final del servidor.
    function handleResponse(status: number) {
        if (status === 200) {setAuth(true); }
        else setAuth(false);
    }

    return (
        (auth === undefined) ? <SplashScreen /> :
            <BrowserRouter>
                <Suspense fallback={<SplashScreen />}>
                    <Routes>

                        <Route path="/" element={!auth? <Home /> : <Navigate to={"/inicio"} />} />   
                        <Route path="/login" element={!auth? <Login /> : <Navigate to={"/inicio"} />} />

                        <Route path="/inicio/*" element={auth? <Start /> : <Navigate to={"/login"} />} />
                        <Route path="/cuenta" element={auth? <Account /> : <Navigate to={"/login"} />} />

                        <Route path="/signup" element={<SignUp />} />
                        <Route path="/acerca-de/*" element={<About />} />
                        <Route path="*" element={<Error404 />} />      

                    </Routes>
                </Suspense>
            </BrowserRouter>
    );

}


/**
 * ESTA ES EL HEADER DE LA REQUEST:
 * 
 * POST /api/auth/init undefined
 * Host: localhost:8080
 * User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:96.0) Gecko/20100101 Firefox/96.0
 * Accept: +/* (ESE + ES UN ASTERISCO XD)
 * Accept-Language: en-US,en;q=0.5
 * Accept-Encoding: gzip, deflate
 * Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MSIsImlzcyI6Ii9sb2dpbiIsImV4cCI6MTY0NjI4NTU4OCwiaWF0IjoxNjQ2MjU2Nzg4LCJyb2wiOiJNQUlOIn0.xauytCdRgtoKK2BvD9nFHNRkQPn5CTo6P4H4JW-xN-U
 * Origin: http://localhost:3000
 * DNT: 1
 * Connection: keep-alive
 * Referer: http://localhost:3000/
 * Sec-Fetch-Dest: empty
 * Sec-Fetch-Mode: cors
 * Sec-Fetch-Site: same-site
 */