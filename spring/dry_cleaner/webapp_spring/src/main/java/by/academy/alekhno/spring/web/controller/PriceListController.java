package by.academy.alekhno.spring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.academy.alekhno.spring.interf.ClotherService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Type;

@Controller
@RequestMapping(value = "/price_list")
public class PriceListController {

	@Autowired
	private ClotherService clotherService;

	@RequestMapping(method = RequestMethod.GET)
	public String toPriceList(Model model) {
		List<Type> types = clotherService.getTypes();
		model.addAttribute("types", types);

		return "price_list";
	}

	@RequestMapping(value = "/{type_id}", method = RequestMethod.GET)
	public String toPriceListWithType(Model model, @PathVariable int type_id) {
		List<Clother> clothes = clotherService.getClothesByTypeId(type_id);
		model.addAttribute("clothes", clothes);

		// Loose type???

		List<Type> types = clotherService.getTypes();
		model.addAttribute("types", types);

		return "price_list";
	}

	// @Secured("ROLE_USER")
	// @RequestMapping(value = "/add/{clother_id}", method = RequestMethod.GET)
	// public String toAddOrder(Model model, @PathVariable int clother_id) {
	// Clother clother = clotherService.getClotherById(clother_id);
	// model.addAttribute("add_clother", clother);
	//
	// return "orders";
	// }
}
