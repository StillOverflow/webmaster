package com.yedam.mapper;

import java.util.List;

import com.yedam.vo.ReplyVO;

public interface ReplyMapper {
	List<ReplyVO> selectList(int boardNo);
	int deleteReply(int replyNo);
	int insertReply(ReplyVO reply);
//	int modifyReply(ReplyVO reply);
	ReplyVO selectReply(int replyNo);
}
