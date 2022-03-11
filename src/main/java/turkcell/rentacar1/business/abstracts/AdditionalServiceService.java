package turkcell.rentacar1.business.abstracts;

import java.util.List;

import turkcell.rentacar1.business.dtos.ListAdditionalServiceDto;
import turkcell.rentacar1.business.requests.CreateAdditionalServiceRequest;
import turkcell.rentacar1.business.requests.DeleteAdditionalServiceRequest;
import turkcell.rentacar1.business.requests.UpdateAdditionalServiceRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;

public interface AdditionalServiceService {
	
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest)throws BusinessException;
	
	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest)throws BusinessException;
	
	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest)throws BusinessException;
	
	DataResult<List<ListAdditionalServiceDto>> getAll();
	
	DataResult<ListAdditionalServiceDto> getById(int additionalServiceId) throws BusinessException;
	
	boolean checkAdditionalServiceId(int additionalServiceId)throws BusinessException;

}
