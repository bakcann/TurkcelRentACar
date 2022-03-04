package turkcell.rentacar1.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import turkcell.rentacar1.business.abstracts.CarService;
import turkcell.rentacar1.business.dtos.ListCarDto;
import turkcell.rentacar1.business.requests.CreateCarRequest;
import turkcell.rentacar1.business.requests.DeleteCarRequest;
import turkcell.rentacar1.business.requests.UpdateCarRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	
	private CarService carService;

	@Autowired
	public CarsController(CarService carService) {
	
		this.carService = carService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListCarDto>> getAll(){
		return carService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateCarRequest createCarRequest) throws BusinessException{
		return this.carService.add(createCarRequest);
	}
	
	@GetMapping("/getcarid")
	public DataResult<ListCarDto> getByCarId(int carId) throws BusinessException{
		return this.carService.getByCarId(carId);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) throws BusinessException{
		return this.carService.delete(deleteCarRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateCarRequest updateCarRequest) throws BusinessException{
		return this.carService.update(updateCarRequest);
	}
	@GetMapping("/getAllPaged")
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize){
		return this.carService.getAllPaged(pageNo, pageSize);
	}
	@GetMapping("/getAllSorted")
	public DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction){
		return this.carService.getAllSorted(direction);
	}
	@GetMapping("/getByDailyPrice")
	public DataResult<List<ListCarDto>> getByDaiilyPrice(double dailyPrice){
		return this.carService.getByDailyPriceLessThanEqual(dailyPrice);
	}

	
	


}