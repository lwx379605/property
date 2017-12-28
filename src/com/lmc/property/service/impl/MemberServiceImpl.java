package com.lmc.property.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmc.property.entity.Renter;
import com.lmc.property.service.MemberService;

/**
 * @author 李敏成
 *
 */
@Service
public class MemberServiceImpl implements MemberService {
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void save(Renter member){
		manager.persist(member);
	}
}
