//ajax(비동기적 처리방식 의미)
//Asynchronous Javascript And Xml.
//ajax 구현하는 방법 중 하나가 fetch

//동기방식(한 번에 처리) 예시
/*
setTimeout(function(){
	console.log("1");
}, 1000)

setTimeout(function(){
	console.log("2");
}, 1000)

setTimeout(function(){
	console.log("3");
}, 1000)
*/

//javascript는 비동기방식(로직 끝날 때까지 기다리지 않고, 빠르게 처리)
let xhtp = new XMLHttpRequest(); //비동기방식 요청처리(ajax)
xhtp.open('get', 'memberjson.do');
xhtp.send(); //서버상 resource 받아옴.

let data = [];
xhtp.onload = function(){
	//xhtp.responseText : json문자열로 위 서버 데이터를 가져옴.
	console.log(xhtp.responseText);
	let obj = JSON.parse(xhtp.responseText); //실제 object 배열로 변환
	data = obj;
	console.log('1', data);
} // 로드 다 되면 실행되는 함수

console.log('2', data);
for(let i = 0; i < data.length; i++){
	console.log(data[i]);
	//데이터 없음!! xhtp.onload에 들어가는 함수 안에서 출력가능.
	//fetch 쓸 때도 then()안에서만 사용되는 것처럼...
}