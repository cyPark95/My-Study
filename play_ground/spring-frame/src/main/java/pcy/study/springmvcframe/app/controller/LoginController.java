package pcy.study.springmvcframe.app.controller;

import pcy.study.springmvcframe.app.domain.Member;
import pcy.study.springmvcframe.app.repository.MemberRepository;
import pcy.study.springmvcframe.mvc.annotation.RequestMapping;
import pcy.study.springmvcframe.mvc.annotation.RequestMethod;
import pcy.study.springmvcframe.mvc.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
public class LoginController {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        String password = paramMap.get("password");

        Member member = memberRepository.findByUsername(username);
        if (Objects.isNull(member)) {
            return "사용자 이름을 확인해 주세요.";
        }

        if (!member.getPassword().equals(password)) {
            return "비밀번호를 확인해 주세요.";
        }

        return member;
    }
}
