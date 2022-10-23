import { toFourDigitNumber } from "utilities/conversions";

export default function jsonToListOfBranchesAndPoints(json: string): any[] {
    const listOfBAP = JSON.parse(json);
    
    return listOfBAP.map((branch: any) => {   
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
    })
}