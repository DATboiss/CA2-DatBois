import 'bootstrap/dist/css/bootstrap.css'
var searchField = document.getElementById("searchField")
var searchBtn = document.getElementById("searchBtn")

searchBtn.addEventListener("click", getPerson)

var URL = "http://localhost:3333/api/person";
var p = {
    age: 4,
    name: "Sebbelicious",
    gender: "female",
    email: "climber69@climbersunited.com"
}
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
    var pathParameter = getSearchValue(searchParameter, pathParameter);

    // fetch(URL, makeOptions("POST", p))
    // fetch(URL + "/" +113, makeOptions("DELETE"))
    fetch(URL + pathParameter)
        .then(handleHttpErrors)
        .then(data => console.log(data))
        .catch(err => {
            if (err.httpError) {
                err.fullError.then(e => console.log(e.message));
            }
            else {
                console.log("Network error");
            }
        })
}


var hasNumber = /\d/;
hasNumber.test("ABC")
hasNumber.test("easy as 123")






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
        return Promise.reject({ httpError: res.status, fullError: res.json() })
    }
    else
        return res.json()
}


