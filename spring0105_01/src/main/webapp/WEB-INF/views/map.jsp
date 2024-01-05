<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>지도</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4ae994b8caf12817d5a753942d711294&libraries=services,clusterer,drawing"></script>
		<script src="/js/daum_api.js"></script>
		<style>
			*{margin: 0; padding: 0;}
			#map{border: 3px solid black;}
			#header{width: 100%; height: 80px; text-align: center; margin-top: 50px;}
			.overlaybox {position:relative;width:360px;height:350px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/box_movie.png') no-repeat;padding:15px 10px;}
			.overlaybox div, ul {overflow:hidden;margin:0;padding:0;}
			.overlaybox li {list-style: none;}
			.overlaybox .boxtitle {color:#fff;font-size:16px;font-weight:bold;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png') no-repeat right 120px center;margin-bottom:8px;}
			.overlaybox .first {position:relative;width:247px;height:136px;background: url('data:image/jpeg;base64,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') no-repeat;margin-bottom:8px;}
			.first .text {color:#fff;font-weight:bold;}
			.first .triangle {position:absolute;width:48px;height:48px;top:0;left:0;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/triangle.png') no-repeat; padding:6px;font-size:18px;}
			.first .movietitle {position:absolute;width:100%;bottom:0;background:rgba(0,0,0,0.4);padding:7px 15px;font-size:14px;}
			.overlaybox ul {width:247px;}
			.overlaybox li {position:relative;margin-bottom:2px;background:#2b2d36;padding:5px 10px;color:#aaabaf;line-height: 1;}
			.overlaybox li span {display:inline-block;}
			.overlaybox li .number {font-size:16px;font-weight:bold;}
			.overlaybox li .title {font-size:13px;}
			.overlaybox ul .arrow {position:absolute;margin-top:8px;right:25px;width:5px;height:3px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/updown.png') no-repeat;} 
			.overlaybox li .up {background-position:0 -40px;}
			.overlaybox li .down {background-position:0 -60px;}
			.overlaybox li .count {position:absolute;margin-top:5px;right:15px;font-size:10px;}
			.overlaybox li:hover {color:#fff;background:#d24545;}
			.overlaybox li:hover .up {background-position:0 0px;}
			.overlaybox li:hover .down {background-position:0 -20px;}
		</style>
		
	</head>
	<body>
		<div id="header"><h1>다음지도 api</h1></div>
		<button id="daumMapBtn">박스오피스 순위</button>
		<div id="map" style="width:100%;height:600px;"></div>

		
		
		<table id="box">
			<tr></tr>
		</table>
	</body>
</html>