import React, { Suspense, lazy } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ReactDOM from 'react-dom';
import reportWebVitals from './reportWebVitals';
import FallBack from './component/FallBack/FallBack';
import './global-style/normalize.css';
import './global-style/outer.css';
//importar diferidamente los contenedores
const Home = lazy(() => import('./container/Home'));
const Start = lazy(() => import('./container/Start'));
const Stats = lazy(() => import('./container/Stats'));
const SignUp = lazy(() => import('./container/SignUp'));
const LogIn = lazy(() => import('./container/LogIn'));
const Account = lazy(() => import('./container/Account'));
const About = lazy(() => import('./container/About'));
const Error404 = lazy(() => import('./container/Error404'));
//mostrar el contenedor indicado
ReactDOM.render(
  <Router>
    <Suspense fallback={<FallBack />}>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/start" element={<Start />}/>
        <Route path="/stats" element={<Stats />}/>
        <Route path="/signup" element={<SignUp />}/>
        <Route path="/account" element={<Account />}/>
        <Route path="/about" element={<About />}/>
        <Route path="*" element={<Error404 />}/>
      </Routes>
    </Suspense>
  </Router>,
  document.getElementById('root')
);
reportWebVitals(console.log);

export default function isUserAuthenticated() {
  return false;
}