<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
    .conatiner{
        width: 990px;
        margin: 0 auto;
    }
    ul, li{
        list-style: none;
    }
    a{
     color: #222;
     text-decoration: none;

    }
    .container>.ad-menu{
        width: 20%;
    }
    .container>.ad-board{
        width: 60%;
    }
    .container>.ad-etc{
        width: 20%;
    }
</style>
<title>관리자 회원관리</title>
</head>
<body>
    <div id="ad-contants">
        <div class="header">
            <div class="header-ban">
                <div class="ban">
                    배너
                </div>
                <div class="user">관리자 ID</div>
            </div>
        </div>
        <div class="container">
            <div class="ad-menu"></div>
                <ul>
                    <li><a href="">게시물관리</a></li>
                    <li><a href="">회원관리</a></li>
                    <li><a href="">이용권 관리</a></li>
                    <li><a href="">책임분양</a></li>
                </ul>
            <div class="ad-board">
                <div class="bord-serch">
                    
                    <table>
                        <tr>
                            <td>date</td>
                            <td>date</td>
                        </tr>
                        <tr>
                            <td>kind</td>
                            <td><input type="checkbox"value="사업자" name="kind">사업자</td>
                            <td><input type="checkbox"value="일반회원"name="kind">일반회원</td>
                            <td><input type="checkbox"value="대기회원"name="kind">대기회원</td>
                        </tr>
                        <tr>
                            <td>검색어</td>
                            <tr><input type="text" name="" id="">;;;
                            </tr>
                        </tr>
                    </table>
                </div>
                <div class="board-kind">
                    작성글 종류 게시글/댓글
                   
                </div>
               
                <div class="board-list">
                    리스트
                    <table>
                        <tr>
                            <td>클릭시 상세 화면 페이지</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <a href="#"> 상태변경</a>
                            </td>
                            
                        </tr>
                    </table>
                </div>
            </div>
            <div class="ad-etc">
                달력, 투두리스트,visitor
            </div>
        </div>
    </div>
</body>
</html>