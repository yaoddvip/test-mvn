<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/17
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-1.11.3.min.js"></script>
    <script>
        //限制文件必须为07
        function test1(){
            var fp = $("#fileId");
            var lg = fp[0].files.length; // get length
            var items = fp[0].files;
            var fragment = "";

            if (lg > 0) {
                for (var i = 0; i < lg; i++) {
                    var fileName = items[i].name; // get file name
                    var fileSize = items[i].size; // get file size
                    var fileType = items[i].type; // get file type

                    // append li to UL tag to display File info
                    fragment += "<li>" + fileName + " (<b>" + fileSize + "</b> bytes) - Type :" + fileType + "</li>";
                     console.log(fileType);
                }
                //$("#ulList").append(fragment);
            }
        }


        function judgeFileType(){
            var fileName = $("#fileId");
            var type = fileName.type();
            alert(type);
            console.log(type);

            var i = fileName.indexOf(".xlsx");
            var b = true;
            if(i == -1){//非07的话
                b = false;
                alert("版本错误！");
            }
            return false;
        }

    </script>
</head>
<body>

    <a href="exportPoi"  >导出</a>
    <a href="javascript:test1();">test</a>
    <form action="importPoi" method="post"
          onsubmit="return judgeFileType()"
          enctype="multipart/form-data">
        <input type="file" name="file" id="fileId">
        <input type="submit">
    </form>

    <hr>

    <c:forEach items="${list}" var="user">
        ${user.userId}--${user.userName}
        <br>
    </c:forEach>
</body>
</html>
