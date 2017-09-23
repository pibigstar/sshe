package com.lei.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.lei.action.base.BaseAction;
import com.lei.service.RepairServiceI;

@Action(value = "repairAction")
public class RepairAction extends BaseAction {
	
	private RepairServiceI repairService;
	
	public RepairServiceI getRepairService() {
		return repairService;
	}
	@Autowired
	public void setRepairService(RepairServiceI repairService) {
		this.repairService = repairService;
	}

	public void repair() {
		repairService.repair();
	}

}
