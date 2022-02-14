import React, { Suspense, lazy, useEffect, useState } from "react";
import FallBack from 'components/Fallback/Fallback';
import Const from "utils/Const";

//Controladores de la sesión.
import authenticate from "services/account/authenticate";
import Session from "utils/Session";

//Routing.
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Protected from 'components/Routing/Protected';

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

    const [loading, setLoading] = useState(true);

    //Comprobar la sesión con el servidor en el primer renderizado.
    useEffect(() => {
        authenticate(handleResponse);
    }, [])

    //Borrar la sesión si no existe o no es válida.
    if (!Session.isAuthenticated()) {
        Session.close();
    }

    function handleResponse(status: number, data?: string) {
        setLoading(false);
        if (status === Const.ok) Session.setSession(JSON.parse(data));
    }

    return (
        loading ? <FallBack /> :
            <BrowserRouter>
                <Suspense fallback={<FallBack />}>
                    <Routes>

                        <Route path="/*" element={<Protected reverse={true}><Home /></Protected>} />
                        <Route path="/login" element={<Protected reverse={true}><Login /></Protected>} />

                        <Route path="/cuenta/*" element={<Protected><Account /></Protected>} />
                        <Route path="/inicio/*" element={<Protected><Start /></Protected>} />

                        <Route path="/sign-up" element={<SignUp />} />
                        <Route path="/acerca-de/*" element={<About />} />
                        <Route path="*" element={<Error404 />} />

                    </Routes>
                </Suspense>
            </BrowserRouter>
    );

}
