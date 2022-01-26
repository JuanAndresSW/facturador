/**
 * solicitar datos necesarios para mostrar el formulario de generacion de documentos;
 * guardar los datos recolectados del usuario en la sesión para enviar al servidor;
 */

export default class DocData {

  /**MÉTODOS DE RECUPERACIÓN DEL SERVIDOR********************************************/

  //recupera arrays de puntos de venta, socios y grupos del servidor. Almacena en la sesión
  public static fetchFormData(): void {
    if (sessionStorage.getItem("points-of-sale") === null) {
      sessionStorage.setItem(
        "points-of-sale",
        JSON.stringify([
          {
            id: 2,
            name: "punto1",
            address: "calle1",
          },
          {
            id: 1,
            name: "punto2",
            address: "calle2",
          },
        ])
      );
      sessionStorage.setItem(
        "third-parties",
        JSON.stringify([
          {
            id: 2,
            name: "empresa",
            vatType: "monotributista",
          },
          {
            id: 1,
            name: "persona",
            vatType: "consumidor final",
          },
        ])
      );
      sessionStorage.setItem(
        "groups",
        JSON.stringify([
          {
            id: 2,
            name: "grupo A",
            members: 22,
          },
          {
            id: 1,
            name: "grupo B",
            members: 12,
          },
        ])
      );
      //determina si los tipos de documentos A Y B deben permitirse
      sessionStorage.setItem("allowAB", "1");
    }
    return;
  }

/*MÉTODOS DE RECUPERACIÓN LOCAL***********************************************/

  //recuperar de la sesión un array para construir los elementos <option> de <select>
  public static getPointsOfSale(): {
    id: number;
    name: string;
    address: string;
  }[] {
    return JSON.parse(sessionStorage.getItem("points-of-sale"));
  }
  public static getThirdParties(): {
    id: number;
    name: string;
    vatType: string;
  }[] {
    return JSON.parse(sessionStorage.getItem("third-parties"));
  }
  public static getGroups(): { id: number; name: string; members: number }[] {
    return JSON.parse(sessionStorage.getItem("groups"));
  }

  //verificar si los documentos A y B están permitidos
  public static allowAB = (): boolean => {
    return !!parseInt(sessionStorage.getItem("allowAB"));
  };

  //verifica si el string corresponde con el nombre de cualquier elemento dentro de `item`;
  //devuelve el índice de la primera coincidencia encontrada;
  //se necesita para saber si un término de búsqueda debe ser aceptado o ignorado
  public static getIndexIfExists(searchTerm: string, item: string): number {
    const matches = (element: { name: string }) =>
      element.name.toLowerCase() === searchTerm.toLowerCase().trim();

    return JSON.parse(sessionStorage.getItem(item)).findIndex(matches);
  }

  /**MÉTODOS DE ALMACENAMIENTO DE DATOS****************************************************/

  public static setPointOfSale(index: number): void {
    let data = JSON.parse(sessionStorage.getItem("doc-data"));
    data.pointOfSale = this.getPointsOfSale()[index].id;
    sessionStorage.setItem("doc-data", JSON.stringify(data));
  }
  public static setThirdParty(index: number): void {
    console.log("huh");
    let data = JSON.parse(sessionStorage.getItem("doc-data"));
    data.thirdParty = this.getThirdParties()[index].id;
    sessionStorage.setItem("doc-data", JSON.stringify(data));
  }
  public static setGroup(index: number): void {
    if (index !== -1) {
      let data = JSON.parse(sessionStorage.getItem("doc-data"));
      data.group = this.getGroups()[index].id;
      sessionStorage.setItem("doc-data", JSON.stringify(data));
    }
  }

  //en la primera renderización, define los valores iniciales de los campos en la sesión
  public static initializeValues() {
    if (sessionStorage.getItem("doc-data") === null)
      sessionStorage.setItem(
        "doc-data",
        JSON.stringify({
          pointOfSale: this.getPointsOfSale()[0].id,
          thirdParty: this.getThirdParties()[0].id,
        })
      );
  }
}
