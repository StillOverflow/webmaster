//{name: "홍길동", age: 20} => objects(javascript)
//{"name": "홍길동", "age": 20} => json object format
//json : 일반적으로 서버에서 클라이언트로 데이터 전송 시 사용하는 포맷.(일정한 패턴의 문자열)

let obj = {name: "홍길동", age: 20}
let json = JSON.stringify(obj); //obj를 json문자열로 변환
obj = JSON.parse(json); //json문자열을 obj로 변환

console.log(obj + json);

console.log(obj);
console.log(json);

//.json data를 불러오는 방법 : 
//ajax 구현하는 방법 중 하나인 fetch 사용
//fetch('json파일 경로').then(function(){return response.json()배열로 반환}).then()
fetch('js/MOCK_DATA.json').then(function(resolve){
	console.log(resolve);
	return resolve.json(); //읽은 데이터를 json객체로 변환하여 반환.
}).then(function (result) { //result라는 최종적인 배열로 만들어 반환.
	console.log(result);
	//makeList(result);
})

 //객체로 반환되는 함수

//obj = JSON.parse(data);
//console.log(data); //=> 단순 문자열로 정의해놓음.
console.log(obj);

function makeList2(obj = []){
	let fields = ['id', 'first_name', 'last_name', 'email', 'salary'];
	let table = document.createElement('table');
	table.classList.add('table');
	
	let thead = document.createElement('thead');
	for(let i = 0; i < fields.length; i++){
		let th = document.createElement('th');
		th.innerText = fields[i];
		thead.appendChild(th);
	}
	table.appendChild(thead);
	
	let tbody = document.createElement('tbody');
	
	for(let i = 0; i < obj.length; i++){
		let tr = document.createElement('tr');
		tr.addEventListener('mouseover', (e) => {
			e.currentTarget.style.backgroundColor = 'skyblue';
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
		
		let td = document.createElement('td');
		let btn = document.createElement('button');
		btn.innerHTML = '삭제';
		btn.classList.add('btn', 'btn-secondary');
		btn.addEventListener('click', (e) => {
			btn.parentElement.parentElement.removeChild(tbody);
		});
		td.appendChild(btn);
		tr.appendChild(td);
		
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
	document.querySelector('#show').appendChild(table);
}


function makeList(){
	let fields = ['id', 'first_name', 'last_name', 'email', 'salary'];
	let table = document.createElement('table');
	table.classList.add('table');
	
	let thead = document.createElement('thead');
	for(let i = 0; i < fields.length; i++){
		let th = document.createElement('th');
		th.innerText = fields[i];
		thead.appendChild(th);
	}
	table.appendChild(thead);
	
	let tbody = document.createElement('tbody');
	
	for(let i = 0; i < obj.length; i++){
		let tr = document.createElement('tr');
		tr.addEventListener('mouseover', (e) => {
			e.currentTarget.style.backgroundColor = 'skyblue';
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
		
		let td = document.createElement('td');
		let btn = document.createElement('button');
		btn.innerHTML = '삭제';
		btn.classList.add('btn', 'btn-secondary');
		btn.addEventListener('click', (e) => {
			tbody.removeChild(tr);
		});
		td.appendChild(btn);
		tr.appendChild(td);
		
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
	document.querySelector('#show').appendChild(table);
}

makeList();