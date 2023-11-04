package developbeginner.projecteasycommunity.service;

import developbeginner.projecteasycommunity.vo.Member;

public interface MemberService {
    void register(Member member);

    void login(Member member);
}
