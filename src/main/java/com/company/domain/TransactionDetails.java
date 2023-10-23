package com.company.domain;

import com.company.enums.ActionStatus;
import com.company.enums.FinalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionDetails {
    private Long id;
    private String finalStatus;
    private String actionStatus;
    private String rejectReason;
}
