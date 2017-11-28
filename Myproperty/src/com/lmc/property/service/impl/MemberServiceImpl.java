package com.lmc.property.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmc.property.entity.Member;
import com.lmc.property.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void save(Member member){
		manager.persist(member);
	}
}
