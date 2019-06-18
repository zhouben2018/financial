package com.zben.saller.repository;

import com.zben.entity.VerificationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @Author: zben
 * @Description:
 * JpaSpecificationExecutor:可以做复杂的条件查询
 * @Date: 09:40 2019/6/12
 */
public interface VerificationRepository extends JpaRepository<VerificationOrder, String>, JpaSpecificationExecutor<VerificationOrder> {


    /**
     * 查询某段时间[start,end)内的某个渠道chanId的对账数据
     * @param chanId
     * @param start
     * @param end
     * @return 对账数据列表
     */
    @Query(value = "SELECT CONCAT_WS('|', order_id,outer_order_id,chan_id,chan_user_id,product_id,order_type,amount,DATE_FORMAT( create_at,'%Y-%m-%d %H:%i:%s')) FROM order_t WHERE order_status = 'success' AND chan_id = ?1 AND create_at >= ?2 AND create_at < ?3",nativeQuery = true)
    List<String> queryVerificationOrders(String chanId, Date start,Date end);

    /**
     * 查询某段时间[start,end)内的某个渠道chanId的长款订单
     * @param chanId
     * @param start
     * @param end
     * @return
     */
    @Query(value = "select t.order_id from order_t t left join verification_order v on t.outer_order_id=v.order_id where t.chan_id=?1 and v.order_id is null AND t.create_at >= ?2 AND t.create_at < ?3",nativeQuery = true)
    List<String> queryExcessOrders(String chanId, Date start,Date end);

    /**
     * 查询某段时间[start,end)内的某个渠道chanId的漏单订单
     * @param chanId
     * @param start
     * @param end
     * @return
     */
    @Query(value = "select v.outer_order_id from verification_order v left join order_t t on v.order_id=t.outer_order_id where v.chan_id=?1 and t.outer_order_id is null AND v.create_at >= ?2 AND v.create_at < ?3",nativeQuery = true)
    List<String> queryMissOrders(String chanId, Date start,Date end);

    /**
     * 查询某段时间[start,end)内的某个渠道chanId的不一致订单
     * @return
     */
    @Query(value = "select t.order_id from order_t t inner join verification_order v on t.order_id=v.outer_order_id and t.chan_id=?1 where CONCAT_WS('|',t.chan_id,t.chan_user_id,t.product_id,t.order_type,t.amount,DATE_FORMAT( t.create_at,'%Y-%m-%d %H:%i:%s')) != CONCAT_WS('|',v.chan_id,v.chan_user_id,v.product_id,v.order_type,v.amount,DATE_FORMAT( v.create_at,'%Y-%m-%d %H:%i:%s')) and v.create_at >= ?2 AND v.create_at < ?3",nativeQuery = true)
    List<String> queryDifferentOrders(String chanId, Date start,Date end);
}
