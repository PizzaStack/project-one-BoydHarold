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
        <button id="managerLoginButton" onclick="authenticateManager(username, password)">Login</button>
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

            }
        }

        xhr.open("POST", "http://localhost:8080/ExpenseReimbursementSystem/authentication", false);

        let credentialArray = [username.value, password.value, "employee"];

        xhr.send(credentialArray);

     })();

        (function() {   
            let xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if(this.readyState === 4 && this.status === 200){
                    if(xhr.responseText == "true"){
                        employeeLogin();
                    } else {
                        alert("Incorrect credentials, try again!");
                    }
                }
    
            };


            xhr.open("GET", "http://localhost:8080/ExpenseReimbursementSystem/authentication", false);
            xhr.send();

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
                <p id="employeeInformation">My Information</p>
            </div>
            <div class="col-md">
                <p id="employeeReimbursements" onclick="employeeManageReimbursements()">Manage Reimbursements</p>
            </div>
            <div class="col-md">
                <p id="logout" onclick="login()">Log Out</p>
            </div>
        </nav>
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
        <form>
        <h2>
        Submit Request:
        </h2>
        Title:<br>
        <input id="titleBox" type="text" name="title" required autofocus><br>
        Description:<br>
        <textarea id="descriptionBox" type="text" name="description" required autofocus></textarea><br>
        Amount:<br>
        <input id="amountBox" type="number" min="0.01" step="0.01" max="10000" name="amount" required autofocus><br>   
        <br>
        <input id="reimbursementImage" type="file" name="reimbursementImage" required><br>
        <br>
        <button id="submitRequest" onclick="authenticateEmployee(username, password)">Submit</button>
        </form>
    </div>
    </div>
    `
}





function getAllEmployees() {
var xhr = new XMLHttpRequest();

xhr.onreadystatechange = function() {
    if(this.readyState === 4 && this.status === 200){
        alert(xhr.responseText);
    }
};

xhr.open("GET", "EmployeeServlet");

xhr.send();

}