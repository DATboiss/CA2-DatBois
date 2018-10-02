/*
  This class is just added to show you how you can use ES6 exports
  Observe, in index.js, how you can import
*/

class Cors {
    constructor(initialData) {
        this._cors = initialData;
    }
    // set addJoke(joke){
    //     this._jokes.push(joke);
    // }

    // getJokeById(i) {
    //     return this._jokes[i];
    // }
    getPersons() {
        return this._cors;
    }
}
var bo = {
    name: "lille lis"
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

// fetch(URL, makeOptions("POST", p))
// fetch(URL + "/" +113, makeOptions("DELETE"))
function getPersons() {
    var person = [];
    fetch("http://localhost:8080/CORS/api/resource/new", makeOptions("POST", bo))
        .then(handleHttpErrors)
        .then(data => {
            document.getElementById("jokes").innerHTML = data.name;
            console.log(data.name)
        }
        )
        .catch(err => {
            if (err.httpError) {
                err.fullError.then(e => console.log(e))
            }
            else {
                console.log("Network error")
            }
        })
    document.getElementById("jokes").innerHTML = person;

}
//Setup some dummy test persons
const initialData = getPersons();


const cors = new Cors(initialData);
// window.jokes = jokes; //Only for debugging

// export default jokes;

function handleHttpErrors(res) {
    if (!res.ok) {
        return Promise.reject({ httpError: res.status, fullError: res.json() })
    }
    else
        return res.json()
}