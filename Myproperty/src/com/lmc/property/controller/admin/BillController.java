package com.lmc.property.controller.admin;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lmc.property.entity.Bill;
import com.lmc.property.service.BillService;

/**
 * @author 李敏成
 *
 */
@Controller
@RequestMapping("/admin/bill")
public class BillController extends BaseController {
	
	@Inject
	private BillService billService;
	
	@PostMapping("/upload")
	public String uploadBill(Bill bill){
		if(!isValid(bill)){
			return null;
		};
		billService.uploadBill(bill);
		return null;
	}
	
	@GetMapping("/delete")
	public String deleteBill(long id){
		billService.delete(id);
		return null;
	}
}
