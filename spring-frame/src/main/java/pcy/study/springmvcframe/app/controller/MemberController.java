package pcy.study.springmvcframe.app.controller;

import pcy.study.springmvcframe.app.domain.Member;
import pcy.study.springmvcframe.app.repository.MemberRepository;
import pcy.study.springmvcframe.mvc.annotation.Controller;
import pcy.study.springmvcframe.mvc.annotation.RequestMapping;
import pcy.study.springmvcframe.mvc.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class MemberController {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    public String newForm(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }

    @RequestMapping(value = "/members", method = RequestMethod.POST)
    public String save(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        String password = paramMap.get("password");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, password, age);
        memberRepository.save(member);

        model.put("member", member);
        return "save-result";
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String getMembers(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();
        model.put("members", members);
        return "members";
    }
}
