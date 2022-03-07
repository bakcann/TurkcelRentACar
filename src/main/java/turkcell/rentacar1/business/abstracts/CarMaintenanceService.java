package turkcell.rentacar1.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import turkcell.rentacar1.business.dtos.GetListCarMaintenanceDto;
import turkcell.rentacar1.business.dtos.ListCarMaintenanceDto;
import turkcell.rentacar1.business.requests.CreateCarMaintenanceRequest;
import turkcell.rentacar1.business.requests.DeleteCarMaintenanceRequest;
import turkcell.rentacar1.business.requests.UpdateCarMaintenanceRequest;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;

public interface CarMaintenanceService {
	
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);
	
	Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest);
	
	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);
	
	DataResult<List<ListCarMaintenanceDto>> getAll();
	
	DataResult<GetListCarMaintenanceDto> getByCarMaintenanceId(int maintenanceId);
	
	DataResult<List<ListCarMaintenanceDto>> getAllPaged(int pageNo, int pageSize);
	
	DataResult<List<ListCarMaintenanceDto>> getAllSorted(Sort.Direction direction);
	
	DataResult<GetListCarMaintenanceDto> getByCar_CarId(int carId);
	

}
