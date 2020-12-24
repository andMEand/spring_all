<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="utf-8">
            <style>
                

                ul,
                li {
                    list-style: none;
                }

                a {
                    color: #222;
                    text-decoration: none;

                }
                #wrap {margin: 0 auto; font-size: 40px; color: #fff; text-align: center; text-transform: uppercase;}
                #header {
                    height: 225px;
                }
                #nav { }
                #side_left {  width: 250px; height: 600px;background-color: bisque;}
                #contents { width: 500px; height: 600px;}
                #side_right { width: 250px; height: 600px;background-color: bisque;}
                #footer {clear: both;  height: 225px;}
                
                .conatiner {
                   width: 990px;
                    margin: 0 auto;
                    height: inherit;
                    display: flex;
                    flex-direction: row;
                }
                .ad_menu {
                height: 400px;
                margin: 0 10px;
                padding: 20px;
                }
                .side_cont>.ad_menu{
                    margin:top 0;
                }
                .side_cont2>.ad_et{
                height: 400px;
                margin: 0 10px;
                padding: 20px;
                }
            </style>
            <title>관리자 회원관리</title>
        </head>

        <body>
            <div class="wrap">
                <div id="header">
                    <div class="container">
                        배너 /ADMIN 
                    </div>
                </div>
                <div id="side_left">
                    <div class="side_cont">
                        <div class="container">
                            <div class="ad_menu">
                                <div><a href=""> 게시물관리</a></div>
                                <div><a href=""> 회원관리</a></div>
                                <div><a href=""> 이용권 관리</a></div>
                                <div><a href=""> 책임분양</a></div>
                            </div>
                        </div>
                </div>
                </div>
                <div id="contents">
                    <div class="container">
                        <div class="board_info">
                           상세 정보
                        </div>
                        <div class="board_status">
                            상태변경
                            <input type = "button" value="상태변경" onclick=""><br>
                        </div>
                </div>
                </div>


                <div id="side_right">
                    <div class="side_cont2">
                        <div class="container">
                            <div class="ad_etc">
                                <div>달력</div>
                                <div>투두</div>
                                <div>visitor</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="footer">
                    <div class="container">
                        <div> 카피라이트</div>
                    </div>
                </div>

            </div>
        </body>

        </html>