<%-- 
    Document   : detail
    Created on : Jan 16, 2021, 6:30:27 PM
    Author     : tanta
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <style type="text/css">
            html,body {
                height : 100%;
                margin : 0;
                padding: 0;
            }
            .container {
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
                z-index: 2;
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
                height: 500px;
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
                box-sizing: border-box;
                margin: auto;
                top:0; left: 0; right: 0; bottom:0;
                margin:0 100px;
                margin-top: 60px;
                margin-bottom: 30px;
                padding: 0 20px;
                overflow: hidden;
                z-index: 1;
            }
            #main-content {	
                width: 100%;
                height: 100%;
                margin-top: 10px;
                animation: fadeIn 2s;
            }
            #main-content .product-box{
                box-sizing: border-box;
                position: relative;
                padding: 10px;
                height: 90%;
                width: 100%;
                margin-right: 1%;
                margin-bottom: 10px;
                background-image: linear-gradient(to bottom,#e8eae6,#cdd0cb);
                padding-top: 20px;
                float: left;
            }
            .product-box #contain-table {
                position: absolute;
                font-size: 20px;
                right: 0;
                bottom: 20%;
                width: 840px;
                height: 300px;
            }
            .inputQuantity {
                position: absolute;
                left: 10%;
                bottom:10%;
                display: block;
                box-sizing: border-box;
                width: 50%;
            }
            .inputQuantity input[type=number] {
                font-size: 20px;
                color: black;
                text-align: center;
                height: 40px;
                width: 50%;
                border:none;
                background-color: white;
                outline: none;
                border: 1px solid black;
                margin-bottom: 20px;
            }

            .product-box #containImg {
                position: relative;
                box-sizing: border-box;
                display: block;
                height: 70%;
                width: 50%;
                float: left;
            }
            .product-box #containImg .imgProduct {
                position: absolute;
                top: 50%;
                left: 50%;
                -ms-transform: translate(-50%, -50%);
                transform: translate(-50%, -50%);
                background-color: red;
                max-width: 100%;
                max-height: 100%;
                overflow: hidden;
            }
            .product-box p {
                display: inline-block;
                font-size: 40px;
                line-height: 20px;
                font-weight: bold;
                /*padding-top: 60px;*/
            }
            .price {
                color: red;
            }
            .quantity {
                color: blue;
            }
            .product-box button {
                box-sizing: border-box;		
                background-color: #ef4f4f;
                color: white;	
                font-size: 20px;
                height: 45px;
                width:50%;
                /*border-radius: 30px;*/
                border:none;
                margin-top: 2px;
                padding: 0 20px;
                outline: none;
                float: left;
            }
            .product-box button:hover {
                background-color:#31326f;
                color: white;
                cursor: pointer;
            }
            .product-box button:active {
                color:#11698e;
            }
            #tableProduct {
                font-family: Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 80%;
                background-color: white;
            }

            #tableProduct td, #tableProduct th {
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
                text-align: left;
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
        <div class="container">
            <div id="contain-taskbar">
                <div id="taskbar">
                    <div id="icon" onclick="location.href = 'ViewMainController'"><img src="img/iconT.png"></div>
                    <form action="ViewCartController">
                        <div id="cartBtn">
                            <button type="submit">
                                <i class="fas fa-shopping-cart fa-3x" style="color: white;">
                                    <c:set var="cartNum" value="${sessionScope.cartProduct.getTotalQuantity()}"></c:set>
                                    <c:if test="${empty cartNum}">
                                        <c:set var="cartNum" value="0"></c:set>
                                    </c:if>
                                    <div id="cartNumber">${cartNum}</div>
                                </i>
                            </button>
                        </div>
                    </form>
                    <div id="contain-dropdown">
                        <button id="userBar" class="drop-btn" value="user-drop">
                            <div><img class="drop-btn"  value="user-drop" src="img/iconProfile.png"></div>
                            <div value="user-drop" class="drop-btn" id="name">${sessionScope.user.name} </div>
                        </button>
                        <div id="user-drop" class="dropdown-content">
                            <form action="MainController" method="POST">
                                <button type="submit" name="action" value="ViewProfile">View My Profile</button>
                                <button type="submit" name="action" value="LogOut">Log Out</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div id="main">
                <div id="main-content">
                    <div class="product-box" >
                        <div id="containImg">
                            <img class="imgProduct" src="img/${requestScope.productDTO.image_File_Name}">
                        </div>
                        <c:url value="MainController" var="myAddCartURL">
                            <c:param name="idProduct" value="${requestScope.productDTO.id}"></c:param>
                            <c:param name="action" value="AddProductCart"></c:param>
                            <c:param name="currentIndex" value="${requestScope.currentIndex}"></c:param>
                        </c:url>
                        <p>Name : ${requestScope.productDTO.name}</p>
                        <br/>
                        <p>Price :</p>
                        <p class="price"> ${requestScope.productDTO.price} VND</p>
                        <br/>
                        <p>Quantity :</p>
                        <p class="quantity"> ${requestScope.productDTO.quantity} </p>
                        <form action="${myAddCartURL}" method="POST">
                            <div class="inputQuantity">
                                <input type="number" name="addQuantity" min="1" value="1" required="">
                                <button type="submit">Add to cart</button>
                            </div>
                        </form>
                        <div id="contain-table">
                            <c:if test="${not empty requestScope.listRecommend}">
                            <p>Usually purchased with<p>
                            <table id="tableProduct">
                                <tr>
                                    <th>Name</th>
                                    <th>Price</th>
                                </tr>
                                <c:forEach items="${requestScope.listRecommend}" var="productRed" varStatus="counter">
                                    <tr>
                                        <td>${productRed.name}</td>
                                        <td>${productRed.price}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                            </c:if>
                        </div>
                    </div>
                </div>

            </div>
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


