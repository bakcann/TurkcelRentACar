package turkcell.rentacar1.business.abstracts;

import java.util.List;

import turkcell.rentacar1.business.dtos.ListColorDto;
import turkcell.rentacar1.business.requests.CreateColorRequest;
import turkcell.rentacar1.business.requests.DeleteColorRequest;
import turkcell.rentacar1.business.requests.UpdateColorRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;

public interface ColorService {
	
		DataResult<List<ListColorDto>> getAll();
		Result add(CreateColorRequest createColorRequest) throws BusinessException;
		DataResult<ListColorDto> getByColorId(int colorId) ;
		Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException;
		Result update(UpdateColorRequest updateColorRequest) throws BusinessException;

}
