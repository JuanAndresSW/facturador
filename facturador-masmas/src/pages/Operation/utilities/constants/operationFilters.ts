/**Especifica para cada propiedad de una operación los documentos que incluyen esa propiedad.
 * @example filters.property = "OcFa"; //La propiedad property es incluida en la orden de compra y la factura.*/
const operationFilters = Object.freeze({
    senderCUIT:         "OcRmFaTkNdNcRxCh",
    senderAddress:      "OcRmFaTkNdNcRxPaCh",
    senderName:         "OcRmFaTkNdNcRxRsPaCh",
    senderContact:      "OcRmFaNdNcRxPa",
    senderVATCategory:  "OcRmFaTkNdNcRx",
    startOfActivities:  "OcRmFaNdNcRx",

    receiverCUIT:       "OcRmFaNdNcRx",
    receiverName:       "OcRmFaNdNcRxRsPaCh",
    receiverAddress:    "OcRmFaNdNcRxPa",
    receiverVATCategory:"OcRmFaNdNcRx",
    receiverPostalCode: "OcRmFaNdNcRx",
    receiverCity:       "OcRmFaNdNcRxCh",

    productTable:       "OcRmFaTkNdNc",
    observations:       "OcRm",
    seller:             "Oc",
    sellConditions:     "OcFaNdNc",
    deadline:           "OcRsPa",
    shippingAddress:    "Oc",
    carrier:            "Oc",
    remittance:         "FaNdNc",
    vat:                "FaNdNc",
    paymentMethods:     "Rx",
    paymentImputation:  "Rx",
    detailOfValues:     "Rx",
    paymentAddress:     "Rx",
    paymentTime:        "Rx",
    description:        "RsPa",
    amount:             "RePaCh",
    noProtest:          "Pa",
    timeDelay:          "Ch",
    crossed:            "Ch",
});

export default operationFilters;