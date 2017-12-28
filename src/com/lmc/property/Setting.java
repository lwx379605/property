/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.lmc.property;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.lmc.property.utils.FreeMarkerUtils;

import freemarker.template.TemplateException;

/**
 * 系统设置
 * 
 * @author 李敏成
 * 
 */
public class Setting implements Serializable {

	private static final long serialVersionUID = -1478999889661796840L;

	/**
	 * 缓存名称
	 */
	public static final String CACHE_NAME = "setting";

	/**
	 * 分隔符
	 */
	private static final String SEPARATOR = ",";

	/**
	 * 小数位精确方式
	 */
	public enum RoundType {

		/**
		 * 四舍五入
		 */
		roundHalfUp,

		/**
		 * 向上取整
		 */
		roundUp,

		/**
		 * 向下取整
		 */
		roundDown
	}

//	/**
//	 * 验证码类型
//	 */
//	public enum CaptchaType {
//
//		/**
//		 * 会员登录
//		 */
//		memberLogin,
//
//		/**
//		 * 会员注册
//		 */
//		memberRegister,
//
//		/**
//		 * 商家注册
//		 */
//		businessRegister,
//
//		/**
//		 * 商家登录
//		 */
//		businessLogin,
//
//		/**
//		 * 后台登录
//		 */
//		adminLogin,
//
//		/**
//		 * 商品评论
//		 */
//		review,
//
//		/**
//		 * 商品咨询
//		 */
//		consultation,
//
//		/**
//		 * 忘记密码
//		 */
//		forgotPassword,
//
//		/**
//		 * 重置密码
//		 */
//		resetPassword,
//
//		/**
//		 * 其它
//		 */
//		other
//	}

	/**
	 * 区域设置
	 */
	public enum Locale {

		/**
		 * 中文(简体, 中国)
		 */
		zh_CN,

		/**
		 * 中文(繁体, 台湾)
		 */
		zh_TW,

		/**
		 * 英语(美国)
		 */
		en_US
	}

	/**
	 * 网站名称
	 */
	@NotEmpty
	@Length(max = 200)
	private String siteName;

	/**
	 * 网站网址
	 */
	@NotEmpty
	@Length(max = 200)
	@Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/).*$")
	private String siteUrl;

	/**
	 * logo
	 */
	@NotEmpty
	@Length(max = 200)
	@Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/|\\/).*$")
	private String logo;

	/**
	 * 联系地址
	 */
	@Length(max = 200)
	private String address;

	/**
	 * 联系电话
	 */
	@Length(max = 200)
	private String phone;

	/**
	 * 邮政编码
	 */
	@Length(max = 200)
	private String zipCode;

	/**
	 * E-mail
	 */
	@Email
	@Length(max = 200)
	private String email;

	/**
	 * 备案编号
	 */
	@Length(max = 200)
	private String certtext;

	/**
	 * 价格精确位数
	 */
	@NotNull
	@Min(0)
	@Max(3)
	private Integer priceScale;

	/**
	 * 价格精确方式
	 */
	@NotNull
	private Setting.RoundType priceRoundType;

	/**
	 * 默认市场价换算比例
	 */
	@NotNull
	@Min(0)
	@Digits(integer = 3, fraction = 3)
	private Double defaultMarketPriceScale;

//	/**
//	 * 验证码类型
//	 */
//	private Setting.CaptchaType[] captchaTypes;

	/**
	 * 最大登录失败尝试次数
	 */
	@Min(1)
	private Integer maxFailedLoginAttempts;

	/**
	 * 密码锁定时间
	 */
	@NotNull
	@Min(1)
	private Integer passwordLockTime;

	/**
	 * 安全密匙有效时间
	 */
	@NotNull
	@Min(0)
	private Integer safeKeyExpiryTime;

	/**
	 * 上传文件最大限制
	 */
	@NotNull
	@Min(0)
	private Integer uploadMaxSize;

	/**
	 * 允许上传图片扩展名
	 */
	@Length(max = 200)
	private String uploadImageExtension;

	/**
	 * 允许上传媒体扩展名
	 */
	@Length(max = 200)
	private String uploadMediaExtension;

	/**
	 * 允许上传文件扩展名
	 */
	@Length(max = 200)
	private String uploadFileExtension;

	/**
	 * 图片上传路径
	 */
	@NotEmpty
	@Length(max = 200)
	private String imageUploadPath;

	/**
	 * 媒体上传路径
	 */
	@NotEmpty
	@Length(max = 200)
	private String mediaUploadPath;

	/**
	 * 文件上传路径
	 */
	@NotEmpty
	@Length(max = 200)
	private String fileUploadPath;

	/**
	 * SMTP服务器地址
	 */
	@NotEmpty
	@Length(max = 200)
	private String smtpHost;

	/**
	 * SMTP服务器端口
	 */
	@NotNull
	@Min(0)
	private Integer smtpPort;

	/**
	 * SMTP用户名
	 */
	@NotEmpty
	@Length(max = 200)
	private String smtpUsername;

	/**
	 * SMTP密码
	 */
	@Length(max = 200)
	private String smtpPassword;

	/**
	 * SMTP是否启用SSL
	 */
	@NotNull
	private Boolean smtpSSLEnabled;

	/**
	 * 发件人邮箱
	 */
	@NotEmpty
	@Email
	@Length(max = 200)
	private String smtpFromMail;

	/**
	 * 货币符号
	 */
	@NotEmpty
	@Length(max = 200)
	private String currencySign;

	/**
	 * 货币单位
	 */
	@NotEmpty
	@Length(max = 200)
	private String currencyUnit;

	/**
	 * 是否开启开发模式
	 */
	@NotNull
	private Boolean isDevelopmentEnabled;

	/**
	 * Cookie路径
	 */
	@NotEmpty
	@Length(max = 200)
	private String cookiePath;

	/**
	 * Cookie作用域
	 */
	@Length(max = 200)
	private String cookieDomain;

	/**
	 * 区域设置
	 */
	@NotNull
	private Setting.Locale locale;

	@NotNull
	@Length(max = 200)
	private String appID;

	@NotNull
	private String privateKey;

	@NotNull
	private String publicKey;
	
	/**
	 * 获取网站名称
	 * 
	 * @return 网站名称
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * 设置网站名称
	 * 
	 * @param siteName
	 *            网站名称
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/**
	 * 获取网站网址
	 * 
	 * @return 网站网址
	 */
	public String getSiteUrl() {
		return siteUrl;
	}

	/**
	 * 设置网站网址
	 * 
	 * @param siteUrl
	 *            网站网址
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = StringUtils.removeEnd(siteUrl, "/");
	}

	/**
	 * 获取logo
	 * 
	 * @return logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * 设置logo
	 * 
	 * @param logo
	 *            logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * 获取联系地址
	 * 
	 * @return 联系地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置联系地址
	 * 
	 * @param address
	 *            联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取联系电话
	 * 
	 * @return 联系电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置联系电话
	 * 
	 * @param phone
	 *            联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取邮政编码
	 * 
	 * @return 邮政编码
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 设置邮政编码
	 * 
	 * @param zipCode
	 *            邮政编码
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 获取E-mail
	 * 
	 * @return E-mail
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置E-mail
	 * 
	 * @param email
	 *            E-mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取备案编号
	 * 
	 * @return 备案编号
	 */
	public String getCerttext() {
		return certtext;
	}

	/**
	 * 设置备案编号
	 * 
	 * @param certtext
	 *            备案编号
	 */
	public void setCerttext(String certtext) {
		this.certtext = certtext;
	}

	/**
	 * 获取价格精确位数
	 * 
	 * @return 价格精确位数
	 */
	public Integer getPriceScale() {
		return priceScale;
	}

	/**
	 * 设置价格精确位数
	 * 
	 * @param priceScale
	 *            价格精确位数
	 */
	public void setPriceScale(Integer priceScale) {
		this.priceScale = priceScale;
	}

	/**
	 * 获取价格精确方式
	 * 
	 * @return 价格精确方式
	 */
	public Setting.RoundType getPriceRoundType() {
		return priceRoundType;
	}

	/**
	 * 设置价格精确方式
	 * 
	 * @param priceRoundType
	 *            价格精确方式
	 */
	public void setPriceRoundType(Setting.RoundType priceRoundType) {
		this.priceRoundType = priceRoundType;
	}


	/**
	 * 获取默认市场价换算比例
	 * 
	 * @return 默认市场价换算比例
	 */
	public Double getDefaultMarketPriceScale() {
		return defaultMarketPriceScale;
	}

	/**
	 * 设置默认市场价换算比例
	 * 
	 * @param defaultMarketPriceScale
	 *            默认市场价换算比例
	 */
	public void setDefaultMarketPriceScale(Double defaultMarketPriceScale) {
		this.defaultMarketPriceScale = defaultMarketPriceScale;
	}

//	/**
//	 * 获取验证码类型
//	 * 
//	 * @return 验证码类型
//	 */
//	public Setting.CaptchaType[] getCaptchaTypes() {
//		return captchaTypes;
//	}
//
//	/**
//	 * 设置验证码类型
//	 * 
//	 * @param captchaTypes
//	 *            验证码类型
//	 */
//	public void setCaptchaTypes(Setting.CaptchaType[] captchaTypes) {
//		this.captchaTypes = captchaTypes;
//	}

	/**
	 * 获取最大登录失败尝试次数
	 * 
	 * @return 最大登录失败尝试次数
	 */
	public Integer getMaxFailedLoginAttempts() {
		return maxFailedLoginAttempts;
	}

	/**
	 * 设置最大登录失败尝试次数
	 * 
	 * @param maxFailedLoginAttempts
	 *            最大登录失败尝试次数
	 */
	public void setMaxFailedLoginAttempts(Integer maxFailedLoginAttempts) {
		this.maxFailedLoginAttempts = maxFailedLoginAttempts;
	}

	/**
	 * 获取密码锁定时间
	 * 
	 * @return 密码锁定时间
	 */
	public Integer getPasswordLockTime() {
		return passwordLockTime;
	}

	/**
	 * 设置密码锁定时间
	 * 
	 * @param passwordLockTime
	 *            密码锁定时间
	 */
	public void setPasswordLockTime(Integer passwordLockTime) {
		this.passwordLockTime = passwordLockTime;
	}

	/**
	 * 获取安全密匙有效时间
	 * 
	 * @return 安全密匙有效时间
	 */
	public Integer getSafeKeyExpiryTime() {
		return safeKeyExpiryTime;
	}

	/**
	 * 设置安全密匙有效时间
	 * 
	 * @param safeKeyExpiryTime
	 *            安全密匙有效时间
	 */
	public void setSafeKeyExpiryTime(Integer safeKeyExpiryTime) {
		this.safeKeyExpiryTime = safeKeyExpiryTime;
	}

	/**
	 * 获取上传文件最大限制
	 * 
	 * @return 上传文件最大限制
	 */
	public Integer getUploadMaxSize() {
		return uploadMaxSize;
	}

	/**
	 * 设置上传文件最大限制
	 * 
	 * @param uploadMaxSize
	 *            上传文件最大限制
	 */
	public void setUploadMaxSize(Integer uploadMaxSize) {
		this.uploadMaxSize = uploadMaxSize;
	}

	/**
	 * 获取允许上传图片扩展名
	 * 
	 * @return 允许上传图片扩展名
	 */
	public String getUploadImageExtension() {
		return uploadImageExtension;
	}

	/**
	 * 设置允许上传图片扩展名
	 * 
	 * @param uploadImageExtension
	 *            允许上传图片扩展名
	 */
	public void setUploadImageExtension(String uploadImageExtension) {
		if (uploadImageExtension != null) {
			uploadImageExtension = uploadImageExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
		}
		this.uploadImageExtension = uploadImageExtension;
	}

	/**
	 * 获取允许上传媒体扩展名
	 * 
	 * @return 允许上传媒体扩展名
	 */
	public String getUploadMediaExtension() {
		return uploadMediaExtension;
	}

	/**
	 * 设置允许上传媒体扩展名
	 * 
	 * @param uploadMediaExtension
	 *            允许上传媒体扩展名
	 */
	public void setUploadMediaExtension(String uploadMediaExtension) {
		if (uploadMediaExtension != null) {
			uploadMediaExtension = uploadMediaExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
		}
		this.uploadMediaExtension = uploadMediaExtension;
	}

	/**
	 * 获取允许上传文件扩展名
	 * 
	 * @return 允许上传文件扩展名
	 */
	public String getUploadFileExtension() {
		return uploadFileExtension;
	}

	/**
	 * 设置允许上传文件扩展名
	 * 
	 * @param uploadFileExtension
	 *            允许上传文件扩展名
	 */
	public void setUploadFileExtension(String uploadFileExtension) {
		if (uploadFileExtension != null) {
			uploadFileExtension = uploadFileExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
		}
		this.uploadFileExtension = uploadFileExtension;
	}

	/**
	 * 获取图片上传路径
	 * 
	 * @return 图片上传路径
	 */
	public String getImageUploadPath() {
		return imageUploadPath;
	}

	/**
	 * 设置图片上传路径
	 * 
	 * @param imageUploadPath
	 *            图片上传路径
	 */
	public void setImageUploadPath(String imageUploadPath) {
		if (imageUploadPath != null) {
			if (!imageUploadPath.startsWith("/")) {
				imageUploadPath = "/" + imageUploadPath;
			}
			if (!imageUploadPath.endsWith("/")) {
				imageUploadPath += "/";
			}
		}
		this.imageUploadPath = imageUploadPath;
	}

	/**
	 * 获取媒体上传路径
	 * 
	 * @return 媒体上传路径
	 */
	public String getMediaUploadPath() {
		return mediaUploadPath;
	}

	/**
	 * 设置媒体上传路径
	 * 
	 * @param mediaUploadPath
	 *            媒体上传路径
	 */
	public void setMediaUploadPath(String mediaUploadPath) {
		if (mediaUploadPath != null) {
			if (!mediaUploadPath.startsWith("/")) {
				mediaUploadPath = "/" + mediaUploadPath;
			}
			if (!mediaUploadPath.endsWith("/")) {
				mediaUploadPath += "/";
			}
		}
		this.mediaUploadPath = mediaUploadPath;
	}

	/**
	 * 获取文件上传路径
	 * 
	 * @return 文件上传路径
	 */
	public String getFileUploadPath() {
		return fileUploadPath;
	}

	/**
	 * 设置文件上传路径
	 * 
	 * @param fileUploadPath
	 *            文件上传路径
	 */
	public void setFileUploadPath(String fileUploadPath) {
		if (fileUploadPath != null) {
			if (!fileUploadPath.startsWith("/")) {
				fileUploadPath = "/" + fileUploadPath;
			}
			if (!fileUploadPath.endsWith("/")) {
				fileUploadPath += "/";
			}
		}
		this.fileUploadPath = fileUploadPath;
	}

	/**
	 * 获取SMTP服务器地址
	 * 
	 * @return SMTP服务器地址
	 */
	public String getSmtpHost() {
		return smtpHost;
	}

	/**
	 * 设置SMTP服务器地址
	 * 
	 * @param smtpHost
	 *            SMTP服务器地址
	 */
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * 获取SMTP服务器端口
	 * 
	 * @return SMTP服务器端口
	 */
	public Integer getSmtpPort() {
		return smtpPort;
	}

	/**
	 * 设置SMTP服务器端口
	 * 
	 * @param smtpPort
	 *            SMTP服务器端口
	 */
	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	/**
	 * 获取SMTP用户名
	 * 
	 * @return SMTP用户名
	 */
	public String getSmtpUsername() {
		return smtpUsername;
	}

	/**
	 * 设置SMTP用户名
	 * 
	 * @param smtpUsername
	 *            SMTP用户名
	 */
	public void setSmtpUsername(String smtpUsername) {
		this.smtpUsername = smtpUsername;
	}

	/**
	 * 获取SMTP密码
	 * 
	 * @return SMTP密码
	 */
	public String getSmtpPassword() {
		return smtpPassword;
	}

	/**
	 * 设置SMTP密码
	 * 
	 * @param smtpPassword
	 *            SMTP密码
	 */
	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	/**
	 * 获取SMTP是否启用SSL
	 * 
	 * @return SMTP是否启用SSL
	 */
	public Boolean getSmtpSSLEnabled() {
		return smtpSSLEnabled;
	}

	/**
	 * 设置SMTP是否启用SSL
	 * 
	 * @param smtpSSLEnabled
	 *            SMTP是否启用SSL
	 */
	public void setSmtpSSLEnabled(Boolean smtpSSLEnabled) {
		this.smtpSSLEnabled = smtpSSLEnabled;
	}

	/**
	 * 获取发件人邮箱
	 * 
	 * @return 发件人邮箱
	 */
	public String getSmtpFromMail() {
		return smtpFromMail;
	}

	/**
	 * 设置发件人邮箱
	 * 
	 * @param smtpFromMail
	 *            发件人邮箱
	 */
	public void setSmtpFromMail(String smtpFromMail) {
		this.smtpFromMail = smtpFromMail;
	}

	/**
	 * 获取货币符号
	 * 
	 * @return 货币符号
	 */
	public String getCurrencySign() {
		return currencySign;
	}

	/**
	 * 设置货币符号
	 * 
	 * @param currencySign
	 *            货币符号
	 */
	public void setCurrencySign(String currencySign) {
		this.currencySign = currencySign;
	}

	/**
	 * 获取货币单位
	 * 
	 * @return 货币单位
	 */
	public String getCurrencyUnit() {
		return currencyUnit;
	}

	/**
	 * 设置货币单位
	 * 
	 * @param currencyUnit
	 *            货币单位
	 */
	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}


	/**
	 * 获取是否开启开发模式
	 * 
	 * @return 是否开启开发模式
	 */
	public Boolean getIsDevelopmentEnabled() {
		return isDevelopmentEnabled;
	}

	/**
	 * 设置是否开启开发模式
	 * 
	 * @param isDevelopmentEnabled
	 *            是否开启开发模式
	 */
	public void setIsDevelopmentEnabled(Boolean isDevelopmentEnabled) {
		this.isDevelopmentEnabled = isDevelopmentEnabled;
	}

	/**
	 * 获取Cookie路径
	 * 
	 * @return Cookie路径
	 */
	public String getCookiePath() {
		return cookiePath;
	}

	/**
	 * 设置Cookie路径
	 * 
	 * @param cookiePath
	 *            Cookie路径
	 */
	public void setCookiePath(String cookiePath) {
		if (cookiePath != null && !cookiePath.endsWith("/")) {
			cookiePath += "/";
		}
		this.cookiePath = cookiePath;
	}

	/**
	 * 获取Cookie作用域
	 * 
	 * @return Cookie作用域
	 */
	public String getCookieDomain() {
		return cookieDomain;
	}

	/**
	 * 设置Cookie作用域
	 * 
	 * @param cookieDomain
	 *            Cookie作用域
	 */
	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}

	/**
	 * 获取区域设置
	 * 
	 * @return 区域设置
	 */
	public Setting.Locale getLocale() {
		return locale;
	}

	/**
	 * 设置区域设置
	 * 
	 * @param locale
	 *            区域设置
	 */
	public void setLocale(Setting.Locale locale) {
		this.locale = locale;
	}

	/**
	 * 获取允许上传图片扩展名
	 * 
	 * @return 允许上传图片扩展名
	 */
	public String[] getUploadImageExtensions() {
		return StringUtils.split(uploadImageExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传媒体扩展名
	 * 
	 * @return 允许上传媒体扩展名
	 */
	public String[] getUploadMediaExtensions() {
		return StringUtils.split(uploadMediaExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传文件扩展名
	 * 
	 * @return 允许上传文件扩展名
	 */
	public String[] getUploadFileExtensions() {
		return StringUtils.split(uploadFileExtension, SEPARATOR);
	}

	/**
	 * 设置精度
	 * 
	 * @param amount
	 *            数值
	 * @return 数值
	 */
	public BigDecimal setScale(BigDecimal amount) {
		if (amount != null && getPriceScale() != null && getPriceRoundType() != null) {
			switch (getPriceRoundType()) {
			case roundUp:
				return amount.setScale(getPriceScale(), BigDecimal.ROUND_UP);
			case roundDown:
				return amount.setScale(getPriceScale(), BigDecimal.ROUND_DOWN);
			case roundHalfUp:
				return amount.setScale(getPriceScale(), BigDecimal.ROUND_HALF_UP);
			}
		}
		return amount;
	}

	/**
	 * 解析图片上传路径
	 * 
	 * @return 图片上传路径
	 */
	public String resolveImageUploadPath() {
		return resolveImageUploadPath(null);
	}

	/**
	 * 解析图片上传路径
	 * 
	 * @param model
	 *            数据
	 * @return 图片上传路径
	 */
	public String resolveImageUploadPath(Map<String, Object> model) {
		try {
			return FreeMarkerUtils.process(getImageUploadPath(), model);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (TemplateException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 解析媒体上传路径
	 * 
	 * @return 媒体上传路径
	 */
	public String resolveMediaUploadPath() {
		return resolveMediaUploadPath(null);
	}

	/**
	 * 解析媒体上传路径
	 * 
	 * @param model
	 *            数据
	 * @return 媒体上传路径
	 */
	public String resolveMediaUploadPath(Map<String, Object> model) {
		try {
			return FreeMarkerUtils.process(getMediaUploadPath(), model);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (TemplateException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 解析文件上传路径
	 * 
	 * @return 文件上传路径
	 */
	public String resolveFileUploadPath() {
		return resolveFileUploadPath(null);
	}

	/**
	 * 解析文件上传路径
	 * 
	 * @param model
	 *            数据
	 * @return 文件上传路径
	 */
	public String resolveFileUploadPath(Map<String, Object> model) {
		try {
			return FreeMarkerUtils.process(getFileUploadPath(), model);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (TemplateException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 重写equals方法
	 * 
	 * @param obj
	 *            对象
	 * @return 是否相等
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * 重写hashCode方法
	 * 
	 * @return HashCode
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public String getAppID() {
		return appID;
		
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public String getPublicKey() {
		return publicKey;
		
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
}
