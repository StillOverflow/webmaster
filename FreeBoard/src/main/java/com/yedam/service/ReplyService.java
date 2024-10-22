package com.yedam.service;

import java.util.List;

import com.yedam.vo.ReplyVO;

public interface ReplyService {
	List<ReplyVO> replyList(int boardNo);
	boolean registerReply(ReplyVO reply);
	boolean removeReply(int replyNo);
	ReplyVO selectReply(int replyNo);
}
