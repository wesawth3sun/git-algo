package MemberRepository.member.impl;

import MemberRepository.member.Member;
import MemberRepository.member.MemberRepository;

import java.util.ArrayList;
import java.util.List;

public class MemoryRepository implements MemberRepository {

    List<Member> members = new ArrayList<>();

    @Override
    public void add(Member member) {
        members.add(member);
    }

    @Override
    public List<Member> findAll() {
        return members;
    }
}
