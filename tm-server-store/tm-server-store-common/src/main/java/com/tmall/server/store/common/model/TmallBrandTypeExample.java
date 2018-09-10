package com.tmall.server.store.common.model;

import java.util.ArrayList;
import java.util.List;

public class TmallBrandTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TmallBrandTypeExample() {
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

        public Criteria andBrandTypeIdIsNull() {
            addCriterion("brand_type_id is null");
            return (Criteria) this;
        }

        public Criteria andBrandTypeIdIsNotNull() {
            addCriterion("brand_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrandTypeIdEqualTo(Integer value) {
            addCriterion("brand_type_id =", value, "brandTypeId");
            return (Criteria) this;
        }

        public Criteria andBrandTypeIdNotEqualTo(Integer value) {
            addCriterion("brand_type_id <>", value, "brandTypeId");
            return (Criteria) this;
        }

        public Criteria andBrandTypeIdGreaterThan(Integer value) {
            addCriterion("brand_type_id >", value, "brandTypeId");
            return (Criteria) this;
        }

        public Criteria andBrandTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("brand_type_id >=", value, "brandTypeId");
            return (Criteria) this;
        }

        public Criteria andBrandTypeIdLessThan(Integer value) {
            addCriterion("brand_type_id <", value, "brandTypeId");
            return (Criteria) this;
        }

        public Criteria andBrandTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("brand_type_id <=", value, "brandTypeId");
            return (Criteria) this;
        }

        public Criteria andBrandTypeIdIn(List<Integer> values) {
            addCriterion("brand_type_id in", values, "brandTypeId");
            return (Criteria) this;
        }

        public Criteria andBrandTypeIdNotIn(List<Integer> values) {
            addCriterion("brand_type_id not in", values, "brandTypeId");
            return (Criteria) this;
        }

        public Criteria andBrandTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("brand_type_id between", value1, value2, "brandTypeId");
            return (Criteria) this;
        }

        public Criteria andBrandTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("brand_type_id not between", value1, value2, "brandTypeId");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameIsNull() {
            addCriterion("brand_type_name is null");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameIsNotNull() {
            addCriterion("brand_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameEqualTo(String value) {
            addCriterion("brand_type_name =", value, "brandTypeName");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameNotEqualTo(String value) {
            addCriterion("brand_type_name <>", value, "brandTypeName");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameGreaterThan(String value) {
            addCriterion("brand_type_name >", value, "brandTypeName");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("brand_type_name >=", value, "brandTypeName");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameLessThan(String value) {
            addCriterion("brand_type_name <", value, "brandTypeName");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameLessThanOrEqualTo(String value) {
            addCriterion("brand_type_name <=", value, "brandTypeName");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameLike(String value) {
            addCriterion("brand_type_name like", value, "brandTypeName");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameNotLike(String value) {
            addCriterion("brand_type_name not like", value, "brandTypeName");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameIn(List<String> values) {
            addCriterion("brand_type_name in", values, "brandTypeName");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameNotIn(List<String> values) {
            addCriterion("brand_type_name not in", values, "brandTypeName");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameBetween(String value1, String value2) {
            addCriterion("brand_type_name between", value1, value2, "brandTypeName");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNameNotBetween(String value1, String value2) {
            addCriterion("brand_type_name not between", value1, value2, "brandTypeName");
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