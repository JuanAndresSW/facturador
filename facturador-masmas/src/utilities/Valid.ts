/**
 * Define métodos de validación de datos.
 * Cada método devuelve un boolean significando la validez del dato argumentado.
 * Opcionalmente llama a funciones pasando como argumento un mensaje de error.
*/
export default class Valid {
  public static names(name: string, setError?: Function): boolean {
    if (name?.trim().length <= 20 && name.trim().length >= 3) return true;
    if (setError) setError("El nombre debe ser de entre 3 y 20 caracteres");
    return false;
  }

  public static email(email: string, setError?: Function): boolean {
    if (/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(email)) return true;
    if (setError) setError("Ingrese una dirección válida de email");
    return false;
  }

  public static password(password: string, setError?: Function): boolean {
    if (password?.length <= 40 && password.length >= 8) return true;
    if (setError) setError("La contraseña debe ser de entre 8 y 40 caracteres");
    return false;
  }

  public static image(image: File, setError?: Function):boolean {
    if (!image) return true;
    if (image?.size < 2097152) return true;
    if (setError) setError("La imágen no debe superar los 2MB");
    return false;
  }

  public static code(code: string, setError?: Function): boolean {
    code = code?.replace(/ |\.|-/g, "");
    //if (code === "") return true; TODO: generate automatic code
    if (/[0-9]{2}[1-9][0-9]{7}[0-9]$/.test(code)) return true;
    if (setError) setError("Ingrese un número de ingresos brutos válido");
    return false;

  }

  public static vatCategory(vatCategory: string, setError?: Function): boolean {
    if (
         vatCategory === "Monotributista" 
      || vatCategory === "Responsable Inscripto"
      || vatCategory === "Sujeto Exento"
    ) return true;
    if (setError) setError("Seleccione una categoría");
    return false;
  }

  public static address(address: string): boolean {
    return address?.length <= 40 && address.length >= 4;
  }

  public static postalCode(postalCode: string, setError?: Function): boolean {
    if  (/^\d{4}$/.test(postalCode)) return true;
    if (setError) setError("Ingrese un código postal con el formato AAAA");
    return false;
  }

  public static phone(phone: string, setError?: Function): boolean {
    phone = phone?.replace(/ |\.|-/g, "");
    if (phone?.trim().length === 0) return true;
    if (/^\d{10}$/.test(phone)) return true;
    if (setError) setError("Ingrese un número telefónico de 10 dígitos");
    return false;
  }

  public static website(url: string, setError?: Function): boolean {
    if (url?.trim().length === 0) return true;
    var regexp = /^(?:(?:https?|ftp):\/\/)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/\S*)?$/;
    if (regexp.test(url)) return true;
    if (setError) setError("Ingrese un URL válido");
    return false;
  }

  public static addressNumber(addressNumber: string, setError?: Function): boolean {
    if (/^[1-9]{1}\d{0,4}$/.test(addressNumber)) return true;
    if (setError) setError("Ingrese un número de dirección");
    return false;
  }

  public static addressHeight(addressHeight: string, setError?: Function): boolean {
    if (/^[\-]?\d{1,3}$/.test(addressHeight)) return true;
    if (setError) setError("Ingrese una altura válida");
    return false;
  }

  public static hexColor(hexColor: string, setError?: Function): boolean {
    if (/^#[\da-f]{6}$/.test(hexColor)) return true;
    if (setError) setError("Ingrese una altura válida");
    return false;
  }
}