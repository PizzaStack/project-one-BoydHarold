var employeeId = "";
var employeeFirstName = "";
var employeeLastName = "";
var employeeEmailAddress = "";
var employeeAddress = "";

var managerId = "";
var managerFirstName = "";
var managerLastName = "";
var managerEmailAddress = "";
var managerAddress = "";

login();

function login(){

    document.getElementById("main").innerHTML = `
    <div id="loginOptions">
        <h2>Choose your login type:</h2>
        <button id="employeeChoice" onclick="loginEmployee()">Employee</button><button id="managerChoice" onclick="loginManager()">Manager</button>
        </div>
    `;

    (function(){
        let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function(){
            if(this.readyState === XMLHttpRequest.DONE && this.status === 200){

            }
        }

        xhr.open("POST","http://localhost:8080/ExpenseReimbursementSystem/connection");

        xhr.send("OFF");
    })();
}

function loginEmployee(){
    document.getElementById("main").innerHTML = `
    <form id="employeeLogin">
        Please provide your employee credentials:<br>
        Username:<br>
        <input id="usernameBox" type="text" name="username" required autofocus><br>
        Password:<br>
        <input id="passwordBox" type="text" name="password" required autofocus><br><br>
        <button type="button" id="employeeLoginButton" onclick="authenticateEmployee(username, password)">Login</button>
    </form>
    `
}

function loginManager(){
    document.getElementById("main").innerHTML = `
    <form id="managerLogin">
        Please provide your manager credentials:<br>
        Username:<br>
        <input id="usernameBox" type="text" name="username" required autofocus><br>
        Password:<br>
        <input id="passwordBox" type="text" name="password" required autofocus><br><br>
        <button type="button" id="managerLoginButton" onclick="authenticateManager(username, password)">Login</button>
    </form>
    `
}

function authenticateEmployee(username, password){
    if(username.value == "") {
        alert("Username cannot be blank! Please enter in a valid username.");
    } else if(password.value == ""){
        alert("Password cannot be blank! Please enter in a valid password.");
    } else {

        (function(){
        let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function() {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                let userInfo = JSON.parse(xhr.responseText);
                if(userInfo.auth == "true"){
                    employeeId = userInfo.id;
                    employeeFirstName = userInfo.firstname;
                    employeeLastName = userInfo.lastname;
                    employeeEmailAddress = userInfo.emailaddress;
                    employeeAddress = userInfo.address;
                    employeeLogin();
                } else {
                    alert("Incorrect credentials, try again!");
                }
            }
        }

        xhr.open("POST", "http://localhost:8080/ExpenseReimbursementSystem/authentication", false);

        let credentialArray = [username.value, password.value, "employee"];

        xhr.send(credentialArray);

     })();

    }
}

function authenticateManager(username, password){
    if(username.value == "") {
        alert("Username cannot be blank! Please enter in a valid username.");
    } else if(password.value == ""){
        alert("Password cannot be blank! Please enter in a valid password.");
    } else {
        (function(){
            let xhr = new XMLHttpRequest();
    
            xhr.onreadystatechange = function() {
                if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                    let userInfo = JSON.parse(xhr.responseText);
                    if(userInfo.auth == "true"){
                        managerId = userInfo.id;
                        managerFirstName = userInfo.firstname;
                        managerLastName = userInfo.lastname;
                        managerEmailAddress = userInfo.emailaddress;
                        managerAddress = userInfo.address;
                        managerLogin();
                    } else {
                        alert("Incorrect credentials, try again!");
                    }
                }
            }
    
            xhr.open("POST", "http://localhost:8080/ExpenseReimbursementSystem/authentication", false);
    
            let credentialArray = [username.value, password.value, "manager"];
    
            xhr.send(credentialArray);
    
         })();
    }
}

function employeeLogin(){
    getEmployeeReimbursementById(employeeId, "pending");
    document.getElementById("main").innerHTML = `
        <nav id="employeeNav">
        <div class="row">
            <div class="col-md">
                <p id="employeeHome" onclick="employeeLogin()">Home</p>
            </div>
            <div class="col-md">
                <p id="employeeInformation" onclick="employeeManageInformation()">My Information</p>
            </div>
            <div class="col-md">
                <p id="employeeReimbursements" onclick="employeeManageReimbursements()">Manage Reimbursements</p>
            </div>
            <div class="col-md">
                <p id="logout" onclick="login()">Log Out</p>
            </div>
        </div>
        </nav>
        <div id="welcomeEmployee">
            <h2 id="welcomeFirstLast">Welcome ${employeeFirstName} ${employeeLastName}!</h2>
            <h3 id="pendingReimbursementRequests">Pending Reimbursement Requests:</h3>
            <div id="pendingRequests">
            <table class="table" id="pendingReimbursementTable">
            <thead>
              <tr>
                <th scope="col">Reimbursement Id</th>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col">Amount</th>
                <th scope="col">Date</th>
                <th scope="col">Status</th>
                <th scope="col">Receipt</th>
              </tr>
            </thead>
            <tbody id="pendingReimbursementTableBody">

            </tbody>
            </table>
            </div>
        </div>
    `
}

function managerLogin(){
    document.getElementById("main").innerHTML = `
        <nav id="managerNav">
        <div class="row">
            <div class="col-md">
                <p id="managerHome" onclick="managerLogin()">Home</p>
            </div>
            <div class="col-md">
                <p id="managerReimbursements" onclick="managerManageReimbursements()">Manage Reimbursements</p>
            </div>
            <div class="col-md">
            <p id="logout" onclick="login()">Log Out</p>
            </div>
        </nav>
        </div>
        <div id="welcomeManager">
        <h2 id="welcomeFirstLast">Welcome ${managerFirstName} ${managerLastName}!</h2>
        <h3 id="employeeList">Employee List:</h3>
        </div>
    `
}

function employeeManageReimbursements(){
    document.getElementById("main").innerHTML = `
    <nav id="employeeNav">
    <div class="row">
        <div class="col-md">
            <p id="employeeHome" onclick="employeeLogin()">Home</p>
        </div>
        <div class="col-md">
            <p id="employeeResolvedReimbursementsId" onclick="employeeResolvedReimbursements()">View Resolved Reimbursements</p>
        </div>
        <div class="col-md">
        <p id="logout" onclick="login()">Log Out</p>
        </div>
    </nav>
    <div id="submitReimbursement">
        <form id="reimbursementForm" enctype="multipart/form-data">
        <h2>
        Submit Reimbursement:
        </h2>
        Title:<br>
        <input id="titleBox" type="text" name="titleBox" required><br>
        Description:<br>
        <textarea id="descriptionBox" type="text" name="description" required></textarea><br>
        Amount:<br>
        <input id="amountBox" type="number" min="0.01" step="0.01" max="10000" name="amount" required><br>   
        <br>
        <input id="reimbursementImage" name="file" type="file" accept=".png,.jpg,.jpeg" required><br>
        <br>
        <button type="button" id="submitRequest" onclick="uploadReimbursementInit(titleBox, description, amount, file)">Submit</button>
        </form>
    </div>
    </div>
    `
}

function managerManageReimbursements(){
    document.getElementById("main").innerHTML = `
    <nav id="managerNav">
    <div class="row">
        <div class="col-md">
            <p id="managerHome" onclick="managerLogin()">Home</p>
        </div>
        <div class="col-md">
            <p id="managerPendingReimbursementsId" onclick="managerPendingReimbursements()">View Pending Reimbursements</p>
        </div>
        <div class="col-md">
            <p id="managerResolvedReimbursementsId" onclick="managerResolvedReimbursements()">View Resolved Reimbursements</p>
        </div>
        <div class="col-md">
        <p id="logout" onclick="login()">Log Out</p>
        </div>
    </nav>
    <div id="viewReimbursementById">
        <form id="reimbursementFormById" enctype="multipart/form-data">
        <h2>
        View Reimbursements by Employee Id:
        </h2>
        <div class="row">
        <div class="col-md">
            <p id="employeeIdReimbursementForm">Employee Id:</p><br>
        </div>
        <div class="col-md">
            <input id="employeeIdBox" type="number" min="1" step="1" max="10000" name="employeeIdBox" required>
        </div>
        <div class="col-md">
        <button type="button" id="submitRequest2" onclick="getEmployeeReimbursementsById(employeeIdBox)">Submit</button>
        </div>
        </div>
 </form>
    </div>
    </div>
    `
}

function employeeManageInformation(){
    document.getElementById("main").innerHTML = `
    <nav id="employeeNav">
    <div class="row">
        <div class="col-md">
            <p id="employeeHome" onclick="employeeLogin()">Home</p>
        </div>
        <div class="col-md">
        <p id="logout" onclick="login()">Log Out</p>
        </div>
    </nav>
    <h2 id="personalInformation">Personal Information:</h2>
    <form id="employeeInformationForm">
    <div class="row">
    <div class="col-md">
        <p class="employeeInfo">Employee Id (Cannot Change):</p>
    </div>
    <div class="col-md">
        <input id="employeeId" value="${employeeId}" readonly><br>
    </div>
    </div>
    <div class="row">
        <div class="col-md">
            <p class="employeeInfo">First Name:</p>
        </div>
        <div class="col-md">
            <input id="employeeFirstName" name="newEmployeeFirstName" value="${employeeFirstName}"><br>
        </div>
    </div>
    <div class="row">
    <div class="col-md">
        <p class="employeeInfo">Last Name:</p>
    </div>
    <div class="col-md">
        <input id="employeeLastName" name="newEmployeeLastName" value="${employeeLastName}"><br>
    </div>
    </div>
    <div class="row">
    <div class="col-md">
        <p class="employeeInfo">Email Address:</p>
    </div>
    <div class="col-md">
        <input id="employeeEmailAddress" name="newEmployeeEmailAddress" value="${employeeEmailAddress}"><br>
    </div>
    </div>
    <div class="row">
    <div class="col-md">
        <p class="employeeInfo">Address:</p>
    </div>
    <div class="col-md">
        <input id="employeeAddress" name="newEmployeeAddress" value="${employeeAddress}"><br>
    </div>
    </div>
    <div class="row">
    <div class="col-md">
    
    </div>
    <div class="col-md">
        <button id="saveEmployeeInfoChangesButton" type="button" onclick="saveEmployeeInfoChanges(newEmployeeFirstName, newEmployeeLastName, newEmployeeEmailAddress, newEmployeeAddress)">Save Changes</button>
    </div>
    </div>

    </form>
    `
}




function getAllEmployees() {
var xhr = new XMLHttpRequest();

xhr.onreadystatechange = function() {
    if(this.readyState === 4 && this.status === 200){
        alert(xhr.responseText);
    }
};

xhr.open("GET", "http://localhost:8080/ExpenseReimbursementSystem/employee");

xhr.send();

}

function getEmployeeReimbursementById(id, type){
var xhr = new XMLHttpRequest();

xhr.onreadystatechange = function() {
    if(this.readyState === XMLHttpRequest.DONE && this.status === 200){

        if(type === "pending"){
        let jsonArray = [];
        jsonArray = JSON.parse(this.responseText);
        let pendingReimbursementTableBodyDOMManipulation = ``;
        for (i in jsonArray){
           json = jsonArray[i];
           let newLocation = json.location.replace(/\\/g,"/");
           pendingReimbursementTableBodyDOMManipulation = pendingReimbursementTableBodyDOMManipulation + `
              <tr>
                <td>${json.reimbursementid}</td>
                <td>${json.title}</td>
                <td>${json.description}</td>
                <td>$${json.amount}</td>
                <td>${json.date}</td>
                <td>Pending</th>
                <td><button class="reimbursementButton" onclick="getReimbursementImage('${newLocation}')">View Receipt</button></td>
              </tr>
           `
        }

        document.getElementById("pendingReimbursementTableBody").innerHTML = pendingReimbursementTableBodyDOMManipulation;

    } else if(type === "resolved"){
        let jsonArray = [];
        jsonArray = JSON.parse(this.responseText);
        let resolvedReimbursementTableBodyDOMManipulation = ``;
        for (i in jsonArray){
           json = jsonArray[i];
           let newLocation = json.location.replace(/\\/g,"/");
           resolvedReimbursementTableBodyDOMManipulation = resolvedReimbursementTableBodyDOMManipulation + `
              <tr>
                <td>${json.reimbursementid}</td>
                <td>${json.title}</td>
                <td>${json.description}</td>
                <td>$${json.amount}</td>
                <td>${json.date}</td>
                <td>Approved</th>
                <td><button class="reimbursementButton" onclick="getReimbursementImage('${newLocation}')">View Receipt</button></td>
              </tr>
           `
    }

    document.getElementById("resolvedReimbursementTableBody").innerHTML = resolvedReimbursementTableBodyDOMManipulation;
        
    }
}
};

xhr.open("POST", "http://localhost:8080/ExpenseReimbursementSystem/downloadReimbursementServlet");

let sendParameters = [id, type];

xhr.send(sendParameters);

}

function saveEmployeeInfoChanges(newEmployeeFirstName, newEmployeeLastName, newEmployeeEmailAddress, newEmployeeAddress){
    if(employeeFirstName === newEmployeeFirstName.value && employeeLastName === newEmployeeLastName.value && employeeEmailAddress === newEmployeeEmailAddress.value && employeeAddress === newEmployeeAddress.value){
        alert("No changes made to save!");
    } else if(newEmployeeFirstName.value == "" || newEmployeeLastName.value == "" || newEmployeeEmailAddress == "" || newEmployeeAddress == ""){
        alert("At least one of the fields is empty, try again!");
    } else {

        (function() {
            let xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function(){
                if(this.readyState === XMLHttpRequest.DONE && this.status === 200){
                    if(xhr.responseText == "true"){
                        employeeFirstName = newEmployeeFirstName.value;
                        employeeLastName = newEmployeeLastName.value;
                        employeeEmailAddress = newEmployeeEmailAddress.value;
                        employeeAddress = newEmployeeAddress.value;
                        alert("Changes saved!");
                    } else {
                        alert("Oops something went wrong, try again!");
                    }
                }
            };

            xhr.open("POST", "http://localhost:8080/ExpenseReimbursementSystem/singleEmployee", false);

            let employeeInfoChanges = JSON.stringify({"employeeid":employeeId,"firstname":newEmployeeFirstName.value,"lastname":newEmployeeLastName.value,"emailaddress":newEmployeeEmailAddress.value,"address":newEmployeeAddress.value});

            xhr.send(employeeInfoChanges);

        })();
        
    }
}

function uploadReimbursementInit(titleBox, description, amount, file){
    let imageList = file.files;
    if(titleBox.value == ""){
        alert("Enter in a title for the request!");
    } else if(description.value == ""){
        alert("Enter in a description for the request!");
    } else if(amount.value == ""){
        alert("Enter in an amount for the request!");
    } else if(imageList.length == 0){
        alert("Attach a receipt for the request!");
    } else {
    uploadReimbursement((copyForm) => {
        let xhr = new XMLHttpRequest();
    
        xhr.onreadystatechange = function(){
            if(this.readyState === XMLHttpRequest.DONE && this.status === 200){
                if(this.responseText == "true"){
                    alert("Upload successful!");
                    document.getElementById("titleBox").value = "";
                    document.getElementById("descriptionBox").value = "";
                    document.getElementById("amountBox").value = "";
                    document.getElementById("reimbursementImage").value = "";
                }
            }
        };
    
        xhr.open("POST","http://localhost:8080/ExpenseReimbursementSystem/uploadReimbursement", false);
    
    
        xhr.send(copyForm);
    });
}
}

function uploadReimbursement(callback){

    let copyForm = new FormData(document.getElementById("reimbursementForm"));
    copyForm.append("employeeid",employeeId);

    callback(copyForm);

}

function getReimbursementImage(path){
    console.log(path);
    (function(){
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState === XMLHttpRequest.DONE && this.status === 200){
            window.open("http://localhost:8080/ExpenseReimbursementSystem/getReceipt");
        }
    };

    xhr.open("POST","http://localhost:8080/ExpenseReimbursementSystem/getReceipt", false);


    xhr.send(path);
    })();
    
}

function employeeResolvedReimbursements(){
    getEmployeeReimbursementById(employeeId,"resolved");
    document.getElementById("main").innerHTML = `
    <nav id="employeeNav">
    <div class="row">
        <div class="col-md">
            <p id="employeeHome" onclick="employeeLogin()">Home</p>
        </div>
        <div class="col-md">
        <p id="logout" onclick="login()">Log Out</p>
        </div>
    </div>    
    </nav>
    <div id="welcomeEmployee">
            <h3 id="resolvedReimbursementRequests">Resolved Reimbursement Requests:</h3>
            <div id="resolvedRequests">
            <table class="table" id="resolvedReimbursementTable">
            <thead>
              <tr>
                <th scope="col">Reimbursement Id</th>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col">Amount</th>
                <th scope="col">Date</th>
                <th scope="col">Status</th>
                <th scope="col">Receipt</th>
              </tr>
            </thead>
            <tbody id="resolvedReimbursementTableBody">

            </tbody>
            </table>
            </div>
        </div>
        `
}
