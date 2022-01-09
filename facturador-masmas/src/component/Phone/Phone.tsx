import React from "react";
import Slider from "react-slick";
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

import img1 from '../../asset/img/doc.png';
import img2 from '../../asset/img/libro.jpg';
import img3 from '../../asset/img/punto.jpg';
import img4 from '../../asset/img/stats.jpg';

import './Phone.css';

const imgs:string[] = [img1, img2, img3, img4];
export default function Phone():JSX.Element {
    const settings = {
        dots: true,
        infinite: true,
        adaptiveHeight: true,
        autoplay: true,
        mobileFirst: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1
    };
    return (
        <div className="phone">
            <h2>Encuéntralo en Play Store..</h2>
            <p>(carrusel no terminado)</p>
            <div className="slider-wrapper">
            <Slider {...settings}>
                {imgs.map((img:string, index:number)=>
                    (<div className="slidex"><img src={img} alt="" /></div>)
                )}
            </Slider>
            </div>
        </div>
    )
}