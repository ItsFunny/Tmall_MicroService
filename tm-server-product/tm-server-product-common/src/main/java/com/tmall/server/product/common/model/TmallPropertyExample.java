package com.tmall.server.product.common.model;

import java.util.ArrayList;
import java.util.List;

import com.tmall.common.dto.PageExample;

public class TmallPropertyExample extends PageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TmallPropertyExample() {
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

        public Criteria andPropertyIdIsNull() {
            addCriterion("property_id is null");
            return (Criteria) this;
        }

        public Criteria andPropertyIdIsNotNull() {
            addCriterion("property_id is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyIdEqualTo(Integer value) {
            addCriterion("property_id =", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdNotEqualTo(Integer value) {
            addCriterion("property_id <>", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdGreaterThan(Integer value) {
            addCriterion("property_id >", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("property_id >=", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdLessThan(Integer value) {
            addCriterion("property_id <", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdLessThanOrEqualTo(Integer value) {
            addCriterion("property_id <=", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdIn(List<Integer> values) {
            addCriterion("property_id in", values, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdNotIn(List<Integer> values) {
            addCriterion("property_id not in", values, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdBetween(Integer value1, Integer value2) {
            addCriterion("property_id between", value1, value2, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("property_id not between", value1, value2, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIsNull() {
            addCriterion("property_name is null");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIsNotNull() {
            addCriterion("property_name is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyNameEqualTo(String value) {
            addCriterion("property_name =", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotEqualTo(String value) {
            addCriterion("property_name <>", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameGreaterThan(String value) {
            addCriterion("property_name >", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameGreaterThanOrEqualTo(String value) {
            addCriterion("property_name >=", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLessThan(String value) {
            addCriterion("property_name <", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLessThanOrEqualTo(String value) {
            addCriterion("property_name <=", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLike(String value) {
            addCriterion("property_name like", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotLike(String value) {
            addCriterion("property_name not like", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIn(List<String> values) {
            addCriterion("property_name in", values, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotIn(List<String> values) {
            addCriterion("property_name not in", values, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameBetween(String value1, String value2) {
            addCriterion("property_name between", value1, value2, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotBetween(String value1, String value2) {
            addCriterion("property_name not between", value1, value2, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyDisSeqIsNull() {
            addCriterion("property_dis_seq is null");
            return (Criteria) this;
        }

        public Criteria andPropertyDisSeqIsNotNull() {
            addCriterion("property_dis_seq is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyDisSeqEqualTo(Integer value) {
            addCriterion("property_dis_seq =", value, "propertyDisSeq");
            return (Criteria) this;
        }

        public Criteria andPropertyDisSeqNotEqualTo(Integer value) {
            addCriterion("property_dis_seq <>", value, "propertyDisSeq");
            return (Criteria) this;
        }

        public Criteria andPropertyDisSeqGreaterThan(Integer value) {
            addCriterion("property_dis_seq >", value, "propertyDisSeq");
            return (Criteria) this;
        }

        public Criteria andPropertyDisSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("property_dis_seq >=", value, "propertyDisSeq");
            return (Criteria) this;
        }

        public Criteria andPropertyDisSeqLessThan(Integer value) {
            addCriterion("property_dis_seq <", value, "propertyDisSeq");
            return (Criteria) this;
        }

        public Criteria andPropertyDisSeqLessThanOrEqualTo(Integer value) {
            addCriterion("property_dis_seq <=", value, "propertyDisSeq");
            return (Criteria) this;
        }

        public Criteria andPropertyDisSeqIn(List<Integer> values) {
            addCriterion("property_dis_seq in", values, "propertyDisSeq");
            return (Criteria) this;
        }

        public Criteria andPropertyDisSeqNotIn(List<Integer> values) {
            addCriterion("property_dis_seq not in", values, "propertyDisSeq");
            return (Criteria) this;
        }

        public Criteria andPropertyDisSeqBetween(Integer value1, Integer value2) {
            addCriterion("property_dis_seq between", value1, value2, "propertyDisSeq");
            return (Criteria) this;
        }

        public Criteria andPropertyDisSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("property_dis_seq not between", value1, value2, "propertyDisSeq");
            return (Criteria) this;
        }

        public Criteria andPropertyIsSearchIsNull() {
            addCriterion("property_is_search is null");
            return (Criteria) this;
        }

        public Criteria andPropertyIsSearchIsNotNull() {
            addCriterion("property_is_search is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyIsSearchEqualTo(Boolean value) {
            addCriterion("property_is_search =", value, "propertyIsSearch");
            return (Criteria) this;
        }

        public Criteria andPropertyIsSearchNotEqualTo(Boolean value) {
            addCriterion("property_is_search <>", value, "propertyIsSearch");
            return (Criteria) this;
        }

        public Criteria andPropertyIsSearchGreaterThan(Boolean value) {
            addCriterion("property_is_search >", value, "propertyIsSearch");
            return (Criteria) this;
        }

        public Criteria andPropertyIsSearchGreaterThanOrEqualTo(Boolean value) {
            addCriterion("property_is_search >=", value, "propertyIsSearch");
            return (Criteria) this;
        }

        public Criteria andPropertyIsSearchLessThan(Boolean value) {
            addCriterion("property_is_search <", value, "propertyIsSearch");
            return (Criteria) this;
        }

        public Criteria andPropertyIsSearchLessThanOrEqualTo(Boolean value) {
            addCriterion("property_is_search <=", value, "propertyIsSearch");
            return (Criteria) this;
        }

        public Criteria andPropertyIsSearchIn(List<Boolean> values) {
            addCriterion("property_is_search in", values, "propertyIsSearch");
            return (Criteria) this;
        }

        public Criteria andPropertyIsSearchNotIn(List<Boolean> values) {
            addCriterion("property_is_search not in", values, "propertyIsSearch");
            return (Criteria) this;
        }

        public Criteria andPropertyIsSearchBetween(Boolean value1, Boolean value2) {
            addCriterion("property_is_search between", value1, value2, "propertyIsSearch");
            return (Criteria) this;
        }

        public Criteria andPropertyIsSearchNotBetween(Boolean value1, Boolean value2) {
            addCriterion("property_is_search not between", value1, value2, "propertyIsSearch");
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