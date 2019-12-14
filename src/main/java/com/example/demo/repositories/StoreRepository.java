package com.example.demo.repositories;

import com.example.demo.entities.InventoryProduct;
import com.example.demo.entities.Store;
import com.example.demo.entities.helperclasses.MyUUID;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Transactional
public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findFirstByAddress(String address);
    @Query(nativeQuery = true, value = "select * from store where store.uuid = :uuid")
    Optional<Store> findByUuid(@Param("uuid") String uuid);

    @Query(nativeQuery = true, value = "SELECT * FROM store where vendor_id = " +
            "(SELECT id FROM user WHERE user_name = :userName)")
    Optional<Store> findByVendorUserName(@Param("userName") String userName);

}
