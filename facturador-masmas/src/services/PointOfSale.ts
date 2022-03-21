import fetch from 'api/fetch';

/**
 * const [name,       setName] =       useState();
  //Dirección.
  const [province,   setProvince] =   useState("Buenos Aires");
  const [department, setDepartment] = useState();
  const [locality,   setLocality] =   useState();
  const [postalCode, setPostalCode] = useState();
  const [street,     setStreet] =     useState();
  const [number,     setNumber] =     useState();
  //Contacto.
  const [email,      setEmail] =      useState();
  const [phone,      setPhone] =      useState();
  const [website,    setWebsite] =    useState();
  //Personalización.
  const [logo,       setLogo] =       useState();
  const [color,      setColor] =      useState("#ffffff");
 */

export type pointOfSale = {
    name: string;
    province: string;
    department: string;
    locality: string;
    postalCode: string;
    street: string;
    number: number;
    height: number;
    email: string;
    phone: string;
    website: string;
    logo: string;
    color: string;
};

export default class PointOfSale {

    public static create(pointOfSale:pointOfSale, callback:Function):void {

    }

    /**
     * Recupera un array de puntos de venta a nombre de la cuenta solicitante.
     * Un id puede ser proporcionado en el URL para filtra
     * @param callback La función que procesará la respuesta. 
     */
    public static retrieve(callback: Function): void {
        callback(200, JSON.stringify(
            [
                {
                    id: 2,
                    tooltip: "punto1",
                    value: "calle1",
                },
                {
                    id: 1,
                    tooltip: "punto2",
                    value: "calle2",
                },
                {
                    id: 3,
                    tooltip: "punto3",
                    value: "calle3",
                },
                {
                    id: 4,
                    tooltip: "punto4",
                    value: "calle4",
                },
                {
                    id: 6,
                    tooltip: "punto5",
                    value: "calle5",
                },
                {
                    id: 8,
                    tooltip: "punto6",
                    value: "calle6",
                }
            ]

        ))
    }
}