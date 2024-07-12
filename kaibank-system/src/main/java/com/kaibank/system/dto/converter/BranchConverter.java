package com.kaibank.system.dto.converter;

import com.kaibank.system.dto.model.BranchDTO;
import com.kaibank.system.entity.Branch;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The BranchConverter class is used to convert branch dto class to branch entity class and vice
 * versa.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@NoArgsConstructor
public class BranchConverter {

  private ModelMapper modelMapper;

  @Autowired
  public BranchConverter(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public Branch branchDtoToBranch(BranchDTO branchDTO) {
    return this.modelMapper.map(branchDTO, Branch.class);
  }

  public BranchDTO branchToBranchDto(Branch branch) {
    return this.modelMapper.map(branch, BranchDTO.class);
  }
}
