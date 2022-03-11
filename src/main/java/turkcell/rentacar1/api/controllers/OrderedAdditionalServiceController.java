package turkcell.rentacar1.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import turkcell.rentacar1.business.abstracts.OrderedAdditionalServiceService;
import turkcell.rentacar1.business.dtos.ListOrderedAdditionalServiceDto;
import turkcell.rentacar1.core.utilities.results.DataResult;

@RestController
@RequestMapping("/api/orderedAdditionalServiceControllers")
public class OrderedAdditionalServiceController {
	
	private OrderedAdditionalServiceService orderedAdditionalServiceService;

	public OrderedAdditionalServiceController(OrderedAdditionalServiceService orderedAdditionalServiceService) {
		this.orderedAdditionalServiceService = orderedAdditionalServiceService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListOrderedAdditionalServiceDto>> getAll(){
		return this.orderedAdditionalServiceService.getAll();
	}

}
