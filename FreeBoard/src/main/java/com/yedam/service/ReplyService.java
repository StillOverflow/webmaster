package com.yedam.service;

import java.util.List;

import com.yedam.vo.ReplyVO;

public interface ReplyService {
	List<ReplyVO> replyList(int boardNo, int page);
	boolean registerReply(ReplyVO reply);
	boolean removeReply(int replyNo);
	ReplyVO selectReply(int replyNo);
	int countReply(int boardNo);
}
