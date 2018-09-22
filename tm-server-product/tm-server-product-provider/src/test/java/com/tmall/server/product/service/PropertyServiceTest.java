/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月22日 下午4:20:32
* 
*/
package com.tmall.server.product.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.PropertyDTO.PropertyValueDTO;
import com.tmall.common.mq.TmallMQEnum;
import com.tmall.common.mq.UserRecordFactory;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.wrapper.UserRecordAspectWrapper;
import com.tmall.server.product.TmallProductServerApplication;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月22日 下午4:20:32
 */
@SpringBootTest(classes = TmallProductServerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class PropertyServiceTest
{
	@Autowired
	private IPropertyService propertyService;

	@Test
	public void testAdd()
	{
		UserDTO user=new UserDTO();
		user.setUsername("joker");
		user.setUserId(1L);
		String detail="ss";
		PropertyDTO propertyDTO=new PropertyDTO();
		propertyDTO.setPropertyId(10);
		propertyDTO.setPropertyName("test-property");
		propertyDTO.setPropertyDisSeq(1);
		List<PropertyValueDTO>values=new ArrayList<PropertyDTO.PropertyValueDTO>();
		PropertyValueDTO valueDTO=new PropertyValueDTO();
		valueDTO.setPropertyValue("test-property-value1");
		valueDTO.setPropertyId(propertyDTO.getPropertyId());
		valueDTO.setPropertyDisSeq(1);
		valueDTO.setPropertyValueId(1L);
		values.add(valueDTO);
		PropertyValueDTO valueDTO2=new PropertyValueDTO();
		valueDTO2.setPropertyValue("test-property-value2");
		valueDTO2.setPropertyId(propertyDTO.getPropertyId());
		valueDTO2.setPropertyDisSeq(1);
		valueDTO2.setPropertyValueId(2L);
		values.add(valueDTO2);
		propertyDTO.setValues(values);
		UserRecordAspectWrapper<PropertyDTO> wrapper = UserRecordFactory.create(user, detail, propertyDTO);
		ResultDTO<?> resultDTO = propertyService.addPropertyAndValue(wrapper);
		
	}

}
