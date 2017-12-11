package com.lmc.property.service;

import com.lmc.property.entity.Bill;

public interface BillService extends BaseService<Bill, Long> {

	void uploadBill(Bill bill);

}
