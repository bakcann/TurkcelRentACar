package turkcell.rentacar1.business.abstracts;

import java.util.List;

import turkcell.rentacar1.business.dtos.ListOrderedAdditionalServiceDto;
import turkcell.rentacar1.core.utilities.results.DataResult;

public interface OrderedAdditionalServiceService {
	
	DataResult<List<ListOrderedAdditionalServiceDto>> getAll();

}
