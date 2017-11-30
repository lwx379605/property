package com.lmc.property.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author 李敏成
 *
 */
public class Bill extends OrderedEntity<Long>{
	
	/**
	 * 类型
	 */
	public enum Type {

		/**
		 * 物业管理费
		 */
		物业管理费,

		/**
		 * 水电公摊费
		 */
		
		水电公摊费,
		
		/**
		 * 垃圾清运费
		 */
		垃圾清运费,
		
		/**
		 * 公共照明费
		 */
		公共照明费,
		
		/**
		 * 水费
		 */
		水费,
		
		/**
		 * 电费
		 */
		电费,
		
		/**
		 * 燃气费
		 */
		燃气费
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7196319351455177885L;
	
	/**
	 * 批次号
	 */
	@NotEmpty
	@Length(max=64)
	@Column(unique=true,nullable = false)
	private String batchID;
	
	/**
	 * 账单费用类型
	 */
	private Bill.Type type;
	
	/**
	 * 账单金额
	 */
	@NotEmpty
	@Min(0)
	@Column(nullable = false)
	private BigDecimal amount;
	
	/**
	 * 房间
	 */
	@NotEmpty
	@ManyToOne
	@Column(nullable = false)
	private Room room;
	
	/**
	 * 明细条目所归属的账期
	 */
	@NotEmpty
	@Column(nullable = false)
	private Date acctPeriod;
	
	/**
	 * 出账日期，格式固定为YYYYMMDD。
	 */
	@NotEmpty
	@Column(nullable = false)
	private Date releaseDay;
	
	/**
	 * 缴费截止日期
	 */
	@NotEmpty
	@Column(nullable = false)
	private Date deadline;
	
	/**
	 * 缴费明细条目关联ID。若物业系统业务约束上传的多条明细条目必须被一次同时支付，则对应的明细条目需传入同样的relate_id。
	 */
	@Length(max=64)
	private String relateId;
	
	/**
	 * 缴费支付确认页显示给用户的提示确认信息，除房间名外的第二重校验信息，预防用户错缴。建议传入和缴费户相关的信息
	 */
	@Length(max=64)
	private String remarkStr;
	
	
}
