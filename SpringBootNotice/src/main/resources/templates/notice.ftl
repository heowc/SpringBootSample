<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Spring Boot Notice Sample</title>
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />

    <script src="/webjars/jquery/3.1.0/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <table class="table table-striped" style="margin-top: 100px; width: 500px;">
        <thead>
        <tr>
            <th>idx.</th>
            <th>제목</th>
            <th>내용</th>
        </tr>
        </thead>
        <tbody id="notice-tbody">
        </tbody>
    </table>

    <div class="clearfix">
        <nav>
            <ul class="pagination" id="notice-paging">
            </ul>
        </nav>
    </div>
</div>
</body>

<script src="/resources/bootstrap-paging.js"></script>
<script src="/resources/notice.js"></script>
</html>