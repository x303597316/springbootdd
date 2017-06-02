package com.ll.dd.web.controller.small;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ll.dd.web.model.CapitalPlan;
import com.ll.dd.web.model.GridModel;
import com.ll.dd.web.utils.JsonMsg;

@Controller
@RequestMapping("/main")
public class IndexController {

	@RequestMapping("/index")
	public @ResponseBody Object index() {
		List<GridModel> glist = new ArrayList<GridModel>();
		GridModel gm = new GridModel();
		gm.setLiClass("mui-icon mui-icon-email");
		gm.setLiName("资金计划");
		gm.setLiUrl("capitalPlan.html");
		glist.add(gm);
		GridModel gm2 = new GridModel();
		gm2.setLiClass("mui-icon mui-icon-home");
		gm2.setLiName("资金拨付");
		glist.add(gm2);
		return new JsonMsg(true, "操作成功", glist);
	}

	@RequestMapping("/capitalPlan")
	public @ResponseBody Object capitalPlan() {
		List<CapitalPlan> cplist = new ArrayList<CapitalPlan>();
		CapitalPlan cp = new CapitalPlan();
		cp.setDeptName("财务部");
		cp.setPersonName("arthur000");
		cp.setPlanMemo("一大波事情1，一大波事情2，一大波事情3，一大波事情4，一大波事情5");
		cp.setPlanTime("23:50");
		cplist.add(cp);
		CapitalPlan cp2 = new CapitalPlan();
		cp2.setDeptName("总经办");
		cp2.setPersonName("arthur222");
		cp2.setPlanMemo("一大波事情1，一大波事情2，一大波事情3，一大波事情4，一大波事情5");
		cp2.setPlanTime("23:55");
		cplist.add(cp2);
		return new JsonMsg(true, "操作成功", cplist);
	}

}
