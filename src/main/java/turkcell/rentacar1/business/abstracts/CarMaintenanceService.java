package turkcell.rentacar1.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import turkcell.rentacar1.business.dtos.GetListCarMaintenanceDto;
import turkcell.rentacar1.business.dtos.ListCarMaintenanceDto;
import turkcell.rentacar1.business.requests.CreateCarMaintenanceRequest;
import turkcell.rentacar1.business.requests.DeleteCarMaintenanceRequest;
import turkcell.rentacar1.business.requests.UpdateCarMaintenanceRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;

public interface CarMaintenanceService {
	
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException;
	
	Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException;
	
	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest)throws BusinessException;
	
	DataResult<List<ListCarMaintenanceDto>> getAll();
	
	DataResult<List<ListCarMaintenanceDto>> getAllPaged(int pageNo, int pageSize);
	
	DataResult<List<ListCarMaintenanceDto>> getAllSorted(Sort.Direction direction);
	
	DataResult<GetListCarMaintenanceDto> getByCarMaintenanceId(int maintenanceId) throws BusinessException;
	
	List<GetListCarMaintenanceDto> getByCar_CarId(int carId) throws BusinessException;
	
	boolean checkIfCarMaintenanceId(int maintenanceId) throws BusinessException;
	
	boolean checkIfCarMaintenceReturnDate(int carId) throws BusinessException;
	

}
