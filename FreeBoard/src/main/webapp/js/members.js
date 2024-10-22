//json형태의 회원목록 데이터 가져와 출력하기
//ajax 구현하는 방법 중 하나인 fetch 사용
fetch('memberjson.do') //promise(resolve, reject) type 반환됨
.then(function(resolve){ 
	return resolve.json();
})
.then(function(result){ //데이터 가져오기 성공 시 실행
	console.log(result);
	makeList(result);
})
.catch(function(err){ //데이터 가져오기 실패 시 실행
	console.log(err);
});

function makeList(obj = []){
	let fields = ['memberId', 'memberName', 'phone'];
	let table = document.createElement('table');
	table.classList.add('table');
	
	//thead 생성
	let thead = document.createElement('thead');
	for(let i = 0; i < fields.length; i++){
		let th = document.createElement('th');
		th.innerText = fields[i];
		thead.appendChild(th);
	}
	table.appendChild(thead);
	
	//tbody와 tr 생성
	let tbody = document.createElement('tbody');
	
	for(let i = 0; i < obj.length; i++){
		let tr = document.createElement('tr');
		tr.setAttribute('data-id', obj[i][fields[0]]);
		tr.addEventListener('mouseover', (e) => {
			e.currentTarget.style.backgroundColor = 'RGB(161, 205, 255)';
			//currentTarget: tr전체 해당, target: 이벤트 발생한 td요소 해당
		});
		tr.addEventListener('mouseout', (e) => {
			e.currentTarget.style.backgroundColor = 'transparent';
		});
		
		for(let j = 0; j < fields.length; j++){
			let td = document.createElement('td');
			td.innerText = obj[i][fields[j]];
			//obj[i].fields[j] 해버리면 obj[i]에 fields[]속성이 없으므로, 동적으로 가져오지 못함.
			tr.appendChild(td);
		}		
		
		createBtn(tr);
		
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
	document.querySelector('#show').appendChild(table);
}

//addBtn(addMemberjson 컨트롤러) 이벤트 추가
document.querySelector('#addBtn').addEventListener('click',(e) => {
	
	let id = document.querySelector('#mid').value;
	let name = document.querySelector('#mname').value;
	let passwd = document.querySelector('#passwd').value;
	let phone = document.querySelector('#phone').value;
	
	fetch('addMemberjson.do?mid=' + id + '&mname=' + name + '&passwd=' + passwd +'&phone=' + phone)
	.then((resolve) => {return resolve.json();})
	.then((result) => {
		console.log(result);
		if(result.retCode == "OK"){
			addList({mid:id, mname:name, ph:phone});
		}
		})
	.catch((err) => console.log(err));
	
	function addList(obj = {}){
		console.log(obj);
		let arr = ['mid', 'mname', 'ph'];
		let tr = document.createElement('tr');
		for(let i = 0; i < 3; i++){
			let td = document.createElement('td');
			td.innerText = obj[arr[i]];
			console.log(obj[arr[i]]);
			tr.appendChild(td);
		}
		
		createBtn(tr);
		
		document.querySelector('#show table tbody').appendChild(tr);
		
	}
	
});

//tr마다 delBtn 만드는 함수
function createBtn(tr){
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.innerHTML = '삭제';
	btn.classList.add('btn', 'btn-secondary');
	btn.addEventListener('click', (e) => {
		//tbody.removeChild(tr);
		//document.querySelector('#show table tbody').removeChild(e.target.parentElement.parentElement);
		deleteRowFunc(e);
	});
	td.appendChild(btn);
	tr.appendChild(td);
}

//delBtn click 이벤트 발생 시 내용
function deleteRowFunc(e){
	console.log(e.target.parentElement.parentElement.firstElementChild.innerText);
	console.log(e.target.parentElement.parentElement.dataset.id);
	
	let id = e.target.parentElement.parentElement.dataset.id;
	
	fetch('removeMemberjson.do?id=' + id)
	.then((resolve) => resolve.json())
	.then((result) => {
		console.log(result);
		if(result.retCode == "OK"){
			document.querySelector('#show table tbody').removeChild(e.target.parentElement.parentElement);
		}
	})
	.catch((err) => console.log(err));

}