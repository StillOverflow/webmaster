<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      
  	//Google Charts - 차트를 구성하는 데이터 부분 (ajax-fetch() 이용해 데이터 가져오기)
  	
  	//데이터 열 이름 꼭 있어야 함.
 	var chartData = [['작성자', '갯수']];
  	
  	fetch("countByWriter.do")
  	.then(resolve => resolve.json())
  	.then(result => {
  		console.log(result);
  		//[{"MEMBER_NAME":"김손님","WRITER":"guest","COUNT":62},{....},...] 형태
  		
  		
  		result.forEach((row) => {
  			console.log(row.count);
  			chartData.push([row.MEMBER_NAME, row.COUNT]);
  		});
  		console.log(chartData);

        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);
  		
  	})
  	.catch(err => console.log(err));
  	
	 function drawChart() {
	
	//샘플 입력 데이터 예시
	/*
	   var data = google.visualization.arrayToDataTable([
	     ['Task', 'Hours per Day'],
	     ['Work',     11],
	     ['Eat',      2],
	     ['Commute',  2],
	     ['Watch TV', 2],
	     ['Sleep',    7]
	   ]);
	*/
	
	   var data = google.visualization.arrayToDataTable(chartData); //배열을 차트용 데이터로 변화
	
	   var options = {
	     title: '사용자별 게시글 등록 현황'
	   };
	
	   var chart = new google.visualization.PieChart(document.getElementById('piechart'));
	   
	   chart.draw(data, options);
	   
	 }
      
    </script>
  </head>
  <body>
  	<h3>원형 차트</h3>
    <div id="piechart" style="width: 900px; height: 500px;"></div>
  </body>
</html>
