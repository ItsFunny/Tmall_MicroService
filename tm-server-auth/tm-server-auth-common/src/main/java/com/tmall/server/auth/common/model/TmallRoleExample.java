package com.tmall.server.auth.common.model;

import java.util.ArrayList;
import java.util.List;

public class TmallRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TmallRoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Long value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Long value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Long value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Long value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Long value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Long> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Long> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Long value1, Long value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Long value1, Long value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNull() {
            addCriterion("store_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("store_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdEqualTo(Long value) {
            addCriterion("store_id =", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotEqualTo(Long value) {
            addCriterion("store_id <>", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThan(Long value) {
            addCriterion("store_id >", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(Long value) {
            addCriterion("store_id >=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThan(Long value) {
            addCriterion("store_id <", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(Long value) {
            addCriterion("store_id <=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIn(List<Long> values) {
            addCriterion("store_id in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotIn(List<Long> values) {
            addCriterion("store_id not in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdBetween(Long value1, Long value2) {
            addCriterion("store_id between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotBetween(Long value1, Long value2) {
            addCriterion("store_id not between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andRoleStatusIsNull() {
            addCriterion("role_status is null");
            return (Criteria) this;
        }

        public Criteria andRoleStatusIsNotNull() {
            addCriterion("role_status is not null");
            return (Criteria) this;
        }

        public Criteria andRoleStatusEqualTo(Byte value) {
            addCriterion("role_status =", value, "roleStatus");
            return (Criteria) this;
        }

        public Criteria andRoleStatusNotEqualTo(Byte value) {
            addCriterion("role_status <>", value, "roleStatus");
            return (Criteria) this;
        }

        public Criteria andRoleStatusGreaterThan(Byte value) {
            addCriterion("role_status >", value, "roleStatus");
            return (Criteria) this;
        }

        public Criteria andRoleStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("role_status >=", value, "roleStatus");
            return (Criteria) this;
        }

        public Criteria andRoleStatusLessThan(Byte value) {
            addCriterion("role_status <", value, "roleStatus");
            return (Criteria) this;
        }

        public Criteria andRoleStatusLessThanOrEqualTo(Byte value) {
            addCriterion("role_status <=", value, "roleStatus");
            return (Criteria) this;
        }

        public Criteria andRoleStatusIn(List<Byte> values) {
            addCriterion("role_status in", values, "roleStatus");
            return (Criteria) this;
        }

        public Criteria andRoleStatusNotIn(List<Byte> values) {
            addCriterion("role_status not in", values, "roleStatus");
            return (Criteria) this;
        }

        public Criteria andRoleStatusBetween(Byte value1, Byte value2) {
            addCriterion("role_status between", value1, value2, "roleStatus");
            return (Criteria) this;
        }

        public Criteria andRoleStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("role_status not between", value1, value2, "roleStatus");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNull() {
            addCriterion("role_name is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNotNull() {
            addCriterion("role_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameEqualTo(String value) {
            addCriterion("role_name =", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotEqualTo(String value) {
            addCriterion("role_name <>", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThan(String value) {
            addCriterion("role_name >", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("role_name >=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThan(String value) {
            addCriterion("role_name <", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(String value) {
            addCriterion("role_name <=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(String value) {
            addCriterion("role_name like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotLike(String value) {
            addCriterion("role_name not like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameIn(List<String> values) {
            addCriterion("role_name in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotIn(List<String> values) {
            addCriterion("role_name not in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameBetween(String value1, String value2) {
            addCriterion("role_name between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotBetween(String value1, String value2) {
            addCriterion("role_name not between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionIsNull() {
            addCriterion("role_description is null");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionIsNotNull() {
            addCriterion("role_description is not null");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionEqualTo(String value) {
            addCriterion("role_description =", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionNotEqualTo(String value) {
            addCriterion("role_description <>", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionGreaterThan(String value) {
            addCriterion("role_description >", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("role_description >=", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionLessThan(String value) {
            addCriterion("role_description <", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionLessThanOrEqualTo(String value) {
            addCriterion("role_description <=", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionLike(String value) {
            addCriterion("role_description like", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionNotLike(String value) {
            addCriterion("role_description not like", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionIn(List<String> values) {
            addCriterion("role_description in", values, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionNotIn(List<String> values) {
            addCriterion("role_description not in", values, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionBetween(String value1, String value2) {
            addCriterion("role_description between", value1, value2, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionNotBetween(String value1, String value2) {
            addCriterion("role_description not between", value1, value2, "roleDescription");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}