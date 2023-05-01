
function format(command, value = null) {
  document.execCommand(command, false, value);
}


function removeFormat() {
  document.execCommand('removeFormat', false, null);
}
function saveContent() {
    const inputContent = document.querySelector('#message');
    const editor = document.querySelector('#editor');
    inputContent.value = editor.innerHTML;
}

// Cuando el usuario haga clic fuera del área de texto, elimina el foco
document.querySelector("#editor").addEventListener("blur", function() {
    this.focus();
});

// Evita que el formulario se envíe al presionar la tecla Enter dentro del área de texto
document.querySelector("#editor").addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        event.preventDefault();
    }
});