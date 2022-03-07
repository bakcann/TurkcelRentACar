package turkcell.rentacar1.business.abstracts;

import java.util.List;

import turkcell.rentacar1.business.dtos.ListBrandDto;
import turkcell.rentacar1.business.requests.CreateBrandRequest;
import turkcell.rentacar1.business.requests.DeleteBrandRequest;
import turkcell.rentacar1.business.requests.UpdateBrandRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;

public interface BrandService {
	
		Result add(CreateBrandRequest createBrandRequest) throws BusinessException;
		
		Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException;
		
		Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException;
		
		DataResult<List<ListBrandDto>> getAll();
		
		DataResult<ListBrandDto> getByBrandId(int brandId);
		
		

}
