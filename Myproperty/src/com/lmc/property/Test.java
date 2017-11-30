package com.lmc.property;

import java.math.BigDecimal;

public class Test {
	public static void main(String[] args) {
		BigDecimal amountPaid = new BigDecimal("-12256.53225");
		System.out.println(amountPaid.intValue());
		System.out.println(amountPaid.floatValue());
		System.out.println(amountPaid.doubleValue());
		System.out.println(amountPaid.longValue());
		System.out.println(0L==0);
	}
}
