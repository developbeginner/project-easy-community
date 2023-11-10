package developbeginner.projecteasycommunity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class MemberDTO {
    @Data
    @NoArgsConstructor
    public static class MemberRequestDTO {
        private String email;
        private String password;
        private String nickname;
    }

    @Data
    @NoArgsConstructor
    public static class MemberResponseDTO {
        private String email;
        private String password;
        private String nickname;
    }

    @Data
    @NoArgsConstructor
    public static class MemberWithRolesDTO {
        private String email;
        private String password;
        private List<String> roles;
    }
}
