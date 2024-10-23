//replyService.js에서 정의해놓은 기능들 활용

//bno 가져오기
/*
let bno = document.querySelector('[data-id="bno"]').innerHTML;
console.log(bno);
*/

//메소드 호출 예시
svc.showMsg('Hello, 예시입니다.');

//댓글 목록 출력
/*
svc.rlist(bno,
function(result){
	let replyList = document.querySelector('#replyList tbody');
	let fields = ['replyer', 'reply'];
	
	for(let i = 0; i < result.length; i++){
		let tr = document.createElement('tr');
		//tr.setAttribute('data-id', result[i][fields[0]]);
		
		for(let j = 0; j < fields.length; j++){
			let td = document.createElement('td');
			td.innerText = result[i][fields[j]];
			tr.appendChild(td);
		}
		replyList.appendChild(tr);
	}
},
function(err){
	console.log('ERROR(실패): ', err);
});
*/

//페이징방식 추가
//기본출력 1페이지
showList(1);
//pagination a 클릭 이벤트
//forEach로 nodeList의 각각 매개변수마다 콜백함수 실행 가능.
//function(a,b,c){} a:현재 요소, b:현재 요소의 인덱스, c:노드리스트
function linkMove(){
	document.querySelectorAll('nav ul.pagination a').forEach((aTag) => {
			aTag.addEventListener('click', moveFunc);
		});	
}

function moveFunc(e){
	e.preventDefault(); //이동 차단(새로고침 없이 page만 동적으로 가져옴)
	console.log(e.target.innerHTML);
	//let page = e.target.innerHTML;
	let page = e.target.dataset.page; //데이터 담기 위해 dataset으로 가져옴
	showList(page);
}

//페이지 넘길 때마다 댓글목록 지우고 다시 출력하는 함수
function showList(page){
	
	let replyCnt = page * 5 - 4;
	
	document.querySelectorAll('div.reply div.content li').forEach((li, idx) => {
		if(idx > 0){
			li.remove();			
		}
	});
	
	document.querySelectorAll('ul.pagination li').forEach((li, idx, lis) => {
		if(idx > 0 & idx < lis.length - 1){
			li.remove();			
		}
	});
	//페이지별 댓글목록 출력
	svc.replyList({bno, page},
	function(result){
		let fields = ['replyNo', 'reply', 'replyer', 'replyDate'];
		
		for(let i = 0; i < result.length; i++){
			let template = document.querySelector(".reply ul li").cloneNode(true);
			//console.log(template); //template(li와 하위요소 전부 복사)
			template.querySelector('span').innerText = `${replyCnt}`;
			template.querySelector('span:nth-of-type(2)').innerText = result[i][fields[0]];
			template.querySelector('span:nth-of-type(3)').innerText = result[i][fields[1]];
			template.querySelector('span:nth-of-type(4)').innerText = result[i][fields[2]];
			template.querySelector('span:nth-of-type(5)').innerText = result[i][fields[3]];
			template.querySelector('span:nth-of-type(6)').innerHTML = isDelBtn(result[i][fields[2]]);
			document.querySelector('.reply ul').appendChild(template);			
			replyCnt++;
	}
	
	let buttons = document.querySelectorAll('span button');
	for(let i = 0; i < buttons.length; i++){
		buttons[i].addEventListener('click', deleteRow)
	}
	
	svc.getReplyCount(bno, 
		result => {
			createPageList(page, result.totalCount);
		}, 
		err => console.log(err)
	);
	
	},
	err => console.log(err));
}
	
//페이지목록 계산하여 nav 출력하는 함수
function createPageList(page, totalCnt){ //현재 페이지 매개변수로 받음
	//페이지 수 계산
	//let totalCnt; //값 가져와야 됨.
	//console.log('total', totalCnt); // 비동기작업이므로 처리속도가 맞지 않아, fetch함수내에서 쓰지 않으면 totalCnt값은 마음대로 할당되지 않음.
	let startPage, endPage, realEnd;
	let prev, next;
	
	endPage = Math.ceil(page / 5) * 5; //nav 끝부분 일정하게 계산
	startPage = endPage - 4;
	realEnd = Math.ceil(totalCnt / 5); //실제 page가 끝나는 곳
	endPage = endPage > realEnd ? realEnd : endPage;
	
	//true-false로 버튼 여부 반환
	prev = startPage > 1;
	next = endPage < realEnd;
	
	let ul = document.querySelector('nav ul.pagination');
	let prevLi = ul.firstElementChild;
	let nextLi = ul.lastElementChild;
	
	/*
	if(prev){
		let li = document.createElement('li');
		li.className = 'page-item';
		let a = document.createElement('a');
		a.className = 'page-link'
		a.setAttribute('href','#');
		a.setAttribute('data-page', startPage - 1);
		a.innerHTML = '&laquo;';
		li.appendChild(a);
		ul.appendChild(li);
	}
	*/
	
	console.log(prev, next);
	if(prev){
		prevLi.querySelector('li a').setAttribute('data-page', startPage - 1);
	} else {
		prevLi.querySelector('li a').preventDefault();
	}
	
	for(let i = startPage; i <= endPage; i++){
		let li = document.createElement('li');
		li.className = 'page-item';
		let a = document.createElement('a');
		a.className = 'page-link';
		a.setAttribute('data-page', i);
		a.innerText = i;
		li.appendChild(a);
		nextLi.before(li);
	}
	
	if(next){
		nextLi.querySelector('li a').setAttribute('data-page', endPage + 1);
	} else {
		nextLi.querySelector('li a').preventDefault();
	}
	
	/*
	if(next){
		let li = document.createElement('li');
		li.className = 'page-item';
		let a = document.createElement('a');
		a.className = 'page-link'
		a.setAttribute('href','#');
		a.setAttribute('data-page', endPage + 1);
		a.innerHTML = '&raquo;';
		li.appendChild(a);
		ul.appendChild(li);
	}
	*/
	
	linkMove(); //a들의 노드리스트에 이벤트리스너 등록
}

//댓글 삭제
function isDelBtn(replyer){
	if(logId == replyer){
		return `<button class='btn btn-outline-secondary'>삭제</button>`;
	} else return ``;
}

//삭제 성공(OK) 시 tr 삭제
function deleteRow(e){
	let targetLi = e.target.parentElement.parentElement;
	let rno = targetLi.firstElementChild.nextElementSibling.innerText;
	
	svc.removeReply(rno,
	function(result){
	if(result.retCode == "OK"){
		alert('댓글이 삭제되었습니다.');	
		document.querySelector('.reply ul').removeChild(targetLi);
	} else if(result.retCode == "FAIL"){
		alert('ERROR: 처리 중 오류가 발생했습니다.');
	} else {
		alert('ERROR: 알 수 없는 코드입니다.');
	}
},
err => console.log(err));
}

//댓글 등록
document.querySelector('#addReplyBtn').addEventListener('click', (e) => {
	
	let reply = document.querySelector('#addReplyBox div textarea').value;
	document.querySelector('#addReplyBox div textarea').value = "";
	//let replyer = document.querySelector('#addReplyBox span').innerText;
	
	svc.addReply({bno, reply, replyer: logId},
	function(result){
		console.log(result);
		if(result.retCode == "OK"){
			let template = makeLi(result.retVal);
			document.querySelector('.reply ul li').after(template);
			let button = document.querySelector('.reply ul li').lastElementChild;
			button.addEventListener('click', deleteRow);
		} else if(result.retCode == "FAIL"){
		alert('다시 입력해주세요.');
		} else {
		alert('ERROR: 알 수 없는 코드입니다.');
		}
	},
	err => console.log(err));
	
});

function makeLi(retVal){
	
	let template = document.querySelector(".reply ul li").cloneNode(true);
	//console.log(template); //template(li와 하위요소 전부 복사)
	template.querySelector('span').innerText = 'New';
	template.querySelector('span:nth-of-type(2)').innerText = retVal.replyNo;
	template.querySelector('span:nth-of-type(3)').innerText = retVal.reply;
	template.querySelector('span:nth-of-type(4)').innerText = retVal.replyer;
	template.querySelector('span:nth-of-type(5)').innerText = '방금 전';
	template.querySelector('span:nth-of-type(6)').innerHTML = isDelBtn(retVal.replyer);
	
	return template;
}