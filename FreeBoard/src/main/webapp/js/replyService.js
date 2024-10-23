//댓글 목록, 등록, 삭제 기능 구현하기

const svc = {
	//목록
	//param = {}로 매개변수값으로 뭐가 넘어오는지 보여주는것. param만 적어도 사용 가능(가독성)
	replyList(param = {bno, page}, successFunc, errorFunc){
		//ajax 호출(fetch 사용)
		fetch('replyList.do?bno=' + param.bno + '&page=' + param.page)
		.then(resolve => resolve.json()) //화살표함수 사용 시 자동 return 반환
		.then(successFunc)
		.catch(errorFunc)
	},
	
	//삭제
	removeReply(rno, successFunc, errorFunc){
		fetch('removeReply.do?rno=' + rno)
		.then(resolve => resolve.json())
		.then(successFunc)
		.catch(errorFunc)
	},
	
	//추가
	addReply(param = {bno, reply, replyer}, successFunc, errorFunc){
		fetch('addReply.do?bno=' + param.bno + '&reply=' + param.reply +'&replyer=' + param.replyer)
		.then(resolve => resolve.json()) //화살표함수 사용 시 자동 return 반환
		.then(successFunc)
		.catch(errorFunc)
	},
	
	//댓글 카운트
	getReplyCount(bno, successFunc, errorFunc){
		fetch('countReply.do?bno=' + bno)
		.then(resolve => resolve.json())
		.then(successFunc)
		.catch(errorFunc)
	},
	
	//사용법 예시(무시)
	showMsg(msg){
		console.log(msg);
	}
}