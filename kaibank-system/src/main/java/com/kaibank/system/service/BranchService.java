package com.kaibank.system.service;

import com.kaibank.system.dto.converter.BranchConverter;
import com.kaibank.system.dto.model.BranchDTO;
import com.kaibank.system.entity.Branch;
import com.kaibank.system.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This BranchService class is used to implement logics related to branch.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Service
public class BranchService {

  @Autowired private BranchRepository branchRepository;

  @Autowired private BranchConverter branchConverter;

  @Transactional(readOnly = true)
  public List<BranchDTO> findAllBranches() {
    List<Branch> branches = branchRepository.findAll();
    List<BranchDTO> branchDTOList = new ArrayList<>();

    if (!CollectionUtils.isEmpty(branches)) {
      branches.forEach(branch -> branchDTOList.add(branchConverter.branchToBranchDto(branch)));
    }
    return branchDTOList;
  }
}
