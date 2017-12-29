package com.lmc.property.controller.admin;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lmc.property.Page;
import com.lmc.property.Pageable;
import com.lmc.property.Results;
import com.lmc.property.entity.Community;
import com.lmc.property.service.CommunityService;

/**
 * @author 李敏成
 *
 */
@Controller
@RequestMapping("/admin/community")
public class CommunityController extends BaseController {

	@Inject
	CommunityService communityService;
	
	@GetMapping("/list")
	public String list(Pageable pageable,ModelMap model) {
		Page<Community> findPage = communityService.findPage(pageable);
		model.addAttribute("page", findPage);
		return "admin/community/list";
	}
	
	@PostMapping("/create")
	public String createCommunity(Community community,RedirectAttributes redirectAttributes){
		if(!isValid(community)){
			return null;
		};
		communityService.createCommunity(community);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list";
	}
	
	@GetMapping("/edit")
	public String editCommunity(Long id, ModelMap model){
		Community community = communityService.find(id);
		model.addAttribute("community", community);
		return "admin/community/edit";
	}
	
	@PostMapping("/update")
	public String updateCommunity(Community community,RedirectAttributes redirectAttributes){
		if(!isValid(community)){
			return null;
		};
		communityService.update(community);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list";
	}
	
	@GetMapping("/delete")
	public ResponseEntity<Map<String,String>> deleteCommunity(Long id){
		communityService.delete(id);
		return Results.OK;
	}

}
