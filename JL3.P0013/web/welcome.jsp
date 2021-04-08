<%-- 
    Document   : welcome
    Created on : Jan 8, 2021, 4:09:51 PM
    Author     : tanta
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>test2Js</title>
        <style type="text/css">
            html, body {
                height: 100%;
                margin: 0px;
                /*overflow: hidden;*/
            }
            #container #main {
                color: white;
                font-size: 40px;
                display: inline-block;
                width: 100%;
                height: 100%;
                overflow: hidden;
            }
            .top-side {
                box-sizing: border-box;
                width:100%;
                height:50%;
                text-align: center;
                align-items: center;
            }
            .top-side img {
                max-width: 100%;
            }

            .bottom-side {
                display: block;
                text-align:center;
                color: white;
                box-sizing: border-box;
                height: 100%;
            }
            .bottom-side #text{
                display: inline-block;
                font-size:60px;
                font-family: Sans-serif;
                animation: fadeIn 3s;
            }

            #button {
                border: none;
                color:white;
                background:none;
                display: inline-block;
                font-size: 30px;
                text-decoration:none;
                margin-left: 50%;
                border-top: 1px solid white;
                border-bottom: 1px solid white;
            }
            #button:hover{
                background-color: #ccff99;
                color : black;
                cursor:pointer;
            }
            #button:visited{
                text-decoration:none;
            }
            #button:active{
                color: red;
            }
            #container {
                height: 100%;
                background-image: linear-gradient(to left top, #0a043c,#cdf0fe, #03506f);
            }
            #main {
                display: none;
                /*animation: fadeIn 3s;*/
            }
            #load {
                display: block;
                position: absolute;
                margin: auto;
                top:0; left: 0; right: 0; bottom:0;
                border: 16px solid #f3f3f3; /* Light grey */
                border-top: 16px solid #3498db; /* Blue */
                border-bottom: 16px solid #3498db;
                border-radius: 50%;
                width: 120px;
                height: 120px;
                animation: spin 2s linear infinite,fadeOut 3s;
            }
            #detail {
                color: #eff8ff;
                font-size: 30px;
                font-family: Times;
                padding-left: 80px;
            }

            @keyframes spin {
                0% { transform: rotate(0deg);}
                100% { transform: rotate(360deg); }
            }
            @keyframes fadeIn {
                0% {opacity: 0%;}
                100%{opacity: 100%;}
            }
            @keyframes fadeOut {
                0% {opacity: 100%;}
                100%{opacity: 0%;}
            }
            @keyframes toRight {
                0%{
                    opacity: 0%;
                    transform:translate(-500px);
                }
                100%{
                    opacity: 100%;
                    transform:translate(0px);
                }
            }
            @keyframes toLeft {
                0%{
                    opacity: 0%;
                    transform:translate(300px);
                }
                100%{
                    opacity: 100%;
                    transform:translate(0px);
                }

            }


        </style>
        <script type="text/javascript">
            function preLoad() {
                document.getElementById("main").style.display = 'block';
                document.getElementById("load").style.display = 'none';
            }
            function loader() {
                setTimeout('preLoad()', 1000)
            }

        </script>
    </head>
    <body onload ="loader()">
        <div id="load"></div>
        <div id="container">
            <div id="main">
                <div class="top-side">
                    <img src="img/welcome.png">
                </div>
                <div class="bottom-side">
                    <div id="text">
                        <c:if test="${empty sessionScope.email}">
                        <p>Welcome ${sessionScope.user.name} to Hana Shop
                        </p>
                        </c:if>
                        <c:if test="${not empty sessionScope.email}">
                        <p>Welcome ${sessionScope.email} to Hana Shop
                        </p>
                        </c:if>
                        <p id="detail">A place to buy food, drink,... </p>
                        </p>
                        <form action="ViewMainController" method="POST">
                            <button id="button" type="submit">Shopping</button>
                            <input type="hidden" name="action" value="ViewMain">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
