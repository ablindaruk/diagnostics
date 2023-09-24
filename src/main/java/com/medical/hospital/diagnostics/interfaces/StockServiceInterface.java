package com.medical.hospital.diagnostics.interfaces;

import com.medical.hospital.diagnostics.medicineresources.dto.StockDTO;

import java.util.List;

public interface StockServiceInterface {
    List<StockDTO> getStockList();
    StockDTO getStockItemById(Integer id);
}
