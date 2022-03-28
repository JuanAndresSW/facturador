/**
 * Define métodos de validación de datos.
 * Cada método devuelve un boolean significando la validez del dato argumentado.
*/
export default class Valid {
  public static names(name: string): boolean {
    return name?.trim().length <= 20 && name.trim().length >= 3;
  }

  public static email(email: string): boolean {
    return /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(email);
  }

  public static password(password: string): boolean {
    return password?.length <= 40 && password.length >= 8;
  }

  public static image(image: File):boolean {
    if (!image) return true;
    return image?.size < 2097152; //2MB
  }

  public static code(code: string): boolean {
    code = code?.replace(/ |\.|-/g, "");
    //if (code === "") return true; TODO: generate automatic code
    if (code?.length !== 11) return false;
    return /[0-9]{2}[1-9][0-9]{7}[0-9]$/.test(code);
  }

  public static vatCategory(vatCategory: string): boolean {
    return (
      vatCategory !== "" &&
      (  vatCategory === "Monotributista" 
      || vatCategory === "Responsable Inscripto"
      || vatCategory === "Sujeto Exento")
    );
  }

  public static address(address: string): boolean {
    return address?.length <= 40 && address.length >= 8;
  }

  public static postalCode(postalCode: string): boolean {
    return /[0-9]{4}$/.test(postalCode);
  }

  public static phone(phone: string): boolean {
    phone = phone?.replace(/ |\.|-/g, "");
    if (phone?.trim().length === 0) return true;
    else return /^\d{10}$/.test(phone);
  }

  public static website(url: string): boolean {
    if (url?.trim().length === 0) return true;
    var regexp =
      /^(?:(?:https?|ftp):\/\/)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/\S*)?$/;
    return regexp.test(url);
  }
}