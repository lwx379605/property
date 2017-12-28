/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.lmc.property.listener;

import java.util.Date;
import java.util.List;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.collections.CollectionUtils;

import com.lmc.property.annotation.AuditingMetadata;


/**
 * Audit - 审计实体类监听器
 * 
 * @author 李敏成
 */
public class AuditingEntityListener {

	/**
	 * 保存前处理
	 * 
	 * @param entity
	 *            实体对象
	 */
	@SuppressWarnings("unchecked")
	@PrePersist
	public void prePersist(Object entity) {
		AuditingMetadata auditingMetadata = AuditingMetadata.getAuditingMetadata(entity.getClass());
		if (!auditingMetadata.isAuditable()) {
			return;
		}

		List<AuditingMetadata.Property> createdDateProperties = auditingMetadata.getCreatedDateProperties();
		List<AuditingMetadata.Property> lastModifiedDateProperties = auditingMetadata.getLastModifiedDateProperties();

		List<AuditingMetadata.Property> dateProperties = (List<AuditingMetadata.Property>) CollectionUtils.union(createdDateProperties, lastModifiedDateProperties);

		if (CollectionUtils.isNotEmpty(dateProperties)) {
			Date now = new Date();
			for (AuditingMetadata.Property property : dateProperties) {
				property.setValue(entity, now);
			}
		}
	}

	/**
	 * 更新前处理
	 * 
	 * @param entity
	 *            实体对象
	 */
	@PreUpdate
	public void preUpdate(Object entity) {
		AuditingMetadata auditingMetadata = AuditingMetadata.getAuditingMetadata(entity.getClass());
		if (!auditingMetadata.isAuditable()) {
			return;
		}

		List<AuditingMetadata.Property> lastModifiedDateProperties = auditingMetadata.getLastModifiedDateProperties();

		if (CollectionUtils.isNotEmpty(lastModifiedDateProperties)) {
			Date now = new Date();
			for (AuditingMetadata.Property property : lastModifiedDateProperties) {
				property.setValue(entity, now);
			}
		}
	}
}