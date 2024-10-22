//댓글 목록, 등록, 삭제 기능 구현하기

const svc = {
	rlist(bno, successFunc, errorFunc){
		//ajax 호출(fetch 사용)
		fetch('replyList.do?bno=' + bno)
		.then(resolve => resolve.json()) //화살표함수 사용 시 자동 return 반환
		.then(successFunc)
		.catch(errorFunc)
	},
	radd(reply = [], successFunc, errorFunc){
		fetch('addReply.do?bno=' + reply[1] + '&replyer=')
		.then(resolve => resolve.json()) //화살표함수 사용 시 자동 return 반환
		.then(successFunc)
		.catch(errorFunc)
	},
	showMsg(msg){
		console.log(msg);
	}
}