package com.deepexi.channel.api.impl;

import com.deepexi.channel.domain.DistributorDTO;
import com.deepexi.channel.domain.DistributorQuery;
import com.deepexi.channel.api.DistributorApi;
import com.deepexi.channel.service.DistributorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 20:20
 */
public class DistributorApiImpl implements DistributorApi {

    @Autowired
    DistributorService distributorService;

    @Override
    public long create(DistributorDTO dto) {
        return distributorService.create ( dto );
    }

    @Override
    public boolean deleteBatch(List<Long> idList) {
        return distributorService.deleteBatch ( idList );
    }

    @Override
    public List<DistributorDTO> findPage(DistributorQuery query) {
        return distributorService.findPage ( query );
    }

    @Override
    public boolean update(DistributorDTO dto) {
        return distributorService.update ( dto );
    }

    @Override
    public DistributorDTO getById(Long id) {
        return distributorService.getById ( id );
    }

    @Override
    public List<String> listDistributorCode() {
        return distributorService.listDistributorCode ();
    }

    @Override
    public List<String> listDistributorName() {
        return distributorService.listDistributorName ();
    }

    @Override
    public List<String> listDistributorNameEn() {
        return distributorService.listDistributorNameEn ();
    }
}
