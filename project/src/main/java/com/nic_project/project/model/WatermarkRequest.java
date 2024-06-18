package com.nic_project.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatermarkRequest {

    private long applicationTransactionId;
    private String watermark;
}
