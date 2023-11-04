package developbeginner.projecteasycommunity.service;

import developbeginner.projecteasycommunity.mapper.MemberMapper;
import developbeginner.projecteasycommunity.vo.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public void register(Member member) {
        memberMapper.insert(member);
    }

    @Override
    public void login(Member member) {
        boolean result = memberMapper.checkMemberExist(member);
        if (!result) {
            throw new IllegalArgumentException("아이디나 비밀번호가 틀렸습니다.");
        }
    }
}
