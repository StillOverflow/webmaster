<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
<meta charset='utf-8' />
<script src='dist/index.global.js'></script>
<script>

	//events 데이터 json으로 db에서 가져오기
	//(날짜는 VARCHAR2 타입에 입력 후 LocalDateTime 으로 가져와 사용하게 됨.)
	var eventList = [];
		
	/*
	fetch("calendarjson.do")
	.then(resolve => resolve.json())
	.then((result) => {
		  console.log(result);
		  
		  eventList = result;
		  
		  console.log(eventList);
		  calendar(); //async, await 활용할 수 있음.
	})
	.catch(e => console.log(e));
	*/
	
  document.addEventListener('DOMContentLoaded', async function() {
  //function calendar() {
    var calendarEl = document.getElementById('calendar');
    
    let today = new Date();
    let year = today.getFullYear();
    let month = today.getMonth() + 1;
    month = month > 9 ? month : '0' + month;
    let date = today.getDate();
    date = date > 9 ? date : '0' + date;
    
    let todate = year + '-' + month + '-' + date;
    console.log(todate);
    
	//fetch().then().then()...와 비슷하게 
	//처리에 따른 promise 객체를 반환해주는 async function(), await 활용
	let resolve = await fetch('calendarjson.do'); //fetch의 처리 결과
	let result = await resolve.json(); //그 다음 단계로 진행
	eventList = result;
    
	//각각의 구성요소 키-값들....
    var calendar = new FullCalendar.Calendar(calendarEl, {
   	  headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      initialDate: todate,
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      
      //일정 추가 기능
      select: function(arg) {
    	var title = prompt('일정 이름:');
        console.log(arg)
        
        //데이터 입력 (title, startDate, endDate 필요.)
        let startDate = gmtToDate(arg.startStr);
        let endDate = gmtToDate(arg.endStr);
                
        fetch('addEvent.do?title=' + title + '&start=' + startDate + '&end=' + endDate)
        .then(resolve => resolve.json())
        .then(result => {
        	if(result.retCode == 'FAIL'){
        		 alert('일정 추가에 문제가 생겼습니다.');
        	}
        })
        .catch(e => console.log(e));
        
        //calendar에 자동으로 추가되는 기능
        if (title) {
          calendar.addEvent({
            title: title,
            start: arg.start,
            end: arg.end,
            allDay: arg.allDay
          })          
        }
        calendar.unselect()
      },
      
      //일정 삭제 기능
      eventClick: function(arg) {
        if (confirm('해당 일정을 삭제하시겠습니까?')) {
          arg.event.remove()
          
          console.log(arg);
          
          //LocalDateTime type 변수를 Date() 및 toISOString()으로 변환
          let startGmt = arg['event']['_instance']['range']['start'];
          let endGmt = arg['event']['_instance']['range']['end'];
          startGmt = gmtToDate(startGmt);
          endGmt = gmtToDate(endGmt);
          /*
          //endDate null인 경우??
          console.log(Number(startGmt.toString().substr(16,2)) + 1);
          console.log(Number(endGmt.toString().substr(16,2)));
          if(Number(startGmt.substr(16,2)) + 1 == Number(endGmt.toString().substr(16,2))){
        	  endGmt = null; console.log(endGmt);
          } else {
        	  endGmt = gmtToDate(endGmt);
          }
          */

          //let isAllDay = arg['event']['allDay'];

          
          let title = arg['event']['_def']['title'];
          console.log(title);
          
          fetch('removeEvent.do?title=' + title + '&start=' + startGmt + '&end=' + endGmt)
          .then(resolve => resolve.json())
          .then(result => {
          	if(result.retCode == 'FAIL'){
          		 alert('일정 삭제에 실패했습니다.');
          	}
          })
          .catch(e => console.log(e));
          
        }
      },
      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      
      events: eventList
    });

    calendar.render();
  }
  );
	
	
	//LocalDateTime
	//Wed Oct 09 2024 20:00:00 GMT+0900 (한국 표준시) (9시간 빼주면 입력시간)
	//LocalDateTime type 변수를 Date() 및 toISOString()으로 변환
	function gmtToDate(inputDate){
		let date = new Date(inputDate);
        let outputDate = date.toISOString().replace('.000Z','');
        
        if(outputDate.substr(10) == "T00:00:00"){
        	outputDate = outputDate.replace('T00:00:00', '');
        }
        
        console.log('gmtToDate func => ' + outputDate);
        return outputDate;
	}

</script>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 1100px;
	margin: 0 auto;
}
</style>
</head>
<body>
	
	<h3>캘린더</h3>
	<div id='calendar'></div>

</body>