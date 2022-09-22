/**Especifica para cada propiedad de una operación los documentos que incluyen esa propiedad.
 * @example filters.property = "OcFa"; //La propiedad property es incluida en la orden de compra y la factura.
 */
const operationFilters = Object.freeze({
    senderCUIT:         "OcRmFaNdNcRxCh",
    senderAddress:      "OcRmFaNdNcRxPaCh",
    senderName:         "OcRmFaNdNcRxRsPaCh",
    senderContact:      "OcRmFaNdNcRxPa",
    senderVATCategory:  "OcRmFaNdNcRx",
    startOfActivities:  "OcRmFaNdNcRx",

    receiverCUIT:       "OcRmFaNdNcRx",
    receiverName:       "OcRmFaNdNcRxRsPaCh",
    receiverAddress:    "OcRmFaNdNcRxPa",
    receiverVATCategory:"OcRmFaNdNcRx",
    receiverPostalCode: "OcRmFaNdNcRx",
    receiverLocality:   "OcRmFaNdNcRxCh",

    productTable:       "OcRmFaNdNc",
    observations:       "OcRm",
    seller:             "Oc",
    sellConditions:     "OcFaNdNc",
    deadline:           "OcRsPa",
    shippingAddress:     "Oc",
    carrier:            "Oc",
    remittance:         "FaNdNc",
    vat:                "FaNdNc",
    paymentMethods:     "Rx",
    paymentImputation:  "Rx",
    detailOfValues:     "Rx",
    paymentAddress:      "Rx",
    paymentTime:        "Rx",
    description:        "RsPa",
    amount:             "RePaCh",
    noProtest:          "Pa",
    timeDelay:          "Ch",
    crossed:            "Ch",
});

export default operationFilters;
export type operationProp =
    "senderCUIT"|
    "receiverCUIT"|
    "senderName"|
    "receiverName"|
    "senderAddress"|
    "receiverAddress"|
    "senderContact"|
    "senderVATCategory"|
    "receiverVATCategory"|
    "startOfActivities"|
    "receiverPostalCode"|
    "receiverLocality"|
    "productTable"|
    "observations"|
    "seller"|
    "sellConditions"|
    "deadline"|
    "shippingAddress"|
    "carrier"|
    "remittance"|
    "vat"|
    "paymentMethods"|
    "paymentImputation"|
    "detailOfValues"|
    "paymentAddress"|
    "paymentTime"|
    "description"|
    "amount"|
    "noProtest"|
    "timeDelay"|
    "crossed"
;