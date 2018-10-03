import 'bootstrap/dist/css/bootstrap.css'
var searchField = document.getElementById("searchField");
var tableFull = document.getElementById("tableFull");
var tableBody = document.getElementById("tableBody");
var searchBtn = document.getElementById("searchBtn");
var addPhone = document.getElementById("addPhone");
var addedPhones = document.getElementById("addedPhones");
var phoneError = document.getElementById("phoneError");
var postButton = document.getElementById("postButton");

postButton.addEventListener("click", postPerson)

var phoneNumbers = [];
addPhone.addEventListener("click", addPhoneToList);
searchBtn.addEventListener("click", getPerson);
addedPhones.addEventListener("click", removePhone);

var URL = "http://localhost:8080/CA2/api/person";
function makeOptions(method, body) {
    var opts = {
        method: method,
        headers: {
            "Content-type": "application/json"
        },
    }
    if (body) {
        opts.body = JSON.stringify(body);
    }
    return opts;
}

function getPerson() {
    var pathParameter = getSearchValue(pathParameter);
    tableFull.style = "visible"
    // fetch(URL, makeOptions("POST", p))
    // fetch(URL + "/" +113, makeOptions("DELETE"))
    fetch(URL + pathParameter)
        .then(handleHttpErrors)
        .then(dataToTable)
        .catch(err => {
            if (err.httpError) {
                err.fullError.then(e => console.log(e));
            }
            else {
                console.log("Network error");
            }
        })
}

function postPerson() {
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var email = document.getElementById("email").value;
    var street = document.getElementById("street").value;
    var additionalInfo = document.getElementById("additionalInfo").value;
    var zipCode = document.getElementById("zipCode").value;
    var city = document.getElementById("city").value;
    var hobby = "WoW"//document.getElementById("hobbyName");
    var hobbyDesc = "Gamez"//document.getElementById("hobbyDescription");
    var p = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        Phone: phoneNumbers,
        Address: {
            street: street,
            additionalInfo: additionalInfo,
            Cityinfo: {
                zipCode: zipCode,
                city: city
            }
        },
        Hobby: {
            name: hobby,
            description: hobbyDesc
        }
    }
    console.log(p);
    fetch(URL + "/", makeOptions("POST", p))
    .then(handleHttpErrors)
    .then(data => console.log(data))
    .catch(err => {
        if (err.httpError) {
            err.fullError.then(e => console.log(e))
        }
        else {
            console.log("Network error")
        }
    })
}

function addPhoneToList() {
    var phoneNumber = document.getElementById("phone").value;
    var phoneDesc = document.getElementById("phoneDesc").value;
    var phone = {
        number: phoneNumber,
        description: phoneDesc
    }
    if (!phoneNumbers.filter(p => p.number == phoneNumber).length > 0)
    {
        phoneNumbers.push(phone);
        phonesToHTML(phoneNumbers);
        phoneError.innerText = "";
    }
    else {
        phoneError.innerText = "You have already added this phone.";
        phoneError.style.color = 'red';
    }
}

function phonesToHTML(array) {
    addedPhones.innerHTML = "";
    addedPhones.innerHTML = array.map(phone => '<button type="button" class="close" aria-label="Close">' +
        '<span aria-hidden="true" id="' + phone.number + '">&times;</span></button><p>' + phone.description + "<br>" + phone.number + '</p>').join('');
}

function removePhone(e) {
    var phone = e.target.id;
    for (var i = 0; i < phoneNumbers.length; i++) {
        if (phoneNumbers[i].number == phone) {
            phoneNumbers.splice(phoneNumbers[i], 1);
        }
        phonesToHTML(phoneNumbers);
    }
}


function dataToTable(data) {
    tableBody.innerHTML = data.map(data => "<tr><td>" + data.firstName + " " + data.lastName + "</td>"
        + "<td>" + data.email + "</td><td>" + data.phoneNumber.join("\n") + "</td><td>" + data.address + "</td><td>"
        + data.city + "</td><td>" + data.zipCode + "</td><td>" + data.hobbies.join("\n") + "</td>");
}

function getSearchValue(pathParameter) {
    var searchParameter = searchField.value

    //If the value in the search box can not be converted to a number
    if (isNaN(searchParameter)) {
        var hasNumber = /\d/;
        searchParameter = searchParameter.toLowerCase();
        //if the search box contains a number
        if (hasNumber.test(searchParameter)) {
            pathParameter = "/address/" + searchParameter;
        }
        else {
            pathParameter = "/name/" + searchParameter;
        }
    }

    if (isNaN(searchParameter)) {
        var numberToString = searchParameter.toString().lenght;
        if (numberToString == 3 || numberToString == 4) {
            pathParameter = "/zipCode/" + searchParameter;
        }
        else {
            pathParameter = "/phoneNumber/" + searchParameter;
        }
    }
    return pathParameter;
}

function handleHttpErrors(res) {
    if (!res.ok) {
        return Promise.reject({ message: res.message, fullError: res.json() })
    }
    else
        return res.json()
}


