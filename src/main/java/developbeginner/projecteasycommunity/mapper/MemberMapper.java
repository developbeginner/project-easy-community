package developbeginner.projecteasycommunity.mapper;

import developbeginner.projecteasycommunity.vo.Member;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO MEMBER(EMAIL, PASSWORD, NICKNAME) VALUES(#{member.email}, #{member.password}, #{member.nickname})")
    int insert(@Param("member") Member member);

    @Select("SELECT EXISTS(SELECT 1 FROM MEMBER WHERE EMAIL=#{member.email} AND PASSWORD=#{member.password})")
    boolean checkMemberExist(@Param("member") Member member);
}
