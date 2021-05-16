function postRequestWithoutToken(url, requestBody) {
    return new Promise(function (resolve, reject) {
      var request = new XMLHttpRequest();
      request.open("POST", url, true);
      request.setRequestHeader("Content-Type", "application/json");
      request.addEventListener("load", function () {
        if (request.status < 400) resolve(request.responseText);
        else reject(new Error("Request failed: " + request.statusText));
      });
      request.addEventListener("error", function () {
        reject(new Error("Network error"));
      });
      request.send(JSON.stringify(requestBody));
    });
}

function postRequestWithToken(url, requestBody) {
  return new Promise(function (resolve, reject) {
    var request = new XMLHttpRequest();
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader('Authorization', localStorage.getItem('token'))
    request.addEventListener("load", function () {
      if (request.status < 400) resolve(request.responseText);
      else reject(new Error("Request failed: " + request.statusText));
    });
    request.addEventListener("error", function () {
      reject(new Error("Network error"));
    });
    request.send(JSON.stringify(requestBody));
  });
}

function getRequest(url) {
    return new Promise(function (resolve, reject) {
      request.open("GET", url, true);
      request.setRequestHeader('Authorization', localStorage.getItem('token'))
      request.addEventListener("load", function () {
        if (request.status < 400) {
          resolve(request.response);
        } else reject(new Error("Request failed: " + request.statusText));
      });
      request.addEventListener("error", function () {
        reject(new Error("Network error"));
      });
      request.send();
    });
}

  module.exports = {postRequestWithoutToken, postRequestWithToken, getRequest}