package com.tm.message.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TmallBatchMessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TmallBatchMessageExample() {
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

        public Criteria andMessageIdIsNull() {
            addCriterion("message_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageIdIsNotNull() {
            addCriterion("message_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageIdEqualTo(String value) {
            addCriterion("message_id =", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotEqualTo(String value) {
            addCriterion("message_id <>", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThan(String value) {
            addCriterion("message_id >", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThanOrEqualTo(String value) {
            addCriterion("message_id >=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThan(String value) {
            addCriterion("message_id <", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThanOrEqualTo(String value) {
            addCriterion("message_id <=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLike(String value) {
            addCriterion("message_id like", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotLike(String value) {
            addCriterion("message_id not like", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdIn(List<String> values) {
            addCriterion("message_id in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotIn(List<String> values) {
            addCriterion("message_id not in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdBetween(String value1, String value2) {
            addCriterion("message_id between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotBetween(String value1, String value2) {
            addCriterion("message_id not between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageDetailIsNull() {
            addCriterion("message_detail is null");
            return (Criteria) this;
        }

        public Criteria andMessageDetailIsNotNull() {
            addCriterion("message_detail is not null");
            return (Criteria) this;
        }

        public Criteria andMessageDetailEqualTo(String value) {
            addCriterion("message_detail =", value, "messageDetail");
            return (Criteria) this;
        }

        public Criteria andMessageDetailNotEqualTo(String value) {
            addCriterion("message_detail <>", value, "messageDetail");
            return (Criteria) this;
        }

        public Criteria andMessageDetailGreaterThan(String value) {
            addCriterion("message_detail >", value, "messageDetail");
            return (Criteria) this;
        }

        public Criteria andMessageDetailGreaterThanOrEqualTo(String value) {
            addCriterion("message_detail >=", value, "messageDetail");
            return (Criteria) this;
        }

        public Criteria andMessageDetailLessThan(String value) {
            addCriterion("message_detail <", value, "messageDetail");
            return (Criteria) this;
        }

        public Criteria andMessageDetailLessThanOrEqualTo(String value) {
            addCriterion("message_detail <=", value, "messageDetail");
            return (Criteria) this;
        }

        public Criteria andMessageDetailLike(String value) {
            addCriterion("message_detail like", value, "messageDetail");
            return (Criteria) this;
        }

        public Criteria andMessageDetailNotLike(String value) {
            addCriterion("message_detail not like", value, "messageDetail");
            return (Criteria) this;
        }

        public Criteria andMessageDetailIn(List<String> values) {
            addCriterion("message_detail in", values, "messageDetail");
            return (Criteria) this;
        }

        public Criteria andMessageDetailNotIn(List<String> values) {
            addCriterion("message_detail not in", values, "messageDetail");
            return (Criteria) this;
        }

        public Criteria andMessageDetailBetween(String value1, String value2) {
            addCriterion("message_detail between", value1, value2, "messageDetail");
            return (Criteria) this;
        }

        public Criteria andMessageDetailNotBetween(String value1, String value2) {
            addCriterion("message_detail not between", value1, value2, "messageDetail");
            return (Criteria) this;
        }

        public Criteria andMessageFromIsNull() {
            addCriterion("message_from is null");
            return (Criteria) this;
        }

        public Criteria andMessageFromIsNotNull() {
            addCriterion("message_from is not null");
            return (Criteria) this;
        }

        public Criteria andMessageFromEqualTo(String value) {
            addCriterion("message_from =", value, "messageFrom");
            return (Criteria) this;
        }

        public Criteria andMessageFromNotEqualTo(String value) {
            addCriterion("message_from <>", value, "messageFrom");
            return (Criteria) this;
        }

        public Criteria andMessageFromGreaterThan(String value) {
            addCriterion("message_from >", value, "messageFrom");
            return (Criteria) this;
        }

        public Criteria andMessageFromGreaterThanOrEqualTo(String value) {
            addCriterion("message_from >=", value, "messageFrom");
            return (Criteria) this;
        }

        public Criteria andMessageFromLessThan(String value) {
            addCriterion("message_from <", value, "messageFrom");
            return (Criteria) this;
        }

        public Criteria andMessageFromLessThanOrEqualTo(String value) {
            addCriterion("message_from <=", value, "messageFrom");
            return (Criteria) this;
        }

        public Criteria andMessageFromLike(String value) {
            addCriterion("message_from like", value, "messageFrom");
            return (Criteria) this;
        }

        public Criteria andMessageFromNotLike(String value) {
            addCriterion("message_from not like", value, "messageFrom");
            return (Criteria) this;
        }

        public Criteria andMessageFromIn(List<String> values) {
            addCriterion("message_from in", values, "messageFrom");
            return (Criteria) this;
        }

        public Criteria andMessageFromNotIn(List<String> values) {
            addCriterion("message_from not in", values, "messageFrom");
            return (Criteria) this;
        }

        public Criteria andMessageFromBetween(String value1, String value2) {
            addCriterion("message_from between", value1, value2, "messageFrom");
            return (Criteria) this;
        }

        public Criteria andMessageFromNotBetween(String value1, String value2) {
            addCriterion("message_from not between", value1, value2, "messageFrom");
            return (Criteria) this;
        }

        public Criteria andMessageStatusIsNull() {
            addCriterion("message_status is null");
            return (Criteria) this;
        }

        public Criteria andMessageStatusIsNotNull() {
            addCriterion("message_status is not null");
            return (Criteria) this;
        }

        public Criteria andMessageStatusEqualTo(Integer value) {
            addCriterion("message_status =", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusNotEqualTo(Integer value) {
            addCriterion("message_status <>", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusGreaterThan(Integer value) {
            addCriterion("message_status >", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("message_status >=", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusLessThan(Integer value) {
            addCriterion("message_status <", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusLessThanOrEqualTo(Integer value) {
            addCriterion("message_status <=", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusIn(List<Integer> values) {
            addCriterion("message_status in", values, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusNotIn(List<Integer> values) {
            addCriterion("message_status not in", values, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusBetween(Integer value1, Integer value2) {
            addCriterion("message_status between", value1, value2, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("message_status not between", value1, value2, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorIsNull() {
            addCriterion("message_creator is null");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorIsNotNull() {
            addCriterion("message_creator is not null");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorEqualTo(String value) {
            addCriterion("message_creator =", value, "messageCreator");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorNotEqualTo(String value) {
            addCriterion("message_creator <>", value, "messageCreator");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorGreaterThan(String value) {
            addCriterion("message_creator >", value, "messageCreator");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("message_creator >=", value, "messageCreator");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorLessThan(String value) {
            addCriterion("message_creator <", value, "messageCreator");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorLessThanOrEqualTo(String value) {
            addCriterion("message_creator <=", value, "messageCreator");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorLike(String value) {
            addCriterion("message_creator like", value, "messageCreator");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorNotLike(String value) {
            addCriterion("message_creator not like", value, "messageCreator");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorIn(List<String> values) {
            addCriterion("message_creator in", values, "messageCreator");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorNotIn(List<String> values) {
            addCriterion("message_creator not in", values, "messageCreator");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorBetween(String value1, String value2) {
            addCriterion("message_creator between", value1, value2, "messageCreator");
            return (Criteria) this;
        }

        public Criteria andMessageCreatorNotBetween(String value1, String value2) {
            addCriterion("message_creator not between", value1, value2, "messageCreator");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdIsNull() {
            addCriterion("message_user_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdIsNotNull() {
            addCriterion("message_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdEqualTo(Long value) {
            addCriterion("message_user_id =", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdNotEqualTo(Long value) {
            addCriterion("message_user_id <>", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdGreaterThan(Long value) {
            addCriterion("message_user_id >", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("message_user_id >=", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdLessThan(Long value) {
            addCriterion("message_user_id <", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdLessThanOrEqualTo(Long value) {
            addCriterion("message_user_id <=", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdIn(List<Long> values) {
            addCriterion("message_user_id in", values, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdNotIn(List<Long> values) {
            addCriterion("message_user_id not in", values, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdBetween(Long value1, Long value2) {
            addCriterion("message_user_id between", value1, value2, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdNotBetween(Long value1, Long value2) {
            addCriterion("message_user_id not between", value1, value2, "messageUserId");
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