package developbeginner.projecteasycommunity.service;

import developbeginner.projecteasycommunity.dao.MemberMapper;
import developbeginner.projecteasycommunity.dto.MemberDTO;
import developbeginner.projecteasycommunity.dto.MemberDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailService implements UserDetailsService {
    private MemberMapper memberMapper;

    public MemberDetailService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberDTO.MemberWithRolesDTO result = memberMapper.selectMemberAndRolesByEmail(email);
        if (result == null) {
            throw new UsernameNotFoundException("email does not exist");
        }
        return new MemberDetails(result);
    }
}
