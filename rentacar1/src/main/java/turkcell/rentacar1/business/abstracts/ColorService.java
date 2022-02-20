package turkcell.rentacar1.business.abstracts;

import java.util.List;

import turkcell.rentacar1.business.dtos.ListColorDto;
import turkcell.rentacar1.business.requests.CreateColorRequest;
import turkcell.rentacar1.business.requests.DeleteColorRequest;
import turkcell.rentacar1.business.requests.UpdateColorRequest;
import turkcell.rentacar1.core.concretes.BusinessException;

public interface ColorService {
	
		List<ListColorDto> getAll();
		void add(CreateColorRequest createColorRequest) throws BusinessException;
		ListColorDto getByColorId(int colorId) throws BusinessException;
		void delete(DeleteColorRequest deleteColorRequest) throws BusinessException;
		void update(UpdateColorRequest updateColorRequest) throws BusinessException;

}
