
var msg = function msg(data) {
    var resultArea = document.getElementById("guessForm:result");
    var errorArea = document.getElementById("guessForm:errors1");
    if (errorArea.innerHTML != null && errorArea.innerHTML != "") {
        resultArea.innerHTML = "";
    }
};