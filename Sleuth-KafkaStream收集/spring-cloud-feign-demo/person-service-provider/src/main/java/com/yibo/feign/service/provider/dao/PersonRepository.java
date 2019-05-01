package com.yibo.feign.service.provider.dao;

import com.yibo.feign.service.provider.entity.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: huangyibo
 * @Date: 2019/1/5 0:43
 * @Description:
 *
 * {@link Person} 仓储
 * extends JpaRepository<Person,Long>,JpaSpecificationExecutor<Person>
 * extends PagingAndSortingRepository<Person,Long>
 * 继承上面这两种都可以
 */

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person,Long> {
}
