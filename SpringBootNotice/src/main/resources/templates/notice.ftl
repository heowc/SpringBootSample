<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Spring Boot Notice Sample</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />

    <script src="/webjars/jquery/3.3.1-2/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootpag/1.0.7/jquery.bootpag.min.js"></script>
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

<script src="/resources/notice.js"></script>
</html>