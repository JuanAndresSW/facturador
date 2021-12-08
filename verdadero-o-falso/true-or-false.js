/**
 * Programa que genera un valor tipo boolean, y evalúa si el botón presionado
 * (#btn-true o #btn-false) es acertado.
 * Cambia los valores de varios <p> en base a ese resultado
 */

let attempts = 0; 
let pass = 0; 
let fail = 0;

//crear una constante para cada elemento que estaremos  modificando, los 4 <p>
const pResult = document.querySelector("#p-result");
const pTotal = document.querySelector("#p-total");
const pPass = document.querySelector("#p-pass");
const pFail = document.querySelector("#p-fail");

//crear los eventos de click para los dos botones
document.querySelector("#btn-true").addEventListener('click', trueSelected);
document.querySelector("#btn-false").addEventListener('click', falseSelected);

function trueSelected() {
    let randomBool = Boolean(Math.round(Math.random()));

    if(randomBool) {
        pResult.style.color = "#8f8";
        pResult.textContent = "Correcto";
        updateStats(true); //llama a la función para respuesta correcta
    } else {
        pResult.style.color = "#f88"; 
        pResult.textContent = "Incorrecto"
        updateStats(false); //llama a la función para respuesta incorrecta
    };   
        
    
};

//lo mismo que la anterior, pero invertido
function falseSelected() {  
    let randomBool = Boolean(Math.round(Math.random()));
    
    if(!randomBool) {
        pResult.style.color = "#8f8";
        pResult.textContent = "Correcto";
        updateStats(true);
    } else {
        pResult.style.color = "#f88";
        pResult.textContent = "Incorrecto"
        updateStats(false);
    };
};

//actualiza las estadísticas del juego en base a si es llamado con un verdadero o falso
function updateStats(resultToCheck) {
    attempts++;
    pTotal.textContent = attempts;
    resultToCheck == true ? pass++ : fail++; //aumenta las variables de aciertos o fallos

    //actualiza los <p> de aciertos y fallos
    pPass.textContent = pass;
    pFail.textContent = fail;
}