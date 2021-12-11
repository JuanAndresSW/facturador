/**
 * Maneja los eventos de cambio de condición de venta
 */
//instanciar los elementos a modificar:
const radios = document.querySelectorAll('#cond-cc, #cond-cont, #cond-pag, #cond-otro');
const radCondOtro = document.getElementById('cond-otro'); 
const txfCondOtro = document.getElementById('txf-cond-otro');
//hacer que el cuadro de texto se active/desactive cuando se selecciona/descelecciona el radio correspondiente
radios.forEach(() => addEventListener('change', () =>{
  if (radCondOtro.checked) {
    txfCondOtro.disabled=false;
  } else {
    txfCondOtro.disabled=true;
  };
}
));
//(cuando se selecciona el radio) => radCondOtro.value="err" reemplazado con el valor del cuadro de texto 
txfCondOtro.addEventListener('change', () => {
  radCondOtro.value = txfCondOtro.value;
});
//actualizar radCondOtro.value="err" al momento de cargar la página
if (txfCondOtro.value != '') {
  radCondOtro.value = txfCondOtro.value;
}