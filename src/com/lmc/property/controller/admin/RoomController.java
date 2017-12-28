package com.lmc.property.controller.admin;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lmc.property.Page;
import com.lmc.property.Pageable;
import com.lmc.property.Results;
import com.lmc.property.entity.Room;
import com.lmc.property.service.RoomService;

/**
 * @author 李敏成
 *
 */
@Controller
@RequestMapping("/admin/room")
public class RoomController extends BaseController {
	
	@Inject
	private RoomService roomService;
	
	@GetMapping("/list")
	public String list(Pageable pageable,ModelMap model) {
		Page<Room> findPage = roomService.findPage(pageable);
		model.addAttribute("page", findPage);
		return null;
	}
	
	@PostMapping("/create")
	public String createRoom(Room room){
		if(!isValid(room)){
			return null;
		}
		roomService.createRoom(room);
		return null;
	}
	
	@GetMapping("/edit")
	public String editRoom(long id ,ModelMap model){
		Room room = roomService.find(id);
		model.addAttribute("room", room);
		return null;
	}
	
	@PostMapping("/update")
	public String updateRoom(Room room ,ModelMap model){
		if(!isValid(room)){
			return null;
		}
		roomService.update(room);
		return null;
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Map<String,String>> deleteRoom(long id){
		roomService.delete(id);
		return Results.OK;
	}

}
