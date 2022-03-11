package turkcell.rentacar1.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import turkcell.rentacar1.business.abstracts.RentalService;
import turkcell.rentacar1.business.dtos.GetListRentalDto;
import turkcell.rentacar1.business.dtos.ListRentalDto;
import turkcell.rentacar1.business.requests.CreateRentalRequest;
import turkcell.rentacar1.business.requests.DeleteRentalRequest;
import turkcell.rentacar1.business.requests.UpdateRentalRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rents")
public class RentalController {
	private RentalService rentalService;
	
	@Autowired
	public RentalController(RentalService rentalService) {
		this.rentalService = rentalService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateRentalRequest createRentalRequest) throws BusinessException{
		return this.rentalService.add(createRentalRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteRentalRequest deleteRentalRequest) throws BusinessException{
		return this.rentalService.delete(deleteRentalRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) throws BusinessException{
		return this.rentalService.update(updateRentalRequest);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListRentalDto>> getAll() {
		return this.rentalService.getAll();
	}
	
	@PostMapping("/getAllPaged")
	public DataResult<List<ListRentalDto>> getAllPaged(int pageNo, int pageSize) {
		return this.rentalService.getAllPaged(pageNo, pageSize);
	}

	@PostMapping("/getAllSorted")
	public DataResult<List<ListRentalDto>> getAllSorted(JpaSort.Direction direction) {
		return this.rentalService.getAllSorted(direction);
	}
	
	@GetMapping("/getRentId")
	public DataResult<GetListRentalDto> getByCarMaintenanceId(int carMaintenanceId) throws BusinessException {
		return this.rentalService.getByRentalId(carMaintenanceId);
	}

	@GetMapping("/getByCar_CarId")
	public List<ListRentalDto> getByCar_CarId(int carId) throws BusinessException {
		return this.rentalService.getByCar_CarId(carId);
	}
}
