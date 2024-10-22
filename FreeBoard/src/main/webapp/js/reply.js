//replyService.js에서 정의해놓은 기능들 활용

//메소드 호출 예시
svc.showMsg('Hello, 예시입니다.');

let bno = document.querySelector();

svc.rlist(392,
function(result){
	let replyList = document.querySelector('#replyList');
	let fields = ['reply', 'replyer'];
	
	for(let i = 0; i < result.length; i++){
		let tr = document.createElement('tr');
		
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