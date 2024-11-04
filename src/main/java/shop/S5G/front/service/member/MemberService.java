package shop.S5G.front.service.member;

import jakarta.servlet.http.HttpServletResponse;
import shop.S5G.front.dto.member.MemberLoginRequestDto;
import shop.S5G.front.dto.member.MemberRegistrationRequestDto;
import shop.S5G.front.dto.MessageDto;

public interface MemberService {
    MessageDto registerMember(MemberRegistrationRequestDto memberRegistrationRequestDto);
    void loginMember(MemberLoginRequestDto memberLoginRequestDto, HttpServletResponse response);
}
