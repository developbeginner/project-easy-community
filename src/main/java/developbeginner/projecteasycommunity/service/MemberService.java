package developbeginner.projecteasycommunity.service;

import developbeginner.projecteasycommunity.dto.MemberDTO;

public interface MemberService {
    void register(MemberDTO.MemberRequestDTO member);
}
