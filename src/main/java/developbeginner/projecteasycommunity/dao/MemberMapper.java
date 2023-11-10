package developbeginner.projecteasycommunity.dao;

import developbeginner.projecteasycommunity.dto.MemberDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO MEMBER(EMAIL, PASSWORD, NICKNAME) VALUES(#{member.email}, #{member.password}, #{member.nickname})")
    int insertMember(@Param("member") MemberDTO.MemberRequestDTO member);

    @Update("UPDATE MEMBER SET PASSWORD=#{member.password} WHERE EMAIL=#{member.email}")
    int updatePasswordByEmail(@Param("member") MemberDTO.MemberRequestDTO member);

    @Update("UPDATE MEMBER SET NICKNAME=#{member.nickname} WHERE EMAIL=#{member.email}")
    int updateNicknameByEmail(@Param("member") MemberDTO.MemberRequestDTO member);

    @Select("SELECT EXISTS(SELECT 1 FROM MEMBER WHERE EMAIL=#{member.email} AND PASSWORD=#{member.password})")
    boolean selectMemberExistsByEmailAndPassword(@Param("member") MemberDTO.MemberRequestDTO member);

    @Select("SELECT M.EMAIL, M.PASSWORD, R.NAME FROM MEMBER M JOIN MEMBER_ROLE MR ON M.ID=MR.M_ID " +
            "JOIN ROLE R ON MR.ROLE_ID=R.ID WHERE M.EMAIL=#{email}")
    MemberDTO.MemberWithRolesDTO selectMemberAndRolesByEmail(@Param("email") String email);
}
