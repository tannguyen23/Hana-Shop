<%-- 
    Document   : main
    Created on : Jan 9, 2021, 6:36:08 PM
    Author     : tanta
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                z-index :2;
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
            #sideBar  .btnView {
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
            #sideBar .btnView:hover {
                cursor : pointer;
                background-color:#76687d;
                color: black;
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
            #searchBox {
                display: block;
                box-sizing: border-box;
                width: 100%;
                height: 400px;
                background-image: linear-gradient(to bottom,#222831,#393e46);
                border-top: 2px #79a3b1 solid;
                border-bottom: 2px #79a3b1 solid;
                margin :10px 0;
            }
            #searchTittle {
                display: block;
                background-color:  #67707e;
                box-sizing: border-box;
                width: 100%;
                height: 24px;
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
            }
            #contain-searchBtn button {
                border: none;
                color: black;
                padding: 10px 25px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 14px;
                background-color: #f2d974;
                margin-left: 30%;
                border-radius: 30px;
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
                margin-left:20%;
                margin-top: 60px;
                overflow: auto;
                padding: 0 20px;
            }
            #main-content {	
                width: 100%;
                margin-top: 10px;
                animation: fadeIn 2s;
            }
            #main-content .product-box{
                box-sizing: border-box;
                padding: 10px;
                height: 400px;
                width: 24%;
                margin-right: 1%;
                margin-bottom: 10px;
                background-image: linear-gradient(to bottom,#e8eae6,#cdd0cb);
                padding-top: 20px;
                float: left;

            }
            #main-content .product-box:hover {
                border:0.5px outset black;
                cursor: pointer;
                animation-name:scale;
                animation-fill-mode: forwards;
                animation-duration: 0.5s;
            }

            .product-box div {
                position: relative;
                box-sizing: border-box;
                display: block;
                height: 70%;
                width: 100%;
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
                font-size: 20px;
                line-height: 20px;
                text-align: center;
                font-weight: bold;
                padding-top: 5px;
            }
            .price {
                color: red;
            }
            .product-box button {
                box-sizing: border-box;		
                background-color:black;
                color: white;	
                font-size: 20px;
                height: 30px;
                width:50%;
                border-radius: 30px;
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
            #pageBar {
                position: absolute;
                bottom: 20px;;
                left: 0;
                right: 0;
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
                    <c:if test="${ not empty sessionScope.user}">
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
                    </c:if>
                    <c:if test="${empty sessionScope.user}">
                        <div id="contain-dropdown">
                            <button id="userBar" class="drop-btn" value="user-drop">
                                <div><img class="drop-btn"  value="user-drop" src="img/iconProfile.png"></div>
                                <div value="user-drop" class="drop-btn" id="name">Guest</div>
                            </button>
                            <div id="user-drop" class="dropdown-content">
                                <form action="login.jsp" method="POST">
                                    <button type="submit" name="action" value="Login">Login</button>
                                </form>
                            </div>
                        </div>
                    </c:if>

                </div>
            </div>
            <div id="sideBar">
                <form action="MainController" method="POST">
                    <button class="btnView" type="submit">History Order</button>
                    <input  type="hidden" name="action" value="ViewHistoryOrder" />
                </form>
                <form action="MainController" method="POST">
                    <div id="searchBox">
                        <div id="searchTittle">Search Product</div>
                        <div class="searchBar">
                            <p class="tittle" >Name : </p> 
                            <input type="text" name="txtSearch" placeholder="Search By Name" value="${requestScope.txtSearch}">
                        </div>
                        <div class="searchBar">
                            <p class="tittle">Category : </p>
                            <select id="slCategory" name="slCategory"  class="custom-select" >
                                <option value="">All</option>
                                <c:forEach items="${requestScope.listCategory}" var="category">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select> 
                            <script>
                                document.getElementById("slCategory").value = "${requestScope.txtCategory}";
                            </script>
                        </div>
                        <div class="searchBar">
                            <p class="tittle">Price : </p>
                            <div id="priceBar">
                                <input type="number" name="txtMin" placeholder="Min Price" step="1000"  min="0" value="${requestScope.txtMin}" /> -
                                <input type="number" name="txtMax" placeholder="Max Price" step="1000" value="${requestScope.txtMax}" max="${requestScope.maxPrice}">
                            </div>
                        </div>
                        <div id="contain-searchBtn" class="searchBar">
                            <button type="submit">Search</button>
                            <input type="hidden" name="action" value="Search Product"/>
                        </div>
                    </div>
                </form>
            </div>
            <div id="main">
                <div id="main-content">
                    <c:forEach items="${requestScope.listProduct}" var="product">
                        <div class="product-box" onclick="location.href = 'MainController?action=ViewProductDetail&productID=${product.id}'">
                            <div>
                                <img class="imgProduct" src="img/${product.image_File_Name}">
                            </div>
                            <p>${product.name}</p>
                            <c:url value="MainController" var="myAddCartURL">
                                <c:param name="idProduct" value="${product.id}"></c:param>
                                <c:param name="action" value="AddProductCart"></c:param>
                                <c:param name="currentIndex" value="${requestScope.currentIndex}"></c:param>
                            </c:url>
                            <form action="${myAddCartURL}" method="POST">
                                <input type="hidden" name="addQuantity" value="1" />
                                <button type="submit">Add to cart</button>
                            </form>
                            <p class="price"><fmt:formatNumber value=" ${product.price}" maxFractionDigits="0"/> VND</p>
                        </div>
                    </c:forEach>

                </div>
                <div id="pageBar">
                    <ul>

                        <c:forEach begin="1" end="${requestScope.totalPage}" var="index">
                            <c:url value ="MainController" var = "myPagingURL">
                                <c:param name="index" value="${index}"></c:param>
                                <c:param name="action" value="Paging"></c:param>
                            </c:url>
                            <c:url value ="MainController" var = "mySearchPagingURL">
                                <c:param name="index" value="${index}"></c:param>
                                <c:param name="action" value="PagingSearch"></c:param>
                                <c:param name="txtSearch" value="${requestScope.txtSearch}"></c:param>
                                <c:param name="slCategory" value="${requestScope.txtCategory}"></c:param>
                                <c:param name="txtMax" value="${requestScope.txtMax}"></c:param>
                                <c:param name="txtMin" value="${requestScope.txtMin}"></c:param>
                            </c:url>
                            <li>
                                <c:if test="${requestScope.checkSearch == 'true'}">
                                    <a href="${mySearchPagingURL}" id="page${index}">${index}</a>
                                </c:if>
                                <c:if test="${requestScope.checkSearch != 'true'}">
                                    <a href="${myPagingURL}" id="page${index}">${index}</a>
                                </c:if>

                                <c:if test="${index == requestScope.currentIndex}" >
                                    <script>
                                        document.getElementById('page${index}').style.backgroundColor = '#16c79a';
                                    </script>
                                </c:if>
                            </li>
                        </c:forEach>

                    </ul>
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
