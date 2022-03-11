package turkcell.rentacar1.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import turkcell.rentacar1.business.abstracts.CarMaintenanceService;
import turkcell.rentacar1.business.dtos.GetListCarMaintenanceDto;
import turkcell.rentacar1.business.dtos.ListCarMaintenanceDto;
import turkcell.rentacar1.business.requests.CreateCarMaintenanceRequest;
import turkcell.rentacar1.business.requests.DeleteCarMaintenanceRequest;
import turkcell.rentacar1.business.requests.UpdateCarMaintenanceRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;

@RestController
@RequestMapping("/api/carMaintenance")
public class CarMaintenanceController {
	
	private CarMaintenanceService carMaintenanceService;

	public CarMaintenanceController(CarMaintenanceService carMaintenanceService) {
		this.carMaintenanceService = carMaintenanceService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException{
		return this.carMaintenanceService.add(createCarMaintenanceRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException{
		return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException {
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		return this.carMaintenanceService.getAll();
	}
	
	@PostMapping("/getAllPaged")
	public DataResult<List<ListCarMaintenanceDto>> getAllPaged(int pageNo, int pageSize) {
		return this.carMaintenanceService.getAllPaged(pageNo, pageSize);
	}

	@PostMapping("/getAllSorted")
	public DataResult<List<ListCarMaintenanceDto>> getAllSorted(Sort.Direction direction) {
		return this.carMaintenanceService.getAllSorted(direction);
	}
	
	@GetMapping("/getCarMaintenanceId")
	public boolean getByMaintenanceId(int maintenanceId) throws BusinessException {
		return this.carMaintenanceService.checkIfCarMaintenanceId(maintenanceId);
	}

	@GetMapping("/getByCar_CarId")
	public List<GetListCarMaintenanceDto> getByCar_CarId(int carId) throws BusinessException {
		return this.carMaintenanceService.getByCar_CarId(carId);
	}
}
