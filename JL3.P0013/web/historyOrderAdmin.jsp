<%-- 
    Document   : historyOrderAdmin
    Created on : Jan 23, 2021, 1:44:32 PM
    Author     : tanta
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cart Page</title>
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
                margin: auto;
                top:0; left: 0; right: 0; bottom:0;
                /*margin:0 300px;*/

                margin-top: 60px;
                overflow: auto;
                padding: 0 20px;
            }
            #main-content {	
                position : absolute;
                width: 100%;
                margin-top: 10px;
            }
            .product-box{
                position: relative;
                box-sizing: border-box;
                height: 180px;
                width: 65%;
                margin-bottom: 10px;
                background-image: linear-gradient(to bottom,#e8eae6,#cdd0cb);
                padding-left: 5px;
                padding-top: 20px;
                float: left;
            }
            #totalCart {
                position: absolute;
                top :0;
                right: 0;
                margin-right:50px;
                box-sizing: border-box;
                height: 180px;
                width: 25%;
                /* background-image: linear-gradient(to left,#e8eae6,#cdd0cb); */
                background-color: white;
            }
            #totalCart p{
                /*position : absolute;*/
                /*left : 30%;*/
                /*top: 0%;*/
                box-sizing: border-box;
                width : 50%;
                float : left;
                padding: 0 10px;
                -ms-transform: translateY(-50%);
                transform: translateY(-50%);
                font-size : 30px;
            }
            #totalCart #price {
                color: red;
            }
            .product-box div {
                position: relative;
                box-sizing: border-box;
                display: block;
                height: 100%;
                width: 20%;
                float: left;
            }
            .product-box .product-content {
                display: inline-block;
                width: 70%;
                height: 100%;

            }
            .product-box div .imgProduct {
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
                font-size: 20px;
                line-height: 20px;
                height: 60%;
                padding-left: 20px;
                font-weight: bold;
                word-wrap: break-word;
                float:left;
                box-sizing: border-box;
            }
            .name {
                width: 40%;
                position: relative;
                margin: 0;
                top: 50%;
                -ms-transform:translateY(-50%);
                transform: translateY(-50%);
            }
            .price {
                width: 20%;
                color: red;
                position: relative;
                margin: 0;
                top: 50%;
                -ms-transform:translateY(-50%);
                transform: translateY(-50%);
            }
            .product-box .inputQuantity {
                display: inline-block;
                box-sizing: border-box;
                text-align: center;
                width: 40%;
            }
            .product-box #updateBtn {
                display: block;
                background-color: #00af91;
                color: white;
                margin: 0 auto;
                font-size: 15px;
                outline: none;
                border:none;
                width: 80px;
                height: 40px;
                position: relative;
                top: 30%;
                -ms-transform:translateY(-50%);
                transform: translateY(-50%);
            }
            .product-box #updateBtn:hover {
                cursor :pointer;
                background-color: #007965;
            }

            .product-box .inputQuantity input[type=number] {
                position: relative;
                top: 30%;
                -ms-transform:translateY(-50%);
                transform: translateY(-50%);
                height: 20px;
                font-size: 20px;
                color: black;
                text-align: center;
                height: 40px;
                width: 50%;
                border-radius: 30px;
                border:none;
                background-color: white;
                padding: 0 20px;
                outline: none;
                border: 1px solid black;
            }

            .product-box button:active {
                color:#11698e;
            }
            .inputQuantity button {
                position: relative;
                top:30%;
                display: block;
                background-color: #ff0000;
                color: white;
                margin: 0 auto;
                font-size: 15px;
                outline: none;
                border:none;
            }
            .inputQuantity button:hover {
                background-color: #ff6666;
                color: black;
                cursor: pointer;
            }
            #btnOrder {
                position: absolute;
                bottom : 0;
                left : 50%;
                -ms-transform:translateX(-50%); 
                transform: translateX(-50%);
                display: inline-block;
                width: 20%;
                height : 40px;
                background-color: #f05454;
                padding: 0;
                margin : 0 auto;
                margin-bottom : 20px;
                border: none;
                font-weight: bold;
            }
            #btnOrder:hover {
                background-color: #f37272;
                cursor:pointer;
            }

            @keyframes scale {
                0%   {transform: scale(1);}
                100% {transform: scale(1.05);}
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div id="contain-taskbar">
                <div id="taskbar">
                    <div id="icon" onclick="location.href = 'ViewAdminController'"><img src="img/iconT.png"></div>
                    <div id="contain-dropdown">
                        <button id="userBar" class="drop-btn" value="user-drop">
                            <div><img class="drop-btn"  value="user-drop" src="img/iconProfile.png"></div>
                            <div value="user-drop" class="drop-btn" id="name">${sessionScope.user.name} </div>
                        </button>
                        <div id="user-drop" class="dropdown-content">
                            <form >
                                <button type="submit" name="action" value="LogOut">Log Out</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div id="main">
                <div id="main-content">
                    <c:forEach items="${requestScope.cartProduct.productCart.values()}" var="product" varStatus="counter">
                        <c:url value="UpdateProductCartController" var="updateProductURL">
                            <c:param name="productID" value="${product.id}"></c:param>
                            <c:param name="productQuantity" value="getValue(${product.id});"></c:param>
                        </c:url>
                        <form  action="MainController" method="POST">
                            <input type="hidden" name="productID" value="${product.id}"/>
                            <div class="product-box" >
                                <div>
                                    <img class="imgProduct" src="img/${product.image_File_Name}">
                                </div>
                                <div class="product-content">
                                    <p class="name">${product.name}</p>
                                    <p class="price">
                                        <fmt:formatNumber value="${product.price}" maxFractionDigits="0"/> VND
                                    </p>
                                    <div class="inputQuantity">
                                        <input id="proQuan${product.id}" type="number" name="productQuantity" min="1" value="${product.quantity}" required="" readonly="" >
                                    </div>
                                </div>
                            </div>
                        </form>

                    </c:forEach>
                    <div id="totalCart">
                        <p>Total : </p>
                        <p id="price">
                            <fmt:formatNumber value="${requestScope.cartProduct.total()}" maxFractionDigits="0"/> VND                            
                        </p>
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

