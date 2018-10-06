import 'bootstrap/dist/css/bootstrap.css'
const searchField = document.getElementById("searchField");
const tableFull = document.getElementById("tableFull");
const tableBody = document.getElementById("tableBody");
const searchBtn = document.getElementById("searchBtn");
const addPhone = document.getElementById("addPhone");
const addedPhones = document.getElementById("addedPhones");
const phoneError = document.getElementById("phoneError");
const postBtn = document.getElementById("postBtn");
const putBtn = document.getElementById("putBtn");
const getPersonBtn = document.getElementById("getPersonBtn");
const deletePersonBtn = document.getElementById("deletePersonBtn");
const hobbyBtn = document.getElementById("hobbyBtn");
const hobbyCountBtn = document.getElementById("hobbyCountBtn");
const zipcodeBtn = document.getElementById("zipcodeBtn");
const hobbyDropDownValues = document.getElementById("hobbyName");
const hobbyError = document.getElementById("hobbyError");
const hobbyDescription = document.getElementById("hobbyDescription");
const errorMsgDiv = document.getElementById("errorMsg");
const tableCityBody = document.getElementById("cityBody");
const tableCity = document.getElementById("tableCity");
const addHobbyBtn = document.getElementById("addHobbyBtn");
const deleteHobbyBtn = document.getElementById("deleteHobbyBtn");
const contactInfoBtn = document.getElementById("contactInfoBtn");
var phoneList = [];
var hobbyList = [];
var allHobbyList = [];

getAllHobbies();

postBtn.addEventListener("click", postPerson)
addPhone.addEventListener("click", addPhoneToList);
searchBtn.addEventListener("click", getPerson);
addedPhones.addEventListener("click", removePhone);
getPersonBtn.addEventListener("click", personInfoToForm);
deletePersonBtn.addEventListener("click", deletePerson);
deleteHobbyBtn.addEventListener("click", deleteHobby);
hobbyBtn.addEventListener("click", getHobbies);
zipcodeBtn.addEventListener("click", getCityInfo);
putBtn.addEventListener("click", putPerson);
addHobbyBtn.addEventListener("click", addHobbyToList);
hobbyDescription.addEventListener("click", removeHobby);
hobbyCountBtn.addEventListener("click", getHobbyCount);
contactInfoBtn.addEventListener("click", getContactInfo);

function getContactInfo() {
    tableFull.style = "visible";
    fetch("http://localhost:8080/CA2/api/person/contactinfo")
        .then(handleHttpErrors)
        .then(data => {
            tableBody.innerHTML = data.map(data => "<tr><td>" + data.firstName + " " + data.lastName + "</td>"
            + "<td>" + data.email + "</td><td>" + data.phoneNumber.join(", ") + "</td><td>" + data.addressStreet + " - Add. Info: " + data.addressAdditionalInfo + "</td><td>"
            + data.city + "</td><td>" + data.zipcode + "</td>");
        })
        .catch(err => {
            console.log('caught :' + err);
            if (err.httpError) {
                err.fullError.then(displaySearchError);
            }
            else {
                console.log("Network error");
            }
        })
}

function getHobbyCount()
{
    var count = document.getElementById("hobyCount");
    fetch("http://localhost:8080/CA2/api/hobby/" + searchField.value)
    .then(handleHttpErrors)
    .then(data => {
        count.innerHTML = "<p>" + "Hobby: " + searchField.value + " has " + data.count + " participants";
    })
    .catch(err => {
        console.log('caught :' + err);
        if (err.httpError) {
            err.fullError.then(displaySearchError);
        }
        else {
            console.log("Network error");
        }
    })
}

function addHobbyToList() {
    var selectedHobby = document.getElementById('hobbyName').options[document.getElementById('hobbyName').selectedIndex].text;
    for (var i = 0; i < allHobbyList.length; i++) {
        if (allHobbyList[i].name == selectedHobby && !hobbyList.filter(h => h.name == selectedHobby).length > 0) {
            hobbyError.style = "hidden";
            hobbyList.push(allHobbyList[i]);
            hobbyDescription.innerHTML = hobbyList.map(hobby => '<button type="button" class="close" aria-label="Close">' +
                '<span aria-hidden="true" id="' + hobby.name + '">&times;</span></button><p>' + hobby.name + "<br>" + hobby.description + '</p>').join("");
        } 
    }

    console.log(hobbyList);
}
function HobbiesToHTML(array) {
    hobbyDescription.innerHTML = "";
    hobbyDescription.innerHTML = array.map(hobby => '<button type="button" class="close" aria-label="Close">' +
        '<span aria-hidden="true" id="' + hobby.name + '">&times;</span></button><p>' + hobby.name + "<br>" + hobby.description + '</p>').join("");
}
function removeHobby(e) {
    var hobby = e.target.id;
    for (var i = 0; i < hobbyList.length; i++) {
        if (hobbyList[i].name == hobby) {
            hobbyList.splice(hobbyList[i], 1);
        }
        HobbiesToHTML(hobbyList);
    }
}



var URL = "http://localhost:8080/CA2/api/person/";

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
    tableCity.style = "hidden";
    fetch(URL + pathParameter)
        .then(handleHttpErrors)
        .then(dataToTable)
        .catch(err => {
            console.log('caught :' + err);
            if (err.httpError) {
                err.fullError.then(displaySearchError);
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
    var p = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        phoneCollection: phoneList,
        address: {
            street: street,
            additionalInfo: additionalInfo,
            cityinfo: {
                zipCode: zipCode,
                city: city,
            },
        },
        hobbyCollection: hobbyList,
    }
    console.log(JSON.stringify(p));
    fetch(URL, makeOptions("POST", p))
        .then(handleHttpErrors)
        .then(data => console.log(data))
        .catch(err => {
            if (err.httpError) {
                err.fullError.then(displaySearchError)
            }
            else {
                console.log("Network error")
            }
        })
}

function getAllHobbies() {
    fetch("http://localhost:8080/CA2/api/hobby")
        .then(handleHttpErrors)
        .then(data => {
            for (var i = 0; i < data.length; i++) {
                hobbyDropDownValues.innerHTML += "<option id='" + data[i].name + "'>" + data[i].name + "</option>";
                allHobbyList.push(data[i]);
            }
        })
        .catch(err => {
            if (err.httpError) {
                err.fullError.then(displaySearchError)
            }
            else {
                console.log("Network error")
            }
        })
}

function putPerson() {
    var id = document.getElementById("id").value;
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var email = document.getElementById("email").value;
    var street = document.getElementById("street").value;
    var additionalInfo = document.getElementById("additionalInfo").value;
    var zipCode = document.getElementById("zipCode").value;
    var city = document.getElementById("city").value;
    var hobbies = hobbyList;
    var p = {
        idPerson: id,
        firstName: firstName,
        lastName: lastName,
        email: email,
        phoneCollection: phoneList,
        address: {
            street: street,
            additionalInfo: additionalInfo,
            cityinfo: {
                zipCode: zipCode,
                city: city
            },
        },
        hobbyCollection: hobbies,
    }
    console.log(JSON.stringify(p));
    fetch(URL + "update/", makeOptions("PUT", p))
        .then(handleHttpErrors)
        .then(data => console.log(data))
        .catch(err => {
            console.log(JSON.parse(err));
            if (err.httpError) {
                err.fullError.then(displaySearchError)
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
                err.fullError.then(displaySearchError)
            }
            else {
                console.log("Network error")
            }
        })
}
function deleteHobby() {
    var id = document.getElementById("id").value;
    fetch("http://localhost:8080/CA2/api/hobby/" + id, makeOptions("DELETE"))
        .then(handleHttpErrors)
        .then(data => console.log(data))
        .catch(err => {
            if (err.httpError) {
                err.fullError.then(displaySearchError)
            }
            else {
                console.log("Network error")
            }
        })
}

function getHobbies() {
    tableCity.style = "hidden";
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
        if (numberToString.length <= 4) {
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
            console.log(err);
            if (err.httpError) {
                err.fullError.then(displaySearchError);
            }
            else {
                console.log("Network error");
            }
        })

    function dataToForm(data) {
        hobbyList = [];
        phoneList = [];
        for(var i = 0; i < data.hobbies.length; i++)
        {
            hobbyList.push(data.hobbies[i]);
        }
        for(var i = 0; i < data.phoneNumber.length; i++)
        {
            phoneList.push(data.phoneNumber[i]);
        }
        HobbiesToHTML(hobbyList);
        phonesToHTML(phoneList);
        document.getElementById("firstName").value = data.firstName;
        document.getElementById("lastName").value = data.lastName;
        document.getElementById("email").value = data.email;
        document.getElementById("street").value = data.address.street;
        document.getElementById("additionalInfo").value = data.address.additionalInfo;
        document.getElementById("zipCode").value = data.address.cityinfo.zipCode;
        document.getElementById("city").value = data.address.cityinfo.city;
    }
}

function addPhoneToList() {
    var phoneNumber = document.getElementById("phone").value;
    var phoneDesc = document.getElementById("phoneDesc").value;
    var phone = {
        number: phoneNumber,
        description: phoneDesc
    }
    if (!phoneList.filter(p => p.number == phoneNumber).length > 0) {
        phoneList.push(phone);
        phonesToHTML(phoneList);
        phoneError.innerText = "";
    }
    else {
        phoneError.innerText = "You have already added this phone.";
        phoneError.style.color = 'red';
    }
}

function phonesToHTML(array) {
    console.log("array: " + array);
    addedPhones.innerHTML = "";
    addedPhones.innerHTML = array.map(phone => '<button type="button" class="close" aria-label="Close">' +
        '<span aria-hidden="true" id="' + phone.number + '">&times;</span></button><p>' + phone.description + "<br>" + phone.number + '</p>').join('');
}

function removePhone(e) {
    var phone = e.target.id;
    for (var i = 0; i < phoneList.length; i++) {
        if (phoneList[i].number == phone) {
            phoneList.splice(phoneList[i], 1);
        }
        phonesToHTML(phoneList);
    }
}


function dataToTable(data) {
    console.log(data);
    tableCityBody.innerHTML = "";
    if (Array.isArray(data)) {
        tableBody.innerHTML = data.map(data => "<tr><td>" + data.firstName + " " + data.lastName + "</td>"
            + "<td>" + data.email + "</td><td>" + data.phoneNumber.join(", ") + "</td><td>" + data.addressStreet + " - Add. Info: " + data.addressAdditionalInfo + "</td><td>"
            + data.city + "</td><td>" + data.zipcode + "</td><td>" + data.hobbies.join(", ") + "</td>");
    } else {
        tableBody.innerHTML = "<tr><td>" + data.firstName + " " + data.lastName + "</td>"
            + "<td>" + data.email + "</td><td>" + data.phoneNumber.join(", ") + "</td><td>" + data.addressStreet + " - Add. Info: " + data.addressAdditionalInfo + "</td><td>"
            + data.city + "</td><td>" + data.zipcode + "</td><td>" + data.hobbies.join(", ") + "</td>";
    }
    tableFull.style = "visible";
}

function getCityInfo() {
    tableBody.innerHTML = "";
    fetch("http://localhost:8080/CA2/api/cityinfo")
        .then(handleHttpErrors)
        .then(cityDataToTable)
        .catch(err => {
            console.log('caught :' + err);
            if (err.httpError) {
                err.fullError.then(displaySearchError);
            }
            else {
                console.log("Network error");
            }
        })

    function cityDataToTable(data) {
        tableCityBody.innerHTML = data.map(data => "<tr><td>" + data.city + "</td><td>" + data.zipCode + "</td></tr>");
        tableCity.style = "visible";
    }
}


function displaySearchError(e) {
    tableFull.style = "hidden";
    errorMsgDiv.innerHTML = "<h2>" + e.message + "</h2>";
    errorMsgDiv.style.color = "red";
}

function handleHttpErrors(res) {
    if (!res.ok) {
        return Promise.reject({ httpError: res.status, fullError: res.json() })
    }
    else
        return res.json()
}

