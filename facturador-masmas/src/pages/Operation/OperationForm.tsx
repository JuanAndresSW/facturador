//React.
import React, { useEffect, useState } from "react";

//Componentes del formulario.
import { BackArrow } from "components/standalone";
import { Button, DateTime, Field, Form, Message, Radio, Select, Switch, Table, Textarea } from "components/formComponents";
import { Cond, FlexDiv, Retractable, Section } from 'components/wrappers';
import { BiChevronsDown } from "react-icons/bi";
import PlusIcon from "./components/PlusIcon";
import Filter from "./components/Filter";

//Elementos de documento.
import Document from "./Document";
//Utilidades.
import getOperationFormTitle from './utilities/getOperationFormTitle';
import isValidOperation from './utilities/isValidOperation';
import Valid from "utilities/Valid";
//Servicios.
import postOperation from './services/postOperation';
import getListOfBranchesAndPoints from './services/getListOfBranchesAndPoints';
//Tipos.
import operation, {documentClassCode} from './models/operation';
import { toFourDigitNumber } from "utilities/conversions";
type props = { documentClassCode: documentClassCode };

/**
 * Devuelve un formulario que recolecta los datos necesitados por el back-end para generar un documento comercial.
 * @param props.type Código del documento de dos caracteres.
 */
export default function OperationForm({ documentClassCode }: props): JSX.Element {

  //Identificadores recibidos del documento creado.
  const [documentNumberLast8Digits, setDocumentNumberLast8Digits] = useState();
  const [documentType, setDocumentType] =      useState();


  //Comunicación con el servidor.
  useEffect(()=>{ getListOfBranchesAndPoints().then( response => {
    if (!response.ok) return;
    setBranchesAndPoints(response.content)
  })});



  async function generateOperation(): Promise<void> {
    setError("");
    setLoading(true);

    if (!isValidOperation(operation, documentClassCode, setError)) return console.log("operacion invalida");
    
    return console.log(operation);

    /* const response = await postOperation(operation, documentClassCode);
    if (!response.ok) return setError(response.message);

    setDocumentNumberLast8Digits(response.content.operationNumber);
    setDocumentType(response.content.type)
    setLoading(false); */
  }

  function viewDocument() {
    
  }
 

  //Datos de control del formulario.
  const [error,               setError] =               useState("hello world");
  const [loading,             setLoading] =             useState(false);
  const [viewingDocument,     setViewingDocument] =     useState(false);
  const [branches,            setBranches] =            useState();
  const [branch,              setBranch] =              useState();


  //Datos de la operación.
  const [operation, setOperation]: [operation, React.Dispatch<React.SetStateAction<operation>>] =
  useState({
    IDPointOfSale: undefined,
    thirdParty: {
      CUIT:             '',
      name:             '',
      address:          '',
      contact:          '',
      VATCategory:      '',
      postalCode:       '',
      locality:         ''
    },
    productTable: {
      quantity:         [0],
      description:      [''],
      price:            [0]
    },
    observations:       '',
    seller:             '',
    sellConditions:     "Cuenta corriente",
    deadline:           '',
    shippingAddress:     '',
    carrier:            '',
    remittance:       '',
    VAT:                21,
    receiptXTables: {
      paymentMethods: {
        check:          '0',
        documents:      '0',
        cash:           '0'
      },
      paymentImputation: {
        type:           [""],
        documentNumber: ['0'],
        amount:         ['0'],
        paid:           ['0']
      },
      detailOfValues: {
        type:           [''],
        bank:           [''],
        documentNumber: [''],
        depositDate:    [''],
        amount:         ['0']
      }
    },
    paymentAddress:    '',
    paymentTime:        '',
    description:        '',
    amount:             0,
    noProtest:          false,
    timeDelay:          0,
    crossed:            false
    
  });


  //Setters personalizados.
  function setBranchesAndPoints(branchesAndPoints: any): void {
    
    setBranches(branchesAndPoints.map((branch: any) => {
      return {
        title: `${branch.locality} ${branch.street} N°${branch.addressNumber}`,
        value: branch.branchID,
        subOptions: branch.pointsOfSale.map((point: any) => {
          return {
            title: `N°${toFourDigitNumber(point.pointOfSaleNumber)}`,
            value: point.pointOfSaleId,
          }
        })
      }
    })); 
  }
  function setThirdParty(thirdParty: typeof operation.thirdParty) { setOperation({...operation, thirdParty: thirdParty}) }

  function setProductTable(table: string[][]) {
    const operationTemp = {...operation}
    operationTemp.productTable.quantity =     table[0].map(quantity=>parseInt(quantity));
    operationTemp.productTable.description =  table[1];
    operationTemp.productTable.price =        table[2].map(quantity=>parseInt(quantity));
    setOperation(operationTemp);
  }
  
  
  return (
    
    viewingDocument? <Document type={documentClassCode} /> :


    <Form title={getOperationFormTitle(documentClassCode, true)} onSubmit={generateOperation}>

      <BackArrow />
      <Message type="error" message={error} />

      <Section label="Partícipes">

        <FlexDiv>
          <Select options={branches} value={branch} onChange={setBranch} 
          subValue={operation.IDPointOfSale} 
          subOnChange={(ID: number) => setOperation({...operation, IDPointOfSale: ID})} 
          fallback="Crea una sucursal:" label="Elige una sucursal y un punto de venta" />
          <PlusIcon link={"/inicio/sucursales/nuevo"} />
        </FlexDiv>
 
        <BiChevronsDown style={{ margin: "0 auto", display: "block", cursor: "default", fontSize: "2rem", color: "#333" }} />

      </Section>



      <Retractable label="Datos del tercero" initial={false}>

        <FlexDiv>

        <Filter by="receiverName" classCode={documentClassCode}>
          <Field label="Nombre"
          bind={[operation.thirdParty.name, (name: string) => 
          setThirdParty({...operation.thirdParty, name: name})]} 
          validator={Valid.names(operation.thirdParty.name)} />
        </Filter>

        <Filter by="receiverCUIT" classCode={documentClassCode}>
          <Field label="C.U.I.T." bind={[operation.thirdParty.CUIT, (CUIT: string) => 
          setThirdParty({...operation.thirdParty, CUIT: CUIT})]} 
          validator={Valid.CUIT(operation.thirdParty.CUIT)} />
        </Filter>

        </FlexDiv>


        <FlexDiv>

        <Filter by="receiverAddress" classCode={documentClassCode}>
          <Field label="Domicilio" note="(calle y altura)" bind={[operation.thirdParty.address, (address: string) => 
          setThirdParty({...operation.thirdParty, address: address})]} validator={Valid.address(operation.thirdParty.address)} />
        </Filter>

        <Filter by="receiverLocality" classCode={documentClassCode}>
          <Field label="Localidad" bind={[operation.thirdParty.locality, (locality: string) => 
          setThirdParty({...operation.thirdParty, locality: locality})]} validator={Valid.address(operation.thirdParty.locality)} />
        </Filter>

        </FlexDiv>

        <Filter by="receiverVATCategory" classCode={documentClassCode}>
          <Radio legend="Categoría" options={["Responsable Monotributista", "Responsable Inscripto", "Consumidor Final", "Sujeto Exento"]} 
          bind={[operation.thirdParty.VATCategory, (VATCategory: string) => 
          setThirdParty({...operation.thirdParty, VATCategory: VATCategory})]} />
        </Filter>

      </Retractable>
      

      
      <Retractable label="Datos de la operación">

        <Filter by="productTable" classCode={documentClassCode}>
          <Table
            thead={[{ name: "Cantidad", type: "number" }, { name: "Descripción" }, { name: "Precio", type: "number" }]}
            tbody={[operation.productTable.quantity, operation.productTable.description, operation.productTable.price]} 
            onChange={(newTable: string[][])=>setProductTable(newTable)} maxRows={10} />
        </Filter>

        <Filter by="vat" classCode={documentClassCode}>
          <Radio legend="IVA" options={[21, 10, 4, 0]} bind={[operation.VAT, (VAT: number)=>setOperation({...operation, VAT: VAT})]} />
        </Filter>
        
      </Retractable>



      <Cond bool={"OcRmFaNdNcRx".includes(documentClassCode)}><Retractable label="Datos opcionales">

        <Filter by="sellConditions" classCode={documentClassCode}>
          <Radio legend="Condiciones de venta" options={["Al contado", "Cuenta corriente", "Cheque", "Pagaré", "Otro"]}
          bind={[operation.sellConditions, (sellConditions: string)=>setOperation({...operation, sellConditions: sellConditions})]} />
        </Filter>

        <Filter by="remittance" classCode={documentClassCode}>
          <Field label="Remito N°" bind={[operation.remittance, (remittance: string)=>setOperation({...operation, remittance: remittance})]}/>
        </Filter>

      </Retractable></Cond>

      

      <Message type="error" message={error} />

      <FlexDiv justify='flex-end'>
        {
          documentNumberLast8Digits?
          <Button onClick={()=>viewDocument()}>Ver PDF</Button>:
          <Button type="submit">Generar</Button>
        }
        
      </FlexDiv>
      
    </Form>
  );
}


/*
BACKUP:


<Cond bool={!"RsCh".includes(type)}>
  <Field label="Teléfono" type="tel" bind={[phone, setPhone]} validator={Valid.phone(phone)} />
</Cond>

<FlexDiv>
  <Field label="Email" type="email" bind={[email, setEmail]} validator={Valid.email(email)} />
  <Cond bool={toSend}>
    <Field label="Código postal" type="number" bind={[postalCode, setPostalCode]} validator={Valid.postalCode(postalCode)} />
  </Cond>
</FlexDiv>


<FlexDiv>

        <Cond bool={"RsPa".includes(type)}>
          <DateTime label="Fecha límite" value={deadline} onChange={setDeadline} />
        </Cond>

        <Cond bool={"Pa"===type}>
          <Switch label="Sin protesto" value={noProtest} setter={setNoProtest} />
        </Cond>

        </FlexDiv>

        

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
        <Cond bool={"RsPa".includes(type)}>
          <Field label="Descripción" bind={[description, setDescription]} />
        </Cond>

        <Cond bool={"RsPaCh".includes(type)}>
          <Field type="number" label="Cantidad" bind={[amount, setAmount]} />
        </Cond>
        </FlexDiv>


        <Cond bool={"Ch"===type}>
          <Field label="Diferencia de tiempo en días" type="number" bind={[timeDelay, setTimeDelay]} />
          <Switch label="Cruzado" value={crossed} setter={setCrossed} />
        </Cond>


        <Cond bool={"OcRm".includes(type)}>
          <Textarea label="Observaciones" bind={[observations, setObservations]} />
        </Cond>

        <Cond bool={"Oc".includes(type)}>
          <Field label="Vendedor de preferencia" bind={[seller, setSeller]} />
        </Cond>


        <Cond bool={"Oc"===type}>
          <FlexDiv>
            <DateTime label="Fecha límite" nonPast value={deadline} onChange={setDeadline} />
            <Field label="Lugar de entrega" bind={[placeOfDelivery, setPlaceOfDelivery]} />
            <Field label="Transportista" bind={[carrier, setCarrier]} />
          </FlexDiv>
        </Cond>

        <Cond bool={"Rx"===type}>
          <FlexDiv>
            <Field label="Domicilio de pago" bind={[paymentAddress, setPaymentAddress]} />
            <DateTime label="Horario de pago" type="time" value={paymentTime} onChange={setPaymentTime} />
          </FlexDiv>
        </Cond>

*/
