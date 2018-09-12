package com.tmall.server.auth.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TmallActionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TmallActionExample() {
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

        public Criteria andActionIdIsNull() {
            addCriterion("action_id is null");
            return (Criteria) this;
        }

        public Criteria andActionIdIsNotNull() {
            addCriterion("action_id is not null");
            return (Criteria) this;
        }

        public Criteria andActionIdEqualTo(Long value) {
            addCriterion("action_id =", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdNotEqualTo(Long value) {
            addCriterion("action_id <>", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdGreaterThan(Long value) {
            addCriterion("action_id >", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("action_id >=", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdLessThan(Long value) {
            addCriterion("action_id <", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdLessThanOrEqualTo(Long value) {
            addCriterion("action_id <=", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdIn(List<Long> values) {
            addCriterion("action_id in", values, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdNotIn(List<Long> values) {
            addCriterion("action_id not in", values, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdBetween(Long value1, Long value2) {
            addCriterion("action_id between", value1, value2, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdNotBetween(Long value1, Long value2) {
            addCriterion("action_id not between", value1, value2, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionVersionIsNull() {
            addCriterion("action_version is null");
            return (Criteria) this;
        }

        public Criteria andActionVersionIsNotNull() {
            addCriterion("action_version is not null");
            return (Criteria) this;
        }

        public Criteria andActionVersionEqualTo(Integer value) {
            addCriterion("action_version =", value, "actionVersion");
            return (Criteria) this;
        }

        public Criteria andActionVersionNotEqualTo(Integer value) {
            addCriterion("action_version <>", value, "actionVersion");
            return (Criteria) this;
        }

        public Criteria andActionVersionGreaterThan(Integer value) {
            addCriterion("action_version >", value, "actionVersion");
            return (Criteria) this;
        }

        public Criteria andActionVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("action_version >=", value, "actionVersion");
            return (Criteria) this;
        }

        public Criteria andActionVersionLessThan(Integer value) {
            addCriterion("action_version <", value, "actionVersion");
            return (Criteria) this;
        }

        public Criteria andActionVersionLessThanOrEqualTo(Integer value) {
            addCriterion("action_version <=", value, "actionVersion");
            return (Criteria) this;
        }

        public Criteria andActionVersionIn(List<Integer> values) {
            addCriterion("action_version in", values, "actionVersion");
            return (Criteria) this;
        }

        public Criteria andActionVersionNotIn(List<Integer> values) {
            addCriterion("action_version not in", values, "actionVersion");
            return (Criteria) this;
        }

        public Criteria andActionVersionBetween(Integer value1, Integer value2) {
            addCriterion("action_version between", value1, value2, "actionVersion");
            return (Criteria) this;
        }

        public Criteria andActionVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("action_version not between", value1, value2, "actionVersion");
            return (Criteria) this;
        }

        public Criteria andActionUrlIsNull() {
            addCriterion("action_url is null");
            return (Criteria) this;
        }

        public Criteria andActionUrlIsNotNull() {
            addCriterion("action_url is not null");
            return (Criteria) this;
        }

        public Criteria andActionUrlEqualTo(String value) {
            addCriterion("action_url =", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotEqualTo(String value) {
            addCriterion("action_url <>", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlGreaterThan(String value) {
            addCriterion("action_url >", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlGreaterThanOrEqualTo(String value) {
            addCriterion("action_url >=", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlLessThan(String value) {
            addCriterion("action_url <", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlLessThanOrEqualTo(String value) {
            addCriterion("action_url <=", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlLike(String value) {
            addCriterion("action_url like", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotLike(String value) {
            addCriterion("action_url not like", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlIn(List<String> values) {
            addCriterion("action_url in", values, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotIn(List<String> values) {
            addCriterion("action_url not in", values, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlBetween(String value1, String value2) {
            addCriterion("action_url between", value1, value2, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotBetween(String value1, String value2) {
            addCriterion("action_url not between", value1, value2, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionNameIsNull() {
            addCriterion("action_name is null");
            return (Criteria) this;
        }

        public Criteria andActionNameIsNotNull() {
            addCriterion("action_name is not null");
            return (Criteria) this;
        }

        public Criteria andActionNameEqualTo(String value) {
            addCriterion("action_name =", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotEqualTo(String value) {
            addCriterion("action_name <>", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThan(String value) {
            addCriterion("action_name >", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThanOrEqualTo(String value) {
            addCriterion("action_name >=", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLessThan(String value) {
            addCriterion("action_name <", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLessThanOrEqualTo(String value) {
            addCriterion("action_name <=", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLike(String value) {
            addCriterion("action_name like", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotLike(String value) {
            addCriterion("action_name not like", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameIn(List<String> values) {
            addCriterion("action_name in", values, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotIn(List<String> values) {
            addCriterion("action_name not in", values, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameBetween(String value1, String value2) {
            addCriterion("action_name between", value1, value2, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotBetween(String value1, String value2) {
            addCriterion("action_name not between", value1, value2, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionValueIsNull() {
            addCriterion("action_value is null");
            return (Criteria) this;
        }

        public Criteria andActionValueIsNotNull() {
            addCriterion("action_value is not null");
            return (Criteria) this;
        }

        public Criteria andActionValueEqualTo(String value) {
            addCriterion("action_value =", value, "actionValue");
            return (Criteria) this;
        }

        public Criteria andActionValueNotEqualTo(String value) {
            addCriterion("action_value <>", value, "actionValue");
            return (Criteria) this;
        }

        public Criteria andActionValueGreaterThan(String value) {
            addCriterion("action_value >", value, "actionValue");
            return (Criteria) this;
        }

        public Criteria andActionValueGreaterThanOrEqualTo(String value) {
            addCriterion("action_value >=", value, "actionValue");
            return (Criteria) this;
        }

        public Criteria andActionValueLessThan(String value) {
            addCriterion("action_value <", value, "actionValue");
            return (Criteria) this;
        }

        public Criteria andActionValueLessThanOrEqualTo(String value) {
            addCriterion("action_value <=", value, "actionValue");
            return (Criteria) this;
        }

        public Criteria andActionValueLike(String value) {
            addCriterion("action_value like", value, "actionValue");
            return (Criteria) this;
        }

        public Criteria andActionValueNotLike(String value) {
            addCriterion("action_value not like", value, "actionValue");
            return (Criteria) this;
        }

        public Criteria andActionValueIn(List<String> values) {
            addCriterion("action_value in", values, "actionValue");
            return (Criteria) this;
        }

        public Criteria andActionValueNotIn(List<String> values) {
            addCriterion("action_value not in", values, "actionValue");
            return (Criteria) this;
        }

        public Criteria andActionValueBetween(String value1, String value2) {
            addCriterion("action_value between", value1, value2, "actionValue");
            return (Criteria) this;
        }

        public Criteria andActionValueNotBetween(String value1, String value2) {
            addCriterion("action_value not between", value1, value2, "actionValue");
            return (Criteria) this;
        }

        public Criteria andActionStatusIsNull() {
            addCriterion("action_status is null");
            return (Criteria) this;
        }

        public Criteria andActionStatusIsNotNull() {
            addCriterion("action_status is not null");
            return (Criteria) this;
        }

        public Criteria andActionStatusEqualTo(Integer value) {
            addCriterion("action_status =", value, "actionStatus");
            return (Criteria) this;
        }

        public Criteria andActionStatusNotEqualTo(Integer value) {
            addCriterion("action_status <>", value, "actionStatus");
            return (Criteria) this;
        }

        public Criteria andActionStatusGreaterThan(Integer value) {
            addCriterion("action_status >", value, "actionStatus");
            return (Criteria) this;
        }

        public Criteria andActionStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("action_status >=", value, "actionStatus");
            return (Criteria) this;
        }

        public Criteria andActionStatusLessThan(Integer value) {
            addCriterion("action_status <", value, "actionStatus");
            return (Criteria) this;
        }

        public Criteria andActionStatusLessThanOrEqualTo(Integer value) {
            addCriterion("action_status <=", value, "actionStatus");
            return (Criteria) this;
        }

        public Criteria andActionStatusIn(List<Integer> values) {
            addCriterion("action_status in", values, "actionStatus");
            return (Criteria) this;
        }

        public Criteria andActionStatusNotIn(List<Integer> values) {
            addCriterion("action_status not in", values, "actionStatus");
            return (Criteria) this;
        }

        public Criteria andActionStatusBetween(Integer value1, Integer value2) {
            addCriterion("action_status between", value1, value2, "actionStatus");
            return (Criteria) this;
        }

        public Criteria andActionStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("action_status not between", value1, value2, "actionStatus");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionIsNull() {
            addCriterion("action_description is null");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionIsNotNull() {
            addCriterion("action_description is not null");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionEqualTo(String value) {
            addCriterion("action_description =", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionNotEqualTo(String value) {
            addCriterion("action_description <>", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionGreaterThan(String value) {
            addCriterion("action_description >", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("action_description >=", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionLessThan(String value) {
            addCriterion("action_description <", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionLessThanOrEqualTo(String value) {
            addCriterion("action_description <=", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionLike(String value) {
            addCriterion("action_description like", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionNotLike(String value) {
            addCriterion("action_description not like", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionIn(List<String> values) {
            addCriterion("action_description in", values, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionNotIn(List<String> values) {
            addCriterion("action_description not in", values, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionBetween(String value1, String value2) {
            addCriterion("action_description between", value1, value2, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionNotBetween(String value1, String value2) {
            addCriterion("action_description not between", value1, value2, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNull() {
            addCriterion("menu_id is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNotNull() {
            addCriterion("menu_id is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdEqualTo(Long value) {
            addCriterion("menu_id =", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotEqualTo(Long value) {
            addCriterion("menu_id <>", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThan(Long value) {
            addCriterion("menu_id >", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("menu_id >=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThan(Long value) {
            addCriterion("menu_id <", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThanOrEqualTo(Long value) {
            addCriterion("menu_id <=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIn(List<Long> values) {
            addCriterion("menu_id in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotIn(List<Long> values) {
            addCriterion("menu_id not in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdBetween(Long value1, Long value2) {
            addCriterion("menu_id between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotBetween(Long value1, Long value2) {
            addCriterion("menu_id not between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andCreateorIsNull() {
            addCriterion("createor is null");
            return (Criteria) this;
        }

        public Criteria andCreateorIsNotNull() {
            addCriterion("createor is not null");
            return (Criteria) this;
        }

        public Criteria andCreateorEqualTo(String value) {
            addCriterion("createor =", value, "createor");
            return (Criteria) this;
        }

        public Criteria andCreateorNotEqualTo(String value) {
            addCriterion("createor <>", value, "createor");
            return (Criteria) this;
        }

        public Criteria andCreateorGreaterThan(String value) {
            addCriterion("createor >", value, "createor");
            return (Criteria) this;
        }

        public Criteria andCreateorGreaterThanOrEqualTo(String value) {
            addCriterion("createor >=", value, "createor");
            return (Criteria) this;
        }

        public Criteria andCreateorLessThan(String value) {
            addCriterion("createor <", value, "createor");
            return (Criteria) this;
        }

        public Criteria andCreateorLessThanOrEqualTo(String value) {
            addCriterion("createor <=", value, "createor");
            return (Criteria) this;
        }

        public Criteria andCreateorLike(String value) {
            addCriterion("createor like", value, "createor");
            return (Criteria) this;
        }

        public Criteria andCreateorNotLike(String value) {
            addCriterion("createor not like", value, "createor");
            return (Criteria) this;
        }

        public Criteria andCreateorIn(List<String> values) {
            addCriterion("createor in", values, "createor");
            return (Criteria) this;
        }

        public Criteria andCreateorNotIn(List<String> values) {
            addCriterion("createor not in", values, "createor");
            return (Criteria) this;
        }

        public Criteria andCreateorBetween(String value1, String value2) {
            addCriterion("createor between", value1, value2, "createor");
            return (Criteria) this;
        }

        public Criteria andCreateorNotBetween(String value1, String value2) {
            addCriterion("createor not between", value1, value2, "createor");
            return (Criteria) this;
        }

        public Criteria andCreateorIdIsNull() {
            addCriterion("createor_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateorIdIsNotNull() {
            addCriterion("createor_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateorIdEqualTo(Long value) {
            addCriterion("createor_id =", value, "createorId");
            return (Criteria) this;
        }

        public Criteria andCreateorIdNotEqualTo(Long value) {
            addCriterion("createor_id <>", value, "createorId");
            return (Criteria) this;
        }

        public Criteria andCreateorIdGreaterThan(Long value) {
            addCriterion("createor_id >", value, "createorId");
            return (Criteria) this;
        }

        public Criteria andCreateorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("createor_id >=", value, "createorId");
            return (Criteria) this;
        }

        public Criteria andCreateorIdLessThan(Long value) {
            addCriterion("createor_id <", value, "createorId");
            return (Criteria) this;
        }

        public Criteria andCreateorIdLessThanOrEqualTo(Long value) {
            addCriterion("createor_id <=", value, "createorId");
            return (Criteria) this;
        }

        public Criteria andCreateorIdIn(List<Long> values) {
            addCriterion("createor_id in", values, "createorId");
            return (Criteria) this;
        }

        public Criteria andCreateorIdNotIn(List<Long> values) {
            addCriterion("createor_id not in", values, "createorId");
            return (Criteria) this;
        }

        public Criteria andCreateorIdBetween(Long value1, Long value2) {
            addCriterion("createor_id between", value1, value2, "createorId");
            return (Criteria) this;
        }

        public Criteria andCreateorIdNotBetween(Long value1, Long value2) {
            addCriterion("createor_id not between", value1, value2, "createorId");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIsNull() {
            addCriterion("last_operator is null");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIsNotNull() {
            addCriterion("last_operator is not null");
            return (Criteria) this;
        }

        public Criteria andLastOperatorEqualTo(String value) {
            addCriterion("last_operator =", value, "lastOperator");
            return (Criteria) this;
        }

        public Criteria andLastOperatorNotEqualTo(String value) {
            addCriterion("last_operator <>", value, "lastOperator");
            return (Criteria) this;
        }

        public Criteria andLastOperatorGreaterThan(String value) {
            addCriterion("last_operator >", value, "lastOperator");
            return (Criteria) this;
        }

        public Criteria andLastOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("last_operator >=", value, "lastOperator");
            return (Criteria) this;
        }

        public Criteria andLastOperatorLessThan(String value) {
            addCriterion("last_operator <", value, "lastOperator");
            return (Criteria) this;
        }

        public Criteria andLastOperatorLessThanOrEqualTo(String value) {
            addCriterion("last_operator <=", value, "lastOperator");
            return (Criteria) this;
        }

        public Criteria andLastOperatorLike(String value) {
            addCriterion("last_operator like", value, "lastOperator");
            return (Criteria) this;
        }

        public Criteria andLastOperatorNotLike(String value) {
            addCriterion("last_operator not like", value, "lastOperator");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIn(List<String> values) {
            addCriterion("last_operator in", values, "lastOperator");
            return (Criteria) this;
        }

        public Criteria andLastOperatorNotIn(List<String> values) {
            addCriterion("last_operator not in", values, "lastOperator");
            return (Criteria) this;
        }

        public Criteria andLastOperatorBetween(String value1, String value2) {
            addCriterion("last_operator between", value1, value2, "lastOperator");
            return (Criteria) this;
        }

        public Criteria andLastOperatorNotBetween(String value1, String value2) {
            addCriterion("last_operator not between", value1, value2, "lastOperator");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIdIsNull() {
            addCriterion("last_operator_id is null");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIdIsNotNull() {
            addCriterion("last_operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIdEqualTo(Long value) {
            addCriterion("last_operator_id =", value, "lastOperatorId");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIdNotEqualTo(Long value) {
            addCriterion("last_operator_id <>", value, "lastOperatorId");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIdGreaterThan(Long value) {
            addCriterion("last_operator_id >", value, "lastOperatorId");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("last_operator_id >=", value, "lastOperatorId");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIdLessThan(Long value) {
            addCriterion("last_operator_id <", value, "lastOperatorId");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIdLessThanOrEqualTo(Long value) {
            addCriterion("last_operator_id <=", value, "lastOperatorId");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIdIn(List<Long> values) {
            addCriterion("last_operator_id in", values, "lastOperatorId");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIdNotIn(List<Long> values) {
            addCriterion("last_operator_id not in", values, "lastOperatorId");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIdBetween(Long value1, Long value2) {
            addCriterion("last_operator_id between", value1, value2, "lastOperatorId");
            return (Criteria) this;
        }

        public Criteria andLastOperatorIdNotBetween(Long value1, Long value2) {
            addCriterion("last_operator_id not between", value1, value2, "lastOperatorId");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
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