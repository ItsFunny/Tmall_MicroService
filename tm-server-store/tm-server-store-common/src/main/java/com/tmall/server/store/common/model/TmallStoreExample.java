package com.tmall.server.store.common.model;

import java.util.ArrayList;
import java.util.List;

public class TmallStoreExample
{
	protected String orderByClause;

	protected boolean distinct;

	protected Integer start;

	protected Integer end;

	protected List<Criteria> oredCriteria;

	public TmallStoreExample()
	{
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause)
	{
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause()
	{
		return orderByClause;
	}

	public void setDistinct(boolean distinct)
	{
		this.distinct = distinct;
	}

	public boolean isDistinct()
	{
		return distinct;
	}

	public List<Criteria> getOredCriteria()
	{
		return oredCriteria;
	}

	public void or(Criteria criteria)
	{
		oredCriteria.add(criteria);
	}

	public Criteria or()
	{
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria()
	{
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0)
		{
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal()
	{
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear()
	{
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	protected abstract static class GeneratedCriteria
	{
		protected List<Criterion> criteria;

		protected GeneratedCriteria()
		{
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid()
		{
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria()
		{
			return criteria;
		}

		public List<Criterion> getCriteria()
		{
			return criteria;
		}

		protected void addCriterion(String condition)
		{
			if (condition == null)
			{
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property)
		{
			if (value == null)
			{
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property)
		{
			if (value1 == null || value2 == null)
			{
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andStoreIdIsNull()
		{
			addCriterion("store_id is null");
			return (Criteria) this;
		}

		public Criteria andStoreIdIsNotNull()
		{
			addCriterion("store_id is not null");
			return (Criteria) this;
		}

		public Criteria andStoreIdEqualTo(Long value)
		{
			addCriterion("store_id =", value, "storeId");
			return (Criteria) this;
		}

		public Criteria andStoreIdNotEqualTo(Long value)
		{
			addCriterion("store_id <>", value, "storeId");
			return (Criteria) this;
		}

		public Criteria andStoreIdGreaterThan(Long value)
		{
			addCriterion("store_id >", value, "storeId");
			return (Criteria) this;
		}

		public Criteria andStoreIdGreaterThanOrEqualTo(Long value)
		{
			addCriterion("store_id >=", value, "storeId");
			return (Criteria) this;
		}

		public Criteria andStoreIdLessThan(Long value)
		{
			addCriterion("store_id <", value, "storeId");
			return (Criteria) this;
		}

		public Criteria andStoreIdLessThanOrEqualTo(Long value)
		{
			addCriterion("store_id <=", value, "storeId");
			return (Criteria) this;
		}

		public Criteria andStoreIdIn(List<Long> values)
		{
			addCriterion("store_id in", values, "storeId");
			return (Criteria) this;
		}

		public Criteria andStoreIdNotIn(List<Long> values)
		{
			addCriterion("store_id not in", values, "storeId");
			return (Criteria) this;
		}

		public Criteria andStoreIdBetween(Long value1, Long value2)
		{
			addCriterion("store_id between", value1, value2, "storeId");
			return (Criteria) this;
		}

		public Criteria andStoreIdNotBetween(Long value1, Long value2)
		{
			addCriterion("store_id not between", value1, value2, "storeId");
			return (Criteria) this;
		}

		public Criteria andStoreNameIsNull()
		{
			addCriterion("store_name is null");
			return (Criteria) this;
		}

		public Criteria andStoreNameIsNotNull()
		{
			addCriterion("store_name is not null");
			return (Criteria) this;
		}

		public Criteria andStoreNameEqualTo(String value)
		{
			addCriterion("store_name =", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameNotEqualTo(String value)
		{
			addCriterion("store_name <>", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameGreaterThan(String value)
		{
			addCriterion("store_name >", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameGreaterThanOrEqualTo(String value)
		{
			addCriterion("store_name >=", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameLessThan(String value)
		{
			addCriterion("store_name <", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameLessThanOrEqualTo(String value)
		{
			addCriterion("store_name <=", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameLike(String value)
		{
			addCriterion("store_name like", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameNotLike(String value)
		{
			addCriterion("store_name not like", value, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameIn(List<String> values)
		{
			addCriterion("store_name in", values, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameNotIn(List<String> values)
		{
			addCriterion("store_name not in", values, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameBetween(String value1, String value2)
		{
			addCriterion("store_name between", value1, value2, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreNameNotBetween(String value1, String value2)
		{
			addCriterion("store_name not between", value1, value2, "storeName");
			return (Criteria) this;
		}

		public Criteria andStoreStatusIsNull()
		{
			addCriterion("store_status is null");
			return (Criteria) this;
		}

		public Criteria andStoreStatusIsNotNull()
		{
			addCriterion("store_status is not null");
			return (Criteria) this;
		}

		public Criteria andStoreStatusEqualTo(Integer value)
		{
			addCriterion("store_status =", value, "storeStatus");
			return (Criteria) this;
		}

		public Criteria andStoreStatusNotEqualTo(Integer value)
		{
			addCriterion("store_status <>", value, "storeStatus");
			return (Criteria) this;
		}

		public Criteria andStoreStatusGreaterThan(Integer value)
		{
			addCriterion("store_status >", value, "storeStatus");
			return (Criteria) this;
		}

		public Criteria andStoreStatusGreaterThanOrEqualTo(Integer value)
		{
			addCriterion("store_status >=", value, "storeStatus");
			return (Criteria) this;
		}

		public Criteria andStoreStatusLessThan(Integer value)
		{
			addCriterion("store_status <", value, "storeStatus");
			return (Criteria) this;
		}

		public Criteria andStoreStatusLessThanOrEqualTo(Integer value)
		{
			addCriterion("store_status <=", value, "storeStatus");
			return (Criteria) this;
		}

		public Criteria andStoreStatusIn(List<Integer> values)
		{
			addCriterion("store_status in", values, "storeStatus");
			return (Criteria) this;
		}

		public Criteria andStoreStatusNotIn(List<Integer> values)
		{
			addCriterion("store_status not in", values, "storeStatus");
			return (Criteria) this;
		}

		public Criteria andStoreStatusBetween(Integer value1, Integer value2)
		{
			addCriterion("store_status between", value1, value2, "storeStatus");
			return (Criteria) this;
		}

		public Criteria andStoreStatusNotBetween(Integer value1, Integer value2)
		{
			addCriterion("store_status not between", value1, value2, "storeStatus");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameIsNull()
		{
			addCriterion("store_abb_name is null");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameIsNotNull()
		{
			addCriterion("store_abb_name is not null");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameEqualTo(String value)
		{
			addCriterion("store_abb_name =", value, "storeAbbName");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameNotEqualTo(String value)
		{
			addCriterion("store_abb_name <>", value, "storeAbbName");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameGreaterThan(String value)
		{
			addCriterion("store_abb_name >", value, "storeAbbName");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameGreaterThanOrEqualTo(String value)
		{
			addCriterion("store_abb_name >=", value, "storeAbbName");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameLessThan(String value)
		{
			addCriterion("store_abb_name <", value, "storeAbbName");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameLessThanOrEqualTo(String value)
		{
			addCriterion("store_abb_name <=", value, "storeAbbName");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameLike(String value)
		{
			addCriterion("store_abb_name like", value, "storeAbbName");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameNotLike(String value)
		{
			addCriterion("store_abb_name not like", value, "storeAbbName");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameIn(List<String> values)
		{
			addCriterion("store_abb_name in", values, "storeAbbName");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameNotIn(List<String> values)
		{
			addCriterion("store_abb_name not in", values, "storeAbbName");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameBetween(String value1, String value2)
		{
			addCriterion("store_abb_name between", value1, value2, "storeAbbName");
			return (Criteria) this;
		}

		public Criteria andStoreAbbNameNotBetween(String value1, String value2)
		{
			addCriterion("store_abb_name not between", value1, value2, "storeAbbName");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionIsNull()
		{
			addCriterion("store_description is null");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionIsNotNull()
		{
			addCriterion("store_description is not null");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionEqualTo(String value)
		{
			addCriterion("store_description =", value, "storeDescription");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionNotEqualTo(String value)
		{
			addCriterion("store_description <>", value, "storeDescription");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionGreaterThan(String value)
		{
			addCriterion("store_description >", value, "storeDescription");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionGreaterThanOrEqualTo(String value)
		{
			addCriterion("store_description >=", value, "storeDescription");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionLessThan(String value)
		{
			addCriterion("store_description <", value, "storeDescription");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionLessThanOrEqualTo(String value)
		{
			addCriterion("store_description <=", value, "storeDescription");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionLike(String value)
		{
			addCriterion("store_description like", value, "storeDescription");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionNotLike(String value)
		{
			addCriterion("store_description not like", value, "storeDescription");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionIn(List<String> values)
		{
			addCriterion("store_description in", values, "storeDescription");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionNotIn(List<String> values)
		{
			addCriterion("store_description not in", values, "storeDescription");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionBetween(String value1, String value2)
		{
			addCriterion("store_description between", value1, value2, "storeDescription");
			return (Criteria) this;
		}

		public Criteria andStoreDescriptionNotBetween(String value1, String value2)
		{
			addCriterion("store_description not between", value1, value2, "storeDescription");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneIsNull()
		{
			addCriterion("store_contact_phone is null");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneIsNotNull()
		{
			addCriterion("store_contact_phone is not null");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneEqualTo(String value)
		{
			addCriterion("store_contact_phone =", value, "storeContactPhone");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneNotEqualTo(String value)
		{
			addCriterion("store_contact_phone <>", value, "storeContactPhone");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneGreaterThan(String value)
		{
			addCriterion("store_contact_phone >", value, "storeContactPhone");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneGreaterThanOrEqualTo(String value)
		{
			addCriterion("store_contact_phone >=", value, "storeContactPhone");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneLessThan(String value)
		{
			addCriterion("store_contact_phone <", value, "storeContactPhone");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneLessThanOrEqualTo(String value)
		{
			addCriterion("store_contact_phone <=", value, "storeContactPhone");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneLike(String value)
		{
			addCriterion("store_contact_phone like", value, "storeContactPhone");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneNotLike(String value)
		{
			addCriterion("store_contact_phone not like", value, "storeContactPhone");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneIn(List<String> values)
		{
			addCriterion("store_contact_phone in", values, "storeContactPhone");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneNotIn(List<String> values)
		{
			addCriterion("store_contact_phone not in", values, "storeContactPhone");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneBetween(String value1, String value2)
		{
			addCriterion("store_contact_phone between", value1, value2, "storeContactPhone");
			return (Criteria) this;
		}

		public Criteria andStoreContactPhoneNotBetween(String value1, String value2)
		{
			addCriterion("store_contact_phone not between", value1, value2, "storeContactPhone");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNull()
		{
			addCriterion("user_id is null");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNotNull()
		{
			addCriterion("user_id is not null");
			return (Criteria) this;
		}

		public Criteria andUserIdEqualTo(Long value)
		{
			addCriterion("user_id =", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotEqualTo(Long value)
		{
			addCriterion("user_id <>", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThan(Long value)
		{
			addCriterion("user_id >", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(Long value)
		{
			addCriterion("user_id >=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThan(Long value)
		{
			addCriterion("user_id <", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThanOrEqualTo(Long value)
		{
			addCriterion("user_id <=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdIn(List<Long> values)
		{
			addCriterion("user_id in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotIn(List<Long> values)
		{
			addCriterion("user_id not in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdBetween(Long value1, Long value2)
		{
			addCriterion("user_id between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotBetween(Long value1, Long value2)
		{
			addCriterion("user_id not between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameIsNull()
		{
			addCriterion("store_contact_name is null");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameIsNotNull()
		{
			addCriterion("store_contact_name is not null");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameEqualTo(String value)
		{
			addCriterion("store_contact_name =", value, "storeContactName");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameNotEqualTo(String value)
		{
			addCriterion("store_contact_name <>", value, "storeContactName");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameGreaterThan(String value)
		{
			addCriterion("store_contact_name >", value, "storeContactName");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameGreaterThanOrEqualTo(String value)
		{
			addCriterion("store_contact_name >=", value, "storeContactName");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameLessThan(String value)
		{
			addCriterion("store_contact_name <", value, "storeContactName");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameLessThanOrEqualTo(String value)
		{
			addCriterion("store_contact_name <=", value, "storeContactName");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameLike(String value)
		{
			addCriterion("store_contact_name like", value, "storeContactName");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameNotLike(String value)
		{
			addCriterion("store_contact_name not like", value, "storeContactName");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameIn(List<String> values)
		{
			addCriterion("store_contact_name in", values, "storeContactName");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameNotIn(List<String> values)
		{
			addCriterion("store_contact_name not in", values, "storeContactName");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameBetween(String value1, String value2)
		{
			addCriterion("store_contact_name between", value1, value2, "storeContactName");
			return (Criteria) this;
		}

		public Criteria andStoreContactNameNotBetween(String value1, String value2)
		{
			addCriterion("store_contact_name not between", value1, value2, "storeContactName");
			return (Criteria) this;
		}

		public Criteria andCompanyIdIsNull()
		{
			addCriterion("company_id is null");
			return (Criteria) this;
		}

		public Criteria andCompanyIdIsNotNull()
		{
			addCriterion("company_id is not null");
			return (Criteria) this;
		}

		public Criteria andCompanyIdEqualTo(Long value)
		{
			addCriterion("company_id =", value, "companyId");
			return (Criteria) this;
		}

		public Criteria andCompanyIdNotEqualTo(Long value)
		{
			addCriterion("company_id <>", value, "companyId");
			return (Criteria) this;
		}

		public Criteria andCompanyIdGreaterThan(Long value)
		{
			addCriterion("company_id >", value, "companyId");
			return (Criteria) this;
		}

		public Criteria andCompanyIdGreaterThanOrEqualTo(Long value)
		{
			addCriterion("company_id >=", value, "companyId");
			return (Criteria) this;
		}

		public Criteria andCompanyIdLessThan(Long value)
		{
			addCriterion("company_id <", value, "companyId");
			return (Criteria) this;
		}

		public Criteria andCompanyIdLessThanOrEqualTo(Long value)
		{
			addCriterion("company_id <=", value, "companyId");
			return (Criteria) this;
		}

		public Criteria andCompanyIdIn(List<Long> values)
		{
			addCriterion("company_id in", values, "companyId");
			return (Criteria) this;
		}

		public Criteria andCompanyIdNotIn(List<Long> values)
		{
			addCriterion("company_id not in", values, "companyId");
			return (Criteria) this;
		}

		public Criteria andCompanyIdBetween(Long value1, Long value2)
		{
			addCriterion("company_id between", value1, value2, "companyId");
			return (Criteria) this;
		}

		public Criteria andCompanyIdNotBetween(Long value1, Long value2)
		{
			addCriterion("company_id not between", value1, value2, "companyId");
			return (Criteria) this;
		}

		public Criteria andCompanyNameIsNull()
		{
			addCriterion("company_name is null");
			return (Criteria) this;
		}

		public Criteria andCompanyNameIsNotNull()
		{
			addCriterion("company_name is not null");
			return (Criteria) this;
		}

		public Criteria andCompanyNameEqualTo(String value)
		{
			addCriterion("company_name =", value, "companyName");
			return (Criteria) this;
		}

		public Criteria andCompanyNameNotEqualTo(String value)
		{
			addCriterion("company_name <>", value, "companyName");
			return (Criteria) this;
		}

		public Criteria andCompanyNameGreaterThan(String value)
		{
			addCriterion("company_name >", value, "companyName");
			return (Criteria) this;
		}

		public Criteria andCompanyNameGreaterThanOrEqualTo(String value)
		{
			addCriterion("company_name >=", value, "companyName");
			return (Criteria) this;
		}

		public Criteria andCompanyNameLessThan(String value)
		{
			addCriterion("company_name <", value, "companyName");
			return (Criteria) this;
		}

		public Criteria andCompanyNameLessThanOrEqualTo(String value)
		{
			addCriterion("company_name <=", value, "companyName");
			return (Criteria) this;
		}

		public Criteria andCompanyNameLike(String value)
		{
			addCriterion("company_name like", value, "companyName");
			return (Criteria) this;
		}

		public Criteria andCompanyNameNotLike(String value)
		{
			addCriterion("company_name not like", value, "companyName");
			return (Criteria) this;
		}

		public Criteria andCompanyNameIn(List<String> values)
		{
			addCriterion("company_name in", values, "companyName");
			return (Criteria) this;
		}

		public Criteria andCompanyNameNotIn(List<String> values)
		{
			addCriterion("company_name not in", values, "companyName");
			return (Criteria) this;
		}

		public Criteria andCompanyNameBetween(String value1, String value2)
		{
			addCriterion("company_name between", value1, value2, "companyName");
			return (Criteria) this;
		}

		public Criteria andCompanyNameNotBetween(String value1, String value2)
		{
			addCriterion("company_name not between", value1, value2, "companyName");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria
	{

		protected Criteria()
		{
			super();
		}
	}

	public static class Criterion
	{
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		public String getCondition()
		{
			return condition;
		}

		public Object getValue()
		{
			return value;
		}

		public Object getSecondValue()
		{
			return secondValue;
		}

		public boolean isNoValue()
		{
			return noValue;
		}

		public boolean isSingleValue()
		{
			return singleValue;
		}

		public boolean isBetweenValue()
		{
			return betweenValue;
		}

		public boolean isListValue()
		{
			return listValue;
		}

		public String getTypeHandler()
		{
			return typeHandler;
		}

		protected Criterion(String condition)
		{
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler)
		{
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>)
			{
				this.listValue = true;
			} else
			{
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value)
		{
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler)
		{
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue)
		{
			this(condition, value, secondValue, null);
		}
	}

	public Integer getStart()
	{
		return start;
	}

	public void setStart(Integer start)
	{
		this.start = start;
	}

	public Integer getEnd()
	{
		return end;
	}

	public void setEnd(Integer end)
	{
		this.end = end;
	}

	public void setOredCriteria(List<Criteria> oredCriteria)
	{
		this.oredCriteria = oredCriteria;
	}
}