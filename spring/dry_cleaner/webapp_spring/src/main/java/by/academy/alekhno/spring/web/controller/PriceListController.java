package by.academy.alekhno.spring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.academy.alekhno.spring.interf.ClotherService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Type;

@Controller
public class PriceListController {

	@Autowired
	private ClotherService clotherService;

	@Secured("ROLE_USER")
	@RequestMapping(value = "/price_list", method = RequestMethod.GET)
	public String toPriceList(Model map) {
		List<Type> types = clotherService.getTypes();
		map.addAttribute("types", types);

		return "price_list";
	}

	// @Secured("ROLE_ADMIN")
	@RequestMapping(value = "/price_list/{type_id}", method = RequestMethod.GET)
	public String toPriceListWithType(Model map, @PathVariable int type_id) {
		List<Clother> clothes = clotherService.getClothesByTypeId(type_id);
		map.addAttribute("clothes", clothes);

		// Loose type???

		List<Type> types = clotherService.getTypes();
		map.addAttribute("types", types);

		return "price_list";
	}

}
