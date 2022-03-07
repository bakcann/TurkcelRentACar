package turkcell.rentacar1.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import turkcell.rentacar1.business.dtos.GetListRentalDto;
import turkcell.rentacar1.business.dtos.ListRentalDto;
import turkcell.rentacar1.business.requests.CreateRentalRequest;
import turkcell.rentacar1.business.requests.DeleteRentalRequest;
import turkcell.rentacar1.business.requests.UpdateRentalRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;

public interface RentalService {
	
	Result add(CreateRentalRequest createRentalRequest) throws BusinessException;
	
	Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException;
	
	Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException;
	
	DataResult<List<ListRentalDto>> getAll();
	
	DataResult<GetListRentalDto> getByRentalId(int rentId);
	
	DataResult<List<ListRentalDto>> getAllPaged(int pageNo, int pageSize);
	
	DataResult<List<ListRentalDto>> getAllSorted(Sort.Direction direction);
	
	DataResult<ListRentalDto> getByCar_CarId(int carId);
	
	

}
