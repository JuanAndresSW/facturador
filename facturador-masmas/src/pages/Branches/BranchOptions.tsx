import React, { useState } from "react";
import { BiCaretDown, BiCaretUp } from "react-icons/bi";

type props = {
  ID: number;
}

/**Formulario para visualizar y cambiar datos de un punto de venta.
 * Precisa del parámetro ID para recuperar los datos del punto específico.*/
export default function PointOptions({ID}:props): JSX.Element {

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

      <p className="error">{pointError}</p>

    </>
  );
}