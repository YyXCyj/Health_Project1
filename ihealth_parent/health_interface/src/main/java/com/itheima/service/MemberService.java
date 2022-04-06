package com.itheima.service;

import com.itheima.pojo.Member;

import java.util.List;

/**
 * @author yy
 * @version 1.0
 */
public interface MemberService {
    public void add(Member member);
    public Member findByTelephone(String telephone);
    public List<Integer> findMemberCountByMonth(List<String> month);
}
