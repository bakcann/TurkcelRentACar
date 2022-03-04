package turkcell.rentacar1.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import turkcell.rentacar1.business.abstracts.ColorService;
import turkcell.rentacar1.business.dtos.ListColorDto;
import turkcell.rentacar1.business.requests.CreateColorRequest;
import turkcell.rentacar1.business.requests.DeleteColorRequest;
import turkcell.rentacar1.business.requests.UpdateColorRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.mapping.ModelMapperService;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.ErrorDataResult;
import turkcell.rentacar1.core.utilities.results.ErrorResult;
import turkcell.rentacar1.core.utilities.results.Result;
import turkcell.rentacar1.core.utilities.results.SuccessDataResult;
import turkcell.rentacar1.core.utilities.results.SuccessResult;
import turkcell.rentacar1.dataAccess.abstracts.ColorDao;
import turkcell.rentacar1.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{
	
	private ColorDao colorDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListColorDto>> getAll() {
		var result = this.colorDao.findAll();
		List<ListColorDto> response =result.stream()
				.map(color->this.modelMapperService.forDto()
						.map(color, ListColorDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListColorDto>>(response);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) throws BusinessException {
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		if(checkIfColorName(color.getColorName())) {
			this.colorDao.save(color);
			return new SuccessResult("Renk eklendi");
		}
		return new ErrorResult("Renk eklenemedi.");
	}

	@Override
	public DataResult<ListColorDto> getByColorId(int colorId)  {
		var result = this.colorDao.getByColorId(colorId);
		if(result!=null) {
			ListColorDto response = this.modelMapperService.forDto().map(result, ListColorDto.class);
		return new SuccessDataResult<ListColorDto>(response);
		}
		return new ErrorDataResult<ListColorDto>("Bu id boş.");	
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException {
		Color color=this.modelMapperService.forRequest().map(deleteColorRequest, Color.class);
		if(checkIfColorId(color.getColorId())) {
			this.colorDao.deleteById(color.getColorId());
			return new SuccessResult("Renk silindi.");
		}
		return new ErrorResult("Renk silinemedi.");
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) throws BusinessException {
		
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		
		if(checkIfColorId(color.getColorId()) && checkIfUpdateColorName(color.getColorName())) {
			this.colorDao.save(color);
			return new SuccessResult("Renk güncellendi.");
		}
		return new ErrorResult("Renk güncellenemedi.");
	
	}
		
	
	private boolean checkIfColorName(String colorName) throws BusinessException {
		if(this.colorDao.getByColorName(colorName)==null) {
			return true;
		}
		throw new BusinessException("Bu renk daha önce eklenmiştir.");
	}
	
	private boolean checkIfColorId(int colorId)throws BusinessException{
		if(this.colorDao.getByColorId(colorId)!=null) {
			return true;
		}
		throw new BusinessException("Böyle bir id mevcut değil.");
		
		
	}
	
	private boolean checkIfUpdateColorName(String colorName) throws BusinessException {
		if(org.apache.commons.lang3.StringUtils.isBlank(colorName)) {
			throw new BusinessException("Renk ismi boş olamaz.");
		} return true;
	}

}
