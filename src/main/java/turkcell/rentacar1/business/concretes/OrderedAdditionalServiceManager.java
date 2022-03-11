package turkcell.rentacar1.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import turkcell.rentacar1.business.abstracts.OrderedAdditionalServiceService;
import turkcell.rentacar1.business.dtos.ListOrderedAdditionalServiceDto;
import turkcell.rentacar1.core.utilities.mapping.ModelMapperService;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.ErrorDataResult;
import turkcell.rentacar1.core.utilities.results.SuccessDataResult;
import turkcell.rentacar1.dataAccess.abstracts.OrderedAdditionalServiceDao;

@Service
public class OrderedAdditionalServiceManager implements OrderedAdditionalServiceService {
	
	private ModelMapperService modelMapperService;
	private OrderedAdditionalServiceDao orderedAdditionalServiceDao;

	@Autowired
	public OrderedAdditionalServiceManager(ModelMapperService modelMapperService,
			OrderedAdditionalServiceDao orderedAdditionalServiceDao) {
		this.modelMapperService = modelMapperService;
		this.orderedAdditionalServiceDao = orderedAdditionalServiceDao;
	}

	@Override
	public DataResult<List<ListOrderedAdditionalServiceDto>> getAll() {
		var result = this.orderedAdditionalServiceDao.findAll();
		 if(result==null) {
			 return new ErrorDataResult<List<ListOrderedAdditionalServiceDto>>("Bu liste boş.");
		 }
		List<ListOrderedAdditionalServiceDto> response =result.stream()
											.map(orderedAdditionalService -> this.modelMapperService.forDto()
														.map(orderedAdditionalService, ListOrderedAdditionalServiceDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListOrderedAdditionalServiceDto>>(response);
	}

}
