var employeeId = "";
var employeeFirstName = "";
var employeeLastName = "";
var employeeEmailAddress = "";
var employeeAddress = "";

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
        managerLogin();
    }
}

function employeeLogin(){
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
            <h2>Welcome ${employeeFirstName} ${employeeLastName}!</h2>
            <h3>Pending Reimbursement Requests:</h3>
            <div id="pendingRequests">
            <table class="table">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">First</th>
                <th scope="col">Last</th>
                <th scope="col">Handle</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
              </tr>
              <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
              </tr>
              <tr>
                <th scope="row">3</th>
                <td>Larry</td>
                <td>the Bird</td>
                <td>@twitter</td>
              </tr>
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
                <p id="managerInformation">Employee Information</p>
            </div>
            <div class="col-md">
                <p id="managerReimbursements">Manage Reimbursements</p>
            </div>
            <div class="col-md">
            <p id="logout" onclick="login()">Log Out</p>
            </div>
        </nav>
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
            <p id="employeeInformation">View Pending Reimbursements</p>
        </div>
        <div class="col-md">
            <p id="employeeReimbursements" onclick="employeeManageReimbursements">View Resolved Reimbursements</p>
        </div>
        <div class="col-md">
        <p id="logout" onclick="login()">Log Out</p>
        </div>
    </nav>
    <div id="submitReimbursement">
        <form id="reimbursementForm" enctype="multipart/form-data">
        <h2>
        Submit Request:
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
        <p>Employee Id (Cannot Change):</p>
    </div>
    <div class="col-md">
        <input id="employeeId" value="${employeeId}" readonly><br>
    </div>
    </div>
    <div class="row">
        <div class="col-md">
            <p>First Name:</p>
        </div>
        <div class="col-md">
            <input id="employeeFirstName" name="newEmployeeFirstName" value="${employeeFirstName}"><br>
        </div>
    </div>
    <div class="row">
    <div class="col-md">
        <p>Last Name:</p>
    </div>
    <div class="col-md">
        <input id="employeeLastName" name="newEmployeeLastName" value="${employeeLastName}"><br>
    </div>
    </div>
    <div class="row">
    <div class="col-md">
        <p>Email Address:</p>
    </div>
    <div class="col-md">
        <input id="employeeEmailAddress" name="newEmployeeEmailAddress" value="${employeeEmailAddress}"><br>
    </div>
    </div>
    <div class="row">
    <div class="col-md">
        <p>Address:</p>
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