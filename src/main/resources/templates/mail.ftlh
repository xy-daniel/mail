<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>邮件发送</title>
</head>
<body>
    <div>
        hello<span>${username}</span>,欢迎加入XXX大家庭 in freemarker！
    </div>
    您的入职信息如下：
    <table style="border: 1px">
        <tr>
            <td>职位</td>
            <td>${position}</td>
        </tr>
        <tr>
            <td>职称</td>
            <td>${joblevel}</td>
        </tr>
        <tr>
            <td>薪水</td>
            <td>${salary}</td>
        </tr>
        <tr>
            <td>部门</td>
            <td>${dep}</td>
        </tr>
    </table>
    <div style="color: red;font-size: 20px">希望在未来的日子里，携手共进！</div>
</body>
</html>