package turkcell.rentacar1.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import turkcell.rentacar1.business.abstracts.AdditionalServiceService;
import turkcell.rentacar1.business.dtos.ListAdditionalServiceDto;
import turkcell.rentacar1.business.requests.CreateAdditionalServiceRequest;
import turkcell.rentacar1.business.requests.DeleteAdditionalServiceRequest;
import turkcell.rentacar1.business.requests.UpdateAdditionalServiceRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.mapping.ModelMapperService;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.ErrorDataResult;
import turkcell.rentacar1.core.utilities.results.Result;
import turkcell.rentacar1.core.utilities.results.SuccessDataResult;
import turkcell.rentacar1.core.utilities.results.SuccessResult;
import turkcell.rentacar1.dataAccess.abstracts.AdditionalServiceDao;
import turkcell.rentacar1.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {
	
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException {
		AdditionalService additionalService = this.modelMapperService.forRequest()
				.map(createAdditionalServiceRequest, AdditionalService.class);
		this.additionalServiceDao.save(additionalService);
		
		return new SuccessResult("AdditionalService.eklendi");
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException {
		checkAdditionalServiceId(deleteAdditionalServiceRequest.getAdditionalServiceId());
		this.additionalServiceDao.deleteById(deleteAdditionalServiceRequest.getAdditionalServiceId());
		
		return new SuccessResult("AdditionalService.silindi");
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException {
		checkAdditionalServiceId(updateAdditionalServiceRequest.getAdditionalServiceId());
		AdditionalService additionalService = this.modelMapperService.forRequest()
				.map(updateAdditionalServiceRequest, AdditionalService.class);
		this.additionalServiceDao.save(additionalService);
		
		return new SuccessResult("AdditionalService.güncellendi");
	}

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getAll() {
		 var result = this.additionalServiceDao.findAll();
		 if(result==null) {
			 return new ErrorDataResult<List<ListAdditionalServiceDto>>("Bu liste boş.");
		 }
		List<ListAdditionalServiceDto> response =result.stream()
											.map(additionalService -> this.modelMapperService.forDto()
														.map(additionalService, ListAdditionalServiceDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListAdditionalServiceDto>>(response);
	}

	@Override
	public DataResult<ListAdditionalServiceDto> getById(int additionalServiceId)throws BusinessException {
		checkAdditionalServiceId(additionalServiceId);
		var result = this.additionalServiceDao.getById(additionalServiceId);
		ListAdditionalServiceDto response = this.modelMapperService.forDto().map(result,ListAdditionalServiceDto.class);
		return new SuccessDataResult<ListAdditionalServiceDto>(response);
	}

	@Override
	public boolean checkAdditionalServiceId(int additionalServiceId) throws BusinessException {
		if(this.additionalServiceDao.getById(additionalServiceId)==null) {
			throw new BusinessException("Bu Id mevcut değil.");
		}
		return true;
	}

}
