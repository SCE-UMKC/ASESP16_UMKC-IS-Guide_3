/**
 * Created by VARSHA-PC on 2/19/2016.
 */
function loginForm() {
    if (document.getElementById("user").value == "") {
        alert("Enter Username");
        document.getElementById("user").focus();
        return false;
    }
    if (document.getElementById("pwd").value == "") {
        alert("Password Mandatory");
        document.getElementById("pwd").focus();
        return false;
    }
    if ((document.getElementById("user").value == "user") && (document.getElementById("pwd").value == "user")) {
        location.href = "index.html";
        return false;
    }
    else {
        alert ("Login was unsuccessful, please check your username and password");
        return false;
    }
}

function signUpForm() {
    if (document.getElementById("fname").value == "") {
        alert("Enter first name");
        document.getElementById("fname").focus();
        return false;
    }
    if (document.getElementById("lname").value == "") {
        alert("Enter last name");
        document.getElementById("lname").focus();
        return false;
    }
    if (document.getElementById("user").value == "") {
        alert("Enter Username");
        document.getElementById("user").focus();
        return false;
    }
    if (document.getElementById("email").value == "") {
        alert("Enter email address");
        document.getElementById("email").focus();
        return false;
    }
    var email = document.getElementById("email").value;
    var regex = /^([0-9a-zA-Z]([-_\\.]*[0-9a-zA-Z]+)*)@([0-9a-zA-Z]([-_\\.]*[0-9a-zA-Z]+)*)[\\.]([a-zA-Z]{2,9})$/;

    if(!regex.test(email)){
        alert("Enter a valid email");
        return false;
    }
    if (document.getElementById("pwd").value == "") {
        alert("Password Mandatory");
        document.getElementById("pwd").focus();
        return false;
    }
    if ((document.getElementById("cpwd").value == "") || (document.getElementById("pwd").value != document.getElementById("cpwd").value)) {
        alert("confirm Password");
        document.getElementById("cpwd").focus();
        return false;
    }
    location.href = "LoginPage.html";
}