window.division;
onload = function () {
    document.getElementById("Figura").value = "vacio";
    division = document.getElementById("Lados");
};

/**
 * Crea un ciclo y mientras existan elementos dentro del parametro division los elimina
 */
function remover() {
    while (division.firstChild) {
        division.removeChild(division.firstChild);
    }
}

/**Funcion que detecta cuando una figura es seleccionada
 * Ya seleccionada llama la funcion para crear los botones correspondientes
 * Posteriormente los anexa al documento html
 */
function añadirBotones() {
    //Obtener el lugar donde se colocaran los botones
    

    //Determinar cual es la figura que fue seleccionada
    var figura = document.getElementById("Figura").value;

    //Antes de añadir botones, remueve los ya existentes
    remover();

    //Switch para seleccionar los botones que se deben crear
    switch (figura) {
        case "Cuadrado":
            texto("Lado", "1");
            break;
        case "Rectangulo":
            texto("Base", "1");
            texto("Altura", "2");
            break;
        case "Poligono":
            texto("Lado", "1");
            texto("Num. Lados", "2");
            break;
        case "Circulo":
            texto("Radio", "1");
            break;
    }
    //If para evitar que cuando no haya figura seleccionada se muestre algo
    if (figura !== "vacio") {
        subir();
        imagen(figura);
    }
}

/**
 * Una función que se encarga de crear los texto de los botones y separarlos debidamente
 * @param {Texto} palabra El texto que se insertara en el documento HTML
 * @param {Texto} identificador dato utilizado para llamar a la funcion botones
 */
function texto(palabra, identificador) {
    var elemento = document.createTextNode(palabra);
    division.appendChild(elemento);
    botones(identificador);
}

/**
 * Se encarga de crear ya sea botones de tipo texto o botones
 * En caso que el parametro valor venga vacio, creará un input de tipo texto
 * En cualquier otro caso crea un input tipo boton con el texto indicado por valor
 * @param {texto} valor Texto que se mostrara en el boton
 */
function botones(valor) {
    //Crea un elemento HTML de tipo input
    var elemento = document.createElement("input");
    elemento.type = "text";
    elemento.name = valor;
    elemento.setAttribute("id", "nums");
    elemento.setAttribute("onkeypress", "return validar(event)");
    elemento.setAttribute("onpaste", "return prevenirPegado()");
    //Anexa el elemento recien creado al elemento HTML ingresado como parametro
    division.appendChild(elemento);
}

/**
 * Función encargada de añadir los elementos restantes. Utilizando division,
 * se crean ambos checkbox y el boton de subir el formulario
 */
function subir() {
    //Crear una variable para aasignarle los valores del checkbox
    var checks = document.createElement("input");

    //Asignar el tipo checkbox al input y un id para obtener su valor luego
    checks.type = "checkbox";
    checks.setAttribute("name", "area");
    checks.setAttribute("onclick", "alert(document.getElementByName('area').value)");

    //Se añade el checks al parametro indicado, en este caso el div vacio del html
    division.appendChild(checks);
    //Añade el texto que se muestra al lado del checkbox, ademas de separa el siguiente
    division.appendChild(document.createTextNode("Área"));
    division.appendChild(document.createElement("br"));

    //Asigna nuevamente el tipo input para eliminar los cambios realizados antes
    checks = document.createElement("input");

    /*
     * Se vuelven a asignar valores para evitar un error que provoca que solamente
     * se genere el primer elemento, ignorando los consiguientes
     */
    checks.type = "checkbox";
    checks.setAttribute("name", "perim");
    checks.setAttribute("onclick", "alert(document.getElementByName('perim').value)");
    division.appendChild(checks);
    division.appendChild(document.createTextNode("Perímetro"));

    //Se realiza el mismo proceso una ultima vez para añadir el submit
    checks = document.createElement("input");
    checks.type = "submit";
    checks.value = "Calcular";
    checks.setAttribute("onclick", "return antesDe()");
    division.appendChild(checks);
}

/**
 * Metodo que asigna la imagen que se debe de colcar en el HTML.
 * Utilizando el parametro de figura, determinado por la función añadirBotones()
 * asigna un archivo que se encuentra dentro de la carpeta images
 * @param {texto} figura Nombre de la figura geometrica y la imagen a usar
 */
function imagen(figura) {
    var elemento = document.createElement("img");
    elemento.setAttribute("src", "../images/" + figura + ".png");
    elemento.setAttribute("class", "figura");
    division.appendChild(elemento);
}

/**
 * Metodo que se encarga de validar cuando una tecla es presionada.
 * @param {Tecla presionada} evento La tecla que fue presionada
 * @returns {Boolean} Si se puede o no usar la letra
 */
function validar(evento) {
    //evento.charCode = genera el códgio ascii de la tecla presionada
    //String.fromCharCode = de el código ascii obtiene que letra es

    //Se asigna la tecla presionada a la letra
    var letra = String.fromCharCode(evento.charCode);

    //Validar si letra es un numero O es un punto
    if (!isNaN(letra) || letra === ".") {
        //Permitir que se muestre la letra presionada
        return true;
    } else {
        //Si es cualquier otra cosa no mostrar lo que se acaba de presionar
        return false;
    }
}

/**
 * Función que impide pegar en las cajas de texto.
 * Siempre retorna falso. Fin
 * @returns {Boolean} Falso
 */
function prevenirPegado() {
    return false;
}

function antesDe() {
    val = document.getElementById("nums").value;
    if (val === null || val.trim() === "") {
        alert("Por favor ingrese datos");
        return false;
    } else {
        return true;
    }
}