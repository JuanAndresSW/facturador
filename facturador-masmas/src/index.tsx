import React, { Suspense, lazy } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ReactDOM from 'react-dom';
import FallBack from './component/FallBack/FallBack';
import './global-style/normalize.css';
import './global-style/outer.css';
//importar diferidamente los contenedores
const Home = lazy(() => import('./container/Home'));
const SignUp = lazy(() => import('./container/SignUp'));
const LogIn = lazy(() => import('./container/LogIn'));
const Account = lazy(() => import('./container/Account'));
const About = lazy(() => import('./container/About'));
const Error404 = lazy(() => import('./container/Error404'));
//procesar la dirección URL
ReactDOM.render(
  <Router>
    <Suspense fallback={<FallBack />}>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/signup" element={<SignUp />}/>
        <Route path="/account" element={<Account />}/>
        <Route path="/about" element={<About />}/>
        <Route path="*" element={<Error404 />}/>
      </Routes>
    </Suspense>
  </Router>,
  document.getElementById('root')
);