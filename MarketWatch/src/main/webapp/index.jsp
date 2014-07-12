<!-- Gregory Pellegrin -->
<!-- pellegrin.gregory.work@gmail.com -->

<!-- MarketWatch -->

<!DOCTYPE html>

<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="author" content="Gregory Pellegrin pellegrin.gregory.work@gmail.com"/>
		<meta name="description" lang="en" content="Market and stock data"/>
		<link rel="icon" type="image/png" href="./resources/icon/logo.png"/>
		<title>Market Watch</title>
		
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		
		<script type="text/javascript" src="./script/function.js"></script>
		<script type="text/javascript" src="./script/googleLoad.js"></script>
		<script type="text/javascript" src="./script/tableLoad.js"></script>
		
		<link rel="stylesheet" type="text/css" href="./css/design.css"/>
		<link id="design" rel="stylesheet" type="text/css" href="./css/lightDesign.css"/>
	</head>
	
	<body>
		<jsp:include page="./page/indiceValue.jsp"></jsp:include>
		
		<jsp:include page="./page/etfValue.jsp"></jsp:include>
		
		<jsp:include page="./page/stockValue.jsp"></jsp:include>
		
		<jsp:include page="./page/changeDesign.jsp"></jsp:include>
	</body>
</html>