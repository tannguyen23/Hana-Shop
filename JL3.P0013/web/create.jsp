<%-- 
    Document   : create
    Created on : Jan 20, 2021, 4:02:15 PM
    Author     : tanta
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Main Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />

        <style type="text/css">
            html,body {
                height : 100%;
                margin : 0;
                padding: 0;
            }
            .myContainer {
                position: fixed;
                width: 100%;
                height : 100%;
                background-image: linear-gradient(to left top, #0a043c,#cdf0fe, #03506f);
            }
            #contain-taskbar {
                width: 100%;
                position: fixed;
                box-sizing: border-box;
                height: 60px;
                margin-top: 0px;
                padding-top: 0px;
            }
            #taskbar {
                position: relative;
                box-sizing: border-box;
                padding:5px;
                height: 60px;
                border-bottom: 1px outset #fcf8ec;
                background-image: linear-gradient(to bottom,#222831,#393e46);
            }
            #taskbar #icon {
                display: inline-block;
                width: 60px;
                float:left;
            }
            #icon:hover {
                cursor :pointer;
            }
            #taskbar #icon img{
                max-height: 50px;
                max-width: 50px;
            }
            #taskbar #searchBar {
                display: inline-block;
                width: 300px;
                height: 100%;
                line-height: 50px;
            }
            #taskbar #searchBar input {
                font-size: 20px;
                color: white;
                height: 40px;
                width: 100%;
                border-radius: 30px;
                border:none;
                background-color: #5c6470;
                padding: 0 20px;
                outline: none;
            }
            #searchBar input::placeholder {
                text-align: center;
                color: #d5d8dd;
                font-size: 15px;
            }
            #contain-dropdown {
                display: inline-block;
                border-radius: 10px;
                position: absolute;
                right: 0;
                top:0;
                width: 300px;
                height: 120px;
            }
            .dropdown-content {
                display: none;
                margin-top: 55px;   
                margin-right: 10px;
                background-color: #393e46;  
                border-radius: 10px;    
            }

            .dropdown-content button {
                display: block;
                width: 100%;
                height: 50px;
                font-size: 20px;
                color: white;
                outline: none;
                background-color: #454b54;
                text-decoration: none;
                padding: 10px;
                padding-left: 20px;
            }
            .dropdown-content button:hover {
                cursor:pointer;
                background-color: #456268;
            }

            #userBar {
                position: absolute;
                display: flex;
                justify-content: center;
                align-items: center;
                right: 0;
                top:0;
                box-sizing: border-box;
                background-color: #454b54;
                width: 300px;
                height: 40px;
                border-radius: 30px;
                padding: 10px;
                margin: 10px 0;
                outline: none;
                border:none;
            }
            #userBar:hover {
                cursor:pointer;
                background-color: #456268;
            }
            #userBar:active {
                background-color: #737d8c;
            }
            #userBar div img {
                display: inline-block;
                max-height: 50px;
                max-width: 50px;
                float: left;
            }
            #userBar #name {
                display: inline-block;
                margin: 0 auto;
                font-family: Times;
                font-size: 20px;
                color: white;
            }
            .container #main {
                color: white;
                display: flex;
                width: 100%;
                height: 97%;
                display: none;
                animation: fadeIn 2s;
            }
            #main-content { 
                width: 100%;
                margin-top: 10px;
                animation: fadeIn 2s;
            }

            #box{
                position: relative;
                width: 700px;
                height: 800px;
                border-radius: 30px;
                box-shadow: 20px 20px 80px black;
                background-image: linear-gradient(to left top,#16a596,#898b8a);
                /*background-color: red;*/
                margin: 0 auto;
                margin-top: 20px;
            }
            #footer {
                animation: fadeIn 2s;
            }
            label {
                display: block;
                font-size: 20px;
            }
            #tittle {
                font-size: 25px;
                position: absolute;
                width: 100%;
                box-sizing: border-box;
                text-align: center;
                background-color: #c5a880;
                border-top-left-radius: 30px;
                border-top-right-radius: 30px;
                padding: 2px;
            }
            #footForm {
                font-size: 35px;
                position: absolute;
                width: 100%;
                box-sizing: border-box;
                text-align: center;
                background-color: #c5a880;
                border-bottom-left-radius: 30px;
                border-bottom-right-radius: 30px;
                padding: 10px;
                bottom:0;
            }
            #formRegister {
                margin: 30px;
                margin-top: 80px;
                padding-top: 40px;
            }
            #formRegister .textInput {
                width: 100%;
                height: 35px;
                margin : 5px 0;
                box-sizing: border-box;
                font-size: 20px;
                padding: 20px;
            }
            #formRegister .chkLabel {
                display: inline-block;
                margin-right: 30px;
            }
            #button {
                color: white;
                font-family:Comic Sans MS;
                font-size: 20px;
                display: block;
                width: 200px;
                height: 50px;
                margin: 0 auto;
                margin-top: 150px;
                border-radius: 20px;
                border : none;
                box-shadow: 2px 2px 20px black;
                background-color: #222831;
                text-decoration: bold;
            }
            #button:hover {
                cursor: pointer;
                background-color: #54e346;
                color: #153e90;
            }
            .contain-custom-select {
                display: inline-block;
                float: left;
                width: 50%;
            }
            .custom-select {
                display: inline-block;
                position: relative;
                font-family: Arial;
                font-size: 15px;
                text-align: center;
                width: 150px;
                height: 50px;
                line-height: 50px;
                background-color: #bbf1fa;
                text-align: center;
                text-align-last: center;

            }
            #contain-img {
                height: 200px;
            }
            input[type=file] {
                margin: 10px 0;
                cursor: pointer;
                width: 300px;
                height: 34px;
                overflow: hidden;
                border:none;;
                outline: none;
            }

            input[type=file]:before {
                width: 100px;
                height: 32px;
                font-size: 16px;
                line-height: 32px;
                content: 'Select';
                display: inline-block;
                background: white;
                border: 1px solid #000;
                padding: 0 10px;
                text-align: center;
                font-family: Helvetica, Arial, sans-serif;
            }

            input[type=file]::-webkit-file-upload-button {
                visibility: hidden;
            }

            @keyframes scale {
                0%   {transform: scale(1);}
                100% {transform: scale(1.05);}
            }
            @keyframes fadeIn {
                0% {opacity: 0%;}
                100%{opacity: 100%;}
            }
        </style>

    </head>
    <body>
        <div class="myContainer">
            <div id="contain-taskbar">
                <div id="taskbar">
                    <div id="icon" onclick="location.href = 'ViewAdminController'"><img src="img/iconT.png"></div>

                    <div id="contain-dropdown">
                        <button id="userBar" class="drop-btn" value="user-drop">
                            <div><img class="drop-btn"  value="user-drop" src="img/iconProfile.png"></div>
                            <div value="user-drop" class="drop-btn" id="name">${sessionScope.user.name} </div>
                        </button>
                        <div id="user-drop" class="dropdown-content">
                            <form action="MainController" method="POST">
                                <button type="submit" name="action" value="LogOut">Log Out</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div id="main">
                <div id="main-content">
                    <div id="box">
                        <label id="tittle">Create Product</label>
                        <form id="formRegister" action="CreateProductController" method="POST" enctype="multipart/form-data">
                            <label>Name</label>
                            <input type="hidden" name="productID" value="" />
                            <input class="textInput" type="text" name="txtName" value="" required=""><br>
                            <label>Quantity</label>
                            <input class="textInput" type="number" name="txtQuantity" value="" required=""><br>
                            <label>Price</label>
                            <input class="textInput" type="number" name="txtPrice" value="" required="" min="1000" step="1000"><br>
                            <div id="contain-img">
                                <label>Image</label>
                                <input class="fileInput"  type="file" name="fileImage" required=""><br>
                            </div>
                            <div class="contain-custom-select">
                                <label>Category</label><br>
                                <select id="slCategory" name="slCategory"  class="custom-select" >
                                    <c:forEach items="${requestScope.listCategory}" var="category">
                                        <option value="${category.id}">${category.name}</option>
                                    </c:forEach>
                                </select> 
                            </div>
                            <div class="contain-custom-select">
                                <label>Status</label><br>
                                <select id="slStatus" name="slStatus"  class="custom-select" >
                                    <option value="true">True</option>
                                    <option value="false">False</option>
                                </select> 
                            </div> 
                            <script>
                                document.getElementById("slCategory").value = "${requestScope.listCategory[0].id}";
                                document.getElementById("slStatus").value = "true";
                            </script>
                            <input id="button" type="submit" name="action" value="Create Product">
                        </form>
                        <label id="footForm"></label>
                    </div>
                </div>
            </div>>

        </div>


    </body>
    <script type="text/javascript">
        var tmp = false;
        var buttons = document.getElementsByClassName('drop-btn');
        var contents = document.getElementsByClassName('dropdown-content');

        for (var i = 0; i < buttons.length; i++) {
            buttons[i].addEventListener("click", function () {
                var id = this.value;
                for (var i = 0; i < contents.length; i++) {
                    contents[i].style.display = 'none';
                }
                if (tmp == false) {
                    document.getElementById(id).style.display = 'block';
                    tmp = true;
                } else {
                    document.getElementById(id).style.display = 'none';
                    tmp = false;
                }

            });
        }

        window.addEventListener('click', function () {
            if (!event.target.matches('.drop-btn')) {
                for (var i = 0; i < contents.length; i++) {
                    contents[i].style.display = 'none';
                    tmp = false;
                }
            }
        });
    </script>
</html>

