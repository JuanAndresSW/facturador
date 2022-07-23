//React.
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
//Componentes del formulario.
import { BackArrow } from "components/standalone";
import { Button, DateTime, Field, Form, Message, Radio, Select, Switch, Table, Textarea } from "components/formComponents";
import { Cond, FlexDiv, Retractable, Section } from 'components/wrappers';
import { BiChevronsDown, BiChevronsUp, BiExport, BiImport, BiPlus } from "react-icons/bi";
//Elementos de documento generado.
import { Check, CreditNote, DebitNote, Invoice, PromissoryNote, PurchaseOrder, Receipt, Remittance } from "./documents";
//Utilidades.
import getDocumentTitle from './utilities/getDocumentTitle';
import isValidOperation from './utilities/isValidOperation';
import Valid from "utilities/Valid";
//Servicios.
import postOperation from './services/postOperation';
import getListsOfThirdsAndPoints from './services/getListsOfThirdsAndPoints';
//Tipos.
import operation from './models/operation';
type props = {
  type: ("Fa" | "Oc" | "Rm" | "Rx" | "Rs" | "Nc" | "Nd" | "Pa" | "Ch" | "Va");
};

/**
 * Devuelve un formulario que recolecta los datos necesitados por el back-end para generar una operación comercial.
 * @param props.type Código del documento de dos caracteres.
 */
export default function OperationForm({ type }: props): JSX.Element {

  useEffect(()=>{
    getListsOfThirdsAndPoints((ok:boolean, content: any)=> {
      if (!ok) return;

      setBranches(content.map((branch: any) => {
        return {

          title: `${branch.locality} ${branch.street} N°${branch.addressNumber}`,
          value: branch.IDBranch,
          subOptions: branch.pointsOfSale.map((points: any) => {
            return {
              title: `N°${points.pointOfSaleNumber}`,
              value: points.pointOfSaleID,
            }
          })
        }
      })); 
    })
  }, []);
 

  //Datos de control del formulario.
  const [error,               setError] =               useState("hello world");
  const [success,             setSuccess] =             useState(false); 
  const [toSend,              setToSend] =              useState(true);
  const [thirds,              setThirds] =              useState();
  const [thirdParty,          setThirdParty] =          useState();
  const [branches,            setBranches] =            useState();
  const [branch,              setBranch] =              useState();

  //Datos del tercero.
  const [IDpointOfSale,       setIDPointOfSale] =       useState();
  const [CUIT,                setCUIT] =                useState();
  const [name,                setName] =                useState();
  const [address,             setAddress] =             useState();
  const [phone,               setPhone] =               useState();
  const [VATCategory,         setVATCategory] =         useState("Responsable Inscripto");
  const [email,               setEmail]=                useState();
  const [startOfActivities,   setStartOfActivities] =   useState();
  const [postalCode,          setPostalCode] =          useState();
  const [locality,            setLocality] =            useState();

  //Datos de la operación
  const [productTable,        setProductTable] =        useState([['0'],[''],['0']]);
  const [observations,        setObservations] =        useState("");
  const [seller,              setSeller] =              useState("");
  const [sellConditions,      setSellConditions] =      useState("");
  const [deadline,            setDeadline] =            useState("");
  const [placeOfDelivery,     setPlaceOfDelivery] =     useState("");
  const [carrier,             setCarrier] =             useState("");
  const [IDRemittance,        setIDRemittance] =        useState();
  const [tax,                 setTax] =                 useState(0);
  const [VAT,                 setVAT] =                 useState("21%");
  const [RxAmounts,           setRxAmounts] =           useState([['0'],['0'],['0']]);
  const [RxInvoices,          setRxInvoices] =          useState([[""],['0'],['0'],['0']]);
  const [RxDetails,           setRxDetails] =           useState([[""],[""],['0'],[""],['0']]);
  const [paymentAddress,      setPaymentAdress] =       useState("");
  const [paymentTime,         setPaymentTime] =         useState("");
  const [description,         setDescription] =         useState("");
  const [amount,              setAmount] =              useState(0);
  const [noProtest,           setNoProtest] =           useState(false);
  const [timeDelay,           setTimeDelay] =           useState(0);
  const [crossed,             setCrossed] =             useState(false);


  function generateOperation(): void {
    setError("");

    const operation: operation = {
      IDPointOfSale:      parseInt(IDpointOfSale),
      thirdParty: {
        CUIT:             CUIT,
        name:             name,
        address:          address,
        phone:            phone,
        VATCategory:      VATCategory,
        email:            email,
        startOfActivities:startOfActivities,
        postalCode:       postalCode,
        locality:         locality
      },
      productTable: {
        quantity:         productTable[0],
        description:      productTable[1],
        price:            productTable[2]
      },
      observations:       observations,
      seller:             seller,
      sellConditions:     sellConditions,
      deadline:           deadline,
      placeOfDelivery:    placeOfDelivery,
      carrier:            carrier,
      IDRemittance:       IDRemittance,
      tax:                tax,
      VAT:                parseInt(VAT),
      receiptXTables: {
        paymentMethods: {
          check:          RxAmounts[0][0],
          documents:      RxAmounts[1][0],
          cash:           RxAmounts[2][0]
        },
        paymentImputation: {
          type:           RxInvoices[0],
          documentNumber: RxInvoices[1],
          amount:         RxInvoices[2],
          paid:           RxInvoices[3]
        },
        detailOfValues: {
          type:           RxDetails[0],
          bank:           RxDetails[1],
          documentNumber: RxDetails[2],
          depositDate:    RxDetails[3],
          amount:         RxDetails[4]
        }
      },
      payementAddress:    paymentAddress,
      paymentTime:        paymentTime,
      description:        description,
      amount:             amount,
      noProtest:          noProtest,
      timeDelay:          timeDelay,
      crossed:            crossed
    };

    if (isValidOperation(operation, type, toSend, setError)) {
      postOperation(operation, type, toSend, (ok:boolean) => {
        if (ok) setSuccess(true);
      });
    }
  }

  
  return (
    
    <Form title={getDocumentTitle(type, toSend)} onSubmit={generateOperation}>

      <BackArrow />
      <Cond bool={type !== "Va"}>
        <Switch falseIcon={<BiImport />} trueIcon={<BiExport />} value={toSend} setter={setToSend}></Switch>
      </Cond>
      <Message type="error" message={error} />



      <Section label="Partícipes">

        <FlexDiv>
          <Select options={branches} value={branch} onChange={setBranch} subValue={IDpointOfSale} subOnChange={setIDPointOfSale} 
            fallback="Crea una sucursal:" label="Elige una sucursal y un punto de venta" />
          <PlusIcon link={"/inicio/sucursales/nuevo"} />
        </FlexDiv>

        
        <Cond bool={type !== "Va"}>
        {toSend?
        <BiChevronsDown style={{ margin: "0 auto", display: "block", cursor: "default", fontSize: "2rem", color: "#333" }} />:
        <BiChevronsUp   style={{ margin: "0 auto", display: "block", cursor: "default", fontSize: "2rem", color: "#333" }} />}
        <FlexDiv>
          <Select
            options={thirdParty} label="Elige un tercero (opcional)" value={thirdParty} onChange={setThirdParty}
            fallback={"Registra un tercero para autocompletar datos de operaciones."} />
          <PlusIcon link="/" />
        </FlexDiv>
        </Cond>

      </Section>


      
      <Cond bool={type !== "Va"}>
      <Retractable label="Datos del tercero" initial={false}>

        <FlexDiv>

        <Field label="Nombre" bind={[name, setName]} validator={Valid.names(name)} />

        <Cond bool={(!toSend&&"OcRmFaNdNcRxCh".includes(type)) || (toSend&&"OcRmFaNdNcRx".includes(type))}>
          <Field label="C.U.I.T." bind={[CUIT, setCUIT]} validator={Valid.CUIT(CUIT)} />
        </Cond>

        </FlexDiv>

        <Cond bool={"OcRmFaNdNcRx".includes(type)}>
          <DateTime label="Fecha de inicio de actividades" value={startOfActivities} onChange={setStartOfActivities}  />
        </Cond>

        <FlexDiv>

        <Cond bool={(!toSend&&!"Rs".includes(type)) || (toSend&&!"RsCh".includes(type))}>
          <Field label="Domicilio" bind={[address, setAddress]} validator={Valid.address(address)} />
        </Cond>

        <Cond bool={toSend&&!"RsPa".includes(type)}>
          <Field label="Localidad" bind={[locality, setLocality]} validator={Valid.address(locality)} />
        </Cond>

        <Cond bool={!"RsCh".includes(type)}>
          <Field label="Teléfono" type="tel" bind={[phone, setPhone]} validator={Valid.phone(phone)} />
        </Cond>

        </FlexDiv>

        <Cond bool={!"RsChPa".includes(type)}>
          <FlexDiv>
          <Field label="Email" type="email" bind={[email, setEmail]} validator={Valid.email(email)} />
          <Cond bool={toSend}>
            <Field label="Código postal" type="number" bind={[postalCode, setPostalCode]} validator={Valid.postalCode(postalCode)} />
          </Cond>
          </FlexDiv>
          <Radio legend="Categoría" options={["Responsable Monotributista", "Responsable Inscripto"]} bind={[VATCategory, setVATCategory]} />
        </Cond>

      </Retractable>
      </Cond>
      

      
      <Retractable label="Datos de la operación">

        <Cond bool={"OcRmFaNdNc".includes(type)}>
          <Table
            thead={[{ name: "Cantidad", type: "number" }, { name: "Descripción" }, { name: "Precio", type: "number" }]}
            tbody={productTable} onChange={setProductTable} maxRows={10} />
        </Cond>
        
        <FlexDiv>

        <Cond bool={"RsPa".includes(type)}>
          <DateTime label="Fecha límite" value={deadline} onChange={setDeadline} />
        </Cond>

        <Cond bool={"Pa"===type}>
          <Switch label="Sin protesto" value={noProtest} setter={setNoProtest} />
        </Cond>

        </FlexDiv>

        <Cond bool={"FaNdNc".includes(type)}>
          <Field label="Impuesto" type="number" bind={[tax, setTax]}/>
          <Radio legend="IVA" options={["21%", "10%", "4%", "0%"]} bind={[VAT, setVAT]} />
        </Cond>

        <Cond bool={"Rx"===type}>
          <Table label="Forma de pago"
            thead={[{ name: "Cheque", type: "number" }, { name: "Documentos", type: "number" }, { name: "Efectivo", type: "number" }]}
            tbody={RxAmounts} onChange={setRxAmounts} maxRows={1} />

          <Table label="Imputación del pago"
            thead={[{ name: "Tipo" }, { name: "Número", type: "number" }, { name: "Importe", type: "number" }, { name: "Abonado", type: "number" }]}
            tbody={RxInvoices} onChange={setRxInvoices} maxRows={3} />

          <Table label="Detalle de valores"
            thead={[{ name: "Tipo" }, { name: "Banco" }, { name: "Número" }, { name: "Fecha de depósito", type: "date" }, { name: "Importe", type: "number" }]}
            tbody={RxDetails} onChange={setRxDetails} maxRows={3} />
        </Cond>


        <FlexDiv>
        <Cond bool={"RsPaVa".includes(type)}>
          <Field label="Descripción" bind={[description, setDescription]} />
        </Cond>

        <Cond bool={"RsPaVaCh".includes(type)}>
          <Field type="number" note={"Va"===type?"(acepta valores negativos)":""} label={"Cantidad"} bind={[amount, setAmount]} />
        </Cond>
        </FlexDiv>


        <Cond bool={"Ch"===type}>
          <Field label="Diferencia de tiempo en días" type="number" bind={[timeDelay, setTimeDelay]} />
          <Switch label="Cruzado" value={crossed} setter={setCrossed} />
        </Cond>

      </Retractable>



      <Cond bool={!"VaChRsPa".includes(type)}>
      <Retractable label="Datos opcionales">

        <Cond bool={"OcRm".includes(type)}>
          <Textarea label="Observaciones" bind={[observations, setObservations]} />
        </Cond>

        <Cond bool={"Oc".includes(type)}>
          <Field label="Vendedor de preferencia" bind={[seller, setSeller]} />
        </Cond>

        <Cond bool={"OcFaNdNc".includes(type)}>
          <Radio legend="Condiciones de venta" options={["Al contado", "Cuenta corriente", "Cheque", "Pagaré"]}
          bind={[sellConditions, setSellConditions]} />
        </Cond>

        
        <Cond bool={"Oc"===type}>
          <FlexDiv>
            <DateTime label="Fecha límite" nonPast value={deadline} onChange={setDeadline} />
            <Field label="Lugar de entrega" bind={[placeOfDelivery, setPlaceOfDelivery]} />
            <Field label="Transportista" bind={[carrier, setCarrier]} />
          </FlexDiv>
        </Cond>

        <Cond bool={"FaNdNc".includes(type)}>
          <Field label="Remito N°" bind={[IDRemittance, setIDRemittance]}/>
        </Cond>

        <Cond bool={"Rx"===type}>
          <FlexDiv>
            <Field label="Domicilio de pago" bind={[paymentAddress, setPaymentAdress]} />
            <DateTime label="Horario de pago" type="time" value={paymentTime} onChange={setPaymentTime} />
          </FlexDiv>
        </Cond>

      </Retractable>
      </Cond>

      <Message type="error" message={error} />

      <Button type="submit">Generar</Button>
    </Form>
  );
}

/**Un signo + con Link a la dirección especificada.*/
const PlusIcon: React.FC<{ link: string }> = ({ link }) => {
  return (
  <Link to={link} style={{ flex: .2, marginTop: ".3rem", fontSize: "1.2rem", display: "block", textAlign: "center", color: "#444" }}>
    <BiPlus />
  </Link>)
}