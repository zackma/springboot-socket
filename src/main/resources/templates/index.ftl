<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
        <script src="/js/socketio/jquery-1.9.1.js" type="text/javascript"></script>
    </head>
    <body>
        <h4>亲爱的${toUserName}，你好！</h4>
        <p style="color:blue;"> ${message}</p>
    祝：开心！
    </br>
        ${fromUserName}
    </br>
        ${time?date}
    </body>
    <input id="fileBtn" type="file" accept="image/*" capture="camera"/>
    <img src="" id="img"/>

    <script type="text/javascript">
        $(function(){
            //do sth...
        });
    </script>
</html>