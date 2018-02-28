<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8" import="model.vo.WishVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Example of JSP</title>
<style type="text/css">
.button {
	border-radius: 4px;
	background-color: #edaa45;
	border: none;
	color: #FFFFFF;
	text-align: center;
	font-size: 15px;
	padding: 10px;
	width: 100%;
	transition: all 0.5s;
	cursor: pointer;
	margin: 5px;
}

.button span {
	cursor: pointer;
	display: inline-block;
	position: relative;
	transition: 0.5s;
}

.button span:after {
	content: '\00bb';
	position: absolute;
	opacity: 0;
	top: 0;
	right: -20px;
	transition: 0.5s;
}

.button:hover span {
	padding-right: 25px;
}

.button:hover span:after {
	opacity: 1;
	right: 0;
}

div.top {
	margin: 5%;
	height: 30%;
}

div.catePart {
	/* opacity: 0.4;
    filter: alpha(opacity=40); */
	float: left;
	width: 15%;
	margin-top: 3%;
	margin-bottom: 5%;
	padding-left: 2%;
	padding-right: 2%;
	/* border : 5px solid blue; */
	background-color: rgba(242, 242, 242, 0.8);
}

div.mainPart {
	/* opacity: 0.4;
    filter: alpha(opacity=40); */
	float: right;
	width: 75%;
	margin-top: 3%;
	margin-bottom: 2%;
	padding-top: 10%;
	padding-bottom: 10%;
	padding-left: 2%;
	padding-right: 2%;
	/* border : 5px dotted gold; */
	background-color: rgba(242, 242, 242, 0.8);
}

div.catego {
	text-color: black;
}

img {
	max-width: 100%;
	max-height: 100%;
}

#cateBtn {
	align: center;
	width: 70%;
}
</style>
</head>
<body id='backgroundImg'
	style="background-size: auto 100%; background-image: url('/wish/image/bucketlist.png');">
	<script>
		var cateClicked = true;
		var btnName = new Array('GAME', 'HOBBY', 'BOOK', 'RELATION', 'JOB');
		var imgArr = new Array('/wish/image/bucketlist.png',
				'/wish/image/gameTitle.png', '/wish/image/hobby.png',
				'/wish/image/book.jpg', '/wish/image/relationship.png',
				'/wish/image/job.png');
		var bckImgArr = new Array('/wish/image/bucketlist.png',
				'/wish/image/bckGame.png', '/wish/image/bckHobby.png',
				'/wish/image/bckBook.png', '/wish/image/bckRelation.png',
				'/wish/image/bckJob.png');
	</script>
	<div class="top"></div>
	<div>
		<div class="catePart">
			<div class="catego">
				<script>
					for (var i = 0; i < 5; i++) {
						document
								.write("<button class='button' onmouseover='changeImg("
										+ (i + 1)
										+ ")' onclick='loadBoard("
										+ (i + 1) + ")'>");
						document.write("<span>" + btnName[i]
								+ "</span></button>");
						if (i != 4)
							document.write("<br>");
					}
				</script>
			</div>
		</div>
		<div class="mainPart">
			<div id="mainPartId">
				<img id="mainImg" src="/wish/image/bucketlist.png"
					style="max-width: 100%; max-height: 100%;">
				<output id="boardLoc"></output>
			</div>
		</div>
	</div>
	<script>
		function changeImg(index) {
			if (cateClicked)
				document.getElementById("mainImg").src = imgArr[index];
		}
		function loadBoard(index) {
			if (cateClicked) {
				var divLoc = document.getElementById("mainPartId");
				var mainImgTag = document.getElementById("mainImg");
				divLoc.removeChild(mainImgTag);
			}
			cateClicked = false;

			document.body.style.backgroundImage = "url('" + bckImgArr[index]+ "')";
			document.body.style.backgroundSize="100%";	
			Board(index);
		}

		function Board(index) {
			var table = "<table><tr><th>#</th><th width=60%>제목</th><th>작성자</th><th width=20%>작성일</th><th>조회수</th></tr></table>";
			document.getElementById("boardLoc").innerHTML = table;
		}
	</script>
</body>
</html>