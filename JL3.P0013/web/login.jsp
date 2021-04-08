<%-- 
    Document   : index
    Created on : Jan 8, 2021, 11:29:35 AM
    Author     : tanta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <style type="text/css">
            html, body {
                height: 100%;
                margin: 0px;
            }
            .container {
                height: 100%;
                background-image: linear-gradient(to left top, #0a043c,#cdf0fe, #03506f);
            }
            .container .mainer {
                color: white;
                font-size: 35px;
                display: inline-block;
                width: 100%;
                height: 97%;
            }
            .left-side {
                display: flex;
                box-sizing: border-box;
                width: 40%;
                float: left;
                height: 100%;
                align-items:center;
            }
            .left-side #logo img {
                margin-left: 400px;
                max-width: 500px;
            }
            .right-side {
                font-family: sans-serif;
                display: flex;
                box-sizing: border-box;
                width: 60%;
                height: 100%;
                justify-content: center;
                align-items: center;

            }
            .right-side #loginForm {
                display: block;            
                color: black;
                border-radius: 30px;
                padding :50px 20px;
                width: 600px;
                height: 310px;
                /*border: 10px hidden ;*/
                background-image: linear-gradient(to bottom right,#bbbbbb, #ffe3d8);
                box-shadow: 20px 20px 80px black;
                font-size:25px;
                float: left;
            }
            #footer {
                color:  #666666;
                background-color: #ffe3d8;
                /*border-top: outset black;*/
                height: 3%;
                font-size: 20px;
                text-align: left;
                padding: 0 30px;
            }
            .input {
                margin : 8px 5px;
                padding : 5px ;
                border-radius: 10px;
                width: 90%;
                height: 30px;
                border:1px solid  #cccccc;
                font-size: 20px;
            }
            a {
                color: #4d4d4d;
                font-size: 20px;
            }
            a:hover {
                color :white;
            }
            .button {
                text-shadow: 2px 2px 20px white;
                display: inline-block;
                color:white;
                background-color: #16c79a;
                margin-left: 100px;
                text-align: center;
                border:none;
                border-radius: 20px;
                padding: 10px;
                font-family: Comic Sans MS;
                font-size: 25px;
                width: 200px;
                height: 50px;
                line-height: 20px;   
            }
            .button:hover {
                color: #29293d;
                cursor: pointer;
            }
            .error {
                color: red;
                text-shadow:none;
                font-size: 20px;
                font-family:Times;
            }
            #containBtnLogGG{

                background-color: green;
            }
            #btnLogGG {
                display: inline-block;
                width: 100%;
                background-color: #f05454;
                margin: 30px 0;
                padding: 0;
            }

        </style>
        <script type="text/javascript">
            function errorPassword() {
                document.getElementById('errorPassword').innerHTML = 'You password is incorrect';
            }
            function errorUsername() {
                document.getElementById('errorUsername').innerHTML = 'Could not find this account';
            }
        </script>
    </head>
    <body>

        <div class="container">
            <div class="mainer">  
                <div class="left-side">
                    <div id="logo">
                        <img src="img/logoT.png">
                    </div>  
                </div>
                <div class="right-side">
                    <div id="loginForm">
                        <form action="MainController" method="POST">
                            <div>
                                UserID <div id="errorUsername" style="display: inline;color:red">${requestScope.errorLogin}</div>
                                <br>
                                <input type="text" class="input" name="txtUserID" required="">
                            </div>
                            <div>
                                Password<br>
                                <input type="password" class="input" name="txtPassword" required="">
                                <div id="errorPassword" class="error"><br></div>
                            </div>
                            <a href="MainController?action=Paging">Continue without login</a>
                            <input type="submit" class="button" name="action" value="Login">
                        </form>
                        <button id="btnLogGG" class="button"><i class="fab fa-google"></i> 
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/JL3.P0013/login-google&response_type=code
                               &client_id=457195291269-04pf6bq60h0vgra0ljavnqr7bfkcqkb7.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>
                        </button>
                    </div>
                </div>
            </div>
            <div id="footer">
                @ This website created by Tan Nguyen
            </div>
        </div>
    </div>
</body>

</html>
