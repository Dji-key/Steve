function onTestGetButtonClick() {
    var httpRequest = new XMLHttpRequest();

    httpRequest.onreadystatechange = function(e) {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            document.getElementById("server_response_section").innerHTML = httpRequest.responseText
        }
    }
}