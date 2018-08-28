/**
*
* @author joker 
* @date 创建时间：2018年6月25日 下午1:27:27
* 
*/
package com.tmall.server.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.common.dto.PictureDTO;
import com.tmall.server.product.dao.PictureDao;
import com.tmall.server.product.service.IProductServerPictureService;

/**
* 
* @author joker 
* @date 创建时间：2018年6月25日 下午1:27:27
*/
@Service
public class PictureServiceImpl implements IProductServerPictureService
{
	@Autowired
	private PictureDao pictureDao;
	@Override
	public void add(PictureDTO pictureDTO)
	{
		
	}

}
