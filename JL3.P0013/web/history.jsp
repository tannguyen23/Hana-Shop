<%-- 
    Document   : history
    Created on : Jan 22, 2021, 9:47:23 PM
    Author     : tanta
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Main Page</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
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
            #sideBar {
                display: block;
                position: fixed;
                background-image: linear-gradient(to bottom,#222831,#393e46);
                width: 20%;
                height: 100%;
                margin-top:60px;
                padding-top:50px;

            }

            #sideBar button {
                display: block;
                width: 100%;
                border: none;
                color: white;
                background-color: #121013;
                padding: 10px 25px;
                text-align: center;
                text-decoration: none;
                font-size: 18px;
                border-bottom :1px outset white;
            }
            #sideBar button:hover {
                cursor : pointer;
                background-color:#76687d;
                color: black;
            }
            #searchBox {
                display: block;
                box-sizing: border-box;
                width: 100%;
                height: 400px;
                background-image: linear-gradient(to bottom,#222831,#393e46);
                border-top: 2px #79a3b1 solid;
                border-bottom: 2px #79a3b1 solid;
                margin :10px 0;
                margin-top: 50px;
            }
            #searchTittle {
                display: block;
                background-color:  #67707e;
                box-sizing: border-box;
                width: 100%;
                height: 30px;
                text-align: center;
                font-size: 20px;
                color: #f8f7de;
                border-bottom: 1px #ffffff outset;
            }
            .searchBar {
                display: inline-block;
                width: 100%;
                height: 70px;;
                line-height: 50px;
                font-size: 16px;
                color: white;
                font:sans-serif;
                padding: 0 5px;
                margin-bottom: 20px;
                text-align: center;
            }
            #contain-searchBtn button {
                width: 50%;
                margin: 0 auto;
                border-radius: 30px;
                height: 45px;
                padding-top :0;
                background-color: #f2d974;
            }
            #contain-searchBtn button:hover {
                background-color: #ebc52d;
                cursor: pointer;
            }
            .tittle {
                display: inline-block;
                font-size: 17px;
                height: 14px;
                /*line-height: 17px;*/
                float:left;
            }
            .searchBar input {
                box-sizing: border-box;
                font-size: 20px;
                color: white;
                height: 40px;
                width: 80%;
                border-radius: 30px;
                border:none;
                background-color: #5c6470;
                padding: 0 20px;
                margin-top: 20px;
                margin-left: 10px;
                outline: none;
            }
            .searchBar input[type=number] {
                display: inline-block;
                width: 40%;
            }
            .searchBar input::placeholder {
                text-align: center;
                color: #d5d8dd;
                font-size: 15px;
            }
            #priceBar {
                display: inline-block;
                width: 80%;
            }
            .custom-select {
                display: inline-block;
                position: relative;
                margin-left: 20px;
                margin-top :20px;
                outline:none;
                border: none;
                font-family: Arial;
                font-size: 15px;
                text-align: center;
                width: 140px;
                height: 45px;
                line-height: 50px;
                background-color: #5c6470;
                color: white;
                text-align: center;
                text-align-last: center;
            }
            #contain-taskbar {
                width: 100%;
                position: fixed;
                box-sizing: border-box;
                height: 60px;
                margin-top: 0px;
                padding-top: 0px;
                z-index: 3;

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
            #searchBar input::placeholder {
                text-align: center;
                color: #d5d8dd;
                font-size: 15px;
            }
            #cartBtn {
                display: inline-block;
                position: absolute;
                right: 300px;
                top: 0;
                width: 100px;
                height: 100%;
                text-align: center;
            }
            #cartBtn button {
                background-color: transparent;
                width: 100%;
                height: 100%;
                outline: none;
                border: none;
                cursor: pointer;
            }
            #cartBtn i {
                position: relative;
            }
            #cartBtn #cartNumber {
                position: absolute;
                box-sizing: border-box;
                background-color: red;
                width: 30px;
                height: 30px;
                font-size: 20px;
                right: -30%;
                top: -20%;
                border-radius: 50%;
                text-align: center;
                padding-top: 5px;
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
            #main {
                position: absolute;
                margin: auto;
                top:0; left: 0; right: 0; bottom:0;
                margin-top: 60px;
                overflow: auto;
                padding: 0 20px;
                z-index: 1;
                text-align: center;
                margin-left:20%;
            }
            #main-content { 
                width: 100%;
                margin-top: 10px;
                /*background-color: white;*/
                animation: fadeIn 2s;
            }

            .contain-img {
                position: relative;
            }
            .imgProduct {
                position: absolute;
                top: 50%;
                left: 50%;
                -ms-transform: translate(-50%, -50%);
                transform: translate(-50%, -50%);
                background-color: red;
                max-width: 70px;
                max-height: 70px;
                overflow: hidden;
            }
            #pageBar {
                position: absolute;
                bottom: 20px;;
                left: 0;
                right: 0;
                z-index: 2;
            }
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                text-align: center;
            }
            ul li {
                margin-top: 20px;
                display: inline-block;
                width: 40px;
                height: 40px;
                line-height: 40px;
                background-color: #e8eae6;
            }
            ul li a {
                display: inline-block;
                width: 100%;
                height: 100%;
                text-decoration: none;
            }
            ul li a:hover{
                background-color: #16c79a;
            }

            #tableProduct {
                font-family: Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 80%;
                background-color: white;
            }

            #tableProduct td, #customers th {
                border: 1px solid #ddd;
                padding: 8px;
                height: 50px;
            }
            #tableProduct th {
                height: 20px;
            }

            #tableProduct tr:nth-child(even){background-color: #f2f2f2;}

            #tableProduct tr:hover {background-color: #ddd;}

            #tableProduct th {
                padding-top: 12px;
                /*padding-bottom: 12px;*/
                text-align: center;
                background-color: #4CAF50;
                color: white;
            }
            #tableProduct button {
                border: none;
                color: white;
                padding: 10px 25px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 14px;
            }
            #tableProduct .showBtn {
                background-color: #16c79a;
            }
            #tableProduct .showBtn:hover {
                background-color: #30e8ba;
                cursor: pointer;
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
                    <div id="icon" onclick="location.href = 'ViewMainController'"><img src="img/iconT.png"></div>
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
            <div id="sideBar">
                <form action="MainController" method="POST">
                    <button type="submit">History Order</button>
                    <input type="hidden" name="action" value="ViewHistoryOrder" />
                </form>
                <form action="MainController" method="POST">
                    <div id="searchBox">
                        <div id="searchTittle">Search Product</div>
                        <div class="searchBar">
                            <p class="tittle" >Name : </p> 
                            <input type="text" name="txtName" placeholder="Search By Name" value="${requestScope.txtName}">
                        </div>
                        <div class="searchBar">
                            <p class="tittle">From : </p>
                            <div id="priceBar">
                                <input type="date" name="txtMin" placeholder="From" value="${requestScope.txtMin}"/>
                            </div>
                        </div>
                        <div class="searchBar">
                            <p class="tittle">To : </p>
                            <div id="priceBar">
                                <input type="date" name="txtMax" placeholder="To" value="${requestScope.txtMax}">
                            </div>
                        </div>
                        <div class="searchBar">
                            <div id="contain-searchBtn" class="searchBar">
                                <button type="submit" id="searchBtn">Search</button>
                                <input type="hidden" name="action" value="Search History"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div id="main">
                <div id="main-content">
                    <table id="tableProduct">
                        <tr>
                            <th>Order ID</th>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th></th>

                        </tr>
                        <c:forEach items="${requestScope.listOrder}" var="orderDTO" varStatus="counter">
                            <tr>
                                <td>${orderDTO.orderID}</td>
                                <td>${orderDTO.productName}</td>
                                <td>${orderDTO.quantity}</td>
                                <td>
                                    <fmt:formatNumber value="${orderDTO.price}" maxFractionDigits="0"/> VND
                                </td>
                                <td>
                                    <form action="MainController" method="POST">
                                        <button class="showBtn" type="submit">
                                            Show this order
                                        </button>
                                        <input type="hidden" name="orderID" value="${orderDTO.orderID}"/>
                                        <input type="hidden" name="action" value="ViewHistoryCartOrder" />
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
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

