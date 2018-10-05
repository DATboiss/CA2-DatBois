import 'bootstrap/dist/css/bootstrap.css'
var searchField = document.getElementById("searchField");
var tableFull = document.getElementById("tableFull");
var tableBody = document.getElementById("tableBody");
var searchBtn = document.getElementById("searchBtn");
var addPhone = document.getElementById("addPhone");
var addedPhones = document.getElementById("addedPhones");
var phoneError = document.getElementById("phoneError");
var postButton = document.getElementById("postButton");
var getPersonBtn = document.getElementById("getPersonBtn");
var deletePersonBtn = document.getElementById("deletePersonBtn");
var hobbyBtn = document.getElementById("hobbyBtn");
var zipcodeBtn = document.getElementById("zipcodeBtn");
var phoneNumbers = [];


postButton.addEventListener("click", postPerson)
addPhone.addEventListener("click", addPhoneToList);
searchBtn.addEventListener("click", getPerson);
addedPhones.addEventListener("click", removePhone);
getPersonBtn.addEventListener("click", personInfoToForm);
deletePersonBtn.addEventListener("click", deletePerson);
hobbyBtn.addEventListener("click", getHobbies);
zipcodeBtn.addEventListener("click", getZipCodes)

var URL = "http://localhost:8084/CA2/api/person/";
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
        phoneCollection: phoneNumbers,
        address: {
            street: street,
            additionalInfo: additionalInfo,
            cityinfo: {
                zipCode: zipCode,
                city: city
            },
        },
        hobbyCollection: [{
            name: hobby,
            description: hobbyDesc
        }]
    }
    console.log(p);
    fetch(URL, makeOptions("POST", p))
        .then(handleHttpErrors)
        .then(data => console.log(data))
        .catch(err => {
            if (err.httpError) {
                err.fullError.then(e => console.log(e.message))
            }
            else {
                console.log("Network error")
            }
        })
}
function deletePerson() {
    var id = document.getElementById("id").value;
    fetch(URL + id, makeOptions("DELETE"))
        .then(handleHttpErrors)
        .then(data => console.log(data))
        .catch(err => {
            if (err.httpError) {
                err.fullError.then(e => console.log(e.message))
            }
            else {
                console.log("Network error")
            }
        })

}

function getHobbies()
{
    fetch(URL + "hobby/" + searchField.value)
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

function getZipCodes()
{
    fetch(URL + "zipCode/" + searchField.value)
    .then(handleHttpErrors)
    //TODO create table with only city & zip code
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

//Used to determine which REST method will be called
function getSearchValue(pathParameter) {
    var searchParameter = searchField.value
    //If the value in the search box can not be converted to a number
    if (isNaN(searchParameter)) {
        var hasNumber = /\d/;
        searchParameter = searchParameter.toLowerCase();
        //if the search box contains a number
        if (hasNumber.test(searchParameter)) {
            pathParameter = "address/" + searchParameter;
        }
        else {
            pathParameter = "name/" + searchParameter;
        }
    }

    if (!isNaN(searchParameter)) {
        var numberToString = searchParameter.toString();
        if (numberToString.length == 3 || numberToString.length == 4) {
            pathParameter = "zipCode/" + searchParameter;
        }
        else {
            pathParameter = "phoneNumber/" + searchParameter;
        }
    }
    return pathParameter;
}

//Inserts persons information into the form values
function personInfoToForm() {
    var id = document.getElementById("id").value;
    fetch(URL + id)
        .then(handleHttpErrors)
        .then(dataToForm)
        .catch(err => {
            if (err.httpError) {
                err.fullError.then(e => console.log(e));
            }
            else {
                console.log("Network error");
            }
        })

    function dataToForm(data) {
        console.log(data);
        document.getElementById("firstName").value = data.firstName;
        document.getElementById("lastName").value = data.lastName;
        document.getElementById("email").value = data.email;
        document.getElementById("street").value = data.addressStreet;
        document.getElementById("additionalInfo").value = data.addressAdditionalInfo;
        document.getElementById("zipCode").value = data.zipcode;
        document.getElementById("city").value = data.city;
    }
}

function addPhoneToList() {
    var phoneNumber = document.getElementById("phone").value;
    var phoneDesc = document.getElementById("phoneDesc").value;
    var phone = {
        number: phoneNumber,
        description: phoneDesc
    }
    if (!phoneNumbers.filter(p => p.number == phoneNumber).length > 0) {
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
    console.log(data.stringify);
    
    tableBody.innerHTML = data.map(data => "<tr><td>" + data.firstName + " " + data.lastName + "</td>"
        + "<td>" + data.email + "</td><td>" + data.phoneNumber.join("\n") + "</td><td>" + data.addressStreet + "</td><td>"
        + data.city + "</td><td>" + data.zipcode + "</td><td>" + data.hobbies.join("\n") + "</td>");
}


function handleHttpErrors(res) {
    if (!res.ok) {
        return Promise.reject({ message: res.message })
    }
    else
        return res.json()
}


