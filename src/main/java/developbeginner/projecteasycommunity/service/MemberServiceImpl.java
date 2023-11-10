package developbeginner.projecteasycommunity.service;

import developbeginner.projecteasycommunity.dao.MemberMapper;
import developbeginner.projecteasycommunity.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public void register(MemberDTO.MemberRequestDTO member) {
        memberMapper.insertMember(member);
    }
}
