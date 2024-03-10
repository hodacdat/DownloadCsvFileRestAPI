package com.example.demo.service;

import com.example.demo.dto.AuditPoolDTO;
import com.example.demo.entity.AuditPool;
import com.example.demo.entity.GroupOfColumn;
import com.example.demo.repository.AuditPoolRepository;
import com.example.demo.repository.GroupOfColumnRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditPoolServiceTest {

    @InjectMocks
    private AuditPoolService yourClassUnderTest;

    @Mock
    private GroupOfColumnRepository groupOfColumnRepository;

    @Mock
    private AuditPoolRepository auditPoolRepository;

    @Test
    public void testGetListAuditPoolDTO() {
        // Mock data
        List<AuditPool> auditPoolList = Arrays.asList(new AuditPool(), new AuditPool());
        when(auditPoolRepository.findByAuditPoolBioId("3")).thenReturn(auditPoolList);

        GroupOfColumn groupOfColumn = new GroupOfColumn();
        groupOfColumn.setNameOfColumn("cl1,cl2,cl3");
        when(groupOfColumnRepository.findByCurId(1L)).thenReturn(groupOfColumn);

        // Call the method under test
        List<AuditPoolDTO> result = yourClassUnderTest.getListAuditPoolDTO();

        for (AuditPoolDTO auditPoolDTO : result){
            System.out.println(auditPoolDTO.toString());
        }

        // Assertions
        assertEquals(2, result.size()); // Assuming the size of the list should be 2
        // Add more assertions as needed based on your requirements
    }

}