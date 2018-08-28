/**
*
* @author joker 
* @date 创建时间：2018年5月27日 上午11:36:03
* 
*/
package com.micro.portal.facaded.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.micro.portal.facaded.IIndexServiceFacaded;
import com.micro.portal.model.PortalCategory;
import com.micro.product.common.dto.CategoryDTO;
import com.tmall.common.dto.ResultVO;
import com.tmall.common.enums.RestAPIStatus;
import com.tmall.server.spi.feign.IProductServerService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月27日 上午11:36:03
 */
@Service
@ComponentScan(basePackages = "com.micro.product.client.service")
public class IndexServiceFacaded implements IIndexServiceFacaded
{
	@Autowired
	private IProductServerService productServerService;
	
	private Map<CategoryDTO, List<CategoryDTO>> stitchData(ResultVO<Collection<CategoryDTO>> resultVO)
	{
		Map<CategoryDTO, List<CategoryDTO>> res=new HashMap<>();
		List<CategoryDTO> list = new ArrayList<>(resultVO.getData());
		if(CollectionUtils.isEmpty(resultVO.getData()))
		{
			return Collections.emptyMap();
		}
		Collections.sort(list, new Comparator<CategoryDTO>()
		{
			@Override
			public int compare(CategoryDTO o1, CategoryDTO o2)
			{
				return o1.getParentCategoryId() > o2.getParentCategoryId() ? 1 : -1;
			}
		});
		for (CategoryDTO categoryDTO :list)
		{
			CategoryDTO t = new CategoryDTO();
			t.setCategoryId(categoryDTO.getParentCategoryId());
			if (categoryDTO.getParentCategoryId().equals(0))
			{
				res.put(categoryDTO, new ArrayList<>());
			} else
			{
				List<CategoryDTO> childs = res.get(t);
				childs.add(categoryDTO);
			}
		}
		return res;
	}
	@Override
	public Collection<PortalCategory> showCategories()
	{
		ResultVO<Collection<CategoryDTO>> resultVO = productServerService.findAllCategories();
//		Stream<Map<CategoryDTO, List<CategoryDTO>>> map2 = resultVO.getData().stream().map(p -> {
//			Map<CategoryDTO, List<CategoryDTO>> map = new HashMap<>();
//			if (p.getParentCategoryId().equals(0))
//			{
//				map.put(p, new ArrayList<>());
//			} else
//			{
//				CategoryDTO categoryDTO = new CategoryDTO();
//				categoryDTO.setCategoryId(p.getParentCategoryId());
//				List<CategoryDTO> childs = map.get(categoryDTO);
//				childs.add(p);
//			}
//			return map;
//		});
		if (resultVO.getCode().equals(RestAPIStatus.SUCESS.ordinal()))
		{
			List<CategoryDTO> list = new ArrayList<>(resultVO.getData());
			if(CollectionUtils.isEmpty(resultVO.getData()))
			{
				return Collections.emptyList();
			}
			Collections.sort(list, new Comparator<CategoryDTO>()
			{
				@Override
				public int compare(CategoryDTO o1, CategoryDTO o2)
				{
					return o1.getParentCategoryId() > o2.getParentCategoryId() ? 1 : -1;
				}
			});
			List<PortalCategory>portalCategories=new ArrayList<>();
			for (CategoryDTO categoryDTO : list)
			{
				PortalCategory portalCategory=new PortalCategory();
				portalCategory.from(categoryDTO);
				if(categoryDTO.getParentCategoryId().equals(0))
				{
					portalCategories.add(portalCategory);
				}else {
					for (PortalCategory p : portalCategories)
					{
						if(p.getCategoryId().equals(categoryDTO.getParentCategoryId()))
						{
							p.getChildCategories().add(categoryDTO);
						}
					}
				}
			}
//			return stitchData(resultVO);
			return portalCategories;
		} else
		{
			return Collections.emptyList();
		}
	}

}
