package com.ideal.oms.repository;

import com.ideal.oms.entity.Parameter;
import com.ideal.oms.framework.orm.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {

    List<Parameter> findParameterListByType(String type);

    List<Parameter> findByParentId(Long parentId);

    List<Parameter> findByParent(Parameter parameter);

    @Query("SELECT a FROM Parameter a WHERE a.parent.id IN (?1)")
    List<Parameter> findByParentIds(List<Long> parentIds);

    Parameter findByCode(String code);
}
