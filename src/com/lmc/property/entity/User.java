/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.lmc.property.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * Entity - 用户
 * 
 * @author 李敏成
 */
@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends BaseEntity<Long> {

	private static final long serialVersionUID = 400776999956262L;


	/**
	 * "登录失败尝试次数"缓存名称
	 */
	public static final String FAILED_LOGIN_ATTEMPTS_CACHE_NAME = "failedLoginAttempts";

	/**
	 * 是否启用
	 */
	@NotNull
	@Column(nullable = false)
	private Boolean isEnabled;

	/**
	 * 是否锁定
	 */
	@Column(nullable = false)
	private Boolean isLocked;

	/**
	 * 锁定日期
	 */
	private Date lockDate;

	/**
	 * 最后登录IP
	 */
	private String lastLoginIp;

	/**
	 * 最后登录日期
	 */
	private Date lastLoginDate;
	
	/**
	 * 获取是否启用
	 * 
	 * @return 是否启用
	 */
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * 设置是否启用
	 * 
	 * @param isEnabled
	 *            是否启用
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * 获取是否锁定
	 * 
	 * @return 是否锁定
	 */
	public Boolean getIsLocked() {
		return isLocked;
	}

	/**
	 * 设置是否锁定
	 * 
	 * @param isLocked
	 *            是否锁定
	 */
	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	/**
	 * 获取锁定日期
	 * 
	 * @return 锁定日期
	 */
	public Date getLockDate() {
		return lockDate;
	}

	/**
	 * 设置锁定日期
	 * 
	 * @param lockDate
	 *            锁定日期
	 */
	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	/**
	 * 获取最后登录IP
	 * 
	 * @return 最后登录IP
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	/**
	 * 设置最后登录IP
	 * 
	 * @param lastLoginIp
	 *            最后登录IP
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * 获取最后登录日期
	 * 
	 * @return 最后登录日期
	 */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * 设置最后登录日期
	 * 
	 * @param lastLoginDate
	 *            最后登录日期
	 */
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * 获取显示名称
	 * 
	 * @return 显示名称
	 */
	@Transient
	public abstract String getDisplayName();

	/**
	 * 获取身份
	 * 
	 * @return 身份
	 */
	@Transient
	public abstract Object getPrincipal();

	/**
	 * 获取凭证
	 * 
	 * @return 凭证
	 */
	@Transient
	public abstract Object getCredentials();

	/**
	 * 判断凭证是否正确
	 * 
	 * @param credentials
	 *            凭证
	 * @return 凭证是否正确
	 */
	@Transient
	public abstract boolean isValidCredentials(Object credentials);

}