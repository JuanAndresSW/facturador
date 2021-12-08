/**
 * Crea filas nuevas en la tabla del formulario,
 * limita la cantidad de filas a 10,
 * asigna un value="" apropiado a cada uno de los input generados en la fila,
 */
const table = document.getElementById('productos');
let rowCounter = 1;
document.getElementById('btn-add-row').addEventListener('click', addRow);

function addRow() {
    if (rowCounter < 10) {
        rowCounter++;
        createRow().map(function(element) {
            table.appendChild(element);
        });  
    } else {
        disableAddButton();
    };
}

//Funciones secundarias******************************

function createRow() {
    let row = [
        newNumberField('c'), newTextField(), newNumberField('p')
    ];
    return row;
}

function newNumberField(prefix) {
    const nf = document.createElement('input');
    nf.name = `${prefix}${rowCounter}`;
    nf.type = 'number';
    nf.class = 'inputControl';
    nf.min = '0.01';
    nf.max = '9999999';
    return nf;
}

function newTextField() {
    const tf = document.createElement('input');

    tf.name = `d${rowCounter}`;
    tf.type = 'text';
    tf.class = 'inputControl';
    tf.maxlength = '20';
    return tf;
}

function disableAddButton() {
    document.getElementById('btn-add-row').style.display = 'none';
}