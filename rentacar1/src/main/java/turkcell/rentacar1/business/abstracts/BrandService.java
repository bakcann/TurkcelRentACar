package turkcell.rentacar1.business.abstracts;

import java.util.List;

import turkcell.rentacar1.business.dtos.ListBrandDto;
import turkcell.rentacar1.business.requests.CreateBrandRequest;
import turkcell.rentacar1.core.concretes.BusinessException;

public interface BrandService {
	
		List<ListBrandDto> getAll();
		void add(CreateBrandRequest createBrandRequest) throws BusinessException;
		ListBrandDto getByBrandId(int brandId) throws BusinessException;
		
		

}
