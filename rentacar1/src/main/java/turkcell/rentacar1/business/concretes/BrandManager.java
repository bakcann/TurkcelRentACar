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
	public List<ListBrandDto> getAll() {
		var result = this.brandDao.findAll();
		List<ListBrandDto> response = result.stream().map(brand->this.modelMapperService.forDto().map(brand, ListBrandDto.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) throws BusinessException {
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		if( !checkIfBrandId(brand.getBrandId())|| !checkIfBrandName(brand.getBrandName())) {
			
		}else {
			this.brandDao.save(brand);
		}
		
	}


	
	@Override
	public ListBrandDto getByBrandId(int brandId) throws BusinessException  {
		
		var result = this.brandDao.getByBrandId(brandId);
		if(result!=null) {
			ListBrandDto response = this.modelMapperService.forDto().map(result, ListBrandDto.class);
		return response;
		}
		throw new BusinessException("Bu id boş.");	
	}

	@Override
	public void delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException {
		Brand brand=this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		if(!checkIfBrandId(brand.getBrandId())) {
		}else {
			this.brandDao.deleteById(brand.getBrandId());
		}
		
	}

	@Override
	public void update(UpdateBrandRequest updateBrandRequest) throws BusinessException {
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		
		if(!checkIfBrandId(brand.getBrandId()) || !checkIfUpdateBrandName(brand.getBrandName())) {
			
		}else {
			this.brandDao.save(brand);
			
		}
	
		
	}
	
	private boolean checkIfBrandName(String brandName) throws BusinessException {
		if(this.brandDao.getByBrandName(brandName)!=null) {
			throw new BusinessException("Bu marka daha önce eklenmiştir.");
		}
		return true;
		
	}
	
	private boolean checkIfBrandId(int brandId)throws BusinessException{
		if(this.brandDao.getByBrandId(brandId)==null) {
			throw new BusinessException("Böyle bir id mevcut değil.");
		}
		return true;
		
	}
	
	private boolean checkIfUpdateBrandName(String brandName) throws BusinessException {
		if(org.apache.commons.lang3.StringUtils.isBlank(brandName)) {
			throw new BusinessException("Marka ismi boş olamaz.");
		} return true;
	}

}
