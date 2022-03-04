package turkcell.rentacar1.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import turkcell.rentacar1.business.dtos.ListCarDto;
import turkcell.rentacar1.business.requests.CreateCarRequest;
import turkcell.rentacar1.business.requests.DeleteCarRequest;
import turkcell.rentacar1.business.requests.UpdateCarRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;

public interface CarService {

	DataResult<List<ListCarDto>> getAll() ;
	
	Result add(CreateCarRequest createCarRequest) throws BusinessException;
	
	DataResult<ListCarDto> getByCarId(int carId);
	
	Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException;
	
	Result update(UpdateCarRequest updateCarRequest) throws BusinessException;
	
	DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize);
	
	DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction);
	
	DataResult<List<ListCarDto>> getByDailyPriceLessThanEqual(double dailyPrice);
}
