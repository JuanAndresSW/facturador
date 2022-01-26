import React, { Suspense, lazy } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import ReactDOM from "react-dom";
import FallBack from "./components/FallBack/FallBack";
import "./style/normalize.css";
import "./style/outer.css";
//deferred import components
const Home = lazy(() => import("./container/Home"));
const SignUp = lazy(() => import("./container/SignUp"));
const Login = lazy(() => import("./container/Login"));
const Account = lazy(() => import("./container/Account"));
const About = lazy(() => import("./container/About"));
const Error404 = lazy(() => import("./container/Error404"));
//process url address
ReactDOM.render(
  <Router>
    <Suspense fallback={<FallBack />}>
      <Routes>
        <Route path="/*" element={<Home />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/login" element={<Login />} />
        <Route path="/account/*" element={<Account />} />
        <Route path="/about/*" element={<About />} />
        <Route path="*" element={<Error404 />} />
      </Routes>
    </Suspense>
  </Router>,
  document.getElementById("root")
);
