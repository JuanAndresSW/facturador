import React, { useEffect } from "react";
import Aos from "aos";
import "aos/dist/aos.css";
import "./FeaturesPreview.css";
import document from "../../assets/svg/feat-doc.svg";
import pointOfSale from "../../assets/svg/feat-punto.svg";
import book from "../../assets/svg/feat-libro.svg";
import stats from "../../assets/svg/feat-grafico.svg";
import openSource from "../../assets/svg/feat-github.svg";

const feats = [
  <>
    <img src={document} />
    <h3>Crea documentos comerciales</h3>
    <p>
      Lorem ipsum dolor sit amet consectetur adipisicing elit. Odio, reiciendis
      fuga dolore non veniam optio dolorem, beatae sunt libero modi quos
      assumenda ut consequuntur. Itaque aut molestias ullam cum quia.
    </p>
  </>,
  <>
    <img src={pointOfSale} />
    <h3>Administra instalaciones</h3>
    <p>
      Lorem ipsum dolor sit amet consectetur adipisicing elit. Excepturi debitis
      in nemo amet temporibus incidunt eveniet quam error nostrum, omnis labore
      repudiandae perferendis repellendus, molestiae vitae voluptatum minus qui?
      Et!
    </p>
  </>,
  <>
    <img src={book} />
    <h3>Libros automáticos</h3>
    <p>
      Lorem ipsum dolor sit amet consectetur adipisicing elit. Facere sapiente
      nulla recusandae! Facilis eveniet iste dolorum illo culpa temporibus
      architecto, corporis eligendi optio quos tempore ut in natus, neque
      excepturi?
    </p>
  </>,
  <>
    <img src={stats} />
    <h3>Reportes personalizados</h3>
    <p>
      Lorem ipsum dolor sit amet consectetur adipisicing elit. Odio, reiciendis
      fuga dolore non veniam optio dolorem, beatae sunt libero modi quos
      assumenda ut consequuntur. Itaque aut molestias ullam cum quia.
    </p>
  </>,
  <>
    <img src={openSource} />
    <h3>Gratis y de código abierto</h3>
    <p>
      Amet consectetur adipisicing elit. Odio, reiciendis fuga dolore non veniam
      optio dolorem, beatae sunt libero{" "}
    </p>
  </>,
];

/**Una lista de características de la aplicación que se animan en scroll. */
export default function FeaturesPreview() {
  useEffect(() => {
    Aos.init({}); //iniciar las animaciones globales de Aos
  }, []);

  return (
    <div className="features">
      {feats.map((feat: JSX.Element, index: number) => (
        <div
          key={index.toString()}
          data-aos={index % 2 === 0 ? "fade-right" : "fade-left"}
          data-aos-anchor-placement="center-bottom"
          data-aos-offset="150"
        >
          {feat}
        </div>
      ))}
    </div>
  );
}
