package turkcell.rentacar1.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import turkcell.rentacar1.business.abstracts.BrandService;
import turkcell.rentacar1.business.dtos.ListBrandDto;
import turkcell.rentacar1.business.requests.CreateBrandRequest;
import turkcell.rentacar1.business.requests.DeleteBrandRequest;
import turkcell.rentacar1.business.requests.UpdateBrandRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	
	private BrandService brandService;

	@Autowired
	public BrandsController(BrandService brandService) {
	
		this.brandService = brandService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListBrandDto>> getAll(){
		return brandService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateBrandRequest createBrandRequest) throws BusinessException {
		return this.brandService.add(createBrandRequest);
	}
	
	@GetMapping("/getbrandid")
	public DataResult<ListBrandDto> getByBrandId(@RequestParam("brandId") int brandId) {
		return this.brandService.getByBrandId(brandId);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteBrandRequest deleteBrandRequest) throws BusinessException {
		return this.brandService.delete(deleteBrandRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateBrandRequest updateBrandRequest) throws BusinessException{
		return this.brandService.update(updateBrandRequest);
	}


}
