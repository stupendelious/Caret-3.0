/*
 * This file is generated by jOOQ.
*/
package brs.schema.tables.records;


import brs.schema.tables.AtState;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AtStateRecord extends UpdatableRecordImpl<AtStateRecord> implements Record11<Long, Long, byte[], Integer, Integer, Integer, Long, Boolean, Long, Integer, Boolean> {

    private static final long serialVersionUID = -1737667443;

    /**
     * Setter for <code>DB.at_state.db_id</code>.
     */
    public void setDbId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>DB.at_state.db_id</code>.
     */
    public Long getDbId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>DB.at_state.at_id</code>.
     */
    public void setAtId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>DB.at_state.at_id</code>.
     */
    public Long getAtId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>DB.at_state.state</code>.
     */
    public void setState(byte... value) {
        set(2, value);
    }

    /**
     * Getter for <code>DB.at_state.state</code>.
     */
    public byte[] getState() {
        return (byte[]) get(2);
    }

    /**
     * Setter for <code>DB.at_state.prev_height</code>.
     */
    public void setPrevHeight(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>DB.at_state.prev_height</code>.
     */
    public Integer getPrevHeight() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>DB.at_state.next_height</code>.
     */
    public void setNextHeight(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>DB.at_state.next_height</code>.
     */
    public Integer getNextHeight() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>DB.at_state.sleep_between</code>.
     */
    public void setSleepBetween(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>DB.at_state.sleep_between</code>.
     */
    public Integer getSleepBetween() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>DB.at_state.prev_balance</code>.
     */
    public void setPrevBalance(Long value) {
        set(6, value);
    }

    /**
     * Getter for <code>DB.at_state.prev_balance</code>.
     */
    public Long getPrevBalance() {
        return (Long) get(6);
    }

    /**
     * Setter for <code>DB.at_state.freeze_when_same_balance</code>.
     */
    public void setFreezeWhenSameBalance(Boolean value) {
        set(7, value);
    }

    /**
     * Getter for <code>DB.at_state.freeze_when_same_balance</code>.
     */
    public Boolean getFreezeWhenSameBalance() {
        return (Boolean) get(7);
    }

    /**
     * Setter for <code>DB.at_state.min_activate_amount</code>.
     */
    public void setMinActivateAmount(Long value) {
        set(8, value);
    }

    /**
     * Getter for <code>DB.at_state.min_activate_amount</code>.
     */
    public Long getMinActivateAmount() {
        return (Long) get(8);
    }

    /**
     * Setter for <code>DB.at_state.height</code>.
     */
    public void setHeight(Integer value) {
        set(9, value);
    }

    /**
     * Getter for <code>DB.at_state.height</code>.
     */
    public Integer getHeight() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>DB.at_state.latest</code>.
     */
    public void setLatest(Boolean value) {
        set(10, value);
    }

    /**
     * Getter for <code>DB.at_state.latest</code>.
     */
    public Boolean getLatest() {
        return (Boolean) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<Long, Long, byte[], Integer, Integer, Integer, Long, Boolean, Long, Integer, Boolean> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<Long, Long, byte[], Integer, Integer, Integer, Long, Boolean, Long, Integer, Boolean> valuesRow() {
        return (Row11) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return AtState.AT_STATE.DB_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return AtState.AT_STATE.AT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<byte[]> field3() {
        return AtState.AT_STATE.STATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return AtState.AT_STATE.PREV_HEIGHT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return AtState.AT_STATE.NEXT_HEIGHT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return AtState.AT_STATE.SLEEP_BETWEEN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field7() {
        return AtState.AT_STATE.PREV_BALANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field8() {
        return AtState.AT_STATE.FREEZE_WHEN_SAME_BALANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field9() {
        return AtState.AT_STATE.MIN_ACTIVATE_AMOUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field10() {
        return AtState.AT_STATE.HEIGHT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field11() {
        return AtState.AT_STATE.LATEST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getDbId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component2() {
        return getAtId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] component3() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getPrevHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component5() {
        return getNextHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component6() {
        return getSleepBetween();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component7() {
        return getPrevBalance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component8() {
        return getFreezeWhenSameBalance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component9() {
        return getMinActivateAmount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component10() {
        return getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component11() {
        return getLatest();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getDbId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value2() {
        return getAtId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] value3() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getPrevHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getNextHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getSleepBetween();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value7() {
        return getPrevBalance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value8() {
        return getFreezeWhenSameBalance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value9() {
        return getMinActivateAmount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value10() {
        return getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value11() {
        return getLatest();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AtStateRecord value1(Long value) {
        setDbId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AtStateRecord value2(Long value) {
        setAtId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AtStateRecord value3(byte... value) {
        setState(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AtStateRecord value4(Integer value) {
        setPrevHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AtStateRecord value5(Integer value) {
        setNextHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AtStateRecord value6(Integer value) {
        setSleepBetween(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AtStateRecord value7(Long value) {
        setPrevBalance(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AtStateRecord value8(Boolean value) {
        setFreezeWhenSameBalance(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AtStateRecord value9(Long value) {
        setMinActivateAmount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AtStateRecord value10(Integer value) {
        setHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AtStateRecord value11(Boolean value) {
        setLatest(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AtStateRecord values(Long value1, Long value2, byte[] value3, Integer value4, Integer value5, Integer value6, Long value7, Boolean value8, Long value9, Integer value10, Boolean value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AtStateRecord
     */
    public AtStateRecord() {
        super(AtState.AT_STATE);
    }

    /**
     * Create a detached, initialised AtStateRecord
     */
    public AtStateRecord(Long dbId, Long atId, byte[] state, Integer prevHeight, Integer nextHeight, Integer sleepBetween, Long prevBalance, Boolean freezeWhenSameBalance, Long minActivateAmount, Integer height, Boolean latest) {
        super(AtState.AT_STATE);

        set(0, dbId);
        set(1, atId);
        set(2, state);
        set(3, prevHeight);
        set(4, nextHeight);
        set(5, sleepBetween);
        set(6, prevBalance);
        set(7, freezeWhenSameBalance);
        set(8, minActivateAmount);
        set(9, height);
        set(10, latest);
    }
}
