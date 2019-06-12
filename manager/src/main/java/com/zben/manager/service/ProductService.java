package com.zben.manager.service;

import com.zben.manager.entity.Product;
import com.zben.manager.enums.ErrorEnums;
import com.zben.manager.enums.ProductStatus;
import com.zben.manager.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zben
 * @Description:
 * @Date: 09:42 2019/6/12
 */
@Service
public class ProductService {

    private static Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    /**
     * 添加产品
     */
    public Product add(Product product) {
        LOG.debug("ProductService...入参 product[{}]",product);
        //参数检验
        checkParam(product);
        //设置默认值
        product = setDefault(product);
        Product result = productRepository.save(product);
        LOG.debug("ProductService...结果 product[{}]",result);
        return result;
    }

    public Product findOne(String id) {
        LOG.debug("查询单个产品...入参 id[{}]",id);
        Assert.notNull(id, "需要产品编号参数");
        Product result = productRepository.findOne(id);
        LOG.debug("查询单个产品...结果 product[{}]",result);
        return result;
    }

    public Page<Product> query(List<String> idList, BigDecimal minRewardRate, BigDecimal maxRewardRate,
                               List<String> statusList, Pageable pageable) {
        LOG.debug("查询多个产品...入参 idList[{}]，minRewardRate[{}], maxRewardRate[{}], statusList[{}], pageable[{}]"
                ,idList, minRewardRate, maxRewardRate, statusList, pageable);
        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Expression<String> idCol = root.get("id");
                Expression<BigDecimal> rateCol =  root.get("rewardRate");
                Expression<String> statusCol = root.get("status");
                List<Predicate> predicateList = new ArrayList<>();
                if (idList != null && idList.size() > 0) {
                    predicateList.add(idCol.in(idList));
                }
                if (BigDecimal.ZERO.compareTo(minRewardRate) < 0) {
                    predicateList.add(criteriaBuilder.ge(rateCol, minRewardRate));
                }
                if (BigDecimal.ZERO.compareTo(maxRewardRate) < 0) {
                    predicateList.add(criteriaBuilder.le(rateCol, maxRewardRate));
                }
                if (statusList != null && statusList.size() > 0) {
                    predicateList.add(statusCol.in(statusList));
                }
                criteriaQuery.where(predicateList.toArray(new Predicate[0]));
                return null;
            }
        };
        Page<Product> page = productRepository.findAll(specification, pageable);
        LOG.debug("查询多个产品...结果 page[{}]", page);
        return page;
    }

    private Product setDefault(Product product) {
        if (product.getStepAmount() == null) {
            product.setStepAmount(BigDecimal.ZERO);
        }
        if (product.getLockTerm() == null) {
            product.setLockTerm(0);
        }
        if (StringUtils.isEmpty(product.getStatus())) {
            product.setStatus(ProductStatus.AUDINING.name());
        }
        return product;
    }

    /**
     * 参数检验
     * 非空检验
     * 收益率0-30之内
     * 投资步长需为整数R
     */
    private void checkParam(Product product) {
        Assert.notNull(product, "product is null");
        Assert.notNull(product.getId(), ErrorEnums.ID_NOT_NULL.getCode());
        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate()) < 0 && BigDecimal.valueOf(30).compareTo(product.getRewardRate()) > 0, "收益率超出范围");
        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue()).compareTo(product.getStepAmount()) == 0, "投资步长必须是整数");
    }

}
