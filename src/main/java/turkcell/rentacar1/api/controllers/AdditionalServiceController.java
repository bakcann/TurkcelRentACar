package turkcell.rentacar1.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import turkcell.rentacar1.business.abstracts.AdditionalServiceService;
import turkcell.rentacar1.business.dtos.ListAdditionalServiceDto;
import turkcell.rentacar1.business.requests.CreateAdditionalServiceRequest;
import turkcell.rentacar1.business.requests.DeleteAdditionalServiceRequest;
import turkcell.rentacar1.business.requests.UpdateAdditionalServiceRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalServiceController")
public class AdditionalServiceController {
	
	private AdditionalServiceService additionalServiceService;
	
	@Autowired
	public AdditionalServiceController(AdditionalServiceService additionalServiceService) {
		this.additionalServiceService = additionalServiceService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest)
										throws BusinessException{
		return this.additionalServiceService.add(createAdditionalServiceRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteAdditionalServiceRequest deleteAdditionalServiceRequest)
			throws BusinessException {
		return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest )
			throws BusinessException {
		return this.additionalServiceService.update(updateAdditionalServiceRequest);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListAdditionalServiceDto>> getAll() {
		return this.additionalServiceService.getAll();
	}

	@GetMapping("/getAdditionalServiceId")
	public DataResult<ListAdditionalServiceDto> getById(int additionalServiceId) throws BusinessException{
		return this.additionalServiceService.getById(additionalServiceId);
	}

}
