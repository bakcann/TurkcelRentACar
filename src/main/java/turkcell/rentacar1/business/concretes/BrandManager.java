package turkcell.rentacar1.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import turkcell.rentacar1.business.abstracts.BrandService;
import turkcell.rentacar1.business.dtos.ListBrandDto;
import turkcell.rentacar1.business.requests.CreateBrandRequest;
import turkcell.rentacar1.business.requests.DeleteBrandRequest;
import turkcell.rentacar1.business.requests.UpdateBrandRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.mapping.ModelMapperService;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.ErrorDataResult;
import turkcell.rentacar1.core.utilities.results.ErrorResult;
import turkcell.rentacar1.core.utilities.results.Result;
import turkcell.rentacar1.core.utilities.results.SuccessDataResult;
import turkcell.rentacar1.core.utilities.results.SuccessResult;
import turkcell.rentacar1.dataAccess.abstracts.BrandDao;
import turkcell.rentacar1.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {
	
	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListBrandDto>> getAll() {
		var result = this.brandDao.findAll();
		List<ListBrandDto> response = result.stream()
				.map(brand->this.modelMapperService.forDto()
						.map(brand, ListBrandDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListBrandDto>>(response);
	}

	
	//500 hatasını kullanıcı görmemeli.
	@Override
	public Result add(CreateBrandRequest createBrandRequest) throws BusinessException {
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		if(checkIfBrandName(brand.getBrandName())) {
			this.brandDao.save(brand);
			return new SuccessResult("Marka eklendi.");
		}
		return new ErrorResult("Marka eklenemedi.");
	}

	@Override
	public DataResult<ListBrandDto> getByBrandId(int brandId)   {
		
		var result = this.brandDao.getByBrandId(brandId);
		if(result!=null) {
			ListBrandDto response = this.modelMapperService.forDto().map(result, ListBrandDto.class);
		return new SuccessDataResult<ListBrandDto>(response);
		}
		return new ErrorDataResult<ListBrandDto>("Bu id boş.");	
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException {
		Brand brand=this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		if(checkIfBrandId(brand.getBrandId())) {
			this.brandDao.deleteById(brand.getBrandId());
			return new SuccessResult("Marka silindi.");
		}
		return new ErrorResult("Marka silinemedi.");
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException {
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		
		if(checkIfBrandId(brand.getBrandId()) && checkIfUpdateBrandName(brand.getBrandName())) {
			this.brandDao.save(brand);
			return new SuccessResult("Marka güncellendi.");
		}
		return new ErrorResult("Marka güncellenemedi.");
	}
	
	private boolean checkIfBrandName(String brandName) throws BusinessException {
		if(this.brandDao.getByBrandName(brandName)==null) {
			return true;
		}
		throw new BusinessException("Bu marka daha önce eklenmiştir.");
		
		
	}
	
	private boolean checkIfBrandId(int brandId)throws BusinessException{
		if(this.brandDao.getByBrandId(brandId)!=null) {
			return true;
		}
		throw new BusinessException("Böyle bir id mevcut değil.");
		
		
	}
	
	private boolean checkIfUpdateBrandName(String brandName) throws BusinessException {
		if(org.apache.commons.lang3.StringUtils.isBlank(brandName)) {
			throw new BusinessException("Marka ismi boş olamaz.");
		} return true;
	}

}
