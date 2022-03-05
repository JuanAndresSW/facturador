/* import React, { useState } from "react";
import Valid from "utils/Valid";
import Retractable from "../../../../components/FormElements/Retractable/Retractable";
import { BiCaretDown, BiCaretUp } from "react-icons/bi";
export default function NewPoint(): JSX.Element {
  //controlador del panel retráctil de datos extra del punto de venta
  const [extraActive, setExtraActive] = useState(true);
  //datos del punto de venta
  const [pointOfSale, setPointOfSale] = useState({
    name: "",
    address: "",
    locality: "",
    postalCode: "",
    email: "",
    phone: "",
    website: "",
    color: "#ffffff",
    logo: null,
  });
  const [pointError, setPointError] = useState("");

  //validación
  const validatePointOfSale = (
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void => {
    console.log("activated");
    setPointError("");
    e.preventDefault();

    if (!Valid.names(pointOfSale.name)) {
      setPointError(
        "El nombre del comercio debe ser de entre 3 y 20 caracteres"
      );
      return;
    }
    if (!Valid.address(pointOfSale.address)) {
      setPointError("La dirección debe ser de entre 8 y 40 caracteres");
      return;
    }
    if (!Valid.address(pointOfSale.locality)) {
      setPointError("La localidad debe ser de entre 8 y 40 caracteres");
      return;
    }
    if (!Valid.postalCode(pointOfSale.postalCode)) {
      setPointError("Ingrese un código postal válido");
      return;
    }
    if (pointOfSale.email.length > 0) {
      if (!Valid.email(pointOfSale.email)) {
        setPointError(
          "La dirección de correo electrónico ingresada no es válida"
        );
        return;
      }
    }
    if (!Valid.phone(pointOfSale.phone)) {
      setPointError("El número télefonico debe ser de 10 cifras");
      return;
    }
    if (!Valid.website(pointOfSale.website)) {
      setPointError("El sitio web ingresado no es válido");
      return;
    }
    //enviar objeto al servidor
    Gateway.submitPoint(pointOfSale, window.location.href);
  };

  return (
    <>
      <h1 className="title">Crea un punto de venta</h1>
      <label>
        {"Nombre del comercio"}
        <input
          type="text"
          maxLength={20}
          value={pointOfSale.name}
          onChange={(e) =>
            setPointOfSale({ ...pointOfSale, name: e.target.value })
          }
          required
        ></input>
      </label>

      <label>
        {"Dirección"}
        <input
          type="text"
          maxLength={40}
          value={pointOfSale.address}
          onChange={(e) =>
            setPointOfSale({ ...pointOfSale, address: e.target.value })
          }
          required
        ></input>
      </label>

      <label>
        {"Localidad"}
        <input
          type="text"
          maxLength={20}
          value={pointOfSale.locality}
          onChange={(e) =>
            setPointOfSale({ ...pointOfSale, locality: e.target.value })
          }
          required
        ></input>
      </label>

      <label>
        {"Código postal"}
        <input
          type="text"
          maxLength={4}
          value={pointOfSale.postalCode}
          onChange={(e) =>
            setPointOfSale({ ...pointOfSale, postalCode: e.target.value })
          }
          required
        ></input>
      </label>

      <Retractable
        active={extraActive}
        tabHeader={
          <div onClick={() => setExtraActive(!extraActive)}>
            <h2>Datos opcionales</h2>
            {extraActive ? <BiCaretUp /> : <BiCaretDown />}
          </div>
        }
      >
        <label>
          {"Logo"}
          <input
            type="file"
            accept=".png, .jpeg, .jpg, .svg"
            onChange={(e) => {
              if (e.target.files && e.target.files.length > 0)
                setPointOfSale({
                  ...pointOfSale,
                  logo: e.target.files.item(0),
                });
            }}
          ></input>
        </label>

        <label>
          {"Correo electrónico"}
          <input
            type="email"
            maxLength={254}
            value={pointOfSale.email}
            onChange={(e) =>
              setPointOfSale({ ...pointOfSale, email: e.target.value })
            }
          ></input>
        </label>

        <label>
          {"Número telefónico"}
          <input
            type="tel"
            maxLength={20}
            value={pointOfSale.phone}
            onChange={(e) =>
              setPointOfSale({ ...pointOfSale, phone: e.target.value })
            }
          ></input>
        </label>

        <label>
          {"Sitio web"}
          <input
            type="url"
            maxLength={20}
            value={pointOfSale.website}
            onChange={(e) =>
              setPointOfSale({ ...pointOfSale, website: e.target.value })
            }
          ></input>
        </label>

        <label>
          {"Color de los documentos"}
          <input
            type="color"
            value={pointOfSale.color}
            onChange={(e) =>
              setPointOfSale({ ...pointOfSale, color: e.target.value })
            }
          ></input>
        </label>
      </Retractable>

      <p className="error">{pointError}</p>

      <button onClick={(e) => validatePointOfSale(e)}>Comprobar</button>
    </>
  );
}
 */