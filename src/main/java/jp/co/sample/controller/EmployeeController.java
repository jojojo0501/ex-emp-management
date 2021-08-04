package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラー
 * 
 * @author kanekojota
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@ModelAttribute
	public UpdateEmployeeForm setUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}

	/**
	 * 従業員一覧を出力する
	 * 
	 * @param model
	 * @return 従業員一覧ページへのフォワード
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		model.addAttribute("employeeList", employeeService.showList());
		return "employee/list";
	}
	/**
	 * 受け取ったidの従業員情報をリクエストスコープに格納する
	 * @param id
	 * @param model
	 * @return 従業員詳細ページへフォワード
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id,Model model) {
		int intId = Integer.parseInt(id);
		model.addAttribute("employee", employeeService.showDetail(intId));
		return "employee/detail";
	}

}
