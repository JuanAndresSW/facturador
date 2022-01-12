import React from 'react';
import Header from '../component/Header/Header';
import Subheader from '../component/SubHeader/Subheader';
import Main from '../component/Main/Main';
import Footer from '../component/Footer/Footer';
export default function Start() {
    return (React.createElement(React.Fragment, null,
        React.createElement(Header, null),
        React.createElement(Subheader, null),
        React.createElement(Main, null),
        React.createElement(Footer, null)));
}
