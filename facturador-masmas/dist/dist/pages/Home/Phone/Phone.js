var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function (t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s)
                if (Object.prototype.hasOwnProperty.call(s, p))
                    t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
import React from "react";
import Slider from "react-slick";
import { DiAndroid } from "react-icons/di";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import img1 from "assets/img/doc.png";
import img2 from "assets/img/libro.jpg";
import img3 from "assets/img/punto.jpg";
import img4 from "assets/img/stats.jpg";
import "./Phone.css";
var imgs = [img1, img2, img3, img4];
export default function Phone() {
    var settings = {
        arrows: false,
        infinite: true,
        autoplay: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
    };
    return (React.createElement("div", { className: "phone" }, React.createElement("a", { href: "about:blank", id: "phone-title" }, React.createElement(DiAndroid, null), React.createElement("h2", null, "Encu\u00E9ntralo en Play Store..")), React.createElement("div", { className: "slider-wrapper" }, React.createElement("hr", null), React.createElement(Slider, __assign({}, settings), imgs.map(function (img, index) {
        return (React.createElement("div", { className: "slide", key: index.toString() }, React.createElement("img", { src: img, alt: "" })));
    })))));
}
